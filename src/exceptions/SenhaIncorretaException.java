package exceptions;

public class SenhaIncorretaException extends Exception {

	private static final long serialVersionUID = 2974901947266082919L;
	
	public SenhaIncorretaException() {
		super("A senha fornecida esta incorreta.");
	}
}
