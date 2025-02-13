package controller;

import model.*;
import repository.*;

public class Fachada {
    private UsuarioController usuarioController;
    private ConsorcioController consorcioController;
    private GrupoController grupoController;
    private static Fachada fachada;

    public static Fachada getInstance() {
        if (fachada == null) {
            fachada = new Fachada();
        }
        return fachada;
    }

    public void cadastrarusuario(Usuario usuario) {
        // cadastroCliente.cadastrarusuario(usuario);
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
        escolherPremiacao(grupo, consorcio);
    }

    public void adicionarGrupo(Long id, Grupo grupo){
        adicionarGrupo(id, grupo);
    }

    //MÉTODOS USUÁRIOS



}
