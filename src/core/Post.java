package core;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
	private List<String> conteudo;
	private List<String> hashTags;
	private LocalDateTime data;
	private int popularidade;
	
	public Post(ArrayList<String> conteudo, ArrayList hashTags, LocalDateTime data) {
		this.conteudo = conteudo;
		this.hashTags = hashTags;
		this.data = data;
		this.popularidade = 0;
	}
}
