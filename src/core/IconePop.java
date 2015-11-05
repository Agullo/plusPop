package core;

public class IconePop implements TipoDeUsuario {
	private static final String EPICWIN = "#epicwin";
	private static final String EPICFAIL = "#epicfail";
	private static final int VALOR_POPULARIDADE = 50;
	
	@Override
	public void curtirPost(Post post, Usuario usuarioAmigo) {
		post.adicionaPopularidade(VALOR_POPULARIDADE);
		post.adicionaCurtida();
		usuarioAmigo.adicionaPops(VALOR_POPULARIDADE);
		if(!post.getListaDeHashtags().contains(EPICWIN)) {
			post.adicionaHashtag(EPICWIN);
		}
	}

	@Override
	public void rejeitarPost(Post post, Usuario usuarioAmigo) {
		post.removePopularidade(VALOR_POPULARIDADE);
		post.adicionaRejeicao();
		usuarioAmigo.removePops(VALOR_POPULARIDADE);
		if(!post.getListaDeHashtags().contains(EPICFAIL))
			post.adicionaHashtag(EPICFAIL);
	}
	
	@Override
	public String toString() {
		return "Icone Pop";
	}

}
