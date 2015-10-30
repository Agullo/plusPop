package exceptions;

public class NomeUsuarioException extends Exception {
	
	private static final long serialVersionUID = -2857716480233722904L;

	public NomeUsuarioException() {
		super("Nome dx usuarix nao pode ser vazio.");
	}
}
