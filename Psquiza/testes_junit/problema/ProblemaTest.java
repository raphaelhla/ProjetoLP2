package problema;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemaTest {

	private Problema p1,p2;
	@BeforeEach
	public void criaProblema() {
		p1 = new Problema("teste", 4, "P1");
		p2 = new Problema("irineu", 2, "P2");
	}

	@Test
	public void testToString() {
		assertEquals("teste - 4", p1.toString());
	}
	
	@Test
	public void testToString2() {
		assertEquals("irineu - 2", p2.toString());
	}
	
	@Test
	public void testBusca() {
		assertTrue(p2.busca("irineu"));
	}
	
	@Test
	public void testBusca2() {
		assertFalse(p2.busca("oi"));
	}
	
	@Test
	public void testGetDescricao() {
		assertEquals("irineu", p2.getDescricao());
	}
	@Test
	public void testGetCodigo() {
		assertEquals("P1", p1.getCodigo());
	}
}
