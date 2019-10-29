package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisaControllerTest {

	private PesquisaController pc;
	@BeforeEach
	void inicializador() {
		pc = new PesquisaController();
		pc.cadastraPesquisa("Teste em ratos", "Biologia, Ciencias");
	}
	
	@Test
	void testCadastraPesquisaFeliz() {
		assertEquals("CIE1",pc.cadastraPesquisa("Experiencia em ratos.", "Ciencia biologica."));
	}
	
	@Test
	void testCadastraPesquisaJaExiste() {
		assertEquals("BIO2",pc.cadastraPesquisa("Teste em ratos", "Biologia, Ciencias"));
	}
	
}
