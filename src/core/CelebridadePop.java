package core;

public class CelebridadePop implements TipoDeUsuario {
	private static final int VALOR_POPULARIDADE = 25;
	private static final int BONUS = 10;
	
	@Override
	public void curtirPost(Post post, Usuario usuarioAmigo) {
		post.adicionaCurtida();
		post.adicionaPopularidade(VALOR_POPULARIDADE);
		usuarioAmigo.adicionaPops(VALOR_POPULARIDADE);
		if (post.isRecente()) {
			post.adicionaPopularidade(BONUS);
			usuarioAmigo.adicionaPops(BONUS);
		}
	}

	@Override
	public void rejeitarPost(Post post, Usuario usuarioAmigo) {
		post.removePopularidade(VALOR_POPULARIDADE);
		post.adicionaRejeicao();
		usuarioAmigo.removePops(VALOR_POPULARIDADE);
		if (post.isRecente())
			post.removePopularidade(BONUS);
			usuarioAmigo.removePops(BONUS);
	}
	
	@Override
	public String toString() {
		return "Celebridade Pop";
	}
}
