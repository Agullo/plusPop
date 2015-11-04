package exceptions;

public class IndiceDePostNaoExisteException extends Exception {

	private static final long serialVersionUID = 2085859121653730714L;
	
	public IndiceDePostNaoExisteException(int indiceInvalido) {
		super("Nao existe um post com indice " + indiceInvalido + ".");
	}
}
