package exceptions;

public class UsuarioJaLogadoException extends Exception {

	private static final long serialVersionUID = -6207423486968499987L;
	
	public UsuarioJaLogadoException(String nomeDoUsuarioLogado) {
		super("Um usuarix ja esta logadx: " + nomeDoUsuarioLogado + ".");
	}
}
