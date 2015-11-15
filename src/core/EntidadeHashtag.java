package core;

/**
 * Entidade que representa uma hashtag do +Pop. Usada pra facilitar a construcao
 * dos treding topics do +Pop.
 * 
 * @author Matteus Silva
 *
 */
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

	/**
	 * toString da Hashtag.
	 */
	@Override
	public String toString() {
		return this.stringDaHashtag;
	}

	/**
	 * compareTo de Hashtag. Compara a frequencia de uma hashtag com um outra.
	 * Caso a frequencia seja a mesma, compara pela ordem alfabetica ignorando
	 * se e caixa alta ou nao.
	 * 
	 * @param outraHashtag
	 *            Uma segunda hashTag para ser comparada.
	 */
	@Override
	public int compareTo(EntidadeHashtag outraHashtag) {
		if (this.getFrequencia() != outraHashtag.getFrequencia()) {
			return this.getFrequencia() - outraHashtag.getFrequencia();
		} else {
			return this.toString().compareToIgnoreCase(outraHashtag.toString());
		}
	}

}
