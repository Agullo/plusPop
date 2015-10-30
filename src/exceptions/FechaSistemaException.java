package exceptions;

public class FechaSistemaException extends Exception {
	
	private static final long serialVersionUID = -7511233918837563319L;
	
	public FechaSistemaException() {
		super("Nao foi possivel fechar o sistema. Um usuarix ainda esta logadx.");
	}
}
