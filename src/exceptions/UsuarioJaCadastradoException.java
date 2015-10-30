package exceptions;

public class UsuarioJaCadastradoException extends Exception {
	
	private static final long serialVersionUID = -5528853386236474447L;

	public UsuarioJaCadastradoException() {
		super("Usuarix ja esta cadastradx.");
	}
}
