package exceptions;

public class SenhaInvalidaException extends Exception {

	private static final long serialVersionUID = 3173520000690785322L;
	
	public SenhaInvalidaException() {
		super("Senha invalida.");
		
	}
}
