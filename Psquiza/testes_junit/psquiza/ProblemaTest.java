package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProblemaTest {

	private Problema p1,p2;
	@BeforeEach
	public void criaProblema() {
		p1 = new Problema("teste", 4);
		p2 = new Problema("irineu", 2);
	}

	@Test
	public void testToString() {
		assertEquals("teste - 4", p1.toString());
	}
	
	@Test
	public void testToString2() {
		assertEquals("irineu - 2", p2.toString());
	}
}
