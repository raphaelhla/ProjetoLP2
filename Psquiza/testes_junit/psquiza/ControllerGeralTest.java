package psquiza;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ControllerGeralTest {

	private ControllerGeral controllerGeral;

	@BeforeEach
	public void criaController() {
		controllerGeral = new ControllerGeral();
	}

	@Test
	public void testeBuscaGeral() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		assertEquals(
				"FAK1: Pesquisa do uso da computacao no combate a fake news | raphael@example.com: Estudante de computacao | O1: Objetivo da computacao | A1: Atividade de computacao",
				controllerGeral.busca("computacao"));
	}

	@Test
	public void testeBuscaEspecifica() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		assertEquals("raphael@example.com: Estudante de computacao", controllerGeral.busca("computacao", 2));
	}
	
	@Test
	public void testeContaResultadosBusca() {
		controllerGeral.cadastraAtividade("Atividade de computacao", "MEDIO", "Pode pegar virus");
		controllerGeral.cadastraObjetivo("GERAL", "Objetivo da computacao", 3, 2);
		controllerGeral.cadastraPesquisa("Pesquisa do uso da computacao no combate a fake news", "fake news");
		controllerGeral.cadastraPesquisador("Raphael", "Estudante", "Estudante de computacao", "raphael@example.com",
				"https://teste.com");
		assertEquals(4, controllerGeral.contaResultadosBusca("computacao"));
	}
}
