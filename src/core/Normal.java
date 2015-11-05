package core;

public class Normal implements TipoDeUsuario {
	private static final int VALOR_POPULARIDADE = 10;
	@Override
	public void curtirPost(Post post, Usuario usuarioAmigo) {
		post.adicionaPopularidade(VALOR_POPULARIDADE);
		post.adicionaCurtida();
		usuarioAmigo.adicionaPops(VALOR_POPULARIDADE);
	}

	@Override
	public void rejeitarPost(Post post, Usuario usuarioAmigo) {
		post.removePopularidade(VALOR_POPULARIDADE);
		post.adicionaRejeicao();
		usuarioAmigo.removePops(VALOR_POPULARIDADE);
	}
	
	@Override
	public String toString() {
		return "Normal Pop";
	}
}
