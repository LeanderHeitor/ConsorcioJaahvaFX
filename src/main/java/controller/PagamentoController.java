package controller;
import model.CartaoDeCredito;
import model.Contrato;
import model.Grupo;
import model.Pagamento;
import model.Usuario;
import model.Boleto;
import repository.ContratoRepository;
import repository.PagamentoRepository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;


public class PagamentoController {
    private static PagamentoController instance;
    private PagamentoRepository pagamentoRepository;

    public static PagamentoController getInstance() {
        if (instance == null) {
            instance = new PagamentoController();
        }
        return instance;
    }

    public PagamentoController(){
        this.pagamentoRepository = PagamentoRepository.getInstance();
    }
    public void processarParcelas(CartaoDeCredito cartao) {
        // 1. Verificar se o objeto cartão é nulo
        if (cartao == null) {
            System.out.println("Erro: Objeto CartaoDeCredito é nulo.");
            return;
        }

        // 2. Verificar se o número do cartão está preenchido
        if (cartao.getNumeroCartao() == null || cartao.getNumeroCartao().trim().isEmpty()) {
            System.out.println("Erro: Número do cartão não informado.");
            return;
        }

        // 3. Remover caracteres especiais para verificar quantidade de dígitos
        String numCartaoLimpo = cartao.getNumeroCartao().replaceAll("-", "").replaceAll("\\s+", "");
        if (numCartaoLimpo.length() != 16) {
            System.out.println("Erro: Número do cartão inválido. Deve conter 16 dígitos.");
            return;
        }


        // 4. Verificar se o valor do pagamento é positivo
        if (cartao.getValor() == null || cartao.getValor() <= 0) {
            System.out.println("Erro: Valor do cartão inválido. Deve ser maior que zero.");
            return;
        }

        // 5. Verificar se o número de parcelas é válido
        if (cartao.getParcelas() <= 0) {
            System.out.println("Erro: Número de parcelas inválido.");
            return;
        }

        // 6. Simular autorização do pagamento
        if (!autorizarPagamento(cartao)) {
            System.out.println("Pagamento não autorizado pelo cartão.");
            return;
        }

        // 7. Calcular valor de cada parcela
        double valorTotal = cartao.getValor();
        double valorParcela = valorTotal / cartao.getParcelas();
        System.out.println("Iniciando o processamento de " + cartao.getParcelas()
                + " parcelas para o cartão " + numCartaoLimpo);
        System.out.println("Valor total: R$" + valorTotal
                + " | Valor de cada parcela: R$" + valorParcela);

        // 8. Processar cada parcela (loop)
        for (int i = 1; i <= cartao.getParcelas(); i++) {
            System.out.println("Processando parcela " + i + " de " + cartao.getParcelas()
                    + " no valor de R$" + valorParcela);
            // Aqui poderia haver lógica para persistir cada parcela, registrar datas, etc.
        }

        // 9. Marcar pagamento como concluído
        cartao.setStatus(true);
        System.out.println("Pagamento parcelado realizado com sucesso!");
    }


    private boolean autorizarPagamento(CartaoDeCredito cartao) {
        // Simples checagem: se o número tiver 16 dígitos, retorna true.
        // Em um cenário real, haveria integração com APIs de cartão.
        String numCartaoLimpo = cartao.getNumeroCartao().replaceAll("-", "").replaceAll("\\s+", "");
        return (numCartaoLimpo.length() == 16);
    }


