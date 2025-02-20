package repository;

import lombok.Data;
import model.Contrato;
import java.util.ArrayList;
import java.util.List;

@Data
public class ContratoRepository implements IRepository<Contrato> {

    private static ContratoRepository instance;
    private ArrayList<Contrato> contratos;
    private int indice;

    public ContratoRepository(){
        this.contratos = new ArrayList<>();
        this.indice = 0;
    }

    public static ContratoRepository getInstance() {
        if (instance == null) {
            instance = new ContratoRepository();
        }
        return instance;
    }

    @Override
    public void add(Contrato contrato) {
        this.contratos.add(contrato);
        indice = indice + 1;
    }

    @Override
    public void remove(Contrato contrato) {
        int i = getIndex(contrato.getIdContrato());
        if (i != -1) {
            this.contratos.remove(i);
            indice = indice - 1;
        } else {
            System.out.println("Contrato não encontrado");
        }
    }

    @Override
    public void update(Contrato contrato) {
        int i = getIndex(contrato.getIdContrato());
        if (i != -1) {
            this.contratos.set(i, contrato);
            System.out.println("Contrato atualizado com sucesso.");
        } else {
            System.out.println("Contrato não encontrado.");
        }
    }

    @Override
    public Contrato findById(long id) {
        int i = getIndex(id);
        if (i != -1) {
            return this.contratos.get(i);
        } else {
            System.out.println("Contrato não encontrado");
            return null;
        }
    }

    @Override
    public List<Contrato> findAll() {
        return this.contratos;
    }


    @Override
    public boolean existsById(long id) {
        int i = getIndex(id);
        return i != -1;
    }

    @Override
    public int getIndex(long id) {
        for (int i = 0; i < indice; i++) {
            if (contratos.get(i).getIdContrato() == id) {
                return i;
            }
        }
        return -1;
    }
}
