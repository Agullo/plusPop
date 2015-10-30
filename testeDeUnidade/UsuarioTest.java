import static org.junit.Assert.*;

import org.junit.Test;

import core.Usuario;
import manager.Controller;

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
	
	@Test
	public void testaCadastroDeUsuario() {
		Controller controller = new Controller();
		
	}
}
