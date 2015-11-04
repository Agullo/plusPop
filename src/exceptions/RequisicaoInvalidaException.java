package exceptions;

public class RequisicaoInvalidaException extends Exception {

	private static final long serialVersionUID = -6320958768580385146L;
	
	public RequisicaoInvalidaException(Exception e) {
		super("Requisicao invalida. " + e.getMessage());
	}
	
	public RequisicaoInvalidaException() {
		super("Requisicao invalida.");
	}
}
