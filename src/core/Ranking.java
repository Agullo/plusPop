package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ranking {
	List<Usuario> maisPopulares;
	List<Usuario> menosPopulares;
	
	public Ranking() {
		this.maisPopulares = new ArrayList<>();
		this.menosPopulares = new ArrayList<>();
	}
	
	public String retornaRanking(List<Usuario> todosOsUsuarios) {
		maisPopulares = constroiMaisPopulares(todosOsUsuarios);
		menosPopulares = constroiMenosPopulares(todosOsUsuarios);
		StringBuilder sb = new StringBuilder();
		int countMaisPopulares = 1;
		int countMenosPopulares = 1;
		sb.append("Mais Populares:");
		for (Usuario usuario : maisPopulares) {
			sb.append(" (" + countMaisPopulares + ") " + usuario.getNome() + " " + usuario.getPops() + ";");
			countMaisPopulares++;
		}
		sb.append(" | Menos Populares:");
		for (Usuario usuario : menosPopulares) {
			sb.append(" (" + countMenosPopulares + ") " + usuario.getNome() + " " + usuario.getPops() + ";");
			countMenosPopulares++;
		}
		return sb.toString();
	}

	private List<Usuario> constroiMenosPopulares(List<Usuario> todosOsUsuarios) {
		Collections.sort(todosOsUsuarios);
		List<Usuario> menosPopulares = new ArrayList<>();
		menosPopulares.add(todosOsUsuarios.get(0));
		menosPopulares.add(todosOsUsuarios.get(1));
		menosPopulares.add(todosOsUsuarios.get(2));
		return menosPopulares;
	}

	private List<Usuario> constroiMaisPopulares(List<Usuario> todosOsUsuarios) {
		Collections.sort(todosOsUsuarios);
		List<Usuario> maisPopulares = new ArrayList<>();
		maisPopulares.add(todosOsUsuarios.get(todosOsUsuarios.size() -1));
		maisPopulares.add(todosOsUsuarios.get(todosOsUsuarios.size() -2));
		maisPopulares.add(todosOsUsuarios.get(todosOsUsuarios.size() -3));
		return maisPopulares;
	}

	public String atualizaTrendingTopics(List<String> hashtagsDoMaisPop) {
		List<EntidadeHashtag> entidadesHashtags = new ArrayList<EntidadeHashtag>();
		
		Map<String, Integer> mapaDeHastags = new HashMap<>();
		for (String hashTag : hashtagsDoMaisPop) {
			if (!mapaDeHastags.containsKey(hashTag))
				mapaDeHastags.put(hashTag, new Integer(1));
			else
				mapaDeHastags.put(hashTag, new Integer (mapaDeHastags.get(hashTag).intValue() + 1));
		}
		
		for (String string : mapaDeHastags.keySet()) {
			EntidadeHashtag hashtag = new EntidadeHashtag(string, mapaDeHastags.get(string));
			entidadesHashtags.add(hashtag);
		}
		
		Collections.sort(entidadesHashtags);
		
		StringBuilder sb = new StringBuilder();
		sb.append("Trending Topics:  ");
		int counter = 1;
		for (int i = entidadesHashtags.size() - 1; i > entidadesHashtags.size() - 4; i--) {
			sb.append("(" + (counter) + ") " + entidadesHashtags.get(i).toString().trim() + ": " + entidadesHashtags.get(i).getFrequencia() + "; ");
			counter++;
		}
		
		return sb.toString().trim();
//		
//		String[] trendingTopics = new String[3];
//		int[] repeticoes = new int[3];
//		for (String hashtag : mapaDeHastags.keySet()) {
//			int auxiliar = mapaDeHastags.get(hashtag);
//			for (int i = 0; i < repeticoes.length; i++) {
//				if (auxiliar > repeticoes[i]) {
//					repeticoes[i] = auxiliar;
//					trendingTopics[i] = hashtag;
//					break;
//				}
//			}
//		}
//		
//		StringBuilder sb = new StringBuilder();
//		sb.append("Trending Topics:  ");
//		for (int i = 0; i < repeticoes.length; i++) {
//			sb.append("(" + (i+1) + ") " + trendingTopics[i].trim() + ": " + repeticoes[i] + "; ");
//		}
//		
//		return sb.toString().trim();
	}
}
