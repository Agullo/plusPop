package core;

public class Normal implements TipoDeUsuario {
	private static final int VALOR_POPULARIDADE = 10;

	@Override
	/**
	 * Curte um Post atravez do usuario e
	 * Adiciona popularidade  e pops.
	 * 
	 * @param post
	 * 			Indica o Post a ser curtido.
	 * @param usuarioAmigo
	 *			Indica o amigo do usuario.
	 */
	public void curtirPost(Post post, Usuario usuarioAmigo) {
		post.adicionaPopularidade(VALOR_POPULARIDADE);
		post.adicionaCurtida();
		usuarioAmigo.adicionaPops(VALOR_POPULARIDADE);
	}

	@Override
	/**
	 * Rejeita um Post atravez do usuario e
	 * Remove popularidade  e pops.
	 * 
	 * @param post
	 * 			Indica o Post a ser curtido.
	 * @param usuarioAmigo
	 *			Indica o amigo do usuario.
	 */
	public void rejeitarPost(Post post, Usuario usuarioAmigo) {
		post.removePopularidade(VALOR_POPULARIDADE);
		post.adicionaRejeicao();
		usuarioAmigo.removePops(VALOR_POPULARIDADE);
	}

	@Override
	/**
	 * toString da classe Normal pop
	 */
	public String toString() {
		return "Normal Pop";
	}
}
