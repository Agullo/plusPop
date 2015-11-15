package core.tiposDeUsuario;

import core.Post;
import core.Usuario;

/**
 * Entidade que implementa TipoDeUsuario.
 * @author Matteus Silva
 *
 */
public class CelebridadePop implements TipoDeUsuario {
	private static final int VALOR_POPULARIDADE = 25;
	private static final int BONUS = 10;

	@Override
	/**
	 * Curte um Post atravez do usuario e
	 * Adiciona popularidade  e pops.
	 * E se o Post for recente ele ira adicionar BONUS de 10 pops.
	 * 
	 * @param post
	 * 			Indica o Post a ser curtido.
	 * @param usuarioAmigo
	 *			Indica o amigo do usuario.
	 */
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
	/**
	 * Rejeita um Post atravez do usuario e
	 * Remove popularidade  e pops.
	 * E se o Post for recente ele ira remover BONUS de 10 pops.
	 * 
	 * @param post
	 * 			Indica o Post a ser rejeitado.
	 * @param usuarioAmigo
	 *			Indica o amigo do usuario.
	 */
	public void rejeitarPost(Post post, Usuario usuarioAmigo) {
		post.removePopularidade(VALOR_POPULARIDADE);
		post.adicionaRejeicao();
		usuarioAmigo.removePops(VALOR_POPULARIDADE);
		if (post.isRecente())
			post.removePopularidade(BONUS);
		usuarioAmigo.removePops(BONUS);
	}

	@Override
	/**
	 * toString da classe Celebridade Pop.
	 */
	public String toString() {
		return "Celebridade Pop";
	}
}
