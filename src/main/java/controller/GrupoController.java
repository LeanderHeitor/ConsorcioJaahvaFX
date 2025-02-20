package controller;

import model.Admin;
import model.Cliente;
import model.Consorcio;
import model.Grupo;
import repository.GrupoRepository;

public class GrupoController {
    private GrupoRepository grupoRepository; 

    public GrupoController() {
        this.grupoRepository = GrupoRepository.getInstance();  // Acesso correto ao repositório singleton
    }

    public Grupo obterGrupo(Long grupoId) {
        return grupoRepository.findById(grupoId);
    }

    public Grupo criarGrupo(Admin admin, Consorcio consorcio) {
        Grupo novoGrupo = new Grupo(admin, consorcio);
        grupoRepository.add(novoGrupo);
        return novoGrupo;
    }

    public void adicionarParticipante(Long grupoId, Cliente cliente) {
        Grupo grupo = grupoRepository.findById(grupoId);
        if (grupo != null) {
            grupo.adicionarParticipante(cliente);
            grupoRepository.update(grupo);
            System.out.println("Participante adicionado com sucesso ao grupo " + grupoId);
        } else {
            System.out.println("Grupo não encontrado com o ID " + grupoId);
        }
    }

    public void imprimirGrupos() {
        for (Grupo grupo : grupoRepository.findAll()) {
            System.out.println("O grupo é formado por: " + grupo);

        }
    }

    public void imprimirGrupo(Long grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId);
        if (grupo != null) {
            System.out.println("O grupo é formado por: " + grupo);
        } else {
            System.out.println("Grupo não encontrado com o ID " + grupoId);
        }
    }

    public void removerGrupo(Long grupoId) {
        Grupo grupo = grupoRepository.findById(grupoId);
        if (grupo != null) {
            grupoRepository.remove(grupo);
            System.out.println("Grupo removido com sucesso");
        } else {
            System.out.println("Grupo não encontrado com o ID " + grupoId);
        }
    }

    public void removerParticipante(Long grupoId, Cliente cliente) {
        Grupo grupo = grupoRepository.findById(grupoId);
        if (grupo != null) {
            grupo.removerParticipante(cliente);
            grupoRepository.update(grupo);
            System.out.println("Participante removido com sucesso do grupo " + grupoId);
        } else {
            System.out.println("Grupo não encontrado com o ID " + grupoId);
        }
    }

    public void atualizarGrupo() {
        Grupo grupo = grupoRepository.findById(1L);
        if (grupo != null) {
            grupo.setGrupoAtivo(true);
            grupoRepository.update(grupo);
            System.out.println("Grupo atualizado com sucesso");
        } else {
            System.out.println("Grupo não encontrado com o ID 1");
        }
    }



    
}
