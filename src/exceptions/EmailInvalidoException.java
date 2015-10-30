package exceptions;

public class EmailInvalidoException extends Exception {
	
	private static final long serialVersionUID = -5013255138803532227L;

	public EmailInvalidoException() {
		super("Formato de e-mail esta invalido.");
	}
}
