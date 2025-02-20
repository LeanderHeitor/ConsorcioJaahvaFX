package controller;

import com.itextpdf.io.exceptions.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.kernel.pdf.canvas.parser.listener.SimpleTextExtractionStrategy;
import enums.StatusCliente;
import enums.TipoServico;
import exception.*;
import lombok.Data;
import model.*;
import repository.ContratoRepository;
import repository.IRepository;
import repository.UsuarioRepository;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Data
public class ContratoController {
    private IRepository<Contrato> contratoRepository;
    private RelatorioController relatorioController;
    private static ContratoController instance;
    private Map<Long, Double> penalidades;

    public static ContratoController getInstance() {
        if (instance == null) {
            instance = new ContratoController();
        }
        return instance;
    }

    public ContratoController(){
        this.contratoRepository = ContratoRepository.getInstance();
        this.relatorioController = RelatorioController.getInstance();
        this.penalidades = new HashMap<>();
    }

    public void ListarContratos(){
        System.out.println("---Listando todos os Contratos---");
       // contratoRepository.findAll().forEach(System.out::println); -> forma de fazer usando Stream
        for (Contrato c : contratoRepository.findAll()) {
            System.out.println(c);
        }
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
    public void imprimirConteudoDocumentos(ArrayList<Contrato> contratos) {
        for (Contrato contrato : contratos) {
            System.out.println("=== Contrato ID: " + contrato.getIdContrato() + " ===");
            System.out.println("Usuário Vinculado: " + contrato.getUsuarioVinculado().getNome());
            System.out.println("Tipo de Serviço: " + contrato.getTipoServico());
            System.out.println("Status do Cliente: " + contrato.getStatusCliente());
            System.out.println("Grupo: " + contrato.getGrupo().getId());
            System.out.println("Finalizado: " + (contrato.isFinalizado() ? "Sim" : "Não"));

            // Extrair e imprimir o conteúdo do documento PDF
            try {
                byte[] documentoBytes = contrato.getDocumento().getBytes();
                PdfDocument pdfDoc = new PdfDocument(new PdfReader(new ByteArrayInputStream(documentoBytes)));
                StringBuilder conteudo = new StringBuilder();

                for (int i = 1; i <= pdfDoc.getNumberOfPages(); i++) {
                    String texto = PdfTextExtractor.getTextFromPage(pdfDoc.getPage(i), new SimpleTextExtractionStrategy());
                    conteudo.append(texto).append("\n");
                }

                System.out.println("Conteúdo do Documento:\n" + conteudo.toString());
                pdfDoc.close();
            } catch (IOException | java.io.IOException e) {
                System.err.println("Erro ao ler o documento do contrato ID: " + contrato.getIdContrato());
                e.printStackTrace();
            }

            System.out.println("=============================\n");
        }
    }
    public void sendRelatorio(Contrato contrato){
        Relatorio relatorio= contrato.sendRelatorio();
        relatorioController.salvarRelatorio(relatorio);
    }

}
