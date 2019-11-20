package pesquisa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import atividade.Atividade;
import atividade.Item;
import objetivo.Objetivo;
import pesquisador.Pesquisador;
import problema.Problema;
import psquiza.ControllerGeral;

class PesquisaTest {

	private Pesquisa pesquisaTest;

	@BeforeEach
	void inicializador() {
		pesquisaTest = new Pesquisa(
				"Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.",
				"eleicao", "ELE1");
	}

	@Test
	void testGetDescricao() {
		assertEquals(
				"Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.",
				pesquisaTest.getDescricao());
	}

	@Test
	void testSetDescricao() {
		pesquisaTest.setDescricao("Nova descricao.");
		assertEquals("Nova descricao.", pesquisaTest.getDescricao());
	}

	@Test
	void testGetCampoDeInteresse() {
		assertEquals("eleicao", pesquisaTest.getCampoDeInteresse());
	}

	@Test
	void testSetCampoDeInteresse() {
		pesquisaTest.setCampoDeInteresse("Novo campo de interesse.");
		assertEquals("Novo campo de interesse.", pesquisaTest.getCampoDeInteresse());
	}

	@Test
	void testGetStatusPesquisaTrue() {
		assertTrue(true == pesquisaTest.getStatusPesquisa());
	}

	@Test
	void testGetStatusPesquisaFalse() {
		pesquisaTest.desativaPesquisa();
		assertTrue(false == pesquisaTest.getStatusPesquisa());
	}

	@Test
	void testToString() {
		assertEquals(
				"ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao",
				pesquisaTest.toString());
	}

	@Test
	void testAtivaPesquisa() {
		pesquisaTest.desativaPesquisa();
		pesquisaTest.ativaPesquisa();
		assertTrue(true == pesquisaTest.getStatusPesquisa());
	}

	@Test
	void testDesativaPesquisa() {
		pesquisaTest.desativaPesquisa();
		assertTrue(false == pesquisaTest.getStatusPesquisa());
	}

	@Test
	public void testBuscaDescricao() {
		assertTrue(pesquisaTest.buscaDescricao("preditivos"));
	}

	@Test
	public void testBuscaDescricao2() {
		assertFalse(pesquisaTest.buscaDescricao("oi"));
	}

	@Test
	public void testBuscaCampoInteresse() {
		assertTrue(pesquisaTest.buscaCampoInteresse("eleicao"));
	}

	@Test
	public void testBuscaCampoInteresse2() {
		assertFalse(pesquisaTest.buscaCampoInteresse("oi"));
	}

	@Test
	public void testAssociaAtividade() {
		Atividade atividade = new Atividade("teste", "ALTO", "TESTE", "A1");
		pesquisaTest.associaAtividade("A1", atividade);
		assertFalse(pesquisaTest.associaAtividade("A1", atividade));
	}

