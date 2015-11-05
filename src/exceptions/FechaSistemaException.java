package exceptions;

public class FechaSistemaException extends Exception {
	
	private static final long serialVersionUID = -7511233918837563319L;
	
	public FechaSistemaException(Exception e) {
		super("Nao foi possivel fechar o sistema. " + e.getMessage());
	}
}
