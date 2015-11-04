package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.Post;
import core.Usuario;
import exceptions.AtualizaPerfilNaoLogado;
import exceptions.AtualizacaoDePerfilException;
import exceptions.CadastroDeUsuarioException;
import exceptions.CriaPostException;
import exceptions.DataNaoExisteException;
import exceptions.EmailInvalidoException;
import exceptions.FormatoDeDataInvalidoException;
import exceptions.IndiceConteudoPostInvalido;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.NenhumUsuarioLogadoException;
import exceptions.NomeUsuarioException;
import exceptions.RequisicaoInvalidaException;
import exceptions.SenhaIncorretaException;
import exceptions.SenhaInvalidaException;
import exceptions.SenhaProtegidaException;
import exceptions.UsuarioJaCadastradoException;
import exceptions.UsuarioJaLogadoException;
import exceptions.UsuarioNaoCadastradoException;
import util.PostFactory;

/**
 * Controller do +Pop. <br>
 * Responsável pela lógica de negócios, realizando operações de forma a diminuir
 * o acoplamento.
 * 
 * @author matteus
 *
 */
public class Controller implements Serializable {
	private static final long serialVersionUID = -5784701982498476044L;
	private Usuario usuarioLogado;
	private List<Usuario> usuariosDoMaisPop;

	/**
	 * Construtor de Controller.
	 */
	public Controller() {
		usuariosDoMaisPop = new ArrayList<Usuario>();
		usuarioLogado = null;
	}

	/**
	 * Método que loga um usuário no sistema.
	 * 
	 * @param email
	 *            Email do usuário que está tentando fazer login.
	 * @param senha
	 *            Senha do usuário que está tentando fazer login.
	 * @throws LoginException
	 *             Excessão lançada nos seguintes casos: quando um usuário não
	 *             está cadastrado, quando a senha é inválida ou quando ele já
	 *             está logado.
	 */
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

	/**
	 * Desloga um usuário do sistema.
	 * 
	 * @throws LogoutException
	 *             Excessão lançada quando não há usuário para ser deslogado.
	 */
	public void logout() throws LogoutException {
		if (isUsuarioLogado()) {
			usuarioLogado = null;
		} else {
			throw new LogoutException(new NenhumUsuarioLogadoException());
		}
	}

	/**
	 * Cadastra um usuário no +Pop.
	 * 
	 * @param nome
	 *            Nome do usuário a ser cadastrado.
	 * @param email
	 *            Email que vai ser usado para fazer login.
	 * @param senha
	 *            Senha que vai ser usada pra fazer login.
	 * @param dataNasc
	 *            Data de nascimento do usuário a ser cadastrado.
	 * @param imagem
	 *            Imagem de perfil do usuário a ser cadastrado.
	 * @return Retorna o e-mail do usuário que foi cadastrado.
	 * @throws CadastroDeUsuarioException
	 *             Excessão lançada em um dos seguintes casos: nome, e-mail ou
	 *             data são inválidos, um usuário com o e-mail informado já está
	 *             cadastrado.
	 */
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

	/**
	 * Atualiza o perfil de um usuário do +Pop.
	 * 
	 * @param atributo
	 *            Variável que determina qual será o atributo de Usuario que vai
	 *            ser atualizado.
	 * @param valor
	 *            Novo valor do atributo a ser atualizado.
	 * @throws AtualizacaoDePerfilException
	 *             Excessão lançada quando o valor não é válido, ou seja, data,
	 *             nome ou e-mail não são válidos.
	 * @throws AtualizaPerfilNaoLogado
	 *             Excessão lançada quando nenhum usuário está logado no +Pop.
	 */
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

	/**
	 * Atualiza a senha de um usuário.
	 * 
	 * @param atributo
	 *            ?????????????
	 * @param valor
	 *            Nova senha do usuário.
	 * @param velhaSenha
	 *            Senha antiga.
	 * @throws AtualizacaoDePerfilException
	 *             Excessão lançada se nenhum usuário estiver logado no +Pop ou
	 *             se a senha estiver incorreta.
	 */
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

	/**
	 * Método que retorna uma informação específica de um usuário.
	 * 
	 * @param atributo
	 *            String que indica qual atributo o método deve retornar.
	 * @param email
	 *            E-mail do usuário que tem a informação.
	 * @return Retorna o atributo requerido no parametro atributo.
	 * @throws UsuarioNaoCadastradoException
	 *             Excessão lançada quando o e-mail passado como parametro não
	 *             está cadastrado em nenhum usuário
	 * @throws SenhaProtegidaException
	 *             Excessão lançada quando se tenta acessar a senha de um
	 *             usuário, pois a senha é protegida.
	 */
	public String getInfoUsuario(String atributo, String email)
			throws UsuarioNaoCadastradoException, SenhaProtegidaException {
		Usuario usuario = buscaUsuarioPorEmail(email);
		return usuario.getInfo(atributo);
	}

	/**
	 * Método que retorna uma informação específica do usuário que está logado
	 * no +Pop
	 * 
	 * @param atributo
	 *            String que indica qual o atributo que o método deve retornar.
	 * @return Retorna um atributo de um usuário logado no +Pop.
	 * @throws SenhaProtegidaException
	 *             Excessão lançada se o atributo for senha, pois a senha é
	 *             protegida.
	 */
	public String getInfoUsuario(String atributo) throws SenhaProtegidaException {
		return usuarioLogado.getInfo(atributo);
	}

	/**
	 * Método que indica se há um usuário logado no +Pop.
	 * 
	 * @return Retorna true se há um usuário logado e false, caso contrário.
	 */
	public boolean isUsuarioLogado() {
		if (usuarioLogado == null)
			return false;
		return true;
	}

	/**
	 * Remove um usuário do +Pop.
	 * 
	 * @param email
	 *            e-mail de cadastro do usuário a ser removido.
	 * @throws UsuarioNaoCadastradoException
	 *             Excessão lançada se não houver usuário cadastrado com esse
	 *             e-mail.
	 */
	public void removeUsuario(String email) throws UsuarioNaoCadastradoException {
		Usuario usuarioParaRemover = buscaUsuarioPorEmail(email);
		usuariosDoMaisPop.remove(usuarioParaRemover);
	}
	
	public void criaPost(String mensagem, String data) throws CriaPostException {
		Post novoPost = PostFactory.getInstance().criaPost(mensagem, data);
		usuarioLogado.adicionaPost(novoPost);
	}

	// Refatoramentos.

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

	public String getPost(int post) throws RequisicaoInvalidaException {
		return usuarioLogado.getPost(post);
	}

	public String getPost(String atributo, int post) throws RequisicaoInvalidaException {
		return usuarioLogado.getPost(atributo, post);
	}

	public String getConteudoPost(int indice, int post) throws RequisicaoInvalidaException, IndiceConteudoPostInvalido {
		return usuarioLogado.getConteudoPost(indice, post);
	}
}
