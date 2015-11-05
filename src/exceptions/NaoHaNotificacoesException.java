package exceptions;

public class NaoHaNotificacoesException extends Exception {

	private static final long serialVersionUID = 937678498710457554L;
	
	public NaoHaNotificacoesException() {
		super("Nao ha mais notificacoes.");
	}
}
