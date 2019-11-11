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
	public void testAlteraPesquisaEExibePesquisa() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.alteraPesquisa("FAK1", "DESCRICAO", "MUDOU");
		assertEquals("FAK1 - MUDOU - fake news", controllerGeral.exibePesquisa("FAK1"));
	}
	
	@Test
	public void testPesquisaEhAtiva() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.encerraPesquisa("FAK1", "DESCONHECIDO");
		assertFalse(controllerGeral.pesquisaEhAtiva("FAK1"));
	}
	
	@Test
	public void testPesquisaEhAtiva2() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.encerraPesquisa("FAK1", "DESCONHECIDO");
		controllerGeral.ativaPesquisa("FAK1");
		assertTrue(controllerGeral.pesquisaEhAtiva("FAK1"));
	}
	
	@Test
	public void testAlteraPesquisadorEExibePesquisador() {
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		controllerGeral.alteraPesquisador("raphael@example.com", "NOME", "IRINEU");
		assertEquals("IRINEU (Estudante) - Estudante de computacao - raphael@example.com - https://teste.com", controllerGeral.exibePesquisador("raphael@example.com"));
	}
	
	@Test
	public void testPesquisadorEhAtivo() {
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		controllerGeral.desativaPesquisador("raphael@example.com");
		controllerGeral.ativaPesquisador("raphael@example.com");
		assertTrue(controllerGeral.pesquisadorEhAtivo("raphael@example.com"));
	}
	
	@Test
	public void testPesquisadorEhAtivo2() {
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		controllerGeral.desativaPesquisador("raphael@example.com");
		assertFalse(controllerGeral.pesquisadorEhAtivo("raphael@example.com"));
	}
	
	@Test
	public void testApagaProblema() {
		try {
			controllerGeral.cadastraProblema("problema teste de computacao", 4);
			controllerGeral.apagarProblema("P1");
			controllerGeral.exibeProblema("P1");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			 assertEquals("Problema nao encontrado", e.getMessage());
		}
	}
	
	@Test
	public void testApagaObjetivo() {
		try {
			controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
			controllerGeral.apagarObjetivo("O1");
			controllerGeral.exibeObjetivo("O1");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			 assertEquals("Objetivo nao encontrado", e.getMessage());
		}
	}
	
	@Test
	public void testExibeProblema() {
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		assertEquals("P1 - problema teste de computacao - 4", controllerGeral.exibeProblema("P1"));
	}
	
	@Test
	public void testExibeObjetivo() {
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		assertEquals("O1 - GERAL - Objetivo da computacao - 5", controllerGeral.exibeObjetivo("O1"));
	}
	
	@Test
	public void testApagaAtividade() {
		try {
			controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
			controllerGeral.apagaAtividade("A1");
			controllerGeral.exibeAtividade("A1");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			 assertEquals("Atividade nao encontrada", e.getMessage());
		}
	}
	
	@Test
	public void testContaItensPendentes() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraItem("A1", "Monitoramento slack");
		assertEquals(1, controllerGeral.contaItensPendentes("A1"));
	}
	
	@Test
	public void testContaItensRealizados() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraItem("A1", "Monitoramento slack");
		controllerGeral.associaAtividade("FAK1", "A1");
		controllerGeral.executaAtividade("A1", 1, 10);
		assertEquals(1, controllerGeral.contaItensRealizados("A1"));
	}
	
	@Test
	public void testExibeAtividade() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		assertEquals("Atividade de computacao (MEDIO - Pode pegar virus)", controllerGeral.exibeAtividade("A1"));
	}
	
	@Test
	public void testAssociaEDesassociaAtividade() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.associaAtividade("FAK1", "A1");
		assertTrue(controllerGeral.desassociaAtividade("FAK1", "A1"));
		
	}
	
	@Test
	public void testExecutaAtividadeSemAssociacao() {
		try {
			controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
			controllerGeral.cadastraItem("A1", "Monitoramento slack");
			controllerGeral.executaAtividade("A1", 1, 10);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			 assertEquals("Atividade sem associacoes com pesquisas.", e.getMessage());
		}
	}
	
	@Test
	public void testCadastraResultado() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		assertEquals(1, controllerGeral.cadastraResultado("A1", "TESTE"));
	}
	
	@Test
	public void testRemoveResultado() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraResultado("A1", "TESTE");
		assertTrue(controllerGeral.removeResultado("A1", 1));
	}
	
	@Test
	public void testListaResultados() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraResultado("A1", "TESTE");
		controllerGeral.cadastraResultado("A1", "IRINEU");
		assertEquals("TESTE | IRINEU", controllerGeral.listaResultados("A1"));
		
	}
	
	@Test
	public void testGetDuracao() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		assertEquals(0, controllerGeral.getDuracao("A1"));
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
