package exceptions;

public class AtualizacaoDePerfilException extends Exception {

	private static final long serialVersionUID = 6824731629582012509L;
	
	public AtualizacaoDePerfilException(Exception e) {
		super("Erro na atualizacao de perfil. " + e.getMessage());
	}
}
