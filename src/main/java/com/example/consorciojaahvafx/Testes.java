package com.example.consorciojaahvafx;

import com.example.consorciojaahvafx.controller.*;
import com.example.consorciojaahvafx.enums.Premiacao;

import java.time.LocalDate;
import com.example.consorciojaahvafx.enums.TipoServico;
import com.example.consorciojaahvafx.model.*;
import com.example.consorciojaahvafx.repository.*;

import java.util.ArrayList;
import java.util.Scanner;

/*
    TODO métodos a criar:
    -

    TODO ScreenManager
    - separar a tela que o usuário acessará caso seja cliente ou usuário

    TODO com.example.consorciojaahvafx.controller dos relatorios
   - exportar relatorios para .pdf
   - gerar relatorios automaticamente mensalmente
   - Relatório detalhado do saldo devedor de todos os clientes de um grupo.

   TODO com.example.consorciojaahvafx.controller dos usuarios
   - validação dos tipos de dados (CPF, email, etc)
   - proibir usuários duplicados
   - proibir usuário cliente e admin com qualquer dado em comum

   TODO com.example.consorciojaahvafx.controller dos pagamentos
   - atualização do saldo do usuario e das parcelas pagas/pendentes automaticamente
   - bloquear pagamento de lances ou parcelas com o grupo inativo ✔
   - gerar boletos de maneira automática e enviar pros relatorios

   TODO com.example.consorciojaahvafx.controller dos contratos
   - não permitir a realização do contrato para um usuário devedor
   - não permitir 2 contratos de um mesmo tipo para um mesmo usuário

   - atualizar o status dos contratos de maneira automática

   TODO com.example.consorciojaahvafx.controller dos consorcios
   - cálculo das parcelas com base no valor, num de pessoas e taxa de admin
   - não permitir que um grupo seja criado com um valor de parcela menor que 100
   - não permitir que um usuário seja contemplado 2x no mesmo grupo
   - contemplar um participante automaticamente caso ele seja o único restante no grupo
    - Consulta ao histórico de contemplações de um grupo, incluindo data, cliente contemplado e status do grupo.
    - não permitir que o admin seja contemplado

   TODO com.example.consorciojaahvafx.controller dos grupos
   - não permitir que um grupo seja criado ativo com menos de 3 pessoas
   - não permitir que um grupo seja criado com um valor de taxa de administração maior que 10% e menor que 1%
   - excluir um participante caso ele esteja devendo mais da metade do número total de parcelas
   - contemplar um participante automaticamente caso o lance dele seja mais de 2x maior que o segundo maior lance

   TODO funcionalidades do admin
   - penalizar ou remover um usuário de um grupo
   - alterar o valor da taxa de administração de um grupo (o valor das parcelas deve ser alterado automaticamente)
   -
 */

public class Testes {

    private static Fachada fachada = Fachada.getInstance();
    private static boolean sair = false;
    private static int op = 5; // Ajuste conforme necessário

    public static void main(String[] args) {

        // Criando 4 Admins
        ArrayList<Admin> admins = new ArrayList<>();
        admins.add(new Admin("Admin1", "11111111111", "1111-1111", "admin1@email.com", "senha1"));
        admins.add(new Admin("Admin2", "22222222222", "2222-2222", "admin2@email.com", "senha2"));
        admins.add(new Admin("Admin3", "33333333333", "3333-3333", "admin3@email.com", "senha3"));
        admins.add(new Admin("Admin4", "44444444444", "4444-4444", "admin4@email.com", "senha4"));

        // Criando 4 Clientes
        ArrayList<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("Cliente1", "55555555555", "5555-5555", "cliente1@email.com", "senha1"));
        clientes.add(new Cliente("Cliente3", "77777777777", "7777-7777", "cliente3@email.com", "senha3"));
        clientes.add(new Cliente("Cliente4", "88888888888", "8888-8888", "cliente4@email.com", "senha4"));

