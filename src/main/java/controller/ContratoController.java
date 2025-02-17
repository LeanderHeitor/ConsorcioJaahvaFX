package controller;

import enums.StatusCliente;
import enums.TipoServico;
import exception.AcessoNegadoException;
import exception.GrupoInativoException;
import exception.RenegociacaoNegadaException;
import exception.UsuarioPenalizadoException;
import lombok.Data;
import model.*;
import repository.ContratoRepository;
import repository.IRepository;
@Data
public class ContratoController {
    private IRepository<Contrato> contratoRepository;
    private RelatorioController relatorioController;
    private static ContratoController instance;

    public static ContratoController getInstance() {
        if (instance == null) {
            instance = new ContratoController();
        }
        return instance;
    }

    public ContratoController(){
        this.contratoRepository = ContratoRepository.getInstance();
        this.relatorioController = RelatorioController.getInstance();
    }

    public void realizarContratoConsorcio(Cliente user, Grupo grupo) throws RuntimeException {
        Contrato contrato = new Contrato();
        contrato.setTipoServico(TipoServico.VENDA);
        contrato.setUsuarioVinculado(user);
        contrato.setGrupo(grupo);
        boolean duplicata = contratoRepository.findAll().stream().anyMatch(c -> c.getUsuarioVinculado().equals(user) && c.getGrupo().equals(grupo));
        boolean devedor = grupo.getNumeroParcelas() /2 > grupo.getContemplacao().getParcelasPagas().get(user);
        boolean premiado = (grupo.getParticipantes().contains(user)) && (grupo.getContemplacao().getContemplados().containsKey(user));
        if(grupo.isGrupoAtivo()){
            if(!duplicata){
                if(!devedor){
                    if (premiado){
                        contratoRepository.add(contrato);
                        Relatorio relatorio= contrato.sendRelatorio();
                        relatorioController.salvarRelatorio(relatorio);
                    }else{
                        throw new UsuarioPenalizadoException("Usuário não contemplado, não pode realizar contratos");
                    }
                }else {
                    throw new UsuarioPenalizadoException("Usuário penalizado, não pode realizar contratos");
                }
            }

        }else{
            throw new GrupoInativoException("Grupo inativo, não pode realizar contratos");
        }
    }
    public void RenegociarContrato(Contrato contrato, Usuario admin)throws RuntimeException{
        contrato.setTipoServico(TipoServico.RENEGOCIACAO);
        if (contrato.getUsuarioVinculado().equals(admin)){
            throw new AcessoNegadoException("Usuário não tem permissão para renegociar o próprio contrato");
        }else{
            if (contrato.isFinalizado()){
                throw new RenegociacaoNegadaException("Contrato já está pago, não pode ser renegociado");
            }else {
                if (contrato.getTipoServico().equals(TipoServico.RENEGOCIACAO)){
                    if (contrato.getStatusCliente().equals(StatusCliente.PAGANTE)){
                        contrato.setStatusCliente(StatusCliente.ATRASADO);
                        Relatorio relatorio= contrato.sendRelatorio();
                        relatorioController.salvarRelatorio(relatorio);
                        contratoRepository.update(contrato);
                    }else{
                        if (contrato.getStatusCliente().equals(StatusCliente.ATRASADO)){
                            contrato.setStatusCliente(StatusCliente.INADIMPLENTE);
                            Relatorio relatorio= contrato.sendRelatorio();
                            relatorioController.salvarRelatorio(relatorio);
                            contratoRepository.update(contrato);
                        }else {
                            throw new RenegociacaoNegadaException("Contrato já foi renegociado");
                        }
                    }
                }
                else{
                    if (contrato.getStatusCliente().equals(StatusCliente.PAGANTE) && contrato.getTipoServico().equals(TipoServico.VENDA)) {
                        contrato.setTipoServico(TipoServico.RENEGOCIACAO);
                        contrato.setStatusCliente(StatusCliente.ATRASADO);
                        Relatorio relatorio= contrato.sendRelatorio();
                        relatorioController.salvarRelatorio(relatorio);
                        contratoRepository.update(contrato);
                    } else {
                       throw new RenegociacaoNegadaException("Contrato não pode ser renegociado");
                    }

                }
            }
        }
    }
}
