package repository;

import lombok.Data;
import model.Grupo;

import java.util.ArrayList;

@Data
public class GrupoRepository implements IRepository<Grupo> {

    private Grupo[] grupos;
    private int indice;
    private static GrupoRepository instance;
    private static long nextId = 1;

    private GrupoRepository() {
        grupos = new Grupo[100];
        indice = 0;
    }

    public static GrupoRepository getInstance() {
        if (instance == null) {
            instance = new GrupoRepository();
        }
        return instance;
    }

    public void add(Grupo grupo) {
        grupo.setId(nextId++);
        grupos[indice] = grupo;
        indice++;
    }

    public void remove(Grupo grupo) {
        for (int i = 0; i < indice; i++) {
            if (grupos[i].equals(grupo)) {
                for (int j = i; j < indice - 1; j++) {
                    grupos[j] = grupos[j + 1];
                }
                grupos[indice - 1] = null;
                indice--;
                return;
            }
        }
    }

    public void update(Grupo grupo) {
        for (int i = 0; i < indice; i++) {
            if (grupos[i].getId().equals(grupo.getId())) {
                grupos[i] = grupo;
                return;
            }
        }
    }


    public Grupo findById(long id) {
        int i = getIndex(id);

        if (i == -1) {
            System.out.println("Grupo não Encontrado!");
            return null;
        }

        return grupos[i];
    }

    public ArrayList<Grupo> findAll(){
        ArrayList<Grupo> resposta = new ArrayList<>();
        for(int i = 0; i< indice; i++){
            resposta.add(grupos[i]);
        }
        return resposta;
    }

    public int getIndex(long id) {
        for (int i = 0; i < indice; i++) {
            if (grupos[i] != null && grupos[i].getId() != null && grupos[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }

    
    public boolean existsById(long id){
        boolean resposta;
        int i = getIndex(id);
        if (i == indice){
            resposta = false;
        }  else {
            resposta = true;
        }
        return resposta;
    }
}
