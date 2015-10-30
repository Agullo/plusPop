package core;

import static org.junit.Assert.*;
import org.junit.Test;

public class UsuarioTest {

	@Test
	public void testaCriacao() {
		try {
			Usuario matteus = new Usuario("Matteus", "matteus.silva@gmail.com", "123", "09/02/1994", "recursos/profile.jpg");
			Usuario fulanoSemImg = new Usuario("fulano", "fulano@hotmail.com", "1234", "05/12/1998", null);
		} catch (Exception e) {
			fail("Nao deve lancar exception");
		}
	}
}
