package model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class Boleto extends Pagamento {
    private String codigoBoleto;
    private LocalDate vencimento;


    public Boleto(String codigoBoleto, LocalDate vencimento, long id, Double valor) {
        super(id, valor);
        this.codigoBoleto = codigoBoleto;
        this.vencimento = vencimento;
    }


}
