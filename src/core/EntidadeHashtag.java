package core;

public class EntidadeHashtag implements Comparable<EntidadeHashtag> {
	private String stringDaHashtag;
	private int frequencia;
	
	public EntidadeHashtag(String hashtag, int frequencia) {
		this.stringDaHashtag = hashtag;
		this.frequencia = frequencia;
	}
	
	public int getFrequencia() {return this.frequencia;}
	
	public void incrementaFrequencia() {this.frequencia++;}
	
	@Override
	public String toString() {return this.stringDaHashtag;}

	@Override
	public int compareTo(EntidadeHashtag outraHashtag) {
		if (this.getFrequencia() != outraHashtag.getFrequencia()) {
			return this.getFrequencia() - outraHashtag.getFrequencia();
		} else {
			return this.toString().compareToIgnoreCase(outraHashtag.toString());
		}
	}
	
	
}
