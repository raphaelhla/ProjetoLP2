package psquiza;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ValidadorTest {

	private Validador validador;

	@BeforeEach
	void inicializador() {
		validador = new Validador();
	}

	@Test
	void testVerificaEntradaNula() {
		try {
			validador.verificaEntradaNulaVazia(null, "msg teste");
			fail("deveria lancar excecao");
		} catch (NullPointerException e) {
			assertEquals("msg teste", e.getMessage());
		}
	}

	@Test
	void testVerificaEntradaVazia() {
		try {
			validador.verificaEntradaNulaVazia("", "msg teste");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("msg teste", e.getMessage());
		}
	}

	@Test
	void testVerificaEmailInvalido1() {
		try {
			validador.verificaEmail("@teste.com");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato de email invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaEmailInvalido2() {
		try {
			validador.verificaEmail("teste.com@");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato de email invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaEmailInvalido3() {
		try {
			validador.verificaEmail("teste.com");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato de email invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificafotoURLInvalida1() {
		try {
			validador.verificafotoURL("http:/");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato de foto invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificafotoURLInvalida2() {
		try {
			validador.verificafotoURL("testEEEEEEEEEEEEEEEe");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato de foto invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificafotoURLInvalida3() {
		try {
			validador.verificafotoURL("https:/");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato de foto invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaNivelRiscoInvalido() {
		try {
			validador.verificaNivelRisco("teste");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Valor invalido do nivel do risco.", e.getMessage());
		}
	}

	@Test
	void testVerificaTipoObjetivoInvalido() {
		try {
			validador.verificaTipoObjetivo("teste");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Valor invalido de tipo.", e.getMessage());
		}
	}

	@Test
	void testVerificaViabilidadeInvalida1() {
		try {
			validador.verificaViabilidade(0);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Valor invalido de viabilidade.", e.getMessage());
		}
	}

	@Test
	void testVerificaViabilidadeInvalida2() {
		try {
			validador.verificaViabilidade(6);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Valor invalido de viabilidade.", e.getMessage());
		}
	}

	@Test
	void testVerificaAderenciaInvalida1() {
		try {
			validador.verificaAderencia(0);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Valor invalido de aderencia", e.getMessage());
		}
	}

	@Test
	void testVerificaAderenciaInvalida2() {
		try {
			validador.verificaAderencia(6);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Valor invalido de aderencia", e.getMessage());
		}
	}

	@Test
	void testVerificaCampoDeInteresseInvalido1() {
		try {
			validador.verificaCampoDeInteresse(
					"Lorem ipsum dolor sit amet consectetur adipiscing elit Integer euismod leo in consequat efficitur. Proin commodo nisi eget ligula consequat imperdiet ac quis turpis In non fringilla orci Pellentesque pellentesque ipsum vel ipsum ultrices scelerisque Nulla facilisi Morbi ut tellus ante Suspendisse malesuada quis quam eu efficitur Ut mollis turpis magna sit amet auctor nunc pulvinar ultricies. Nam pharetra scelerisque magna ut feugiat.");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato do campo de interesse invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaCampoDeInteresseInvalido2() {
		try {
			validador.verificaCampoDeInteresse("teste, teste, teste, teste, teste");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato do campo de interesse invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaCampoDeInteresseInvalido3() {
		try {
			validador.verificaCampoDeInteresse("te, te");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato do campo de interesse invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaCampoDeInteresseInvalido4() {
		try {
			validador.verificaCampoDeInteresse("       ,        ");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Formato do campo de interesse invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataFormatoInvalido1() {
		try {
			validador.verificaData("111111111111111111");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataFormatoInvalido2() {
		try {
			validador.verificaData("1");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataFormatoInvalido3() {
		try {
			validador.verificaData("1/1/1");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataFormatoInvalido4() {
		try {
			validador.verificaData("11/1/1999");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataFormatoInvalido5() {
		try {
			validador.verificaData("1/11/1999");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataDiaInvalido1() {
		try {
			validador.verificaData("32/12/1999");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataDiaInvalido2() {
		try {
			validador.verificaData("0/12/1999");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataMesInvalido1() {
		try {
			validador.verificaData("31/13/1999");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataMesInvalido2() {
		try {
			validador.verificaData("31/0/1999");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataAnoInvalido1() {
		try {
			validador.verificaData("31/12/00000");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaDataAnoInvalido2() {
		try {
			validador.verificaData("31/12/000");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo data com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaSemestreInvalido() {
		try {
			validador.verificaSemestre(0);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo semestre com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaIEAInvalidoMenorQueZero() {
		try {
			validador.verificaIEA(-1);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo IEA com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaIEAInvalidoMaiorQueDez() {
		try {
			validador.verificaIEA(11);
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo IEA com formato invalido.", e.getMessage());
		}
	}

	@Test
	void testVerificaNumInvalidoMenorQueZero() {
		try {
			validador.verificaNum(-1, "msg teste error");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("msg teste error", e.getMessage());
		}
	}

	@Test
	void testVerificaEstrategiaInvalida() {
		try {
			validador.verificaEstrategia("TESTE");
			fail("deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Valor invalido da estrategia", e.getMessage());
		}
	}

}
