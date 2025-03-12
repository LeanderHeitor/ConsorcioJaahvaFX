package com.example.consorciojaahvafx.repository;

import lombok.Data;
import com.example.consorciojaahvafx.model.Contrato;
import java.util.ArrayList;
import java.util.List;

@Data
public class ContratoRepository implements IRepository<Contrato> {

    private static ContratoRepository instance;
    private List<Contrato> contratos;

    private ContratoRepository() {
        this.contratos = new ArrayList<>();
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
    }

    @Override
    public void remove(Contrato contrato) {
        contratos.remove(contrato);
    }

    @Override
    public void update(Contrato contrato) {
        // Implementar lógica de atualização, se necessário
    }

    @Override
    public List<Contrato> findAll() {
        return this.contratos;
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
    public boolean existsById(long id) {
        int i = getIndex(id);
        return i != -1;
    }

    @Override
    public int getIndex(long id) {
        for (int i = 0; i < contratos.size(); i++) {
            if (contratos.get(i).getIdContrato() == id) {
                return i;
            }
        }
        return -1;
    }
}
