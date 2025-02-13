package exception;

public class FormularioIncorretoException extends RuntimeException {
    public FormularioIncorretoException(String message) {
        super(message);
    }
}
