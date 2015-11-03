package manager;

import exceptions.AtualizaPerfilNaoLogado;
import exceptions.AtualizacaoDePerfilException;
import exceptions.CadastroDeUsuarioException;
import exceptions.FechaSistemaException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.SenhaProtegidaException;
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
		return controller.cadastraUsuario(nome, email, senha, dataNasc, imagem);
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
	 * @return Retorna o e-mail do usuário que foi cadastrado.
	 * @throws CadastroDeUsuarioException
	 *             Excessão lançada em um dos seguintes casos: nome, e-mail ou
	 *             data são inválidos, um usuário com o e-mail informado já está
	 *             cadastrado.
	 */
	public String cadastraUsuario(String nome, String email, String senha, String dataNasc)
			throws CadastroDeUsuarioException {
		return controller.cadastraUsuario(nome, email, senha, dataNasc, null);
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
		controller.login(email, senha);
	}

	/**
	 * Desloga um usuário do sistema.
	 * 
	 * @throws LogoutException
	 *             Excessão lançada quando não há usuário para ser deslogado.
	 */
	public void logout() throws LogoutException {
		controller.logout();
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
		controller.atualizaPerfil(atributo, valor);
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
		controller.atualizaPerfil(atributo, valor, velhaSenha);
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
	public String getInfoUsuario(String atributo, String usuario)
			throws UsuarioNaoCadastradoException, SenhaProtegidaException {
		return controller.getInfoUsuario(atributo, usuario);
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
		return controller.getInfoUsuario(atributo);
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
	 *             Excessão lançada quando ainda há um usuário logado no +Pop.
	 */
	public void fechaSistema() throws FechaSistemaException {
		if (controller.isUsuarioLogado())
			throw new FechaSistemaException();
	}
}
