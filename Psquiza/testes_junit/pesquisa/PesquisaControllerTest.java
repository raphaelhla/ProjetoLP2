package pesquisa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import objetivo.Objetivo;
import problema.Problema;

class PesquisaControllerTest {

	private PesquisaController pc;

	@BeforeEach
	void inicializador() {
		pc = new PesquisaController();
		pc.cadastraPesquisa(
				"Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras.",
				"eleicao");
		pc.cadastraPesquisa("TESTE", "Irineu");
		pc.cadastraPesquisa("Bob jow", "Irineuliston");
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
				"ELE1: Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. | ELE1: eleicao",
				pc.busca("eleic"));
	}
	
	@Test
	public void testAssociaProblemaFeliz() {
		assertTrue(pc.associaProblema("ELE1", "P1", new Problema("teste", 3)));
	}
	
	@Test
	public void testAssociaProblemaTriste() {
		Problema problema =  new Problema("teste", 3);
		pc.associaProblema("ELE1", "P1", problema);
		assertFalse(pc.associaProblema("ELE1", "P1", problema));
	}
	
	@Test
	public void testDesassociaProblemaFeliz() {
		pc.associaProblema("ELE1", "P1", new Problema("teste", 3));
		assertTrue(pc.desassociaProblema("ELE1"));
	}
	
	@Test
	public void testDesassociaProblemaTriste() {
		assertFalse(pc.desassociaProblema("ELE1"));
	}
	
	@Test
	public void testAssociaObjetivoFeliz() {
		assertTrue(pc.associaProblema("ELE1", "P1", new Problema("teste", 3)));
	}
	
	@Test
	public void testAssociaObjetivoTriste() {
		Objetivo objetivo =  new Objetivo("ESPECIFICO", "teste", 2, 3);
		pc.associaObjetivo("ELE1", "O1", objetivo);
		assertFalse(pc.associaObjetivo("ELE1", "O1", objetivo));
	}
	
	@Test
	public void testDesassociaObjetivoFeliz() {
		pc.associaObjetivo("ELE1", "O1", new Objetivo("ESPECIFICO", "teste", 3, 2));
		assertTrue(pc.desassociaObjetivo("ELE1", "O1"));
	}
	
	@Test
	public void testDesassociaObjetivoTriste() {
		assertFalse(pc.desassociaObjetivo("ELE1", "O1"));
	}
	
	@Test
	public void testVerificaAssociacaoObjetivo() {
		try {
			Objetivo objetivo =  new Objetivo("ESPECIFICO", "teste", 2, 3);
			pc.associaObjetivo("ELE1", "O1", objetivo);
			pc.associaObjetivo("IRI1", "O1", objetivo);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			 assertEquals("Objetivo ja associado a uma pesquisa.", e.getMessage());
		}
	}
	
	@Test
	public void testListaPesquisasValorInvalido() {
		try {
			pc.listaPesquisas("irineu");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			 assertEquals("Valor invalido da ordem", e.getMessage());
		}
	}
	
	@Test
	public void testListaPesquisasPorPesquisa() {
		assertEquals("IRI2 - Bob jow - Irineuliston | IRI1 - TESTE - Irineu | ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao", pc.listaPesquisas("PESQUISA"));
	}
	
	@Test
	public void testListaPesquisasPorObjetivo() {
		Objetivo objetivo =  new Objetivo("ESPECIFICO", "teste", 2, 3);
		Objetivo objetivo2 =  new Objetivo("ESPECIFICO", "irineu", 5, 3);
		pc.associaObjetivo("ELE1", "O1", objetivo);
		pc.associaObjetivo("IRI1", "O2", objetivo2);
		assertEquals("IRI1 - TESTE - Irineu | ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao | IRI2 - Bob jow - Irineuliston", pc.listaPesquisas("OBJETIVOS"));
	}
	
	@Test
	public void testListaPesquisasPorProblemas() {
		pc.cadastraPesquisa("ola tudo bem", "mensagem de ola");
		pc.cadastraPesquisa("pesquisa do meio ambiente", "ambiente");
		pc.cadastraPesquisa("java", "programador");
		Problema problema =  new Problema("teste", 3);
		Problema problema2 =  new Problema("teste do teste", 5);
		Problema problema3 = new Problema("teste2", 2);
		pc.associaProblema("ELE1", "P1", problema);
		pc.associaProblema("IRI1", "P1", problema);
		pc.associaProblema("IRI2", "P2", problema2);
		pc.associaProblema("PRO1", "P3", problema3);
		
		assertEquals("PRO1 - java - programador | IRI2 - Bob jow - Irineuliston | IRI1 - TESTE - Irineu | ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao | MEN1 - ola tudo bem - mensagem de ola | AMB1 - pesquisa do meio ambiente - ambiente", pc.listaPesquisas("PROBLEMA"));
	}
	

	@Test
	public void testListaPesquisasPorProblemas2() {
		Problema problema =  new Problema("teste", 3);
		pc.associaProblema("ELE1", "P1", problema);
		
		assertEquals("ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao | IRI2 - Bob jow - Irineuliston | IRI1 - TESTE - Irineu", pc.listaPesquisas("PROBLEMA"));
	}
	
	@Test
	public void testListaPesquisasPorProblemas3() {
		Problema problema =  new Problema("teste", 3);
		pc.associaProblema("IRI1", "P1", problema);
		assertEquals("IRI1 - TESTE - Irineu | IRI2 - Bob jow - Irineuliston | ELE1 - Avaliacao de modelos preditivos para a extracao de caracteristicas significativas nas eleicoes brasileiras. - eleicao", pc.listaPesquisas("PROBLEMA"));
	}
}
