package com.example.consorciojaahvafx.repository;

import com.example.consorciojaahvafx.exception.BuscaInvalidaException;
import com.example.consorciojaahvafx.exception.PagamentoConcluidoException;
import com.example.consorciojaahvafx.exception.PagamentoFalhouException;
import lombok.Data;
import com.example.consorciojaahvafx.model.Pagamento;

import java.util.ArrayList;
import java.util.List;


@Data
public class PagamentoRepository implements IRepository<Pagamento> {

    private static PagamentoRepository instance;
    private ArrayList<Pagamento> pagamentos;
    private int indice;

    public PagamentoRepository() {
        pagamentos = new ArrayList<>();
        indice = 0;
    }

    public static PagamentoRepository getInstance() {
        if (instance == null) {
            instance = new PagamentoRepository();
        }
        return instance;
    }



    public void add(Pagamento pagamento) throws PagamentoConcluidoException, PagamentoFalhouException {
        pagamentos.add(pagamento);
        indice = indice + 1;

    }

    @Override
    public void remove(Pagamento pagamento) throws BuscaInvalidaException {
        int i = getIndex(pagamento.getId());
        if (i == indice) {
            System.out.println("Pagamento não encontrado");
        } else {
            pagamentos.remove(i);
            indice = indice - 1;
        }

    }

    @Override
    public void update(Pagamento pagamento) throws BuscaInvalidaException {
        int i = getIndex(pagamento.getId());
        if (i != -1) {
            pagamentos.set(i, pagamento);
        } else {
            throw new BuscaInvalidaException("Pagamento não encontrado no repositório.");
        }
    }


    public Pagamento findById(long id) {
        for (Pagamento pagamento : pagamentos) {
            if (pagamento.getId() == id) {
                return pagamento;
            }
        }
        return null; // Retorna null se nenhum Pagamento correspondente for encontrado
    }

    @Override
    public List<Pagamento> findAll() {
        return this.pagamentos;
    }



    @Override
    public boolean existsById(long id) {
        boolean resposta;
        int i = getIndex(id);
        if (i == indice) {
            resposta = true;
        } else {
            resposta = false;
        }
        return resposta;
    }
    @Override
    public int getIndex(long id) {
        for (int i = 0; i < indice; i++) {
            if (pagamentos.get(i).getId() == id) {
                return i;
            }
        }
        return indice;
    }
}