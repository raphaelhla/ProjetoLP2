package atividade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AtividadeTest {

	private Atividade a1,a2;
	
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
}
