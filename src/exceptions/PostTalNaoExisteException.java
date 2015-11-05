package exceptions;

public class PostTalNaoExisteException extends Exception {

	private static final long serialVersionUID = -3759259092325489456L;
	
	public PostTalNaoExisteException(int post, int qtdDePosts) {
		super("Post #" + post + " nao existe. Usuarix possui apenas " + qtdDePosts + " post(s).");
	}
}
