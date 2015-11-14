package manager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import core.Post;
import core.Ranking;
import core.Usuario;
import exceptions.AtualizaPerfilNaoLogado;
import exceptions.AtualizacaoDePerfilException;
import exceptions.CadastroDeUsuarioException;
import exceptions.ConsultaDePopsException;
import exceptions.CriaPostException;
import exceptions.DataNaoExisteException;
import exceptions.EmailInvalidoException;
import exceptions.FormatoDeDataInvalidoException;
import exceptions.IndiceConteudoPostInvalido;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.NaoHaNotificacoesException;
import exceptions.NaoTemAmizadeException;
import exceptions.NenhumUsuarioLogadoException;
import exceptions.NomeUsuarioException;
import exceptions.PostTalNaoExisteException;
import exceptions.RequisicaoInvalidaException;
import exceptions.SenhaIncorretaException;
import exceptions.SenhaInvalidaException;
import exceptions.SenhaProtegidaException;
import exceptions.SolicitacaoInexistenteException;
import exceptions.UsuarioAindaLogadoException;
import exceptions.UsuarioJaCadastradoException;
import exceptions.UsuarioJaLogadoException;
import exceptions.UsuarioNaoCadastradoException;
import util.PostFactory;

/**
 * Controller do +Pop. <br>
 * Responsavel pela logica de negocios, realizando operacoes de forma a diminuir
 * o acoplamento.
 * 
 * @author matteus
 *
 */
public class Controller implements Serializable {
	private static final long serialVersionUID = -5784701982498476044L;
	private Usuario usuarioLogado;
	private List<Usuario> usuariosDoMaisPop;
	private Ranking ranking;

	/**
	 * Construtor de Controller.
	 */
	public Controller() {
		usuariosDoMaisPop = new ArrayList<Usuario>();
		usuarioLogado = null;
		ranking = new Ranking();
	}

	/**
	 * Metodo que loga um usuario no sistema.
	 * 
	 * @param email
	 *            Email do usuario que esta tentando fazer login.
	 * @param senha
	 *            Senha do usuario que esta tentando fazer login.
	 * @throws LoginException
	 *             Excessao lancada nos seguintes casos: quando um usuario nao
	 *             esta cadastrado, quando a senha e invalida ou quando ele ja
	 *             esta logado.
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
	 * Desloga um usuario do sistema.
	 * 
	 * @throws LogoutException
	 *             Excessao lancada quando nao ha usuario para ser deslogado.
	 */
	public void logout() throws LogoutException {
		if (isUsuarioLogado()) {
			usuarioLogado = null;
		} else {
			throw new LogoutException(new NenhumUsuarioLogadoException());
		}
	}

	/**
	 * Cadastra um usuario no +Pop.
	 * 
	 * @param nome
	 *            Nome do usuario a ser cadastrado.
	 * @param email
	 *            Email que vai ser usado para fazer login.
	 * @param senha
	 *            Senha que vai ser usada pra fazer login.
	 * @param dataNasc
	 *            Data de nascimento do usuario a ser cadastrado.
	 * @param imagem
	 *            Imagem de perfil do usuario a ser cadastrado.
	 * @return Retorna o e-mail do usuario que foi cadastrado.
	 * @throws CadastroDeUsuarioException
	 *             Excessao lancada em um dos seguintes casos: nome, e-mail ou
	 *             data sao invalidos, um usuario com o e-mail informado ja esta
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
	 * Atualiza o perfil de um usuario do +Pop.
	 * 
	 * @param atributo
	 *            Variavel que determina qual sera o atributo de Usuario que vai
	 *            ser atualizado.
	 * @param valor
	 *            Novo valor do atributo a ser atualizado.
	 * @throws AtualizacaoDePerfilException
	 *             Excessao lancada quando o valor nao e valido, ou seja, data,
	 *             nome ou e-mail nao sao validos.
	 * @throws AtualizaPerfilNaoLogado
	 *             Excessao lancada quando nenhum usuario esta logado no +Pop.
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
	 * Atualiza a senha de um usuario.
	 * 
	 * @param atributo
	 *            ?????????????
	 * @param valor
	 *            Nova senha do usuario.
	 * @param velhaSenha
	 *            Senha antiga.
	 * @throws AtualizacaoDePerfilException
	 *             Excessao lancada se nenhum usuario estiver logado no +Pop ou
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
	 * metodo que retorna uma informacao especifica de um usuario.
	 * 
	 * @param atributo
	 *            String que indica qual atributo o metodo deve retornar.
	 * @param email
	 *            E-mail do usuario que tem a informacao.
	 * @return Retorna o atributo requerido no parametro atributo.
	 * @throws UsuarioNaoCadastradoException
	 *             Excessao lancada quando o e-mail passado como parametro nao
	 *             esta cadastrado em nenhum usuario
	 * @throws SenhaProtegidaException
	 *             Excessao lancada quando se tenta acessar a senha de um
	 *             usuario, pois a senha e protegida.
	 */
	public String getInfoUsuario(String atributo, String email)
			throws UsuarioNaoCadastradoException, SenhaProtegidaException {
		Usuario usuario = buscaUsuarioPorEmail(email);
		return usuario.getInfo(atributo);
	}

