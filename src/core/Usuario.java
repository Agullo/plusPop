package core;

import java.time.LocalDate;

import exceptions.DataNaoExisteException;
import exceptions.EmailInvalidoException;
import exceptions.FormatoDeDataInvalidoException;
import exceptions.NomeUsuarioException;
import exceptions.SenhaInvalidaException;
import exceptions.SenhaProtegidaException;
import util.ValidaDadosDoUsuario;

/**
 * Essa classe representa um Usuario do +Pop.
 * @author matteus
 *
 */
public class Usuario {
	private static final String IMAGEM_DEFAULT = "resources/default.jpg";
	private String nome;
	private String email;
	private String senha;
	private LocalDate dataNasc;
	private String imagem;
	
	/**
	 * Construtor de Usuario.
	 * @param nome Indica o nome de Usuario
	 * @param email String com o e-mail do Usuario
	 * @param senha String com a senha
	 * @param dataNasc Um objeto LocalDate contendo a data de nascimento do Usuario
	 * @param imagem String contendo o caminho para a imagem de perfil do Usuario
	 * @throws NomeUsuarioException 
	 * @throws EmailInvalidoException 
	 * @throws DataNaoExisteException 
	 * @throws FormatoDeDataInvalidoException 
	 */
	public Usuario(String nome, String email, String senha, String dataNasc, String imagem) throws NomeUsuarioException, EmailInvalidoException, FormatoDeDataInvalidoException, DataNaoExisteException {
		setNome(nome);
		setEmail(email);
		setSenha(senha);
		setImagem(imagem);
		setDataNasc(dataNasc);
	}
	
	private void setDataNasc(String dataNasc) throws FormatoDeDataInvalidoException, DataNaoExisteException { //ainda n lanca exception.
		this.dataNasc = ValidaDadosDoUsuario.validaData(dataNasc);
	}
	
	private void setImagem(String imagem) {
		if (isStringVazia(imagem)) {
			if (isStringVazia(this.imagem)) {
				this.imagem = IMAGEM_DEFAULT;
			}
		} else {
			this.imagem = imagem;
		}
	}
	
	private void setSenha(String senha) {
		this.senha = senha;
	}
	
	private void setEmail(String email) throws EmailInvalidoException {
		ValidaDadosDoUsuario.validaEmail(email);
		this.email = email;
	}
	
	private void setNome(String nome) throws NomeUsuarioException {
		if (isStringVazia(nome))
			throw new NomeUsuarioException();
		this.nome = nome;
	}
	
	public String getInfo(String atributo) throws SenhaProtegidaException {
		switch (atributo.toUpperCase()) {
		case "NOME":
			return this.nome;
		case "DATA DE NASCIMENTO":
			return this.dataNasc.toString();
		case "FOTO":
			return this.imagem;
		case "SENHA":
			throw new SenhaProtegidaException();
		default:
			return "Este atributo e invalido!";
		}
	}
	
	public String getEmail() {
		return this.email;
	}

	private boolean isStringVazia(String string) {
		return string == null || string.trim().equals("");
	}

	public String getSenha() {
		return this.senha;
	}
	
	public void isSenhaCorreta(String senhaDigitada) throws SenhaInvalidaException {
		if (!this.senha.equals(senhaDigitada)) {
			throw new SenhaInvalidaException();
		}
	}
}