	@Test
	public void testAssociaAtividadePesquisaDesativada() {
		try {
			Atividade atividade = new Atividade("teste", "ALTO", "TESTE", "A1");
			pesquisaTest.desativaPesquisa();
			pesquisaTest.associaAtividade("A1", atividade);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}

	@Test
	public void testDesassociaAtividadePesquisaDesativada() {
		try {
			pesquisaTest.desativaPesquisa();
			pesquisaTest.desassociaAtividade("A1");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}

	@Test
	public void testDesassociaAtividade() {
		assertFalse(pesquisaTest.desassociaAtividade("A2"));
	}

	@Test
	public void testAtividadeEstaAssociada() {
		assertFalse(pesquisaTest.atividadeEstaAssociada("A1"));
	}

	@Test
	public void testAssociaProblema() {
		assertTrue(pesquisaTest.associaProblema("P1", new Problema("irineu", 3, "P1")));
	}

	@Test
	public void testAssociaProblema2() {
		pesquisaTest.associaProblema("P1", new Problema("irineu", 3, "P1"));
		assertFalse(pesquisaTest.associaProblema("P1", new Problema("irineu", 3, "P1")));
	}

	@Test
	public void testAssociaProblemaPesquisaDesativada() {
		try {
			pesquisaTest.desativaPesquisa();
			pesquisaTest.associaProblema("P1", new Problema("irineu", 3, "P1"));
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}

	@Test
	public void testAssociaProblemaJaAssociada() {
		try {
			pesquisaTest.associaProblema("P1", new Problema("irineu", 3, "P1"));
			pesquisaTest.associaProblema("P2", new Problema("teste", 3, "P1"));
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa ja associada a um problema.", e.getMessage());
		}
	}

	@Test
	public void testDesassociaProblemaPesquisaDesativada() {
		try {
			pesquisaTest.desativaPesquisa();
			pesquisaTest.desassociaProblema();
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}

	@Test
	public void testDesassociaProblemaPesquisaNaoAssociada() {
		assertFalse(pesquisaTest.desassociaProblema());
	}

	@Test
	public void testDesassociaProblemaFeliz() {
		pesquisaTest.associaProblema("P1", new Problema("irineu", 3, "P1"));
		assertTrue(pesquisaTest.desassociaProblema());
	}

	@Test
	public void testAssociaObjetivoPesquisaDesativada() {
		try {
			pesquisaTest.desativaPesquisa();
			pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3, "O1"));
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}

	@Test
	public void testAssociaObjetivoFeliz() {
		assertTrue(pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3, "O1")));
	}

	@Test
	public void testAssociaObjetivoTriste() {
		pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3, "O1"));
		assertFalse(pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3, "O1")));
	}

	@Test
	public void testDesassociaObjetivoPesquisaDesativada() {
		try {
			pesquisaTest.desativaPesquisa();
			pesquisaTest.desassociaObjetivo("P1");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}

	@Test
	public void testDesassociaObjetivoPesquisaNaoAssociada() {
		assertFalse(pesquisaTest.desassociaObjetivo("O1"));
	}

	@Test
	public void testDesassociaObjetivoFeliz() {
		pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3, "O1"));
		assertTrue(pesquisaTest.desassociaObjetivo("O1"));
	}

	@Test
	public void testGetAssociacaoObjetivo() {
		pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3, "O1"));
		assertTrue(pesquisaTest.getAssociacaoObjetivo("O1"));
	}

	@Test
	public void testGetAssociacaoObjetivo2() {
		assertFalse(pesquisaTest.getAssociacaoObjetivo("O1"));
	}

	@Test
	public void testGetCodigo() {
		assertEquals("ELE1", pesquisaTest.getCodigo());
	}

	@Test
	public void testGetQtdObjetivo() {
		pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3, "O1"));
		assertEquals(1, pesquisaTest.getQtdObjetivo());
	}

	@Test
	public void testTemProblemaAssociado() {
		assertFalse(pesquisaTest.getTemProblemaAssociado());
	}

	@Test
	public void testTemProblemaAssociado2() {
		pesquisaTest.associaProblema("P1", new Problema("irineu", 3, "P1"));
		assertTrue(pesquisaTest.getTemProblemaAssociado());
	}

	@Test
	public void testGetCodigoProblema() {
		pesquisaTest.associaProblema("P1", new Problema("irineu", 3, "P1"));
		assertEquals("P1", pesquisaTest.getCodigoProblema());
	}

	@Test
	public void testGetCodigoProblemaSemProblema() {
		try {
			pesquisaTest.getCodigoProblema();
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa sem problema associado.", e.getMessage());
		}
	}

	@Test
	public void testCompareTo() {
		List<Pesquisa> lista = new ArrayList<>();
		lista.add(pesquisaTest);
		lista.add(new Pesquisa("Teste", "Alok", "ALO1"));
		Collections.sort(lista);
		assertEquals(
				"[ALO1 - Teste - Alok, ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao]",
				lista.toString());
	}

	@Test
	void testEstrategiaMaisAntiga() {
		Atividade atividade = new Atividade("teste", "ALTO", "IRI", "A1");
		Atividade atividade2 = new Atividade("ooooo", "MEDIO", "KKKK", "A2");
		atividade2.cadastraItem("oooolfldl,fdlf");
		atividade.cadastraItem("testando123");
		atividade.cadastraItem("llllll");
		atividade.executaAtividade(2, 7);
		atividade.executaAtividade(1, 10);
		pesquisaTest.associaAtividade("A1", atividade);
		pesquisaTest.associaAtividade("A2", atividade2);
		assertEquals("A2", pesquisaTest.estrategiaMaisAntiga());
	}

	@Test
	void testEstrategiaMaiorDuracao() {
		Atividade atividade = new Atividade("teste", "ALTO", "IRI", "A1");
		Atividade atividade2 = new Atividade("ooooo", "MEDIO", "KKKK", "A2");
		atividade.cadastraItem("testando123");
		atividade.cadastraItem("llllll");
		atividade2.cadastraItem("oooopppp");
		atividade2.cadastraItem("poxa");
		atividade2.executaAtividade(1, 10);
		atividade.executaAtividade(2, 7);
		pesquisaTest.associaAtividade("A1", atividade);
		pesquisaTest.associaAtividade("A2", atividade2);
		assertEquals("A2", pesquisaTest.estrategiaMaiorDuracao());
	}

	@Test
	void testEstrategiaMenosPendencias() {
		Atividade atividade = new Atividade("teste", "ALTO", "IRI", "A1");
		Atividade atividade2 = new Atividade("ooooo", "MEDIO", "KKKK", "A2");
		atividade.cadastraItem("testando123");
		atividade.cadastraItem("llllll");
		atividade2.cadastraItem("ppp");
		atividade.executaAtividade(2, 7);
		pesquisaTest.associaAtividade("A1", atividade);
		pesquisaTest.associaAtividade("A2", atividade2);
		assertEquals("A1", pesquisaTest.estrategiaMenosPendencias());
	}

	@Test
	void testEstrategiaMaiorRisco() {
		Atividade atividade = new Atividade("teste", "ALTO", "IRI", "A1");
		Atividade atividade2 = new Atividade("ooooo", "MEDIO", "KKKK", "A2");
		atividade.cadastraItem("testando123");
		atividade.cadastraItem("llllll");
		atividade2.cadastraItem("ppp");
		atividade.executaAtividade(2, 7);
		pesquisaTest.associaAtividade("A1", atividade);
		pesquisaTest.associaAtividade("A2", atividade2);
		assertEquals("A1", pesquisaTest.estrategiaMaiorRisco());
	}

	@Test
	public void testAssociaPesquisadorPesquisaDesativada() {
		try {
			Pesquisador pesquisador = new Pesquisador("biu", "aluno", "teste", "biu@email.com", "https://teste");
			pesquisaTest.desativaPesquisa();
			pesquisaTest.associaPesquisador("biu@email.com", pesquisador);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}

	@Test
	public void testDesassociaPesquisadorPesquisaDesativada() {
		try {
			pesquisaTest.desativaPesquisa();
			pesquisaTest.desassociaPesquisador("biu@email.com");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}

	@Test
	public void testExibeResumoPesquisa() {
		ControllerGeral cg = new ControllerGeral();
		Pesquisa pesquisa = new Pesquisa("apenas um teste", "teste de junit", "TES1");
		cg.cadastraPesquisa("apenas um teste", "teste de junit");
		Pesquisador pesquisador = new Pesquisador("biu", "computação", "teste", "biu@email.com", "https://teste");
		cg.cadastraPesquisador("biu", "computação", "teste", "biu@email.com", "https://teste");
		cg.associaPesquisador("TES1", "biu@email.com");
		Problema problema = new Problema("problema de teste", 2, "P1");
		cg.cadastraProblema("problema de teste", 2);
		cg.associaProblema("TES1", "P1");
		Objetivo objetivo = new Objetivo("GERAL", "descrição teste", 2, 2, "O1");
		cg.cadastraObjetivo("GERAL", "descrição teste", 2, 2);
		cg.associaObjetivo("TES1", "O1");
		Atividade atividade = new Atividade("descrição teste", "BAIXO", "descrição risco teste", "A1");
		cg.cadastraAtividade("descrição teste", "BAIXO", "descrição risco teste");
		cg.associaAtividade("TES1", "A1");
		Item item = new Item("descrição teste");
		cg.cadastraItem("A1", "descrição teste");

		assertEquals("- Pesquisa: TES1 - apenas um teste - teste de junit" + System.lineSeparator()
				+ "    - Pesquisadores:" + System.lineSeparator()
				+ "	    - biu (externo) - computação - biu@email.com - https://teste" + System.lineSeparator()
				+ "    - Problema:" + System.lineSeparator() + "	    - P1 - problema de teste - 2"
				+ System.lineSeparator() + "    - Objetivos:" + System.lineSeparator()
				+ "	    - O1 - tipo teste - descrição teste - 2" + System.lineSeparator() + "    - Atividades:"
				+ System.lineSeparator() + "		- descrição teste (baixo - descrição risco teste)"
				+ System.lineSeparator() + "	        - PENDENTE - ITEM1", pesquisa.exibeResumoPesquisa());

	}

	@Test
	public void testExibeResultadoPesquisa() {
		ControllerGeral cg = new ControllerGeral();
		Pesquisa pesquisa = new Pesquisa("apenas um teste", "teste de junit", "TES1");
		cg.cadastraPesquisa("apenas um teste", "teste de junit");
		Atividade atividade = new Atividade("descrição teste", "BAIXO", "descrição risco teste", "A1");
		cg.cadastraAtividade("descrição teste", "BAIXO", "descrição risco teste");
		cg.associaAtividade("TES1", "A1");
		Item item = new Item("descrição teste");
		cg.cadastraItem("A1", "descrição teste");
		item.executaItem(5);

		assertEquals("- Pesquisa: TES1 - apenas um teste - teste de junit" + System.lineSeparator()
				+ "    - Resultados:" + System.lineSeparator() + "        - descrição teste" + System.lineSeparator()
				+ "            - ITEM1 - 5" + System.lineSeparator()
				+ "            - Ocorreu sem nenhum problema aparente o monitoramento de chats dos alunos de computacao do primeiro periodo em diversas plataformas.",
				pesquisa.exibeResultadoPesquisa());

	}

}
