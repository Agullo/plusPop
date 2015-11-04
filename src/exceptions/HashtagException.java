package exceptions;

public class HashtagException extends Exception {

	private static final long serialVersionUID = -8155324758373905824L;
	
	public HashtagException(String hashTagInvalida) {
		super("As hashtags devem comecar com '#'. Erro na hashtag: '" + hashTagInvalida + "'.");
	}
}
