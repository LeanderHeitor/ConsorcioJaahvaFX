package com.consorcio.app.ConsorcioApp;

import controller.*;
import enums.Premiacao;
import enums.TipoServico;
import model.*;
import repository.*;


import java.util.Scanner;

/*
    TODO métodos a criar:
    -

    TODO ScreenManager
    - separar a tela que o usuário acessará caso seja cliente ou usuário

    TODO controller dos relatorios
   - exportar relatorios para .pdf
   - gerar relatorios automaticamente mensalmente
   - Relatório detalhado do saldo devedor de todos os clientes de um grupo.

   TODO controller dos usuarios
   - validação dos tipos de dados (CPF, email, etc)
   - proibir usuários duplicados
   - proibir usuário cliente e admin com qualquer dado em comum

   TODO controller dos pagamentos
   - atualização do saldo do usuario e das parcelas pagas/pendentes automaticamente
   - bloquear pagamento de lances ou parcelas com o grupo inativo ✔
   - gerar boletos de maneira automática e enviar pros relatorios

   TODO controller dos contratos
   - não permitir a realização do contrato para um usuário devedor
   - não permitir 2 contratos de um mesmo tipo para um mesmo usuário

   - atualizar o status dos contratos de maneira automática

   TODO controller dos consorcios
   - cálculo das parcelas com base no valor, num de pessoas e taxa de admin
   - não permitir que um grupo seja criado com um valor de parcela menor que 100
   - não permitir que um usuário seja contemplado 2x no mesmo grupo
   - contemplar um participante automaticamente caso ele seja o único restante no grupo
    - Consulta ao histórico de contemplações de um grupo, incluindo data, cliente contemplado e status do grupo.
    - não permitir que o admin seja contemplado

   TODO controller dos grupos
   - não permitir que um grupo seja criado ativo com menos de 3 pessoas
   - não permitir que um grupo seja criado com um valor de taxa de administração maior que 10% e menor que 1%
   - excluir um participante caso ele esteja devendo mais da metade do número total de parcelas
   - contemplar um participante automaticamente caso o lance dele seja mais de 2x maior que o segundo maior lance

   TODO funcionalidades do admin
   - penalizar ou remover um usuário de um grupo
   - alterar o valor da taxa de administração de um grupo (o valor das parcelas deve ser alterado automaticamente)
   -
 */

