package com.example.consorciojaahvafx.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Pix extends Pagamento {
    private String chavePix;
    public Pix(String chavePix ,long id, Double valor, Cliente pagador) {
        super(id, valor, pagador);
        this.chavePix = chavePix;
    }
}