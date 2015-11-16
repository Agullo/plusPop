package core.tiposDeUsuario;

import core.Post;
import core.Usuario;

/**
 * Interface usada para atribuir um comportamento polimorfico a classe Usuario.
 * 
 * @author Matteus Silva
 *
 */
public interface TipoDeUsuario {

	/**
	 * Curte um Post atravez do usuario e
	 * Adiciona popularidade e pops.
	 * E se o Post for recente ele ira adicionar BONUS.
	 * 
	 * @param post
	 *            Indica o Post a ser curtido.
	 * @param usuarioAmigo
	 *            Indica o amigo do usuario.
	 */
	public void curtirPost(Post post, Usuario usuarioAmigo);

	/**
	 * Rejeita um Post atravez do usuario e
	 * Remove popularidade e pops.
	 * E se o Post for recente ele ira remover BONUS.
	 * 
	 * @param post
	 *            Indica o Post a ser curtido.
	 * @param usuarioAmigo
	 *            Indica o amigo do usuario.
	 */
	public void rejeitarPost(Post post, Usuario usuarioAmigo);
}
