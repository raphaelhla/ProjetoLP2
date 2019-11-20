package objetivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObjetivoTest {

	private Objetivo o1, o2;
	
	@BeforeEach
	public void criaObjetivos() {
		o1 = new Objetivo("GERAL", "TESTE", 2, 1, "O1");
		o2 = new Objetivo("ESPECIFICO", "TESTE", 4, 2, "O2");
	}
	
	@Test
	public void testToString() {
		assertEquals("GERAL - TESTE - 3", o1.toString());
	}

	@Test
	public void testToString2() {
		assertEquals("ESPECIFICO - TESTE - 6", o2.toString());
	}
	
	@Test
	public void testGetDescricao() {
		assertEquals("TESTE", o2.getDescricao());
	}
	
	@Test
	public void testBuscaDescricao() {
		assertTrue(o1.busca("TESTE"));
	}
	
	@Test
	public void testBuscaDescricao2() {
		assertFalse(o1.busca("oi"));
	}
	
	@Test
	public void testGetCodigo() {
		assertEquals("O1", o1.getCodigo());
	}
}
