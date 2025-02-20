package controller;
import lombok.Data;
import model .*;
import repository.ContratoRepository;
import repository.PagamentoRepository;

import java.time.LocalDate;

@Data
public class PagamentoController {
    private static PagamentoController instance;
    private PagamentoRepository pagamentoRepository;

    public PagamentoController getInstance() {
        if (instance == null) {
            instance = new PagamentoController();
        }
        return instance;
    }

    public PagamentoController(){
        this.pagamentoRepository = PagamentoRepository.getInstance();
    }

    public void processarParcelas(CartaoDeCredito cartao) {
        // Valida o número do cartão
        if (cartao.getNumeroCartao() == null || cartao.getNumeroCartao().trim().isEmpty()) {
            System.out.println("Erro: Número do cartão não informado.");
            return;
        }
        // Remove espaços para uma verificação simples de tamanho
        String numCartaoLimpo = cartao.getNumeroCartao().replaceAll("-", "");
        if (numCartaoLimpo.length() != 16) {
            System.out.println("Erro: Número do cartão inválido. Deve conter 16 dígitos.");
            return;
        }

        // Simula a autorização do pagamento
        if (!autorizarPagamento(cartao)) {
            System.out.println("Pagamento não autorizado pelo cartão.");
            return;
        }

        // Calcula o valor de cada parcela
        double valorTotal = cartao.getValor();
        double valorParcela = valorTotal / cartao.getParcelas();
        System.out.println("Iniciando o processamento de " + cartao.getParcelas() + " parcelas para o cartão " + numCartaoLimpo);
        System.out.println("Valor total: R$" + valorTotal + " | Valor de cada parcela: R$" + valorParcela);

        // Processa cada parcela
        for (int i = 1; i <= cartao.getParcelas(); i++) {
            // Aqui você pode incluir lógica para atualizar o status parcial de cada parcela, se necessário.
            System.out.println("Processando parcela " + i + " de " + cartao.getParcelas() + " no valor de R$" + valorParcela);
            // um delay ou chamada a métodos de persistência podem ser implementados aqui.
        }

        // Ao final, marca o pagamento como concluído
        cartao.setStatus(true);
        System.out.println("Pagamento parcelado realizado com sucesso!");
    }


    private boolean autorizarPagamento(CartaoDeCredito cartao) {
        // Aqui você pode implementar integrações com APIs de autorização de cartão.
        // A simulação abaixo considera o cartão autorizado se o número tiver 16 dígitos.
        String numCartaoLimpo = cartao.getNumeroCartao().replaceAll("\\s+", "");
        return numCartaoLimpo.length() == 16;

    }

    // Processar pagamento
    public void processarPagamento(Pagamento pagamento, Grupo grupo, Contrato contrato) {
        // 1. Bloqueia o pagamento se o grupo estiver inativo
        if (!grupo.isGrupoAtivo()) {
            System.out.println("Pagamento bloqueado: O grupo está inativo ❌.");
        }

        System.out.println("Pagamento autorizado ✔.");
        pagamento.setStatus(true);

        double novoValorArrecadado = grupo.getValorArrecadadoAtualizado() + pagamento.getValor();
        grupo.setValorArrecadadoAtualizado(novoValorArrecadado);

        // 3. Atualizar o contrato

        Usuario usuario = contrato.getUsuarioVinculado();
        System.out.println("Pagamento processado para o usuário: " + usuario.getNome());

        //Falta inserir a lógica para terminar um contrato de fato

        // Atualize o contrato no repositório
        ContratoRepository contratoRepo = (ContratoRepository) ContratoRepository.getInstance();
        contratoRepo.update(contrato);





    }
    // 4. Atualizar o saldo devedor
    public void atualizarSaldoDevedor(Pagamento pagamento, double valor) {
        if (pagamento != null) {
            pagamento.setValor(valor);
            System.out.println("Saldo devedor atualizado com sucesso.");
        } else {
            System.out.println("Pagamento não encontrado.");
        }
    }

    public void gerarBoleto(Pagamento pagamento) {
        System.out.println("----- BOLETO DE PAGAMENTO -----");
        System.out.println("ID do Pagamento: " + pagamento.getId());

        // Se você tiver um pagador definido
        if (pagamento.getPagador() != null) {
            System.out.println("Cliente: " + pagamento.getPagador().getNome());
        } else {
            System.out.println("Cliente: [Não informado]");
        }

        System.out.println(String.format("Valor: R$ %.2f", pagamento.getValor()));
        System.out.println("Data de Emissão: " + LocalDate.now());
        System.out.println("Data de Vencimento: " + LocalDate.now().plusDays(7));
        System.out.println("-------------------------------");
        System.out.println("Use este boleto para efetuar o pagamento.");
        System.out.println("-----------------------------------------\n");
    }
    public void ListarPagamentos(){
        System.out.println("---Listando todos os pagamentos---");
        for (Pagamento p : pagamentoRepository.getPagamentos()) {
            System.out.println(p);
        }
    }

}


