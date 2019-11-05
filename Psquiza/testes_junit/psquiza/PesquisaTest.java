package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
