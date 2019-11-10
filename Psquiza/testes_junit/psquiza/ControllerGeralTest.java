package psquiza;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
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
	
	@Test
	public void testAssociaProblemaFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		assertTrue(controllerGeral.associaProblema("FAK1", "P1"));
	}
	
	@Test
	public void testAssociaProblemaTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		controllerGeral.associaProblema("FAK1", "P1");
		assertFalse(controllerGeral.associaProblema("FAK1", "P1"));
	}
	
	@Test
	public void testDesassociaProblemaFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		controllerGeral.associaProblema("FAK1", "P1");
		assertTrue(controllerGeral.desassociaProblema("FAK1"));
	}
	
	@Test
	public void testDesassociaProblemaTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		assertFalse(controllerGeral.desassociaProblema("FAK1"));
	}
	
	@Test
	public void testAssociaObjetivoFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		assertTrue(controllerGeral.associaObjetivo("FAK1", "O1"));
	}
	
	@Test
	public void testAssociaObjetivoTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.associaObjetivo("FAK1", "O1");
		assertFalse(controllerGeral.associaObjetivo("FAK1", "O1"));
	}
	
	@Test
	public void testDesassociaObjetivoFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.associaObjetivo("FAK1", "O1");
		assertTrue(controllerGeral.desassociaObjetivo("FAK1", "O1"));
	}
	
	@Test
	public void testDesassociaObjetivoTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		assertFalse(controllerGeral.desassociaObjetivo("FAK1", "O1"));
	}
	
	@Test
	public void testListaPesquisas() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisa("teste", "teste");
		assertEquals("TES1 - teste - teste | FAK1 - Pesquisa do uso da computacao no combate a fake news - fake news", controllerGeral.listaPesquisas("PROBLEMA"));
	}
}
