package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.Usuario;
import exceptions.CadastroDeUsuarioException;
import exceptions.DataNaoExisteException;
import exceptions.EmailInvalidoException;
import exceptions.FormatoDeDataInvalidoException;
import exceptions.NomeUsuarioException;
import exceptions.UsuarioJaCadastradoException;

public class Controller implements Serializable {
	
	private static final long serialVersionUID = -5784701982498476044L;
	private Usuario usuarioLogado;
	private List<Usuario> usuariosDoMaisPop;
	
	public Controller() {
		usuariosDoMaisPop = new ArrayList<Usuario>();
		usuarioLogado = null;
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataNasc, String imagem) throws CadastroDeUsuarioException {
		try {
			//isUsuarioJaCadastrado(email);  PROBLEMA NA IMPLEMENTACAO DO FOR??
			Usuario usuario = new Usuario(nome, email, senha, dataNasc, imagem);
			usuariosDoMaisPop.add(usuario);
			return usuario.getEmail();
		} catch (NomeUsuarioException | EmailInvalidoException | FormatoDeDataInvalidoException | DataNaoExisteException e) { // UsuarioJaCadastradoException | 
			throw new CadastroDeUsuarioException(e);
		}
	}
	
	private void isUsuarioJaCadastrado(String email) throws UsuarioJaCadastradoException {
		for (Usuario usuario : usuariosDoMaisPop) {
			if (usuario.getEmail().equals(email))
				throw new UsuarioJaCadastradoException();
		}
		
	}

	public boolean isUsuarioLogado() {
		if (usuarioLogado == null)
			return false;
		return true;
	}

}
