package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import exceptions.DataNaoExisteException;
import exceptions.EmailInvalidoException;
import exceptions.FormatoDeDataInvalidoException;

/**
 * Classe estatica que contem os metodos que validam os dados de data e e-mail
 * de Usuario.
 * 
 * @author matteus
 *
 */
public class ValidaDadosDoUsuario {
	private static DateTimeFormatter DATA_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	/**
	 * Metodo que valida a data de nascimento de um usuario.
	 * 
	 * @param dataNasc
	 *            Data a ser validada.
	 * @return Retorna a data ja validada.
	 * @throws FormatoDeDataInvalidoException
	 *             Excessao lancada caso a data de entrada nao esteja em um
	 *             formato valido.
	 * @throws DataNaoExisteException
	 *             Excessao lancada caso a data de entrada nao exista.
	 */
	public static LocalDate validaData(String dataNasc) throws FormatoDeDataInvalidoException, DataNaoExisteException {
		if (isFormatoDeDataInvalido(dataNasc))
			throw new FormatoDeDataInvalidoException();
		try {
			LocalDate dataValidada;
			dataValidada = LocalDate.parse(dataNasc, DATA_PATTERN);
			String[] dataSeparada = dataNasc.split("/");
			if (Integer.parseInt(dataSeparada[0]) != dataValidada.getDayOfMonth())
				throw new DataNaoExisteException();
			return dataValidada;
		} catch (Exception e) {
			throw new DataNaoExisteException();
		}
	}

	/**
	 * Valida o e-mail de Usuario.
	 * 
	 * @param email
	 *            E-mail a ser validado.
	 * @throws EmailInvalidoException
	 *             Excessao lancada caso o e-mail passado como parametro seja
	 *             invalido.
	 */
	public static void validaEmail(String email) throws EmailInvalidoException {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.find()) {
			throw new EmailInvalidoException();
		}
	}

	private static boolean isFormatoDeDataInvalido(String data) {
		String[] dataSeparada = data.split("/");
		if (dataSeparada[0].length() != 2)
			return true;
		if (dataSeparada[1].length() != 2)
			return true;
		if (dataSeparada[2].length() != 4)
			return true;
		try {
			Integer.valueOf(dataSeparada[0]);
			Integer.valueOf(dataSeparada[1]);
			Integer.valueOf(dataSeparada[2]);
		} catch (NumberFormatException e) {
			return true;
		}
		return false;
	}

}