    public void processarPagamento(Pagamento pagamento, Grupo grupo, Contrato contrato) {
        // 1. Verificar se os objetos são nulos
        if (pagamento == null) {
            System.out.println("Erro: Objeto Pagamento é nulo.");
            return;
        }
        if (grupo == null) {
            System.out.println("Erro: Objeto Grupo é nulo.");
            return;
        }
        if (contrato == null) {
            System.out.println("Erro: Objeto Contrato é nulo.");
            return;
        }

        // 2. Bloqueia o pagamento se o grupo estiver inativo
        if (!grupo.isGrupoAtivo()) {
            System.out.println("Pagamento bloqueado: O grupo está inativo ❌.");
            return;
        }

        // 3. Verificar se o contrato está finalizado
        if (contrato.isFinalizado()) {
            System.out.println("Erro: Contrato já finalizado. Não é possível processar pagamento.");
            return;
        }

        // 4. Verificar se o usuário do contrato pertence ao grupo
        Usuario usuario = contrato.getUsuarioVinculado();
        if (usuario == null) {
            System.out.println("Erro: Contrato sem usuário vinculado.");
            return;
        }
        if (!grupo.getParticipantes().contains(usuario)) {
            System.out.println("Erro: Usuário do contrato não pertence a este grupo.");
            return;
        }

        // 5. Verificar se o pagamento já está marcado como pago
        if (Boolean.TRUE.equals(pagamento.getStatus())) {
            System.out.println("Aviso: Pagamento já está marcado como efetuado. Operação duplicada?");
            return;
        }

        // 6. Verificar se o valor do pagamento é válido
        if (pagamento.getValor() == null || pagamento.getValor() <= 0) {
            System.out.println("Erro: Valor do pagamento inválido.");
            return;
        }

        // 7. Se passou por todas as validações, processar
        System.out.println("Pagamento autorizado ✔.");
        pagamento.setStatus(true);

        // 8. Atualizar valor arrecadado no grupo
        double novoValorArrecadado = grupo.getValorArrecadadoAtualizado() + pagamento.getValor();
        grupo.setValorArrecadadoAtualizado(novoValorArrecadado);

        // 9. Exibir mensagem de sucesso
        System.out.println("Pagamento processado para o usuário: " + usuario.getNome());

        // 10. Atualizar o contrato no repositório (caso haja mudanças a persistir)
        ContratoRepository contratoRepo = (ContratoRepository) ContratoRepository.getInstance();
        contratoRepo.update(contrato);
    }


    public void atualizarSaldoDevedor(Pagamento pagamento, double valor) {
        // 1. Verificar se o pagamento é nulo
        if (pagamento == null) {
            System.out.println("Pagamento não encontrado ou é nulo.");
            return;
        }

        // 2. Verificar se o valor é válido
        if (valor <= 0) {
            System.out.println("Erro: Valor inválido para abater do saldo devedor.");
            return;
        }

        // 3. Se o pagamento já está concluído, talvez não possa ser alterado
        if (Boolean.TRUE.equals(pagamento.getStatus())) {
            System.out.println("Não é possível atualizar saldo devedor de um pagamento já concluído.");
            return;
        }

        // 4. Verificar se o novo saldo não fica negativo
        double novoSaldo = pagamento.getValor() - valor;
        if (novoSaldo < 0) {
            System.out.println("Erro: O valor a ser subtraído excede o saldo devedor.");
            return;
        }

        // 5. Atualizar o saldo
        pagamento.setValor(novoSaldo);
        System.out.println("Saldo devedor atualizado com sucesso. Novo saldo: R$" + pagamento.getValor());
    }



    public void gerarBoletoTxt(Boleto boleto) {
        if (boleto.getCodigoBoleto() == null || boleto.getCodigoBoleto().isEmpty()) {
            System.out.println("Erro: Código do Boleto não informado.");
            return;
        }
        if (boleto.getVencimento() == null) {
            System.out.println("Erro: Data de vencimento não informada.");
            return;
        }

        // Nome do arquivo de texto
        String nomeArquivo = "boleto_pagamento_" + boleto.getId() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))) {

            // Cabeçalho do boleto
            writer.write("----- BOLETO DE PAGAMENTO -----\n");
            writer.write("Código do Boleto: " + boleto.getCodigoBoleto() + "\n");
            writer.write("ID do Pagamento: " + boleto.getId() + "\n");

            // Pagador
            if (boleto.getPagador() != null) {
                writer.write("Cliente: " + boleto.getPagador().getNome() + "\n");
            } else {
                writer.write("Cliente: [Não informado]\n");
            }

            // Valor
            writer.write(String.format("Valor: R$ %.2f\n", boleto.getValor()));

            // Status (Pago ou Pendente)
            if (boleto.getStatus() != null) {
                String status = boleto.getStatus() ? "Pago" : "Pendente";
                writer.write("Status: " + status + "\n");
            }

            // Data de vencimento
            writer.write("Data de Vencimento: " + boleto.getVencimento() + "\n");

            writer.write("-------------------------------\n");
            writer.write("Use este boleto para efetuar o pagamento até o vencimento.\n");
            writer.write("-----------------------------------------\n");

            // Mensagem de sucesso no console
            System.out.println("Boleto gerado com sucesso em: " + nomeArquivo);

        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo TXT do Boleto: " + e.getMessage());
        }
    }

    public void listarPagamentos(){
        System.out.println("---Listando todos os pagamentos---");
        for (Pagamento p : pagamentoRepository.getPagamentos()) {
            System.out.println(p);
        }
    }
}


