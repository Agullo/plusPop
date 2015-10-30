package exceptions;

public class SenhaProtegidaException extends Exception {

	private static final long serialVersionUID = -7208937267515132985L;
	
	public SenhaProtegidaException() {
		super("A senha dx usuarix eh protegida.");
	}
}
