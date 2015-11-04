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
	
	public static PostFactory getInstance() {
		if (instance == null)
			instance = new PostFactory();
		return instance;
	}
	
	public PostFactory() {
	}
	
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
		String[] hashTags = mensagem.substring(this.indiceDaPrimeiraHashtag).split(" ");
		for (String hashTag : hashTags)
			if (hashTag.trim().equals("") || !hashTag.startsWith("#"))
				throw new HashtagException(hashTag);
		return hashTags;
	}
	
	private String[] recuperaMidias(String mensagem) {
		String[] midias = mensagem.substring(indiceDaPrimeiraMidia, indiceDaPrimeiraHashtag).split(" ");
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
		
		if(indiceDaPrimeiraImagem != -1 && indiceDoPrimeiroAudio == -1)
			indiceDaPrimeiraMidia = indiceDaPrimeiraImagem;
		else if (indiceDaPrimeiraImagem == -1 && indiceDoPrimeiroAudio != -1)
			indiceDaPrimeiraMidia = indiceDoPrimeiroAudio;
		else if (indiceDaPrimeiraImagem == -1 && indiceDoPrimeiroAudio == -1)
			indiceDaPrimeiraMidia = indiceDaPrimeiraHashtag;
		else {
			indiceDaPrimeiraMidia = Integer.min(indiceDaPrimeiraImagem, indiceDoPrimeiroAudio);
		}
		
		return mensagem.substring(0, indiceDaPrimeiraMidia);
	}

	private int buscaPrimeiraHashTag(String mensagem) {
		if (mensagem.contains("#"))
			return mensagem.indexOf("#");
		return mensagem.length();
	}
	
	
}
