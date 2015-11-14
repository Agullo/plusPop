package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import core.Post;
import exceptions.CriaPostException;
import exceptions.HashtagException;
import exceptions.TamanhoDaMensagemException;

public class PostFactory {
	private static PostFactory instance;
	private int indiceDaPrimeiraHashtag;
	private int indiceDaPrimeiraMidia;
	
	/**
	 * Factory de Post.
	 * 
	 * @return cria a factory de post.
	 */
	public static PostFactory getInstance() {
		if (instance == null)
			instance = new PostFactory();
		return instance;
	}
	
	/**
	 * Factory de Post.
	 */
	public PostFactory() {
	}
	
	/**
	 * Metodo que cria Post.
	 * 
	 * @param mensagem
	 *            Mensagem digitada pelo usuario.
	 * @param stringComData
	 *            String da data local.
	 * @return Cria o post.
	 * 
	 * @throws CriaPostException
	 *             Execssao lancada quando nao e possivel criar o post.
	 */
	public Post criaPost(String mensagem, String stringComData) throws CriaPostException{
		try {
			indiceDaPrimeiraHashtag = buscaPrimeiraHashTag(mensagem); 
			String textoDaMensagem = recuperaTextoValidado(mensagem);
			String[] midias = recuperaMidias(mensagem);
			String[] hashTagsvalidadas = validaHashtags(mensagem);
			List<String> conteudo = geraListaDeConteudo(textoDaMensagem, midias);
			List<String> hashtags = geraListaDeHashtags(hashTagsvalidadas);
			LocalDateTime dataHoraPost = criaDataDoPost(stringComData);
			return new Post(conteudo, hashtags, dataHoraPost);
		} catch (TamanhoDaMensagemException | HashtagException e) {
			throw new CriaPostException(e);
		}
	}
	
	//refatoramentos.
	
	private LocalDateTime criaDataDoPost(String stringComData) {
		DateTimeFormatter formatterData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(stringComData.substring(0, 10), formatterData);
		DateTimeFormatter formatterHora = DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalTime hora = LocalTime.parse(stringComData.substring(11, 19), formatterHora);
		return LocalDateTime.of(data, hora);
	}

	private List<String> geraListaDeHashtags(String[] hashTagsvalidadas) {
		List<String> hashtags = new ArrayList<String>();
		Collections.addAll(hashtags, hashTagsvalidadas);
		return hashtags;
	}

	private List<String> geraListaDeConteudo(String textoDaMensagem, String[] midias) {
		List<String> conteudo = new ArrayList<String>();
		conteudo.add(textoDaMensagem);
		Collections.addAll(conteudo, midias);
		return conteudo;
	}

	private String[] validaHashtags(String mensagem) throws HashtagException {
		if (indiceDaPrimeiraHashtag == -1) {
			String[] vazio = new String[0];
			return vazio;
		} 
		String[] hashTags = mensagem.substring(this.indiceDaPrimeiraHashtag).split(" ");
		for (String hashTag : hashTags)
			if (hashTag.trim().equals("") || !hashTag.startsWith("#"))
				throw new HashtagException(hashTag);
		return hashTags;
	}
	
	private String[] recuperaMidias(String mensagem) {
		String[] midias;
		if (temHashtag())
			midias = mensagem.substring(indiceDaPrimeiraMidia, indiceDaPrimeiraHashtag).split(" ");
		else
			midias = mensagem.substring(indiceDaPrimeiraMidia, mensagem.length()).split(" ");
		midias[midias.length - 1] += " ";
		return midias;
	}

	private String recuperaTextoValidado(String mensagem) throws TamanhoDaMensagemException {
		String textoParaValidar = getTextoParaValidar(mensagem);
		if (textoParaValidar.length() > 200)
			throw new TamanhoDaMensagemException();
		
		return textoParaValidar;
	}
	
	private String getTextoParaValidar(String mensagem) {
		int indiceDaPrimeiraImagem = mensagem.indexOf("<imagem>");
		int indiceDoPrimeiroAudio = mensagem.indexOf("<audio>");
		
		if(temImagemEAudio(indiceDaPrimeiraImagem, indiceDoPrimeiroAudio))
			indiceDaPrimeiraMidia = Integer.min(indiceDaPrimeiraImagem, indiceDoPrimeiroAudio);
		else if (temSoAudio(indiceDaPrimeiraImagem, indiceDoPrimeiroAudio))
			indiceDaPrimeiraMidia = indiceDoPrimeiroAudio;
		else if (temSoImagem(indiceDaPrimeiraImagem, indiceDoPrimeiroAudio))
			indiceDaPrimeiraMidia = indiceDaPrimeiraImagem;
		else if (temHashtag())
			indiceDaPrimeiraMidia = indiceDaPrimeiraHashtag;
		else
			indiceDaPrimeiraMidia = mensagem.length();
		
		return mensagem.substring(0, indiceDaPrimeiraMidia);
	}

	private boolean temHashtag() {
		return indiceDaPrimeiraHashtag != -1;
	}

	private boolean temSoImagem(int indiceDaPrimeiraImagem, int indiceDoPrimeiroAudio) {
		return indiceDaPrimeiraImagem != -1 && indiceDoPrimeiroAudio == -1;
	}

	private boolean temSoAudio(int indiceDaPrimeiraImagem, int indiceDoPrimeiroAudio) {
		return indiceDaPrimeiraImagem == -1 && indiceDoPrimeiroAudio != -1;
	}

	private boolean temImagemEAudio(int indiceDaPrimeiraImagem, int indiceDoPrimeiroAudio) {
		return indiceDaPrimeiraImagem != -1 && indiceDoPrimeiroAudio != -1;
	}

	private int buscaPrimeiraHashTag(String mensagem) {
		return mensagem.indexOf("#");
	}
	
	
}
