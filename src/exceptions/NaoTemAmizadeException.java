package exceptions;

import core.Usuario;

public class NaoTemAmizadeException extends Exception {

	private static final long serialVersionUID = -496703252852983681L;
	
	public NaoTemAmizadeException(Usuario usuario) {
		super("Nao foi possivel curtir o post de " + usuario.getNome() + ". Voce nao tem amizade com elx.");
	}
}
