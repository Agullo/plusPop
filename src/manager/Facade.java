package manager;

import exceptions.AtualizaPerfilNaoLogado;
import exceptions.AtualizacaoDePerfilException;
import exceptions.CadastroDeUsuarioException;
import exceptions.ConsultaDePopsException;
import exceptions.CriaPostException;
import exceptions.FechaSistemaException;
import exceptions.IndiceConteudoPostInvalido;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.NaoHaNotificacoesException;
import exceptions.NaoTemAmizadeException;
import exceptions.NenhumUsuarioLogadoException;
import exceptions.PostTalNaoExisteException;
import exceptions.RequisicaoInvalidaException;
import exceptions.SenhaProtegidaException;
import exceptions.SolicitacaoInexistenteException;
import exceptions.UsuarioAindaLogadoException;
import exceptions.UsuarioNaoCadastradoException;

/**
 * Facade do +Pop
 * 
 * @author matteus
 *
 */
public class Facade {
	private Controller controller;

	/**
	 * Construtor de Facade.
	 */
	public Facade() {
		this.controller = new Controller();
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
	 *             data sao invalidos, um usuario com o e-mail informado ja
	 *             esta cadastrado.
	 */
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc, String imagem)
			throws CadastroDeUsuarioException {
		return controller.cadastraUsuario(nome, email, senha, dataNasc, imagem);
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
	 * @return Retorna o e-mail do usuario que foi cadastrado.
	 * @throws CadastroDeUsuarioException
	 *             Excessao lancada em um dos seguintes casos: nome, e-mail ou
	 *             data sao invalidos, um usuario com o e-mail informado ja
	 *             esta cadastrado.
	 */
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc)
			throws CadastroDeUsuarioException {
		return controller.cadastraUsuario(nome, email, senha, dataNasc, null);
	}

	/**
	 * Metodo que loga um usuario no sistema.
	 * 
	 * @param email
	 *            Email do usuario que esta tentando fazer login.
	 * @param senha
	 *            Senha do usuario que esta tentando fazer login.
	 * @throws LoginException
	 *             Excessao lancada nos seguintes casos: quando um usuario
	 *             nao esta cadastrado, quando a senha e invalida ou quando
	 *             ele ja esta logado.
	 */
	public void login(String email, String senha) throws LoginException {
		controller.login(email, senha);
	}

	/**
	 * Desloga um usuario do sistema.
	 * 
	 * @throws LogoutException
	 *             Excessao lancada quando nao ha usuario para ser
	 *             deslogado.
	 */
	public void logout() throws LogoutException {
		controller.logout();
	}

