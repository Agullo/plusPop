package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.DataBindingException;

import exceptions.DataNaoExisteException;
import exceptions.EmailInvalidoException;
import exceptions.FormatoDeDataInvalidoException;

public class ValidaDadosDoUsuario {
	private static final DateTimeFormatter DATA_PATTERN = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static LocalDate validaData(String dataNasc) throws FormatoDeDataInvalidoException, DataNaoExisteException { // Dando errado aqui, só pode!
		if (isFormatoDeDataInvalido(dataNasc))
			throw new FormatoDeDataInvalidoException();
		try {
			LocalDate dataValidada = LocalDate.parse(dataNasc, DATA_PATTERN);
			return dataValidada;
		} catch (Exception e) {
			throw new DataNaoExisteException();
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
	
	public static void validaEmail(String email) throws EmailInvalidoException {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email);
		if (!matcher.find()) {
			throw new EmailInvalidoException();
		}
	}

}
