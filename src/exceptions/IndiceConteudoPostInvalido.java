package exceptions;

public class IndiceConteudoPostInvalido extends Exception {

	private static final long serialVersionUID = 191337228948123990L;
	
	public IndiceConteudoPostInvalido(int indice, int tamanhoDeConteudo) {
		super("Item #" + indice +" nao existe nesse post, ele possui apenas " + tamanhoDeConteudo + " itens distintos.");
	}
}
