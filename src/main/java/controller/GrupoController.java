package controller;

import model.Admin;
import model.Cliente;
import model.Consorcio;
import model.Grupo;
import repository.GrupoRepository;

public class GrupoController {
    private GrupoRepository grupoRepository;
    private ConsorcioController consorcioController;

    public GrupoController() {
        this.grupoRepository = GrupoRepository.getInstance();  // Acesso correto ao repositório singleton
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

    
}
