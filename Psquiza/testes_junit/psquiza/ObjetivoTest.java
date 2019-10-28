package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ObjetivoTest {

	private Objetivo o1, o2;
	
	@BeforeEach
	public void criaObjetivos() {
		o1 = new Objetivo("GERAL", "TESTE", 2, 1);
		o2 = new Objetivo("ESPECIFICO", "TESTE", 4, 2);
	}
	
	@Test
	public void testToString() {
		assertEquals("GERAL - TESTE - 3", o1.toString());
	}

	@Test
	public void testToString2() {
		assertEquals("ESPECIFICO - TESTE - 6", o2.toString());
	}
}
