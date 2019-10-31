package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObjetivoControllerTest {

	private ObjetivoController objetivoController;
	
	@BeforeEach
	public void criaController() {
		objetivoController = new ObjetivoController();
	}
	
	@Test
	public void testCadastraObjetivo() {
		assertEquals("O1", objetivoController.cadastraObjetivo("GERAL", "teste", 4, 5));
	}
	
	@Test
	public void testCadastraObjetivo2() {
		objetivoController.cadastraObjetivo("ESPECIFICO", "TESTE", 4, 2);
		assertEquals("O2", objetivoController.cadastraObjetivo("GERAL", "teste", 4, 5));
	}

	@Test
	public void testApagaObjetivo() {
		try {
			objetivoController.cadastraObjetivo("ESPECIFICO", "TESTE", 4, 2);
			objetivoController.apagarObjetivo("O1");
			objetivoController.exibeObjetivo("O1");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Objetivo nao encontrado", e.getMessage());
		}
	}
	
	@Test
	public void testExibeObjetivo() {
		objetivoController.cadastraObjetivo("ESPECIFICO", "TESTE", 4, 2);
		assertEquals("O1 - ESPECIFICO - TESTE - 6", objetivoController.exibeObjetivo("O1"));
	}
}
