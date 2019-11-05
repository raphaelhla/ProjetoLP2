package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisaControllerTest {

	private PesquisaController pc;

	@BeforeEach
	void inicializador() {
		pc = new PesquisaController();
		pc.cadastraPesquisa(
				"Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.",
				"eleicao");
	}

	@Test
	void testCadastraPesquisaFeliz() {
		assertEquals("CIE1", pc.cadastraPesquisa("Experiencia em ratos.", "Ciencias biologicas."));
	}

	@Test
	void testCadastraPesquisaJaExiste() {
		assertEquals("ELE2", pc.cadastraPesquisa(
				"Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.",
				"eleicao"));
	}

	@Test
	void testAlteraDescricaoPesquisa() {
		pc.alteraPesquisa("ELE1", "DESCRICAO", "Nova descricao.");
		assertEquals("ELE1 - Nova descricao. - eleicao", pc.exibePesquisa("ELE1"));

	}

	@Test
	void testAlteraCampoPesquisa() {
		pc.alteraPesquisa("ELE1", "CAMPO", "Novo campo.");
		assertEquals(
				"ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - Novo campo.",
				pc.exibePesquisa("ELE1"));

	}

	@Test
	void testAlteraPesquisaDesativada() {
		try {
			pc.encerraPesquisa("ELE1", "Teste");
			pc.alteraPesquisa("ELE1", "DESCRICAO", "Nova descricao.");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testAlteraPesquisaAtivadaAtributoInexistente() {
		try {
			pc.alteraPesquisa("ELE1", "nao existe", "Novo valor.");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testAlteraPesquisaInexistente() {
		try {
			pc.alteraPesquisa("nao existe", "DESCRICAO", "Novo valor.");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testEncerraPesquisa() {
		assertTrue(true == pc.ehAtiva("ELE1"));
		pc.encerraPesquisa("ELE1", "teste");
		assertTrue(false == pc.ehAtiva("ELE1"));
	}

	@Test
	void testEncerraPesquisaJaEncerrada() {
		pc.encerraPesquisa("ELE1", "Teste");
		try {
			pc.encerraPesquisa("ELE1", "Teste");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testAtivaPesquisa() {
		pc.encerraPesquisa("ELE1", "teste");
		pc.ativaPesquisa("ELE1");
		assertTrue(true == pc.ehAtiva("ELE1"));
	}

	@Test
	void testAtivaPesquisaJaAtivada() {
		try {
			pc.ativaPesquisa("ELE1");
		} catch (IllegalArgumentException e) {

		}
	}

	@Test
	void testExibePesquisa() {
		assertEquals(
				"ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao",
				pc.exibePesquisa("ELE1"));
	}

	@Test
	public void testBusca() {
		assertEquals("", pc.busca("slack"));
	}

	@Test
	public void testBusca2() {
		assertEquals(
				"ELE1: Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. | ELE1: Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.",
				pc.busca("eleic"));
	}
}
