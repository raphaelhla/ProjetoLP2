package pesquisador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisadorTest {

	private Pesquisador p1;
	
	@BeforeEach
	public void criaPesquisador() {
		p1 = new Pesquisador("Raphael", "Estudante", "estudante de computacao", "raphael@example.com", "https://teste.com");
	}

	@Test
	public void testToString() {
		assertEquals("Raphael (Estudante) - estudante de computacao - raphael@example.com - https://teste.com", p1.toString());
	}
	
	@Test
	public void testAtivaPesquisador() {
		p1.desativaPesquisador();
		p1.ativaPesquisador();
		assertEquals("ativo", p1.getStatus());
	}
	
	@Test
	public void testDesativaPesquisador() {
		p1.desativaPesquisador();
		assertEquals("inativo", p1.getStatus());
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
	
	@Test
	public void testGetFuncao() {
		assertEquals("Estudante", p1.getFuncao());
	}
}
