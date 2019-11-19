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
	void criaController() {
		controllerGeral = new ControllerGeral();
	}

	@Test
	void testAlteraPesquisaEExibePesquisa() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.alteraPesquisa("FAK1", "DESCRICAO", "MUDOU");
		assertEquals("FAK1 - MUDOU - fake news", controllerGeral.exibePesquisa("FAK1"));
	}

	@Test
	void testPesquisaEhAtiva() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.encerraPesquisa("FAK1", "DESCONHECIDO");
		assertFalse(controllerGeral.pesquisaEhAtiva("FAK1"));
	}

	@Test
	void testPesquisaEhAtiva2() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.encerraPesquisa("FAK1", "DESCONHECIDO");
		controllerGeral.ativaPesquisa("FAK1");
		assertTrue(controllerGeral.pesquisaEhAtiva("FAK1"));
	}

	@Test
	void testAlteraPesquisadorEExibePesquisador() {
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		controllerGeral.alteraPesquisador("raphael@example.com", "NOME", "IRINEU");
		assertEquals("IRINEU (Estudante) - Estudante de computacao - raphael@example.com - https://teste.com",
				controllerGeral.exibePesquisador("raphael@example.com"));
	}

	@Test
	void testPesquisadorEhAtivo() {
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		controllerGeral.desativaPesquisador("raphael@example.com");
		controllerGeral.ativaPesquisador("raphael@example.com");
		assertTrue(controllerGeral.pesquisadorEhAtivo("raphael@example.com"));
	}

	@Test
	void testPesquisadorEhAtivo2() {
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		controllerGeral.desativaPesquisador("raphael@example.com");
		assertFalse(controllerGeral.pesquisadorEhAtivo("raphael@example.com"));
	}

	@Test
	void testApagaProblema() {
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
	void testApagaObjetivo() {
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
	void testExibeProblema() {
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		assertEquals("P1 - problema teste de computacao - 4", controllerGeral.exibeProblema("P1"));
	}

	@Test
	void testExibeObjetivo() {
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		assertEquals("O1 - GERAL - Objetivo da computacao - 5", controllerGeral.exibeObjetivo("O1"));
	}

	@Test
	void testApagaAtividade() {
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
	void testContaItensPendentes() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraItem("A1", "Monitoramento slack");
		assertEquals(1, controllerGeral.contaItensPendentes("A1"));
	}

	@Test
	void testContaItensRealizados() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraItem("A1", "Monitoramento slack");
		controllerGeral.associaAtividade("FAK1", "A1");
		controllerGeral.executaAtividade("A1", 1, 10);
		assertEquals(1, controllerGeral.contaItensRealizados("A1"));
	}

	@Test
	void testExibeAtividade() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		assertEquals("Atividade de computacao (MEDIO - Pode pegar virus)", controllerGeral.exibeAtividade("A1"));
	}

	@Test
	void testAssociaEDesassociaAtividade() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.associaAtividade("FAK1", "A1");
		assertTrue(controllerGeral.desassociaAtividade("FAK1", "A1"));

	}

	@Test
	void testExecutaAtividadeSemAssociacao() {
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
	void testCadastraResultado() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		assertEquals(1, controllerGeral.cadastraResultado("A1", "TESTE"));
	}

	@Test
	void testRemoveResultado() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraResultado("A1", "TESTE");
		assertTrue(controllerGeral.removeResultado("A1", 1));
	}

	@Test
	void testListaResultados() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraResultado("A1", "TESTE");
		controllerGeral.cadastraResultado("A1", "IRINEU");
		assertEquals("TESTE | IRINEU", controllerGeral.listaResultados("A1"));

	}

	@Test
	void testGetDuracao() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		assertEquals(0, controllerGeral.getDuracao("A1"));
	}

	@Test
	void testeBuscaGeral() {
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
	void testeBuscaGera2() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		assertEquals("", controllerGeral.busca("IRINEU"));
	}

	@Test
	void testeBuscaEspecifica() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		assertEquals("raphael@example.com: Estudante de computacao", controllerGeral.busca("computacao", 2));
	}

	@Test
	void testBuscaEspecificaNumeroResultadoNegativo() {
		try {
			controllerGeral.busca("irineu", -2);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Numero do resultado nao pode ser negativo", e.getMessage());
		}
	}

	@Test
	void testBuscaEspecificaNumeroResultadoInexiste() {
		try {
			controllerGeral.busca("irineu", 2);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Entidade nao encontrada.", e.getMessage());
		}
	}

	@Test
	void testeContaResultadosBusca() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		assertEquals(4, controllerGeral.contaResultadosBusca("computacao"));
	}

	@Test
	void testContaResultadosBuscaInexistente() {
		try {
			controllerGeral.contaResultadosBusca("irineu");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Nenhum resultado encontrado", e.getMessage());
		}
	}

	@Test
	void testAssociaProblemaFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		assertTrue(controllerGeral.associaProblema("FAK1", "P1"));
	}

	@Test
	void testAssociaProblemaTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		controllerGeral.associaProblema("FAK1", "P1");
		assertFalse(controllerGeral.associaProblema("FAK1", "P1"));
	}

	@Test
	void testDesassociaProblemaFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		controllerGeral.associaProblema("FAK1", "P1");
		assertTrue(controllerGeral.desassociaProblema("FAK1"));
	}

	@Test
	void testDesassociaProblemaTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraProblema("problema teste de computacao", 4);
		assertFalse(controllerGeral.desassociaProblema("FAK1"));
	}

	@Test
	void testAssociaObjetivoFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		assertTrue(controllerGeral.associaObjetivo("FAK1", "O1"));
	}

	@Test
	void testAssociaObjetivoTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.associaObjetivo("FAK1", "O1");
		assertFalse(controllerGeral.associaObjetivo("FAK1", "O1"));
	}

	@Test
	void testDesassociaObjetivoFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.associaObjetivo("FAK1", "O1");
		assertTrue(controllerGeral.desassociaObjetivo("FAK1", "O1"));
	}

	@Test
	void testDesassociaObjetivoTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		assertFalse(controllerGeral.desassociaObjetivo("FAK1", "O1"));
	}

	@Test
	void testListaPesquisas() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisa("teste", "teste");
		assertEquals("TES1 - teste - teste | FAK1 - Pesquisa do uso da computacao no combate a fake news - fake news",
				controllerGeral.listaPesquisas("PROBLEMA"));
	}

	@Test
	void testAssociaPesquisadorFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Weslley", "estudante", "Estudante de computacao", "weslley@email.com",
				"https://urlfoto.com");
		assertTrue(controllerGeral.associaPesquisador("FAK1", "weslley@email.com"));

	}

	@Test
	void testAssociaPesquisadorTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Weslley", "estudante", "Estudante de computacao", "weslley@email.com",
				"https://urlfoto.com");
		controllerGeral.associaPesquisador("FAK1", "weslley@email.com");
		assertFalse(controllerGeral.associaPesquisador("FAK1", "weslley@email.com"));
	}

	@Test
	void testDesassociaPesquisadorFeliz() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Weslley", "estudante", "Estudante de computacao", "weslley@email.com",
				"https://urlfoto.com");
		controllerGeral.associaPesquisador("FAK1", "weslley@email.com");
		assertTrue(controllerGeral.desassociaPesquisador("FAK1", "weslley@email.com"));
	}

	@Test
	void testDesassociaPesquisadorTriste() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Weslley", "estudante", "Estudante de computacao", "weslley@email.com",
				"https://urlfoto.com");
		assertFalse(controllerGeral.desassociaPesquisador("FAK1", "weslley@email.com"));
	}

	@Test
	void testCadastraEspecialidadeProfessorFeliz() {
		controllerGeral.cadastraPesquisador("Gaudencio", "professor", "Professor de computacao", "gaudencio@email.com",
				"https://urlfoto.com");
		controllerGeral.cadastraEspecialidadeProfessor("gaudencio@email.com", "computacao", "LP", "31/12/2025");
		assertEquals(
				"Gaudencio (professor) - Professor de computacao - gaudencio@email.com - https://urlfoto.com - computacao - LP - 31/12/2025",
				controllerGeral.listaPesquisadores("PROFESSORA"));
	}

	@Test
	void testCadastraEspecialidadeAlunoFeliz() {
		controllerGeral.cadastraPesquisador("Weslley", "estudante", "Estudante de computacao", "weslley@email.com",
				"https://urlfoto.com");
		controllerGeral.cadastraEspecialidadeAluno("weslley@email.com", 6, 8.0);
		assertEquals(
				"Weslley (estudante) - Estudante de computacao - weslley@email.com - https://urlfoto.com - 6o SEMESTRE - 8.0",
				controllerGeral.listaPesquisadores("ALUNA"));
	}

	@Test
	void testListaPesquisadoresTipoAluno() {
		controllerGeral.cadastraPesquisador("Weslley", "estudante", "Estudante de computacao", "weslley@email.com",
				"https://urlfoto.com");
		controllerGeral.cadastraEspecialidadeAluno("weslley@email.com", 6, 8.0);
		controllerGeral.cadastraPesquisador("Jao", "Estudante", "Estudante de eletrica", "jao@email.com",
				"https://urlfoto.com");
		assertEquals(
				"Weslley (estudante) - Estudante de computacao - weslley@email.com - https://urlfoto.com - 6o SEMESTRE - 8.0",
				controllerGeral.listaPesquisadores("ALUNA"));
	}

	@Test
	void testListaPesquisadoresTipoProfessor() {
		controllerGeral.cadastraPesquisador("Gaudencio", "professor", "Professor de computacao", "gaudencio@email.com",
				"https://urlfoto.com");
		controllerGeral.cadastraEspecialidadeProfessor("gaudencio@email.com", "computacao", "LP", "31/12/2025");
		controllerGeral.cadastraPesquisador("Livia", "Professora", "Professora de computacao", "livia@email.com",
				"https://urlfoto.com");
		assertEquals(
				"Gaudencio (professor) - Professor de computacao - gaudencio@email.com - https://urlfoto.com - computacao - LP - 31/12/2025",
				controllerGeral.listaPesquisadores("PROFESSORA"));
	}

	@Test
	void testListaPesquisadoresTipoExterno() {
		controllerGeral.cadastraPesquisador("Gaudencio", "externo", "Professor de computacao", "gaudencio@email.com",
				"https://urlfoto.com");
		controllerGeral.cadastraPesquisador("Livia", "externo", "Professora de computacao", "livia@email.com",
				"https://urlfoto.com");
		assertEquals(
				"Gaudencio (externo) - Professor de computacao - gaudencio@email.com - https://urlfoto.com | Livia (externo) - Professora de computacao - livia@email.com - https://urlfoto.com",
				controllerGeral.listaPesquisadores("EXTERNO"));
	}
	
	@Test
	void test() {
	}
	
	@Test
	void testDefineProximaAtividade() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		controllerGeral.defineProximaAtividade("A1", "A2");
	}
	
	@Test
	void testTiraProximaAtividade() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		controllerGeral.tiraProximaAtividade("A1");
	}
	
	@Test
	void testContaProximo1() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		controllerGeral.defineProximaAtividade("A1", "A2");
		assertTrue(1 == controllerGeral.contaProximos("A1"));
	}
	
	@Test
	void testContaProximo2() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		assertTrue(0 == controllerGeral.contaProximos("A1"));
	}
	
	@Test
	void testPegaProximo1() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		controllerGeral.defineProximaAtividade("A1", "A2");
		assertEquals("A2",controllerGeral.pegaProximo("A1", 1));
	}
		
	@Test
	void testPegaMaiorRiscoAtividades() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		controllerGeral.defineProximaAtividade("A1", "A2");
		assertEquals("A2",controllerGeral.pegaMaiorRiscoAtividades("A1"));
	}
	
	@Test
	void testProximaAtividade() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.configuraEstrategia("MAIOR_RISCO");
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		controllerGeral.associaAtividade("FAK1", "A1");
		controllerGeral.associaAtividade("FAK1", "A2");
		controllerGeral.cadastraItem("A1", "IRIJOW");
		controllerGeral.cadastraItem("A2", "IRINEU");
		assertEquals("A2", controllerGeral.proximaAtividade("FAK1"));
	}
	
	@Test
	void testProximaAtividade2() {
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraAtividade("Atividade de pesquisa em peixe baiacu", "ALTO", "Pode morrer");
		controllerGeral.associaAtividade("FAK1", "A1");
		controllerGeral.associaAtividade("FAK1", "A2");
		controllerGeral.cadastraItem("A1", "IRIJOW");
		controllerGeral.cadastraItem("A2", "IRINEU");
		assertEquals("A1", controllerGeral.proximaAtividade("FAK1"));
	}
}
