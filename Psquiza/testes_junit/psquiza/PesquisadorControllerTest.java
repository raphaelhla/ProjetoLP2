package psquiza;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisadorControllerTest {

	private PesquisadorController controller;

	@BeforeEach
	void inicializador() {
		controller = new PesquisadorController();
		controller.cadastraPesquisador("João", "estudante", "interessado em games", "monoteemo@lol", "https://steam");
	}

	@Test
	void testCadastraPesquisador() {
		assertEquals("João (estudante) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		
		}


	@Test
	void testAlteraPesquisador() {
		assertEquals("João (estudante) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		controller.alteraPesquisador("monoteemo@lol", "nome", "Matheus");
		assertEquals("Matheus (estudante) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		controller.alteraPesquisador("monoteemo@lol", "funcao", "streamer");
		assertEquals("Matheus (streamer) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		controller.alteraPesquisador("monoteemo@lol", "biografia", "solta cogumelos pelo mapa");
		assertEquals("Matheus (streamer) - solta cogumelos pelo mapa - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		controller.alteraPesquisador("monoteemo@lol", "email", "melhorteemo@lol");
		assertEquals("Matheus (streamer) - solta cogumelos pelo mapa - melhorteemo@lol - https://steam", controller.exibePesquisador("melhorteemo@lol"));
		controller.alteraPesquisador("melhorteemo@lol", "fotoURL", "https://twitch.tv/nayark");
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
		assertEquals("João (estudante) - interessado em games - monoteemo@lol - https://steam", controller.exibePesquisador("monoteemo@lol"));
		

	}

	@Test
	void testPesquisadorEhAtivo() {
		assertEquals(true, controller.pesquisadorEhAtivo("monoteemo@lol"));
		controller.desativaPesquisador("monoteemo@lol");
		assertEquals(false, controller.pesquisadorEhAtivo("monoteemo@lol"));
	}
}
