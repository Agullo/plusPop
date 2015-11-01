package exceptions;

public class AtualizaPerfilNaoLogado extends Exception {

	private static final long serialVersionUID = -7626627892931478593L;
	
	public AtualizaPerfilNaoLogado(Exception e) {
		super("Nao eh possivel atualizar um perfil. " + e.getMessage());
	}
}
