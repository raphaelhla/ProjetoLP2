package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisadorControllerTest {

	private PesquisadorController controller;

	@BeforeEach
	void inicializador() {
		controller = new PesquisadorController();
		controller.cadastraPesquisador("Joao", "estudante", "interessado em games", "monoteemo@lol", "https://steam");
	}

	@Test
	void testCadastraPesquisador() {
		assertEquals("Joao (estudante) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		
		}


	@Test
	void testAlteraPesquisador() {
		assertEquals("Joao (estudante) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		controller.alteraPesquisador("monoteemo@lol", "NOME", "Matheus");
		assertEquals("Matheus (estudante) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		controller.alteraPesquisador("monoteemo@lol", "FUNCAO", "streamer");
		assertEquals("Matheus (streamer) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		controller.alteraPesquisador("monoteemo@lol", "BIOGRAFIA", "solta cogumelos pelo mapa");
		assertEquals("Matheus (streamer) - solta cogumelos pelo mapa - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		controller.alteraPesquisador("monoteemo@lol", "EMAIL", "melhorteemo@lol");
		assertEquals("Matheus (streamer) - solta cogumelos pelo mapa - melhorteemo@lol - https://steam", controller.exibePesquisador("melhorteemo@lol"));
		controller.alteraPesquisador("melhorteemo@lol", "FOTO", "https://twitch.tv/nayark");
	}

	@Test
	void testAtivaPesquisador() {
		assertEquals(true, controller.pesquisadorEhAtivo("monoteemo@lol"));
		
		}

	@Test
	void testDesativaPesquisador() {
		controller.desativaPesquisador("monoteemo@lol");
		assertEquals(false, controller.pesquisadorEhAtivo("monoteemo@lol"));

	}

	@Test
	void testExibePesquisador() {
		assertEquals("Joao (estudante) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		

	}

	@Test
	void testPesquisadorEhAtivo() {
		assertEquals(true, controller.pesquisadorEhAtivo("monoteemo@lol"));
		controller.desativaPesquisador("monoteemo@lol");
		assertEquals(false, controller.pesquisadorEhAtivo("monoteemo@lol"));
	}
}
