package core;

public interface TipoDeUsuario {
	
	public void curtirPost(Post post, Usuario usuarioAmigo);
	
	public void rejeitarPost(Post post, Usuario usuarioAmigo);
}
