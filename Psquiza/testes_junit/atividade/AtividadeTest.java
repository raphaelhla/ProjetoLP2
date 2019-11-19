package atividade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AtividadeTest {

	private Atividade a1, a2;

	@BeforeEach
	public void criaAtividade() {
		a1 = new Atividade("teste", "BAIXO", "descricao do teste", "A1");
		a2 = new Atividade("irineu", "MEDIO", "testenivel", "A2");
	}

	@Test
	public void testGetQtdItensPendentes() {
		a1.cadastraItem("Monitoramento slack");
		a1.cadastraItem("Monitoramento discord");
		a1.executaAtividade(2, 4);
		assertEquals(1, a1.getQtdItensPendentes());
	}

	@Test
	public void testGetQtdItensRealizados() {
		a1.cadastraItem("Monitoramento slack");
		a1.cadastraItem("Monitoramento discord");
		a1.executaAtividade(2, 4);
		assertEquals(1, a1.getQtdItensRealizados());
	}

	@Test
	public void testToString() {
		a2.cadastraItem("Monitoramento discord");

		assertEquals("irineu (MEDIO - testenivel) | PENDENTE - Monitoramento discord", a2.toString());
	}

	@Test
	public void testGetDescricao() {
		assertEquals("teste", a1.getDescricao());
	}

	@Test
	public void testGetDescricaoRisco() {
		assertEquals("testenivel", a2.getDescricaoRisco());
	}

	@Test
	public void testBuscaDescricao() {
		assertTrue(a1.buscaDescricao("teste"));
	}

	@Test
	public void testBuscaDescricao2() {
		assertFalse(a1.buscaDescricao("oi"));
	}

	@Test
	public void testBuscaDescricaoRisco() {
		assertTrue(a1.buscaDescricaoRisco("descricao"));
	}

	@Test
	public void testBuscaDescricaoRisco2() {
		assertFalse(a1.buscaDescricaoRisco("oi"));
	}

	@Test
	public void testNext() {
		a1.next(a2);
		assertEquals(a2, a1.getSusquente());
	}

	@Test
	public void testNextInvalido() {
		a1.next(a2);
		try {
			a1.next(a2);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atividade ja possui uma subsequente.", e.getMessage());
		}
	}

	@Test
	public void testNextInvalidoLoop() {
		a1.next(a2);
		try {
			a2.next(a1);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Criacao de loops negada.", e.getMessage());
		}
	}

	@Test
	public void testGetCodigo() {
		assertEquals("A1", a1.getCodigo());
	}

	@Test
	public void testGetNivelRisco() {
		assertEquals("BAIXO", a1.getNivelRisco());
	}

	@Test
	public void testPegaUltimo() {
		a1.next(a2);
		assertEquals("A2", a1.pegaUltimo());
	}

	@Test
	void testPegaUltimo2() {
		assertEquals("A1", a1.pegaUltimo());
	}

	@Test
	void testPegaMaiorRiscoAtividade() {
		Atividade a3 = new Atividade("oi", "ALTO", "BIU", "A3");
		a2.next(a3);
		a3.next(a1);
		assertEquals("A3", a2.pegaMaiorRiscoAtividade());
	}

	@Test
	public void testPegaProximo() {
		a1.next(a2);
		assertEquals("A2", a2.pegaProximo(0));
	}

	@Test
	public void testPegaProximo2() {
		a1.next(a2);
		assertEquals("A2", a1.pegaProximo(1));
	}

	@Test
	public void testExecutaAtividadeItemInexistente() {
		try {
			a1.executaAtividade(1, 6);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Item nao encontrado.", e.getMessage());
		}
	}

	@Test
	public void testExecutaAtividadeItemJaExecutado() {
		try {
			a1.cadastraItem("irineu");
			a1.executaAtividade(1, 6);
			a1.executaAtividade(1, 6);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Item ja executado.", e.getMessage());
		}
	}

	@Test
	public void testCadastraResultado() {
		assertEquals(1, a1.cadastraResultado("resultado"));
	}

	@Test
	public void testRemoveResultadoNaoEncontrado() {
		try {
			a1.cadastraResultado("resultado");
			a1.removeResultado(6);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Resultado nao encontrado.", e.getMessage());
		}
	}
	
	@Test
	public void testRemoveResultadoJaRemovido() {
		a1.cadastraResultado("resultado");
		a1.removeResultado(1);
		assertFalse(a1.removeResultado(1));
		
	}
}
