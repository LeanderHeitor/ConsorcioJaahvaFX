package com.example.consorciojaahvafx.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CartaoDeCredito extends Pagamento {
    private int parcelas;
    private String numeroCartao;

    public CartaoDeCredito(){}
    public CartaoDeCredito(int parcelas, String numeroCartao, long id, Double valor, Cliente pagador) {
        super(id, valor, pagador);
        this.parcelas = parcelas;
        this.numeroCartao = numeroCartao;
    }

}