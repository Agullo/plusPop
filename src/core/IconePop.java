package core;

public class IconePop implements TipoDeUsuario {
	private static final String EPICWIN = "#epicwin";
	private static final String EPICFAIL = "#epicfail";
	private static final int VALOR_POPULARIDADE = 50;

	@Override
	/**
	 * Curte um Post atravez do usuario e
	 * Adiciona popularidade  e pops.
	 * E se o Post for recente ele ira adicionar BONUS.
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
		if (!post.getListaDeHashtags().contains(EPICWIN)) {
			post.adicionaHashtag(EPICWIN);
		}
	}

	@Override
	/**
	 * Rejeita um Post atravez do usuario e
	 * Remove popularidade  e pops.
	 * E se o Post for recente ele ira remover BONUS.
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
		if (!post.getListaDeHashtags().contains(EPICFAIL))
			post.adicionaHashtag(EPICFAIL);
	}

	@Override
	/**
	 * toString da classe Icone Pop
	 */
	public String toString() {
		return "Icone Pop";
	}

}
