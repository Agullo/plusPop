package util;

import core.Post;

public class PostFactory {
	private static PostFactory instance;
	
	public static PostFactory getInstance() {
		if (instance == null)
			instance = new PostFactory();
		return instance;
	}
	
	public PostFactory() {
	}
	
	public Post criaPost(String mensagem, String data) {
		//TODO: Fazer tudo xD
		return null;
	}
}
