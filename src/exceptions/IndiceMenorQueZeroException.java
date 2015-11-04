package exceptions;

public class IndiceMenorQueZeroException extends Exception {

	private static final long serialVersionUID = -1224161975777228614L;
	
	public IndiceMenorQueZeroException() {
		super("O indice deve ser maior ou igual a zero.");
	}
}
