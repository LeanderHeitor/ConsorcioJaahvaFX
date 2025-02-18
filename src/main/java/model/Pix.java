package model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Pix extends Pagamento {
    private String chavePix;
    public Pix(String chavePix ,long id, Double valor) {
        super(id, valor);
        this.chavePix = chavePix;
    }
}
