package exceptions;

public class TamanhoDaMensagemException extends Exception {

	private static final long serialVersionUID = 6419996188035926541L;
	
	public TamanhoDaMensagemException() {
		super("O limite maximo da mensagem sao 200 caracteres.");
	}
}
