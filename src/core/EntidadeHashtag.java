package core;

public class EntidadeHashtag implements Comparable<EntidadeHashtag> {
	private String stringDaHashtag;
	private int frequencia;

	/**
	 * Verifica a frequencia de publicacao de Hashtags.
	 * 
	 * @param hashtag
	 *            String com hashtag.
	 * @param frequencia
	 *            Int para verificar a frequencia da Hashtag.
	 */
	public EntidadeHashtag(String hashtag, int frequencia) {
		this.stringDaHashtag = hashtag;
		this.frequencia = frequencia;
	}

	/**
	 * Retorna a frequencia de hashtag.
	 * 
	 * @return frequencia da hashtag.
	 */
	public int getFrequencia() {
		return this.frequencia;
	}

	/**
	 * Incrementa a Frequencia de hashtag.
	 */
	public void incrementaFrequencia() {
		this.frequencia++;
	}

	@Override
	/**
	 * toString da Hashtag.
	 */
	public String toString() {
		return this.stringDaHashtag;
	}

	@Override
	/**
	 * compareTo de Hashtag.
	 * Compara a frequencia de uma hashtag com um outra.
	 * 
	 * @param outraHashtag 
	 * 			Uma segunda hashTag para ser comparada.
	 */
	public int compareTo(EntidadeHashtag outraHashtag) {
		if (this.getFrequencia() != outraHashtag.getFrequencia()) {
			return this.getFrequencia() - outraHashtag.getFrequencia();
		} else {
			return this.toString().compareToIgnoreCase(outraHashtag.toString());
		}
	}

}
