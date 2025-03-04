package com.example.consorciojaahvafx.model;

import lombok.Data;

@Data
public abstract class Pagamento implements Processo {
    private long id;
    private Cliente pagador;
    private Double valor;
    private Boolean status;
    private Contrato contrato;

    public Pagamento(){}
    public Pagamento(long id, Double valor, Cliente pagador) {
        this.id = id;
        this.valor = valor;
        this.status = false;
        this.pagador = pagador;
    }


    @Override
    public Relatorio sendRelatorio(){
         return null;
    }

   
}
