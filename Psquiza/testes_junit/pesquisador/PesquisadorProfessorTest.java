package pesquisador;

import static org.junit.Assert.assertEquals;
import org.junit.jupiter.api.Test;
import pesquisador.PesquisadorProfessor;



class PesquisadorProfessorTest {
	

	@Test
	void testPesquisadorProfessor() {
		PesquisadorProfessor pf = new PesquisadorProfessor("ufcg", "splab", "01/01/2020");
		assertEquals(" - ufcg - splab - 01/01/2020", pf.toString());
	}

	@Test
	void testGetFormacao() {
		PesquisadorProfessor pf = new PesquisadorProfessor("ufcg", "splab", "01/01/2020");
		assertEquals("ufcg", pf.getFormacao());
		
		
	}

	@Test
	void testGetUnidadeAlocada() {
		PesquisadorProfessor pf = new PesquisadorProfessor("ufcg", "splab", "01/01/2020");
		assertEquals("splab", pf.getUnidadeAlocada());
	}

	@Test
	void testGetDataContratacao() {
		PesquisadorProfessor pf = new PesquisadorProfessor("ufcg", "splab", "01/01/2020");
		assertEquals("01/01/2020", pf.getDataContratacao());
	}

	@Test
	void testToString() {
		PesquisadorProfessor pf = new PesquisadorProfessor("ufcg", "splab", "01/01/2020");
		assertEquals(" - ufcg - splab - 01/01/2020", pf.toString());
	}

	@Test
	void testAlteraPesquisador() {
		PesquisadorProfessor pf = new PesquisadorProfessor("ufcg", "splab", "01/01/2020");
		pf.alteraPesquisador("FORMACAO", "ufpe");
		assertEquals(" - ufpe - splab - 01/01/2020", pf.toString());
		
	}

}