public class Main {
    public static void main(String[] args) {

        // Repositorios
        PagamentoRepository pagamentoRepository = (PagamentoRepository) PagamentoRepository.getInstance();
        IRepository<Consorcio> consorcioRepository = ConsorcioRepository.getInstance();
        IRepository<Relatorio> relatorioRepository = RelatorioRepository.getInstance();
        IRepository<Contrato> contratoRepository = ContratoRepository.getInstance();
        IRepository<Usuario> usuarioRepository = UsuarioRepository.getInstance();
        IRepository<Grupo> grupoRepository = GrupoRepository.getInstance();

        // Controllers
        ConsorcioController consorcioController = new ConsorcioController();
        GrupoController grupoController = new GrupoController();
        PagamentoController pagamentoController = new PagamentoController();
        UsuarioController usuarioController = new UsuarioController();

        //Fachada
        Fachada fachada = new Fachada();

        Scanner dado = dado = new Scanner(System.in);
        boolean sair = false;

        while (!sair) {
            System.out.println("---------MENU---------");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Testes Arthur:");
            System.out.println("2 - Testes Heitor:");
            System.out.println("3 - Testes João:");
            System.out.println("4 - Testes Antônio:");
            System.out.println("5 - Testes Simulados:");
            System.out.println("0 - Sair:");

            int op = dado.nextInt();
            dado.nextLine();

            switch (op) {
                case 1:
                    System.out.println("\n\n");
                    // first try
                    Usuario usuario = new Cliente("Joao", 12345678901L, "123-456-7890", "joao.bolado@example.com", "123456");
                    Relatorio relatorio = new Relatorio("Monthly Report");
                    Pix pix = new Pix("john.doe@pix.com", 1, 100.0);
                    // Create Cliente objects
                    Cliente cliente1 = new Cliente("Alice", 12345678901L, "123-456-7890", "alice@example.com", "123456");
                    Cliente cliente2 = new Cliente("Bob", 23456789012L, "234-567-8901", "bob@example.com", "123456"); 
                    Cliente cliente3 = new Cliente("Charlie", 34567890123L, "345-678-9012", "charlie@example.com", "123456");
                    Cliente cliente4 = new Cliente("Diana", 45678901234L, "456-789-0123", "diana@example.com", "123456");

                    // fazendo Admin objects
                    Admin admin1 = new Admin("Eve", 56789012345L, "567-890-1234", "eve@example.com", "123456");
                    Admin admin2 = new Admin("Frank", 67890123456L, "678-901-2345", "frank@example.com", "123456");

                    // criar Pagamento objects
                    Pix pix1 = new Pix("alice@pix.com", 2, 200.0);
                    Pix pix2 = new Pix("bob@pix.com", 3, 400.0);
                    pix1.setValor(500.0);
                    CartaoDeCredito cartao1 = new CartaoDeCredito(23, "1234-5678-9012-3456", 5, 525.5);
                    CartaoDeCredito cartao2 = new CartaoDeCredito(23, "1544-5678-9012-3456", 4, 525.5);

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

                    for (Pagamento p : pagamentoRepository.getPagamentos()) {
                        System.out.println(p);
                    }
                    break;
                case 2:
                    System.out.println("\n\n");

                    /// Recursos para teste
                    Consorcio pConsorcio = consorcioController.criarConsorcio();
                    Admin pAdmin = new Admin("Heitor", 00001, "81984951", "eito@gmail.com", "123456");
                    Cliente pClienteA = new Cliente("Antonio", 00002, "819999989002", "antonio@gmail.com", "123456");
                    Cliente pClienteB = new Cliente("Joao", 00003, "819999989002", "Joao@gmail.com", "123456");
                    Cliente pClienteC = new Cliente("Eito", 00004, "819999989002", "seabra@gmail.com", "123456");

                    Grupo PgrupoAtivo = grupoController.criarGrupo(pAdmin, pConsorcio);
                    Grupo PgrupoInativo = grupoController.criarGrupo(pAdmin, pConsorcio);

                    grupoController.adicionarParticipante(PgrupoAtivo.getId(), pClienteA);
                    grupoController.adicionarParticipante(PgrupoAtivo.getId(), pClienteB);
                    grupoController.adicionarParticipante(PgrupoAtivo.getId(), pClienteC);

                    Contrato pContrato = new Contrato(pAdmin, TipoServico.CONTRATACAO);
                    Pix pPix = new Pix("pix@example.com", 1, 100.0);

                    // Teste Pagamento
                    Pix pix5 = new Pix("jonas@pix.com", 2, 200.0);
                    pagamentoController.processarPagamento(pPix, PgrupoAtivo, pContrato);
                    pagamentoController.processarPagamento(pPix, PgrupoInativo, pContrato);
                    Pix pix12 = new Pix("alice@pix.com", 2, 200.0);
                    CartaoDeCredito cartao12 = new CartaoDeCredito(23, "1234-5678-9012-3456", 5, 525.5);
                    pagamentoController.atualizarSaldoDevedor(pix12, 500);
                    pagamentoController.gerarBoleto(pix12);
                    break;
                case 3:
                    System.out.println("\n\n");

                    Consorcio jConsorcio = consorcioController.criarConsorcio();

                    Admin jAdmin = new Admin("Lebron", 0010, "777787777", "TheKing@gmail.com", "123456");

                    Cliente jClienteA = new Cliente("Luka", 0020, "888898888", "Tesouro@gmail.com", "654321");
                    Cliente jClienteB = new Cliente("Reaves", 0030, "999909999", "TheWhiteKobe@gmail.com", "654123");

                    Grupo grupoLakers = grupoController.criarGrupo(jAdmin, jConsorcio);

                    grupoController.adicionarParticipante(grupoLakers.getId(), jClienteA);
                    grupoController.adicionarParticipante(grupoLakers.getId(), jClienteB);

                    System.out.println("Grupo: " + grupoLakers);

                    break;

                case 4:
                    System.out.println("\n\n");

                    // CLASSE CONSÓRCIO
                    Consorcio consorcio = consorcioController.criarConsorcio();

                    // CLASSE GRUPO
                    Admin cAdmin = new Admin("Heitor", 00001, "81984951", "eito@gmail.com", "123456");
                    Cliente clienteA = new Cliente("Antonio", 00002, "819999989002", "antonio@gmail.com", "123456");
                    Cliente clienteB = new Cliente("Joao", 00003, "819999989002", "Joao@gmail.com", "123456");
                    Cliente clienteC = new Cliente("Eito", 00004, "819999989002", "seabra@gmail.com", "123456");

                    Grupo grupoAtivo = grupoController.criarGrupo(cAdmin, consorcio);
                    Grupo grupoInativo = grupoController.criarGrupo(cAdmin, consorcio);

                    grupoController.adicionarParticipante(grupoAtivo.getId(), clienteA);
                    grupoController.adicionarParticipante(grupoAtivo.getId(), clienteB);
                    grupoController.adicionarParticipante(grupoAtivo.getId(), clienteC);

                    consorcioController.adicionarGrupo(consorcio.getId(), grupoInativo);
                    consorcioController.adicionarGrupo(consorcio.getId(), grupoAtivo);

                    System.out.println("Grupo Ativo: " + grupoAtivo);
                    System.out.println("Grupo Inativo: " + grupoInativo);

                    // usuarioController.cadastrarUsuario(clienteA);

                    break;
                    

                    

                case 5:
                    System.out.println("\n\n");
                    // TESTE DE USABILIDADE
                    System.out.println("\n\n----TESTES DE USABILIDADE-----\n\n");

                    Consorcio uConsorcio = consorcioController.criarConsorcio(); // É aberto um consórcio

                    Admin admin = new Admin("Seabra", 00001, "1985198", "Seabra@gmail.com", "saas"); // Admin é criado

                    Grupo grupo = fachada.criarGrupo(admin, uConsorcio); // Grupo é criado inativo

                    consorcioController.adicionarGrupo(uConsorcio.getId(), grupo); // Grupo é adicionado ao consórcio

                    System.out.println("Consorcio criado: " + uConsorcio);

                    System.out.println("Grupo criado: " + grupo);

                    Cliente clienteT1 = new Cliente("Antonio", 00001, "819999989002", "a.guedes.cn@gmail.com", "123456");
                    Cliente clienteT2 = new Cliente("Eito", 00002, "4984984", "eito@gmail.com", "123456");
                    Cliente clienteT3 = new Cliente("joao", 00003, "123456789", "joao@example.com", "123456");

                    fachada.adicionarParticipante(grupo.getId(), clienteT1);
                    fachada.adicionarParticipante(grupo.getId(), clienteT2);
                    fachada.adicionarParticipante(grupo.getId(), clienteT3);

                    uConsorcio.setPremiacao(Premiacao.Casa);

                    fachada.escolherPremiacao(grupo, uConsorcio);

                    System.out.println("Grupo Ativo: " + grupo);
                    
                    fachada.cadastrarusuario(clienteT1);
                    fachada.cadastrarusuario(clienteT2);
                    fachada.cadastrarusuario(clienteT3);

                    

                    
                    break;

                    

                case 0:
                    System.out.println("Fim dos testes.");
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }

        }

    }
}
