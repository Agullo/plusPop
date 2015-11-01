package exceptions;

public class LogoutException extends Exception {

	private static final long serialVersionUID = 8929572306041805751L;
	
	public LogoutException(Exception e) {
		super("Nao eh possivel realizar logout. " + e.getMessage());
	}
}
