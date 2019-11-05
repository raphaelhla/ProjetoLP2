package psquiza;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerGeralTest {

	private ControllerGeral controllerGeral;

	@BeforeEach
	public void criaController() {
		controllerGeral = new ControllerGeral();
	}

	@Test
	public void testeBuscaGeral() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		assertEquals(
				"FAK1: Pesquisa do uso da computacao no combate a fake news | raphael@example.com: Estudante de computacao | P1: problema teste de computacao | O1: Objetivo da computacao | A1: Atividade de computacao",
				controllerGeral.busca("computacao"));
	}

	@Test
	public void testeBuscaGera2() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		assertEquals("", controllerGeral.busca("IRINEU"));
	}

	@Test
	public void testeBuscaEspecifica() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		assertEquals("raphael@example.com: Estudante de computacao", controllerGeral.busca("computacao", 2));
	}
	
	@Test
	public void testBuscaEspecificaNumeroResultadoNegativo() {
		try {
			controllerGeral.busca("irineu", -2);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Numero do resultado nao pode ser negativo", e.getMessage());
		}
	}
	
	@Test
	public void testBuscaEspecificaNumeroResultadoInexiste() {
		try {
			controllerGeral.busca("irineu", 2);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Entidade nao encontrada.", e.getMessage());
		}
	}

	@Test
	public void testeContaResultadosBusca() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		assertEquals(4, controllerGeral.contaResultadosBusca("computacao"));
	}
	
	@Test
	public void testContaResultadosBuscaInexistente() {
		try {
			controllerGeral.contaResultadosBusca("irineu");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Nenhum resultado encontrado", e.getMessage());
		}
	}
}
