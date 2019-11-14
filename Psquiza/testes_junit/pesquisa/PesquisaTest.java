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

import objetivo.Objetivo;
import problema.Problema;

class PesquisaTest {

	private Pesquisa pesquisaTest;

	@BeforeEach
	void inicializador() {
		pesquisaTest = new Pesquisa(
				"Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.",
				"eleicao","ELE1");
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
		assertEquals("Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao", pesquisaTest.toString());
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
	public void testAssociaProblema() {
		assertTrue(pesquisaTest.associaProblema("P1", new Problema("irineu", 3)));
	}
	
	@Test
	public void testAssociaProblema2() {
		pesquisaTest.associaProblema("P1", new Problema("irineu", 3));
		assertFalse(pesquisaTest.associaProblema("P1", new Problema("irineu", 3)));
	}
	
	@Test
	public void testAssociaProblemaPesquisaDesativada() {
		try {
			pesquisaTest.desativaPesquisa();
			pesquisaTest.associaProblema("P1", new Problema("irineu", 3));
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			 assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}
	
	@Test
	public void testAssociaProblemaJaAssociada() {
		try {
			pesquisaTest.associaProblema("P1", new Problema("irineu", 3));
			pesquisaTest.associaProblema("P2", new Problema("teste", 3));
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
		pesquisaTest.associaProblema("P1", new Problema("irineu", 3));
		assertTrue(pesquisaTest.desassociaProblema());
	}
	
	@Test
	public void testAssociaObjetivoPesquisaDesativada() {
		try {
			pesquisaTest.desativaPesquisa();
			pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3));
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			 assertEquals("Pesquisa desativada.", e.getMessage());
		}
	}
	
	@Test
	public void testAssociaObjetivoFeliz() {
		assertTrue(pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3)));
	}
	
	@Test
	public void testAssociaObjetivoTriste() {
		pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3));
		assertFalse(pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3)));
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
		pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3));
		assertTrue(pesquisaTest.desassociaObjetivo("O1"));
	}
	
	@Test
	public void testGetAssociacaoObjetivo() {
		pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3));
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
		pesquisaTest.associaObjetivo("O1", new Objetivo("GERAL", "irineu", 4, 3));
		assertEquals(1, pesquisaTest.getQtdObjetivo());
	}
	
	@Test
	public void testTemProblemaAssociado() {
		assertFalse(pesquisaTest.getTemProblemaAssociado());
	}
	
	@Test
	public void testTemProblemaAssociado2() {
		pesquisaTest.associaProblema("P1", new Problema("irineu", 3));
		assertTrue(pesquisaTest.getTemProblemaAssociado());
	}
	
	@Test
	public void testGetCodigoProblema() {
		pesquisaTest.associaProblema("P1", new Problema("irineu", 3));
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
		assertEquals("[Teste - Alok, Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao]", lista.toString());
	}
}