	/**
	 * metodo que retorna uma informacao especifica do usuario que esta logado
	 * no +Pop
	 * 
	 * @param atributo
	 *            String que indica qual o atributo que o metodo deve retornar.
	 * @return Retorna um atributo de um usuario logado no +Pop.
	 * @throws SenhaProtegidaException
	 *             Excessao lancada se o atributo for senha, pois a senha e
	 *             protegida.
	 */
	public String getInfoUsuario(String atributo) throws SenhaProtegidaException {
		return usuarioLogado.getInfo(atributo);
	}

	/**
	 * Metodo que indica se ha um usuario logado no +Pop.
	 * 
	 * @return Retorna true se ha um usuario logado e false, caso contrario.
	 */
	public boolean isUsuarioLogado() {
		if (usuarioLogado == null)
			return false;
		return true;
	}

	/**
	 * Remove um usuario do +Pop.
	 * 
	 * @param email
	 *            e-mail de cadastro do usuario a ser removido.
	 * @throws UsuarioNaoCadastradoException
	 *             Excessao lancada se nao houver usuario cadastrado com esse
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

	public String getPost(int post) throws RequisicaoInvalidaException {
		return usuarioLogado.getPost(post);
	}

	public String getPost(String atributo, int post) throws RequisicaoInvalidaException {
		return usuarioLogado.getPost(atributo, post);
	}

	public String getConteudoPost(int indice, int post) throws RequisicaoInvalidaException, IndiceConteudoPostInvalido {
		return usuarioLogado.getConteudoPost(indice, post);
	}

	public void adicionaAmigo(String usuario) throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		Usuario amigoPendente = buscaUsuarioPorEmail(usuario);
		String novaNotificacao = usuarioLogado.getNome() + " quer sua amizade.";
		amigoPendente.adicionaSolicitacaoDeAmizade(usuarioLogado);
		amigoPendente.adicionaNotificacao(novaNotificacao);
	}

	public int getNotificacoes() throws NenhumUsuarioLogadoException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		return usuarioLogado.getNotificacoes();
	}

	public String getNextNotificacao() throws NenhumUsuarioLogadoException, NaoHaNotificacoesException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		return usuarioLogado.getNextNotificacao();
	}

	public void rejeitaAmizade(String usuario) throws UsuarioNaoCadastradoException, SolicitacaoInexistenteException {
		Usuario usuarioRejeitado = buscaUsuarioPorEmail(usuario);
		usuarioLogado.rejeitaAmizade(usuarioRejeitado);
		String notificacao = usuarioLogado.getNome() + " rejeitou sua amizade.";
		usuarioRejeitado.adicionaNotificacao(notificacao);
	}

	public int getQtdAmigos() throws NenhumUsuarioLogadoException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		return usuarioLogado.getQtdAmigos();
	}

	public void aceitaAmizade(String usuario)
			throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException, SolicitacaoInexistenteException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		Usuario usuarioAceito = buscaUsuarioPorEmail(usuario);
		usuarioLogado.aceitaAmizade(usuarioAceito);
		usuarioAceito.adionaAmigo(usuarioLogado);
		String notificacao = usuarioLogado.getNome() + " aceitou sua amizade.";
		usuarioAceito.adicionaNotificacao(notificacao);
	}

	public void curtirPost(String amigo, int post)
			throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException, NaoTemAmizadeException, RequisicaoInvalidaException, PostTalNaoExisteException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		Usuario usuarioAmigo = buscaUsuarioPorEmail(amigo);
		usuarioLogado.verificaAmizade(usuarioAmigo);
		Post postDoAmigo = usuarioAmigo.buscaPost(post);
		usuarioLogado.curtirPost(postDoAmigo, usuarioAmigo);
		String notificacao = usuarioLogado.getNome() + " curtiu seu post de " + postDoAmigo.getData() + ".";
		usuarioAmigo.adicionaNotificacao(notificacao);
	}

	public void rejeitarPost(String amigo, int post)
			throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException, NaoTemAmizadeException, RequisicaoInvalidaException, PostTalNaoExisteException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		Usuario usuarioAmigo = buscaUsuarioPorEmail(amigo);
		usuarioLogado.verificaAmizade(usuarioAmigo);
		Post postDoAmigo = usuarioAmigo.buscaPost(post);
		usuarioLogado.rejeitarPost(postDoAmigo, usuarioAmigo);
		String notificacao = usuarioLogado.getNome() + " rejeitou sua amizade.";
		usuarioAmigo.adicionaNotificacao(notificacao);
	}

	public void removeAmigo(String usuario)
			throws UsuarioNaoCadastradoException, NaoTemAmizadeException, NenhumUsuarioLogadoException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		Usuario usuarioParaRemover = buscaUsuarioPorEmail(usuario);
		usuarioLogado.verificaAmizade(usuarioParaRemover);
		usuarioLogado.removeAmigo(usuarioParaRemover);
		usuarioParaRemover.removeAmigo(usuarioLogado);
		String notificacao = usuarioLogado.getNome() + " removeu a sua amizade.";
		usuarioParaRemover.adicionaNotificacao(notificacao);
	}

	private void isUsuarioJaCadastrado(String email) throws UsuarioJaCadastradoException {
		for (Usuario usuario : usuariosDoMaisPop) {
			if (usuario.getEmail().equals(email))
				throw new UsuarioJaCadastradoException();
		}
	}

	private Usuario buscaUsuarioPorEmail(String email) throws UsuarioNaoCadastradoException {
		for (Usuario usuario : usuariosDoMaisPop) {
			if (usuario.getEmail().equals(email))
				return usuario;
		}
		throw new UsuarioNaoCadastradoException(email);
	}

	public void adicionaPops(int pops) {
		usuarioLogado.adicionaPops(pops);
	}

	public String getPopularidade() throws NenhumUsuarioLogadoException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		return usuarioLogado.getPopularidade();
	}

	public int getPopsPost(int indiceDoPost) throws NenhumUsuarioLogadoException, RequisicaoInvalidaException, PostTalNaoExisteException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		Post post = usuarioLogado.buscaPost(indiceDoPost);
		return post.getPopularidade();
	}

	public int qtdCurtidasDePost(int indiceDoPost) throws NenhumUsuarioLogadoException, PostTalNaoExisteException, RequisicaoInvalidaException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		Post post = usuarioLogado.buscaPost(indiceDoPost);
		return post.getCurtidas();
	}

	public int qtdRejeicoesDePost(int indiceDePost) throws NenhumUsuarioLogadoException, RequisicaoInvalidaException, PostTalNaoExisteException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		Post post = usuarioLogado.buscaPost(indiceDePost);
		return post.getRejeicoes();
	}

	public int getPopsUsuario(String usuario) throws ConsultaDePopsException, UsuarioNaoCadastradoException {
		if (isUsuarioLogado())
			throw new ConsultaDePopsException(new UsuarioAindaLogadoException());
		Usuario usuarioCorrente = buscaUsuarioPorEmail(usuario);
		return usuarioCorrente.getPops();
		
	}

	public int getPopsUsuario() throws NenhumUsuarioLogadoException {
		if (!isUsuarioLogado())
			throw new NenhumUsuarioLogadoException();
		return usuarioLogado.getPops();
	}

	public String atualizaRanking() {
		return ranking.retornaRanking(usuariosDoMaisPop);
	}

	public String atualizaTrendingTopics() {
		List<String> hashtagsDoMaisPop = new ArrayList<>();
		for (Usuario usuario : usuariosDoMaisPop) {
			hashtagsDoMaisPop.addAll(usuario.getHashtags());
		}
		return ranking.atualizaTrendingTopics(hashtagsDoMaisPop);
	}
}
