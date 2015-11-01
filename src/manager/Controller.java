package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.Usuario;
import exceptions.AtualizaPerfilNaoLogado;
import exceptions.AtualizacaoDePerfilException;
import exceptions.CadastroDeUsuarioException;
import exceptions.DataNaoExisteException;
import exceptions.EmailInvalidoException;
import exceptions.FormatoDeDataInvalidoException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.NenhumUsuarioLogadoException;
import exceptions.NomeUsuarioException;
import exceptions.SenhaIncorretaException;
import exceptions.SenhaInvalidaException;
import exceptions.SenhaProtegidaException;
import exceptions.UsuarioJaCadastradoException;
import exceptions.UsuarioJaLogadoException;
import exceptions.UsuarioNaoCadastradoException;

public class Controller implements Serializable {

	private static final long serialVersionUID = -5784701982498476044L;
	private Usuario usuarioLogado;
	private List<Usuario> usuariosDoMaisPop;

	public Controller() {
		usuariosDoMaisPop = new ArrayList<Usuario>();
		usuarioLogado = null;
	}

	public void login(String email, String senha) throws LoginException {
		try {
			if (isUsuarioLogado())
				throw new UsuarioJaLogadoException(usuarioLogado.getInfo("nome"));
			Usuario usuarioTentandoLogin = buscaUsuarioPorEmail(email);
			usuarioTentandoLogin.validaSenhaLogin(senha);
			;
			this.usuarioLogado = usuarioTentandoLogin;
		} catch (UsuarioNaoCadastradoException | SenhaInvalidaException | UsuarioJaLogadoException
				| SenhaProtegidaException e) {
			throw new LoginException(e);
		}
	}

	public void logout() throws LogoutException {
		if (isUsuarioLogado()) {
			usuarioLogado = null;
		} else {
			throw new LogoutException(new NenhumUsuarioLogadoException());
		}
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataNasc, String imagem)
			throws CadastroDeUsuarioException {
		try {
			isUsuarioJaCadastrado(email);
			Usuario usuario = new Usuario(nome, email, senha, dataNasc, imagem);
			usuariosDoMaisPop.add(usuario);
			return usuario.getEmail();
		} catch (UsuarioJaCadastradoException | NomeUsuarioException | EmailInvalidoException
				| FormatoDeDataInvalidoException | DataNaoExisteException e) {
			throw new CadastroDeUsuarioException(e);
		}
	}

	public void atualizaPerfil(String atributo, String valor)
			throws AtualizacaoDePerfilException, AtualizaPerfilNaoLogado {
		try {
			if (!isUsuarioLogado())
				throw new NenhumUsuarioLogadoException();
			usuarioLogado.setAtributo(atributo, valor);
		} catch (DataNaoExisteException | NomeUsuarioException | FormatoDeDataInvalidoException
				| EmailInvalidoException e) {
			throw new AtualizacaoDePerfilException(e);
		} catch (NenhumUsuarioLogadoException e) {
			throw new AtualizaPerfilNaoLogado(e);
		}
	}

	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws AtualizacaoDePerfilException {
		try {
			if (!isUsuarioLogado())
				throw new NenhumUsuarioLogadoException();
			usuarioLogado.isSenhaCorreta(velhaSenha);
			usuarioLogado.setSenha(valor);
		} catch (NenhumUsuarioLogadoException | SenhaIncorretaException e) {
			throw new AtualizacaoDePerfilException(e);
		}
	}

	public String getInfoUsuario(String atributo, String email)
			throws UsuarioNaoCadastradoException, SenhaProtegidaException {
		Usuario usuario = buscaUsuarioPorEmail(email);
		return usuario.getInfo(atributo);
	}

	public String getInfoUsuario(String atributo) throws SenhaProtegidaException {
		return usuarioLogado.getInfo(atributo);
	}

	private Usuario buscaUsuarioPorEmail(String email) throws UsuarioNaoCadastradoException {
		for (Usuario usuario : usuariosDoMaisPop) {
			if (usuario.getEmail().equals(email))
				return usuario;
		}
		throw new UsuarioNaoCadastradoException(email);
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

	public void removeUsuario(String email) throws UsuarioNaoCadastradoException {
		Usuario usuarioParaRemover = buscaUsuarioPorEmail(email);
		usuariosDoMaisPop.remove(usuarioParaRemover);
	}
}
