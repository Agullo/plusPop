package exceptions;

public class DataNaoExisteException extends Exception {

	private static final long serialVersionUID = 5226434422499481510L;
	
	public DataNaoExisteException() {
		super("Data nao existe.");
	}
}
