package com.example.consorciojaahvafx.controller;

import com.example.consorciojaahvafx.exception.CPFNaoPodeSerNuloException;
import com.example.consorciojaahvafx.exception.LimiteEmailException;
import com.example.consorciojaahvafx.exception.UsuarioNuloException;
import com.example.consorciojaahvafx.exception.ValorDaPenalidadePositivoException;
import com.example.consorciojaahvafx.model.*;

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

   // MÉTODOS GRUPO

    public void obterGrupo(Long grupoId) {
        grupoController.obterGrupo(grupoId);
    }

    public Grupo criarGrupo(Admin admin, Consorcio consorcio) {
        return grupoController.criarGrupo(admin, consorcio);
    }

    public void adicionarParticipante(Long grupoId, Cliente cliente) {
        grupoController.adicionarParticipante(grupoId, cliente);
    }

    public void imprimirGrupos() {
        grupoController.imprimirGrupos();
    }

    public void imprimirGrupo(Long grupoId) {
        grupoController.imprimirGrupo(grupoId);
    }

    public void removerGrupo(Long grupoId) {
        grupoController.removerGrupo(grupoId);
    }

    public void removerParticipanteGrupo(Long grupoId, Cliente cliente) {
        grupoController.removerParticipante(grupoId, cliente);
    }

    public void atualizarGrupo() {
        grupoController.atualizarGrupo();
    }

    // MÉTODOS CONSORCIO

    public Consorcio criarConsoricio() {
        return consorcioController.criarConsorcio();
    }

    public void escolherPremiacao(Grupo grupo, Consorcio consorcio) {
        consorcioController.escolherPremiacao(grupo, consorcio);
    }

    public void obterConsorcio(Long id) {
        consorcioController.obterConsorcio(id);
    }

    public void listarConsorcios() {
        consorcioController.listarConsorcios();
    }

    public void imprimirConsorcios() {
        consorcioController.imprimirConsorcios();
    }

    public void atualizarConsorcio(Long id, Consorcio consorcioAtualizado) {
        consorcioController.atualizarConsorcio(id, consorcioAtualizado);
    }

    public void removerConsorcio(Long id) {
        consorcioController.removerConsorcio(id);
    }

    public void adicionarGrupo(Long id, Grupo grupo) {
        consorcioController.adicionarGrupo(id, grupo);
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

    public void gerarBoletoTxt(Boleto boleto){
        pagamentoController.gerarBoletoTxt(boleto);
    }
    public void listarPagamento(){
        pagamentoController.listarPagamentos();
    }

    //MÉTODOS USUÁRIOS
    public void checarLogin(Long cpf, String senha){usuarioController.checarLogin(cpf, senha);}
    public void  cadastrarUsuario(Usuario usuario) throws UsuarioNuloException, LimiteEmailException{usuarioController.cadastrarUsuario(usuario);}
    public boolean isAdmin(long cpf) throws CPFNaoPodeSerNuloException, UsuarioNuloException {usuarioController.isAdmin(cpf); return true;}
    public void cadastrarAdmin (Usuario usuario) throws UsuarioNuloException, CPFNaoPodeSerNuloException, LimiteEmailException {usuarioController.cadastrarUsuario(usuario);}
    public void cadastrarCliente (Cliente cliente) throws UsuarioNuloException, CPFNaoPodeSerNuloException {usuarioController.cadastrarUsuario(cliente);}
    public void penalizarUsuario(long cpf, double valorPenalidade) throws UsuarioNuloException, ValorDaPenalidadePositivoException {usuarioController.penalizarUsuario(cpf, valorPenalidade);}
    public void cancelarLance(long cpfAdmin, long cpfCliente, int idGrupo){usuarioController.cancelarLance(cpfAdmin, cpfCliente, idGrupo);}
    public void alterarTaxaAdmin(long cpf,int idGrupo, double valorTaxa) {usuarioController.alterarTaxaAdmin(cpf, idGrupo, valorTaxa);}
}
