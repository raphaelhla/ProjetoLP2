package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AtividadeTest {

	private Atividade a1,a2;
	
	@BeforeEach
	public void criaAtividade() {
		a1 = new Atividade("teste", "BAIXO", "descricao do teste");
		a2 = new Atividade("irineu", "MEDIO", "testenivel");
	}
	
	@Test
	public void testGetQtdItensPendentes() {
		a1.cadastraItem("Monitoramento slack");
		a1.cadastraItem("Monitoramento discord");
		assertEquals(2, a1.getQtdItensPendentes());
	}
	
	@Test
	public void testGetQtdItensPendentes2() {
		assertEquals(0, a1.getQtdItensPendentes());
	}
	
	@Test
	public void testGetQtdItensRealizados() {
		a1.cadastraItem("Monitoramento slack");
		a1.cadastraItem("Monitoramento discord");
		assertEquals(0, a1.getQtdItensRealizados());
	}
	
	@Test
	public void testToString() {
		a2.cadastraItem("Monitoramento discord");
		
		assertEquals("irineu (MEDIO - testenivel) | PENDENTE - Monitoramento discord", a2.toString());
	}
}