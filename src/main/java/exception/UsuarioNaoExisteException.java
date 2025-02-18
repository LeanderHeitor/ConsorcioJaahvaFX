package exception;

public class UsuarioNaoExisteException extends RuntimeException {
    public UsuarioNaoExisteException(String message) {
        super(message);
    }
}
