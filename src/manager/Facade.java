package manager;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import exceptions.CadastroDeUsuarioException;
import exceptions.FechaSistemaException;

public class Facade {
	private static final String CAMINHO_BACKUP = "backupSistema/sistemaPop";
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
		if (!controller.isUsuarioLogado())
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
