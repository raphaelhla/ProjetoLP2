package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AtividadeControllerTest {

	private AtividadeController atividadeController;
	
	@BeforeEach
	public void criaController() {
		atividadeController = new AtividadeController();
	}
	
	@Test
	public void testCadastraAtividade() {
		assertEquals("A1", atividadeController.cadastraAtividade("Monitoramento slack", "MEDIO", "Risco medio"));
	}

	@Test
	public void testApagaAtividade() {
		try {
			atividadeController.cadastraAtividade("Monitoramento slack", "MEDIO", "Risco medio");
			atividadeController.apagaAtividade("A1");
			atividadeController.exibeAtividade("A1");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atividade nao encontrada", e.getMessage());
		}
	}
	
	@Test
	public void testExibeAtividade() {
		atividadeController.cadastraAtividade("Monitoramento slack", "MEDIO", "Risco medio");
		atividadeController.cadastraItem("A1", "Monitoramento discord");
		assertEquals("Monitoramento slack (MEDIO - Risco medio) | PENDENTE - Monitoramento discord", atividadeController.exibeAtividade("A1"));
	}
	
	@Test
	public void testContaItensPendentes() {
		atividadeController.cadastraAtividade("Monitoramento slack", "MEDIO", "Risco medio");
		atividadeController.cadastraItem("A1", "Monitoramento discord");
		assertEquals(1, atividadeController.contaItensPendentes("A1"));
	}
	
	@Test
	public void testContaItensRealizados() {
		atividadeController.cadastraAtividade("Monitoramento slack", "MEDIO", "Risco medio");
		assertEquals(0, atividadeController.contaItensRealizados("A1"));
	}
}
