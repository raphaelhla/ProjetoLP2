package atividade;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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
		assertEquals("Monitoramento slack (MEDIO - Risco medio) | PENDENTE - Monitoramento discord",
				atividadeController.exibeAtividade("A1"));
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

	@Test
	public void testBusca() {
		atividadeController.cadastraAtividade("Monitoramento slack", "MEDIO", "Risco medio");
		assertEquals("A1: Monitoramento slack", atividadeController.busca("slack"));
	}

	@Test
	public void testBusca2() {
		atividadeController.cadastraAtividade("Monitoramento slack", "MEDIO", "Risco medio");
		assertEquals("A1: Risco medio", atividadeController.busca("medio"));
	}

	@Test
	void testDefineProximaAtividade() {
		atividadeController.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		atividadeController.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		atividadeController.defineProximaAtividade("A1", "A2");
	}

	@Test
	void testTiraProximaAtividade() {
		atividadeController.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		atividadeController.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		atividadeController.tiraProximaAtividade("A1");
	}

	@Test
	void testContaProximo1() {
		atividadeController.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		atividadeController.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		atividadeController.defineProximaAtividade("A1", "A2");
		assertTrue(1 == atividadeController.contaProximos("A1"));
	}

	@Test
	void testContaProximo2() {
		atividadeController.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		assertTrue(0 == atividadeController.contaProximos("A1"));
	}

	@Test
	void testPegaProximo1() {
		atividadeController.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		atividadeController.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		atividadeController.defineProximaAtividade("A1", "A2");
		assertEquals("A2", atividadeController.pegaProximo("A1", 1));
	}

	@Test
	void testPegaMaiorRiscoAtividades() {
		atividadeController.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		atividadeController.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		atividadeController.defineProximaAtividade("A1", "A2");
		assertEquals("A2", atividadeController.pegaMaiorRiscoAtividades("A1"));
	}

	@Test
	void testPegaProximoInvalido() {
		try {
			atividadeController.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
			atividadeController.pegaProximo("A1", 0);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("EnesimaAtividade nao pode ser negativa ou zero.", e.getMessage());
		}
	}

	@Test
	void testPegaProximoInvalido2() {
		try {
			atividadeController.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
			atividadeController.pegaProximo("A1", 2);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atividade inexistente.", e.getMessage());
		}
	}

	@Test
	void testPegaProximoInexistente() {
		try {
			atividadeController.pegaProximo("A1", 1);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atividade nao encontrada.", e.getMessage());
		}
	}

	@Test
	void testPegaMaiorRiscoAtividadesInvalido() {
		try {
			atividadeController.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
			atividadeController.pegaMaiorRiscoAtividades("A1");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Nao existe proxima atividade.", e.getMessage());
		}
	}
}
