package pesquisador;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import pesquisador.PesquisadorAluno;

import org.junit.jupiter.api.Test;

class PesquisadorAlunoTest {

	@Test
	void testPesquisadorAluno() {
		PesquisadorAluno pa = new PesquisadorAluno(2, 7.0);
		assertEquals(" - 2o SEMESTRE - 7.0", pa.toString());
	}

	@Test
	void testGetSemestreIngresso() {
		PesquisadorAluno pa = new PesquisadorAluno(2, 7.0);
		assertEquals(2, pa.getSemestreIngresso());
	}

	@Test
	void testGetIea() {
		PesquisadorAluno pa = new PesquisadorAluno(2, 7.0);
		assertEquals(7.0, pa.getIea(), 7.0);
	}

	@Test
	void testToString() {
		PesquisadorAluno pa = new PesquisadorAluno(2, 7.0);
		assertEquals(" - 2o SEMESTRE - 7.0", pa.toString());
	}

	@Test
	void testAlteraPesquisador() {
		PesquisadorAluno pa = new PesquisadorAluno(2, 7.0);
		pa.alteraPesquisador("IEA", "7.1");
		assertEquals(" - 2o SEMESTRE - 7.1", pa.toString());
		
	}

}
