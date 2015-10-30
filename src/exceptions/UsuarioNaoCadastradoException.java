package exceptions;

public class UsuarioNaoCadastradoException extends Exception {

	private static final long serialVersionUID = 7168473310156454001L;

	public UsuarioNaoCadastradoException(String emailNaoCadastrado) {
		super("Um usuarix com email " + emailNaoCadastrado + " nao esta cadastradx.");
	}
}
