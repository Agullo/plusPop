package core;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import exceptions.IndiceConteudoPostInvalido;

public class Post {
	private List<String> conteudo;
	private List<String> hashTags;
	private LocalDateTime data;
	private int popularidade;
	private int curtidas;
	private int rejeicoes;
	
	/**
	 * Construtor de Post.
	 * 
	 * @param conteudo
	 *            Tem o texto interno do Post.
	 * @param hashTags
	 *            Guarda as hashtags usadas no Post.
	 * @param data
	 *            Indica a data que foi feito o Post.
	 */
	public Post(List<String> conteudo, List<String> hashTags, LocalDateTime data) {
		this.conteudo = conteudo;
		this.hashTags = hashTags;
		this.data = data;
		this.popularidade = 0;
		this.curtidas = 0;
		this.rejeicoes = 0;
	}
	
	@Override
	/**
	 * toString da classe Post.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(passaConteudoPraString());
		sb.append(" ");
		for (String ht : hashTags) {
			sb.append(ht);
			sb.append(" ");
		}
		sb.append(passaDataPraString());
		
		return sb.toString().trim();
	}
	
	private String passaDataPraString() {
		String stringDaData = "(" + getData() + ")";
		return stringDaData;
	}
	
	/**
	 * Pega o conteudo do Post e converte para String
	 * 
	 * @return o conteudo sem espacos execivos.
	 */
	public String passaConteudoPraString() {
		StringBuilder sb = new StringBuilder();
		sb.append(conteudo.get(0));
		for (int i = 1; conteudo.size() > i; i++) {
			sb.append(conteudo.get(i));
			sb.append(" ");
		}
		return sb.toString().trim();
	}

	/**
	 * Pega as hashTags contidas dentro do Post.
	 * 
	 * @return toString das hashtags.
	 */
	public String getHashtags() {
		StringBuilder sb = new StringBuilder();
		sb.append(hashTags.get(0));
		for (int i = 1; hashTags.size() > i; i++){
			sb.append(",");
			sb.append(hashTags.get(i));
		}
		return sb.toString();
	}

	/**
	 * Pega a data do Post.
	 * 
	 * @return A string da Data.
	 */
	public String getData() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String stringDaData = data.format(formatter);
		return stringDaData;
	}

	/**
	 * Pega o conteudo do Post.
	 * 
	 * @param indice
	 *            Indice do post.
	 * @return se comecar com imagem, retorna o complemento da imagem mais a
	 *         string do post. se comecar com audio, retorna o complemento do
	 *         audio mais a string do post.
	 * @throws IndiceConteudoPostInvalido
	 *             Excessão lancada quando o indice nao existe neste post.
	 */
	public String getConteudo(int indice) throws IndiceConteudoPostInvalido {
		if (indice >= conteudo.size())
			throw new IndiceConteudoPostInvalido(indice, conteudo.size());
		String saida = conteudo.get(indice).trim();
		int tamanhoDaSaida = saida.length();
		String complementoAudio = "$arquivo_audio:";
		String complementoImagem = "$arquivo_imagem:";
		if (saida.startsWith("<imagem>"))
			return complementoImagem + saida.substring(8, tamanhoDaSaida - 9);
		if (saida.startsWith("<audio>"))
			return complementoAudio + saida.substring(7, tamanhoDaSaida - 8);
		return saida;
	}
	
	/**
	 * Adiciona curtida no Post.
	 */
	public void adicionaCurtida() {curtidas++;}
	
	/**
	 * Adiciona rejeicao no Post.
	 */
	public void adicionaRejeicao() {rejeicoes++;}
	
	/**
	 * Adiciona popularidade no Post.
	 * 
	 * @param valor
	 *            Valor a ser adicionado no Post.
	 */
	public void adicionaPopularidade(int valor) {popularidade += valor;}
	
	/**
	 * Remove popularidade no Post.
	 * 
	 * @param valor
	 *            Valor a ser removido no Post.
	 */
	public void removePopularidade(int valor) {popularidade -= valor;}

	/**
	 * Lista contendo Hashtags
	 * 
	 * @return lista das hashtags.
	 */
	public List<String> getListaDeHashtags() {
		return this.hashTags;
	}

	/**
	 * Adiciona uma nova hashtag.
	 * 
	 * @param novaHashtag
	 *            Nova hashtag a ser adicionada
	 */
	public void adicionaHashtag(String novaHashtag) {
		hashTags.add(novaHashtag);
	}
	
	/**
	 * Metodo para verificar se o Post e recente.
	 * 
	 * @return true, se o post e recente
	 *  	   false, se o post nao e recente.
	 */
	public boolean isRecente() {
		LocalDateTime agora = LocalDateTime.now();
		if (agora.getYear() == this.data.getYear()) {
			if (agora.getMonth() == this.data.getMonth()) {
				if (agora.getDayOfMonth() == this.data.getDayOfMonth())
					return true;
			}
		}
		return false;
	}

	/**
	 * Pega a popularidade do Post.
	 * 
	 * @return popularidade
	 */
	public int getPopularidade() {
		return this.popularidade;
	}

	/**
	 * Pega as curtidas do Post.
	 * 
	 * @return curtidas
	 */
	public int getCurtidas() {
		return this.curtidas;
	}

	/**
	 * Pega as rejeicoes do Post.
	 * 
	 * @return rejeicoes
	 */
	public int getRejeicoes() {
		return this.rejeicoes;
	}
}
