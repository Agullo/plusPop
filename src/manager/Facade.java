package manager;

import exceptions.AtualizaPerfilNaoLogado;
import exceptions.AtualizacaoDePerfilException;

//import java.io.BufferedOutputStream;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;

import exceptions.CadastroDeUsuarioException;
import exceptions.FechaSistemaException;
import exceptions.LoginException;
import exceptions.LogoutException;
import exceptions.SenhaProtegidaException;
import exceptions.UsuarioNaoCadastradoException;

public class Facade {
//	private static final String CAMINHO_BACKUP = "backupSistema/sistemaPop";
	private Controller controller;
	
	public Facade() {
		this.controller = new Controller();
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataNasc, String imagem)
			throws CadastroDeUsuarioException {
		return controller.cadastraUsuario(nome, email, senha, dataNasc, imagem);
	}

	public String cadastraUsuario(String nome, String email, String senha, String dataNasc)
			throws CadastroDeUsuarioException {
		return controller.cadastraUsuario(nome, email, senha, dataNasc, null);
	}
	
	public void login(String email, String senha) throws LoginException {
		controller.login(email, senha);
	}
	
	public void logout() throws LogoutException {
		controller.logout();
	}
	
	public void atualizaPerfil(String atributo, String valor) throws AtualizacaoDePerfilException, AtualizaPerfilNaoLogado {
		controller.atualizaPerfil(atributo, valor);
	}
	
	public void atualizaPerfil(String atributo, String valor, String velhaSenha) throws AtualizacaoDePerfilException {
		controller.atualizaPerfil(atributo, valor, velhaSenha);
	}
	
	public String getInfoUsuario(String atributo, String usuario) throws UsuarioNaoCadastradoException, SenhaProtegidaException {
		return controller.getInfoUsuario(atributo, usuario);
	}
	
	public String getInfoUsuario(String atributo) throws SenhaProtegidaException {
		return controller.getInfoUsuario(atributo);
	}
	
	public void removeUsuario(String email) throws UsuarioNaoCadastradoException {
		controller.removeUsuario(email);
	}

	public void iniciaSistema() {
//		try {
//			FileInputStream arquivoDeLeitura = new FileInputStream(CAMINHO_BACKUP);
//			ObjectInputStream objetoDeLeitura = new ObjectInputStream(arquivoDeLeitura);
//			controller = (Controller) objetoDeLeitura.readObject();
//			objetoDeLeitura.close();
//			arquivoDeLeitura.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		if (controller == null) {
//			controller = new Controller();
//		}
	}

	public void fechaSistema() throws FechaSistemaException {
		if (controller.isUsuarioLogado())
			throw new FechaSistemaException();
//		try (BufferedOutputStream arquivoDeGravacao = new BufferedOutputStream(new FileOutputStream(CAMINHO_BACKUP));
//				ObjectOutputStream objetoDeGravacao = new ObjectOutputStream(arquivoDeGravacao);) {
//			objetoDeGravacao.writeObject(controller);
//			objetoDeGravacao.flush();
//			arquivoDeGravacao.flush();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		controller == null;
	}
}
