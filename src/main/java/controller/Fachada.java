package controller;

import model.*;
import repository.*;

public class Fachada {
    private UsuarioController usuarioController;
    private ConsorcioController consorcioController;
    private GrupoController grupoController;
    private static Fachada fachada;
    private PagamentoController pagamentoController;

    public Fachada(){
        this.consorcioController = new ConsorcioController();
        this.grupoController = new GrupoController();
        this.usuarioController = new UsuarioController();
        this.pagamentoController = new PagamentoController();
    }

    public static Fachada getInstance() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    public void cadastrarusuario(Usuario usuario) {
        usuarioController.cadastrarUsuario(usuario);
    }

    //MÉTODOS GRUPO

    public Grupo criarGrupo(Admin admin, Consorcio consorcio) {
        return grupoController.criarGrupo(admin, consorcio);
    }

    public void adicionarParticipante(Long grupoId, Cliente cliente) {
        grupoController.adicionarParticipante(grupoId, cliente);
    }

    public void imprimirGrupos(){
        grupoController.imprimirGrupos();
    }

    //MÉTODOS CONSORCIO

    public Consorcio criarConsoricio(){
        return consorcioController.criarConsorcio();
    }


    public void escolherPremiacao(Grupo grupo, Consorcio consorcio){
        consorcioController.escolherPremiacao(grupo, consorcio);
    }

    public void adicionarGrupo(Long id, Grupo grupo){
        adicionarGrupo(id, grupo);
    }

    //MÉTODOS USUÁRIOS

    //METODOS PAGAMENTOS

    public void processarParcelas(CartaoDeCredito cartao){
        pagamentoController.processarParcelas(cartao);
    }

    public void processarPagamento(Pagamento pagamento, Grupo grupo, Contrato Contrato){
        pagamentoController.processarPagamento(pagamento, grupo, Contrato);
    }

    public void atualizarSaldoDevedor(Pagamento pagamento, double valor){
        pagamentoController.atualizarSaldoDevedor(pagamento, valor);
    }

    public void gerarBoletoTxt(Pagamento pagamento){
        pagamentoController.gerarBoletoTxt(pagamento);
    }
}
