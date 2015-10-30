package exceptions;

public class CadastroDeUsuarioException extends Exception{

	private static final long serialVersionUID = -2595058416759658345L;
	
	public CadastroDeUsuarioException(Exception e) {
		super("Erro no cadastro de Usuarios. " + e.getMessage());
	}
}
