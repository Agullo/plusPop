package exceptions;

public class SolicitacaoInexistenteException extends Exception {

	private static final long serialVersionUID = -3338177617984580781L;
	
	public SolicitacaoInexistenteException(String nomeDoUsuarioRejeitado) {
		super(nomeDoUsuarioRejeitado +" nao lhe enviou solicitacoes de amizade.");
	}
}
