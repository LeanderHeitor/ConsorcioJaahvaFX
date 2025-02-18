package model;

import com.itextpdf.layout.Document;
import lombok.Data;

@Data
public abstract class Pagamento /*implements Processo*/ {
    private long id;
    private Cliente pagador;
    private Double valor;
    private Boolean status;
    private Contrato contrato;

    public Pagamento(){}
    public Pagamento(long id, Double valor) {
        this.id = id;
        this.valor = valor;
        this.status = false;
    }


    // @Override
    // public Document sendRelatorio(){
    //     return null;
    // }

   
}
