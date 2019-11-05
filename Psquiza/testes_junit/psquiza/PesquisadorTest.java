package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisadorTest {

	private Pesquisador p1;
	
	@BeforeEach
	public void criaPesquisador() {
		p1 = new Pesquisador("Raphael", "Estudante", "estudante de computacao", "raphael@example.com", "https://teste.com");
	}

	@Test
	public void testBuscaDescricao() {
		assertTrue(p1.busca("computacao"));
	}
	
	@Test
	public void testBusca() {
		assertFalse(p1.busca("oi"));
	}
	
	@Test
	public void testGetBiografia() {
		assertEquals("estudante de computacao", p1.getBiografia());
	}
	
	@Test
	public void testGetEmail() {
		assertEquals("raphael@example.com", p1.getEmail());
	}
}
