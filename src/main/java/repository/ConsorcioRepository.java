package repository;

import lombok.Data;
import model.Consorcio;

import java.util.ArrayList;

@Data
public class ConsorcioRepository implements IRepository<Consorcio> {

    private Consorcio[] consorcios;
    private int indice;
    private static ConsorcioRepository instance;
    private static long nextId = 1;

    private ConsorcioRepository() {
        consorcios = new Consorcio[100];
        indice = 0;
    }

    public static ConsorcioRepository getInstance() {
        if (instance == null) {
            instance = new ConsorcioRepository();
        }
        return instance;
    }

    public void add(Consorcio consorcio) {
        consorcio.setId(nextId++);
        consorcios[indice] = consorcio;
        indice++;
    }

    public void remove(Consorcio consorcio) {
        for (int i = 0; i < indice; i++) {
            if (consorcios[i].equals(consorcio)) {
                for (int j = i; j < indice - 1; j++) {
                    consorcios[j] = consorcios[j + 1];
                }
                consorcios[indice - 1] = null;
                indice--;
                return;
            }
        }
    }

    public void update(Consorcio consorcio) {
        for (int i = 0; i < indice; i++) {
            if (consorcios[i].getId().equals(consorcio.getId())) {
                consorcios[i] = consorcio;
                return;
            }
        }
    }


    public Consorcio findById(long id) {
        int i = getIndex(id);

        if (i == -1) {
            System.out.println("Consorcio não Encontrado!");
            return null;
        }

        return consorcios[i];
    }

    public ArrayList<Consorcio> findAll(){
        ArrayList<Consorcio> resposta = new ArrayList<>();
        for(int i = 0; i< indice; i++){
            resposta.add(consorcios[i]);
        }
        return resposta;
    }

    @Override
    public int getIndex(long id) {
        for (int i = 0; i < indice; i++) {
            if (consorcios[i] != null && consorcios[i].getId() != null && consorcios[i].getId().equals(id)) {
                return i;
            }
        }
        return -1;
    }



    public void imprimirConsorcios() {
        for (int i = 0; i < indice; i++) {
            Consorcio consorcio = consorcios[i];
            System.out.println("O consorcio é formado por: " + consorcio);

        }
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