	/**
	 * Atualiza o perfil de um usuario do +Pop.
	 * 
	 * @param atributo
	 *            Variavel que determina qual sera o atributo de Usuario que
	 *            vai ser atualizado.
	 * @param valor
	 *            Novo valor do atributo a ser atualizado.
	 * @throws AtualizacaoDePerfilException
	 *             Excessao lancada quando o valor nao e valido, ou seja,
	 *             data, nome ou e-mail nao sao validos.
	 * @throws AtualizaPerfilNaoLogado
	 *             Excessao lancada quando nenhum usuario esta logado no
	 *             +Pop.
	 */
	public void atualizaPerfil(String atributo, String valor)
			throws AtualizacaoDePerfilException, AtualizaPerfilNaoLogado {
		controller.atualizaPerfil(atributo, valor);
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
	 *             Excessao lancada se nenhum usuario estiver logado no +Pop
	 *             ou se a senha estiver incorreta.
	 */
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws AtualizacaoDePerfilException {
		controller.atualizaPerfil(atributo, valor, velhaSenha);
	}

	/**
	 * Metodo que retorna uma informacao especifica de um usuario.
	 * 
	 * @param atributo
	 *            String que indica qual atributo o metodo deve retornar.
	 * @param email
	 *            E-mail do usuario que tem a informacao.
	 * @return Retorna o atributo requerido no parametro atributo.
	 * @throws UsuarioNaoCadastradoException
	 *             Excessao lancada quando o e-mail passado como parametro
	 *             nao esta cadastrado em nenhum usuario
	 * @throws SenhaProtegidaException
	 *             Excessao lancada quando se tenta acessar a senha de um
	 *             usuario, pois a senha e protegida.
	 */
	public String getInfoUsuario(String atributo, String usuario)
			throws UsuarioNaoCadastradoException, SenhaProtegidaException {
		return controller.getInfoUsuario(atributo, usuario);
	}

	/**
	 * Metodo que retorna uma informacao especifica do usuario que esta
	 * logado no +Pop
	 * 
	 * @param atributo
	 *            String que indica qual o atributo que o metodo deve retornar.
	 * @return Retorna um atributo de um usuario logado no +Pop.
	 * @throws SenhaProtegidaException
	 *             Excessao lancada se o atributo for senha, pois a senha e
	 *             protegida.
	 */
	public String getInfoUsuario(String atributo) throws SenhaProtegidaException {
		return controller.getInfoUsuario(atributo);
	}

	/**
	 * Remove um usuario do +Pop.
	 * 
	 * @param email
	 *            e-mail de cadastro do usuario a ser removido.
	 * @throws UsuarioNaoCadastradoException
	 *             Excessao lancada se nao houver usuario cadastrado com
	 *             esse e-mail.
	 */
	public void removeUsuario(String email) throws UsuarioNaoCadastradoException {
		controller.removeUsuario(email);
	}

	/**
	 * Inicia o +Pop.
	 */
	public void iniciaSistema() {
	}

	/**
	 * Fecha o +Pop.
	 * 
	 * @throws FechaSistemaException
	 *             Excessao lancada quando ainda ha um usuario logado no
	 *             +Pop.
	 */
	public void fechaSistema() throws FechaSistemaException {
		if (controller.isUsuarioLogado())
			throw new FechaSistemaException(new UsuarioAindaLogadoException());
	}

	public void criaPost(String mensagem, String data) throws CriaPostException {
		controller.criaPost(mensagem, data);
	}

	public String getPost(int post) throws RequisicaoInvalidaException {
		return controller.getPost(post);
	}

	public String getPost(String atributo, int post) throws RequisicaoInvalidaException {
		return controller.getPost(atributo, post);
	}

	public String getConteudoPost(int indice, int post) throws RequisicaoInvalidaException, IndiceConteudoPostInvalido {
		return controller.getConteudoPost(indice, post);
	}

	public void adicionaAmigo(String usuario) throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException {
		controller.adicionaAmigo(usuario);
	}

	public int getNotificacoes() throws NenhumUsuarioLogadoException {
		return controller.getNotificacoes();
	}

	public String getNextNotificacao() throws NenhumUsuarioLogadoException, NaoHaNotificacoesException {
		return controller.getNextNotificacao();
	}

	public void rejeitaAmizade(String usuario) throws UsuarioNaoCadastradoException, SolicitacaoInexistenteException {
		controller.rejeitaAmizade(usuario);
	}

	public int getQtdAmigos() throws NenhumUsuarioLogadoException {
		return controller.getQtdAmigos();
	}

	public void aceitaAmizade(String usuario)
			throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException, SolicitacaoInexistenteException {
		controller.aceitaAmizade(usuario);
	}

	public void curtirPost(String amigo, int post) throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException,
			NaoTemAmizadeException, RequisicaoInvalidaException, PostTalNaoExisteException {
		controller.curtirPost(amigo, post);
	}

	public void rejeitarPost(String amigo, int post) throws NenhumUsuarioLogadoException, UsuarioNaoCadastradoException,
			NaoTemAmizadeException, RequisicaoInvalidaException, PostTalNaoExisteException {
		controller.rejeitarPost(amigo, post);
	}

	public void removeAmigo(String usuario)
			throws UsuarioNaoCadastradoException, NaoTemAmizadeException, NenhumUsuarioLogadoException {
		controller.removeAmigo(usuario);
	}

	public void adicionaPops(int pops) {
		controller.adicionaPops(pops);
	}

	public String getPopularidade() throws NenhumUsuarioLogadoException {
		return controller.getPopularidade();
	}

	public int getPopsPost(int post)
			throws NenhumUsuarioLogadoException, RequisicaoInvalidaException, PostTalNaoExisteException {
		return controller.getPopsPost(post);
	}

	public int qtdCurtidasDePost(int post)
			throws NenhumUsuarioLogadoException, RequisicaoInvalidaException, PostTalNaoExisteException {
		return controller.qtdCurtidasDePost(post);
	}

	public int qtdRejeicoesDePost(int post)
			throws NenhumUsuarioLogadoException, RequisicaoInvalidaException, PostTalNaoExisteException {
		return controller.qtdRejeicoesDePost(post);
	}

	public int getPopsUsuario(String usuario) throws ConsultaDePopsException, UsuarioNaoCadastradoException {
		return controller.getPopsUsuario(usuario);
	}

	public int getPopsUsuario() throws NenhumUsuarioLogadoException {
		return controller.getPopsUsuario();
	}

	public String atualizaRanking() {
		return controller.atualizaRanking();
	}

	public String atualizaTrendingTopics() {
		return controller.atualizaTrendingTopics();
	}
}
