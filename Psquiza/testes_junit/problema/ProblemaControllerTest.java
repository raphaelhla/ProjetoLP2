package problema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemaControllerTest {

	private ProblemaController problemaController;
	
	@BeforeEach
	public void criaController() {
		problemaController = new ProblemaController();
	}
	
	@Test
	public void testCadastraProblema() {
		assertEquals("P1", problemaController.cadastraProblema("teste", 4));
	}
	
	@Test
	public void testApagaProblema() {
		try {
			problemaController.cadastraProblema("TESTE", 4);
			problemaController.apagarProblema("P1");
			problemaController.exibeProblema("P1");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Problema nao encontrado", e.getMessage());
		}
	}
	
	@Test
	public void testExibeProblema() {
		problemaController.cadastraProblema("TESTE", 4);
		assertEquals("P1 - TESTE - 4", problemaController.exibeProblema("P1"));
	}

	@Test
	public void testBusca() {
		problemaController.cadastraProblema("TESTE", 4);
		assertEquals("", problemaController.busca("slack"));
	}
	
	@Test
	public void testBusca2() {
		problemaController.cadastraProblema("TESTE", 4);
		assertEquals("P1: TESTE", problemaController.busca("TEST"));
	}
	
	@Test
	public void testGetProblema() {
		problemaController.cadastraProblema("TESTE", 4);
		assertEquals("TESTE - 4", problemaController.getProblema("P1").toString());
	}
}
