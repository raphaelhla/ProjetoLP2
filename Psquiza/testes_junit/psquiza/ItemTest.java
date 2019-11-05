package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ItemTest {

	private Item i1;
	
	@BeforeEach
	public void criaItem() {
		i1= new Item("irineu");
		i1.executaItem(5);
	}
	
	@Test
	public void testToString() {
		assertEquals("REALIZADO - irineu", i1.toString());
	}
	
	@Test
	public void testGetStatus() {
		assertEquals("REALIZADO", i1.getStatus());
	}
	
	@Test
	public void testGetDuracao() {
		assertEquals(5,  i1.getDuracao());
	}
	
	@Test
	public void testGetDescricao() {
		assertEquals("irineu", i1.getDescricao());
	}
	
	@Test
	public void testBusca() {
		assertTrue(i1.busca("neu"));
	}
	
	@Test
	public void testBusca2() {
		assertFalse(i1.busca("xxx"));
	}
}