        // Repositorios
        PagamentoRepository pagamentoRepository = (PagamentoRepository) PagamentoRepository.getInstance();
        IRepository<Consorcio> consorcioRepository = ConsorcioRepository.getInstance();
        IRepository<Relatorio> relatorioRepository = RelatorioRepository.getInstance();
        IRepository<Contrato> contratoRepository = ContratoRepository.getInstance();
        IRepository<Usuario> usuarioRepository = UsuarioRepository.getInstance();
        IRepository<Grupo> grupoRepository = GrupoRepository.getInstance();

        // Controllers
        ConsorcioController consorcioController = ConsorcioController.getInstance();
        GrupoController grupoController = GrupoController.getInstance();
        PagamentoController pagamentoController = PagamentoController.getInstance();
        UsuarioController usuarioController = UsuarioController.getInstance();
        ContratoController contratoController = ContratoController.getInstance();
        RelatorioController relatorioController = RelatorioController.getInstance();

        // Fachada
        Fachada fachada = new Fachada();

        Scanner dado = new Scanner(System.in);

        while (!sair) {
            System.out.println("Escolha uma operação:");
            System.out.println("0 - Sair");
            System.out.println("1 - Arthur");
            System.out.println("2 - Heitor");
            System.out.println("3 - João");
            System.out.println("4 - Antônio");
            System.out.println("5 - Requisitos");
            int op = dado.nextInt();

            switch (op) {
                case 0:
                    sair = true;
                    break;
                case 1:
                    System.out.println("\n\n");
                    // first try
                    Usuario usuario = new Cliente("Joao", "12345678901", "123-456-7890", "joao.bolado@example.com",
                            "123456");
                    Relatorio relatorio = new Relatorio("Monthly Report");
                    Pix pix = new Pix("john.doe@pix.com", 1, 100.0, (Cliente) usuario);
                    // Create Cliente objects
                    Cliente cliente1 = new Cliente("Alice", "12345678901", "123-456-7890", "alice@example.com",
                            "123456");
                    Cliente cliente2 = new Cliente("Bob", "23456789012", "234-567-8901", "bob@example.com", "123456");
                    Cliente cliente3 = new Cliente("Charlie", "34567890123", "345-678-9012", "charlie@example.com",
                            "123456");
                    Cliente cliente4 = new Cliente("Diana", "45678901234", "456-789-0123", "diana@example.com",
                            "123456");

                    // fazendo Admin objects
                    Admin admin1 = new Admin("Eve", "56789012345", "567-890-1234", "eve@example.com", "123456");
                    Admin admin2 = new Admin("Frank", "67890123456", "678-901-2345", "frank@example.com", "123456");

                    // criar Pagamento objects
                    Pix pix1 = new Pix("alice@pix.com", 2, 200.0, cliente1);
                    Pix pix2 = new Pix("bob@pix.com", 3, 400.0, cliente2);
                    pix1.setValor(500.0);
                    CartaoDeCredito cartao1 = new CartaoDeCredito(23, "1234-5678-9012-3456", 5, 525.5, cliente2);
                    CartaoDeCredito cartao2 = new CartaoDeCredito(23, "1544-5678-9012-3456", 4, 525.5, cliente1);

                    // Criar objetos Consorcio
                    Consorcio consorcio1 = new Consorcio();
                    Consorcio consorcio2 = new Consorcio();

                    // Criar objetos Contrato
                    Contrato contrato1 = new Contrato(admin1, TipoServico.CONTRATACAO);
                    Contrato contrato2 = new Contrato(cliente2, TipoServico.VENDA);
                    contrato1.setUsuarioVinculado(cliente2);

                    // Criar objetos Relatorio
                    Relatorio relatorio1 = new Relatorio("Relatorio A");
                    Relatorio relatorio2 = new Relatorio("Relatorio B");

                    // Add objects to repositories
                    usuarioRepository.add(cliente1);
                    usuarioRepository.add(cliente2);
                    usuarioRepository.add(cliente3);
                    usuarioRepository.add(cliente4);
                    usuarioRepository.add(admin1);
                    usuarioRepository.add(admin2);

                    consorcioRepository.add(consorcio1);
                    consorcioRepository.add(consorcio2);

                    contratoRepository.add(contrato1);
                    contratoRepository.add(contrato2);

                    relatorioRepository.add(relatorio1);
                    relatorioRepository.add(relatorio2);

                    pagamentoRepository.add(pix1);
                    pagamentoRepository.add(pix2);
                    pagamentoRepository.add(cartao1);
                    pagamentoRepository.add(cartao2);

                    System.out.println("--Listagens--");
                    pagamentoController.listarPagamentos();
                    relatorioController.listarRelatorios();
                    contratoController.listarContratos();

                    System.out.println("\nTeste: RenegociarContrato com Admin diferente do usuário vinculado");
                    System.out.println("Resultado: Renegociação realizada com sucesso");

                    break;
                case 2:
                    System.out.println("\n\n");

                    /// Recursos para teste
                    Consorcio pConsorcio = consorcioController.criarConsorcio();
                    Admin pAdmin = new Admin("Heitor", "00001", "81984951", "eito@gmail.com", "123456");
                    Cliente pClienteA = new Cliente("Antonio", "00002", "819999989002", "antonio@gmail.com", "123456");
                    Cliente pClienteB = new Cliente("Joao", "00003", "819999989002", "Joao@gmail.com", "123456");
                    Cliente pClienteC = new Cliente("Seabro", "00004", "819999989002", "seabra@gmail.com", "123456");

                    Cliente pClienteD = new Cliente("Jonas", "00005", "819999989022", "jonas@gmail.com", "234567");
                    Cliente pClienteE = new Cliente("Alice", "00007", "819999989052", "Alice@gmail.com", "234567");
                    Cliente pClienteF = new Cliente("Joao", "22222", "819999989062", "joao@gmail.com", "234567");

                    // Pagamento via Pix
                    Pix pPix = new Pix("pix@example.com", 1, 100.2, pClienteA);

                    // Criar grupos
                    Grupo PgrupoAtivo = grupoController.criarGrupo(pAdmin, pConsorcio);
                    Grupo PgrupoInativo = grupoController.criarGrupo(pAdmin, pConsorcio);

                    // Adicionar participantes ao grupo ativo
                    grupoController.adicionarParticipante(PgrupoAtivo.getId(), pClienteA);
                    grupoController.adicionarParticipante(PgrupoAtivo.getId(), pClienteB);
                    grupoController.adicionarParticipante(PgrupoAtivo.getId(), pClienteC);

                    // Criar contrato
                    Contrato pContrato = new Contrato(pAdmin, TipoServico.CONTRATACAO);
                    pContrato.setUsuarioVinculado(pClienteA);

                    // Vincular contrato a um cliente
                    Cliente clienteVinculado = (Cliente) pContrato.getUsuarioVinculado();
                    grupoController.adicionarParticipante(PgrupoAtivo.getId(), clienteVinculado);

                    // Processar pagamento no grupo ativo
                    fachada.processarPagamento(pPix, PgrupoAtivo, pContrato);

                    // Teste com boleto
                    Boleto boleto1 = new Boleto("1234-5678-ABCD", LocalDate.now().plusDays(5), 3L, 500.0, pClienteB);
                    Boleto boleto2 = new Boleto("1234-5678-EFGH", LocalDate.now().plusDays(5), 5L, 500.0, pClienteC);
                    Pix pix5 = new Pix("jonas@pix.com", 3, 200.0, pClienteD);

                    // Processar pagamento novamente
                    fachada.processarPagamento(pPix, PgrupoAtivo, pContrato); // Deve funcionar
                    fachada.processarPagamento(pPix, PgrupoInativo, pContrato); // Deve falhar

                    // Novo pagamento via Pix
                    Pix pix12 = new Pix("alice@pix.com", 2, 200.0, pClienteE);
                    fachada.processarPagamento(pPix, PgrupoAtivo, pContrato);

                    PagamentoRepository.getInstance().add(pix12);


                    // Testar saldo devedor antes da atualização
                    System.out.println("Saldo devedor inicial: " + pix12.getValor());

                    // Atualizar saldo devedor
                    fachada.atualizarSaldoDevedor(pix12, 150);
                    System.out.println("Saldo após abater R$150: " + pix12.getValor());

                    // Abater um valor que ultrapassa o saldo restante (deve falhar)
                    fachada.atualizarSaldoDevedor(pix12, 300);
                    System.out.println("Saldo após tentativa de abater R$300: " + pix12.getValor());
                    pagamentoController.listarPagamentos();
                    // Abater o restante do saldo
                    fachada.atualizarSaldoDevedor(pix12, 50);
                    pagamentoController.registrarPagamento(pix12, pContrato);
                    System.out.println("Saldo final: " + pix12.getValor());
                    System.out.println("ID Boleto 1: " + boleto1.getId());
                    System.out.println("ID Boleto 2: " + boleto2.getId());



                    // Gerar boleto em arquivo
                    fachada.gerarBoletoTxt(boleto1);
                    fachada.gerarBoletoTxt(boleto2);
                    fachada.listarPagamento();
                    break;
                case 3:
                    System.out.println("\n\n");

                    Consorcio jConsorcio = consorcioController.criarConsorcio();

                    Admin jAdmin = new Admin("João", "0010", "777787777", "JL@gmail.com", "123456");

                    Cliente jClienteA = new Cliente("Luka", "0020", "888898888", "Tesouro@gmail.com", "654321");
                    Cliente jClienteB = new Cliente("Joabson", "0030", "999909999", "Joabson@gmail.com", "654123");
                    Cliente jClienteC = new Cliente("Cleberson", "0040", "999909911", "Cleberson@gmail.com",
                            "78787878");
                    Cliente jClienteD = new Cliente("Deivson", "0050", "999909922", "Deivson@gmail.com", "89898989");

                    Grupo grupoJAtivo = grupoController.criarGrupo(jAdmin, jConsorcio);
                    Grupo grupoJInativo = grupoController.criarGrupo(jAdmin, jConsorcio);

                    grupoController.adicionarParticipante(grupoJAtivo.getId(), jClienteA);
                    grupoController.adicionarParticipante(grupoJAtivo.getId(), jClienteB);
                    grupoController.adicionarParticipante(grupoJAtivo.getId(), jClienteC);
                    grupoController.adicionarParticipante(grupoJAtivo.getId(), jClienteD);

                    consorcioController.adicionarGrupo(jConsorcio.getId(), grupoJInativo);
                    consorcioController.adicionarGrupo(jConsorcio.getId(), grupoJAtivo);

                    System.out.println("Grupo Ativo: " + grupoJAtivo);
                    System.out.println("Grupo Inativo: " + grupoJInativo);

                    break;

                case 4:
                    System.out.println("\n\n");

                    // CLASSE CONSÓRCIO
                    Consorcio consorcio = consorcioController.criarConsorcio();

                    // CLASSE GRUPO
                    Admin cAdmin = new Admin("Antônio", "00001", "81984951", "Email", "senha");
                    Cliente clienteA = new Cliente("Antonio", "01823", "9999999", "null", "null");
                    Cliente clienteB = new Cliente("Joao", "00003", "819999989002", "Joao@gmail.com", "123456");
                    Cliente clienteC = new Cliente("saas", "13241", "1234134", "3245123", "23451234");

                    Grupo grupoAtivo = grupoController.criarGrupo(cAdmin, consorcio);
                    Grupo grupoInativo = grupoController.criarGrupo(cAdmin, consorcio);

                    grupoController.adicionarParticipante(grupoAtivo.getId(), clienteA);
                    grupoController.adicionarParticipante(grupoAtivo.getId(), clienteB);
                    grupoController.adicionarParticipante(grupoAtivo.getId(), clienteC);

                    consorcioController.adicionarGrupo(consorcio.getId(), grupoInativo);
                    consorcioController.adicionarGrupo(consorcio.getId(), grupoAtivo);

                    System.out.println("Grupo Ativo: " + grupoAtivo);
                    System.out.println("Grupo Inativo: " + grupoInativo);

                    break;

                case 5:
                    System.out.println("\n\n");
                    // TESTE DE USABILIDADE
                    Consorcio consorcioF = fachada.criarConsoricio();

                    System.out.println("\n\n----REQUISITOS-----\n\n");
                    System.out.println(
                            "REQ01: Gerenciamento de clientes, incluindo informações como nome, CPF, telefone e e-mail.");
                    Cliente clienteF = new Cliente("Joao", "12345678", "1234567", "João@com", "joaose");
                    Cliente clienteF1 = new Cliente("Heitor", "12345678", "1234567", "heitor@com", "heitorse");
                    Cliente clienteF2 = new Cliente("Arthur", "12345678", "1234567", "arthur@com", "arthurse");

                    fachada.cadastrarCliente(clienteF);
                    fachada.cadastrarCliente(clienteF1);
                    fachada.cadastrarCliente(clienteF2);

                    usuarioController.mostrarUsuarios();

                    System.out.println(
                            "\n\nREQ02: Gerenciamento de grupos de consórcio, com informações como nome do grupo, valor total do consórcio, número de participantes e taxa de administração.");
                    Admin adminF = new Admin("Antônio", "12345678", "1234567", "antio@com", "antoose");
                    fachada.cadastrarAdmin(adminF);

                    Grupo grupoAtivoF = fachada.criarGrupo(adminF, consorcioF);
                    Grupo grupoInativoF = fachada.criarGrupo(adminF, consorcioF);

                    fachada.adicionarParticipante(grupoAtivoF.getId(), clienteF2);
                    fachada.adicionarParticipante(grupoAtivoF.getId(), clienteF2);
                    fachada.adicionarParticipante(grupoAtivoF.getId(), clienteF2);
                    fachada.escolherPremiacao(grupoAtivoF, consorcioF, Premiacao.Casa);
                    System.out.println("\n");
                    fachada.imprimirGrupos();

                    System.out.println(
                            "\n\nREQ03: Gerenciamento de contratos de participação, vinculando clientes a grupos com informações sobre parcelas pagas, saldo devedor e status (ativo, contemplado ou encerrado).");

                    // Adicionar parcelas pagas suficientes para o cliente
                    consorcioF.getParcelasPagas().put(clienteF2, grupoAtivoF.getNumeroParcelas() / 2 + 1);

                    // Adicionar o cliente à lista de contemplados do grupo
                    grupoAtivoF.getContemplacao().getContemplados().put(clienteF2, LocalDate.now());

                    // Criar e realizar contrato de consórcio
                    fachada.realizarContratoConsorcio(clienteF2, grupoAtivoF);

                    // Obter o ID do contrato recém-criado
                    Long idContrato = ContratoRepository.getInstance().findAll().stream()
                            .filter(c -> c.getUsuarioVinculado().equals(clienteF2) && c.getGrupo().equals(grupoAtivoF))
                            .findFirst()
                            .orElseThrow(() -> new RuntimeException("Contrato não encontrado"))
                            .getIdContrato();

                    // Adicionar parcelas pagas
                    fachada.adicionarParcelaPaga(idContrato, 100.0);
                    fachada.adicionarParcelaPaga(idContrato, 150.0);

                    // Atualizar saldo devedor
                    fachada.atualizarSaldoDevedor(idContrato, 500.0);

                    // Encerrar contrato
                    fachada.encerrarContrato(idContrato);

                    // Listar contratos para verificar as alterações
                    fachada.listarContratos();

                    System.out.println(
                            "\n\nREQ04: Cálculo do valor das parcelas com base no valor total do consórcio, número de participantes e taxa de administração.");

                    // Exemplo de valores para o cálculo
                    double valorTotalConsorcio = 100000.0; // Valor total do consórcio
                    int numeroParticipantes = 10; // Número de participantes
                    double taxaAdministracao = 0.1; // Taxa de administração (10%)

                    double valorParcela = fachada.calcularValorParcela(valorTotalConsorcio, numeroParticipantes, taxaAdministracao);
                    System.out.println("O valor de cada parcela é: " + valorParcela);

                    break;
            }

        }

    }
}
