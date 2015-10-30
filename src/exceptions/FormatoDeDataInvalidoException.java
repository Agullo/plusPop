package exceptions;

public class FormatoDeDataInvalidoException extends Exception {

	private static final long serialVersionUID = -8388101673916995240L;
	
	public FormatoDeDataInvalidoException() {
		super("Formato de data esta invalida.");
	}
}
