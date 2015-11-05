package exceptions;

public class ConsultaDePopsException extends Exception {

	private static final long serialVersionUID = 9058106654821717940L;
	
	public ConsultaDePopsException(Exception e) {
		super("Erro na consulta de Pops. " + e.getMessage());
	}
}
