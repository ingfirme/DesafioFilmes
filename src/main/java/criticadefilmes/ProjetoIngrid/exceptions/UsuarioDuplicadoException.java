package criticadefilmes.ProjetoIngrid.exceptions;

public class UsuarioDuplicadoException extends RuntimeException {
    public UsuarioDuplicadoException(String email_dupliicado) {
        super(email_dupliicado);
    }
}
