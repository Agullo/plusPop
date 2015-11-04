package exceptions;

public class CriaPostException extends Exception{

	private static final long serialVersionUID = 5378486579564921556L;
	
	public CriaPostException(Exception e) {
		super("Nao eh possivel criar o post. " + e.getMessage());
	}
}
