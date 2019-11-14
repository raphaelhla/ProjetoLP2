package pesquisador;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PesquisadorControllerTest {

	private PesquisadorController pesquisadorController;

	@BeforeEach
	void inicializador() {
		pesquisadorController = new PesquisadorController();
		pesquisadorController.cadastraPesquisador("Joao", "estudante", "interessado em games", "monoteemo@lol",
				"https://steam");
		pesquisadorController.cadastraPesquisador("Geriscleiton", "estudante", "nao e ninguem na vida",
				"geris@gmail.com", "https://gerisfoto.com");
		pesquisadorController.cadastraEspecialidadeAluno("geris@gmail.com", 8, 7.0);
		pesquisadorController.cadastraPesquisador("Jeriscleuza", "professor", "e quase alguem na vida",
				"jeris@gmail.com", "https://jerisfoto.com");
		pesquisadorController.cadastraEspecialidadeProfessor("jeris@gmail.com", "Doutorado", "DSC", "19/12/2019");
		pesquisadorController.cadastraPesquisador("Jhoeicileifranklinsheixe", "externo", "sofreu bullying a vida toda",
				"jhoei@gmail.com", "https://jhoei.com");
	}

	@Test
	void testCadastraPesquisador() {
		assertEquals("Joao (estudante) - interessado em games - monoteemo@lol - https://steam",
				pesquisadorController.exibePesquisador("monoteemo@lol"));
	}

	@Test
	void testAlteraPesquisadorNome() {
		pesquisadorController.alteraPesquisador("monoteemo@lol", "NOME", "Matheus");
		assertEquals("Matheus (estudante) - interessado em games - monoteemo@lol - https://steam",
				pesquisadorController.exibePesquisador("monoteemo@lol"));
	}

	@Test
	void testAlteraPesquisadorFuncao() {
		pesquisadorController.alteraPesquisador("monoteemo@lol", "FUNCAO", "streamer");
		assertEquals("Joao (streamer) - interessado em games - monoteemo@lol - https://steam",
				pesquisadorController.exibePesquisador("monoteemo@lol"));
	}

	@Test
	void testAlteraPesquisadorBiografia() {
		pesquisadorController.alteraPesquisador("monoteemo@lol", "BIOGRAFIA", "solta cogumelos pelo mapa");
		assertEquals("Joao (estudante) - solta cogumelos pelo mapa - monoteemo@lol - https://steam",
				pesquisadorController.exibePesquisador("monoteemo@lol"));
	}

	@Test
	void testAlteraPesquisadorEmail() {
		pesquisadorController.alteraPesquisador("monoteemo@lol", "EMAIL", "melhorteemo@lol");
		assertEquals("Joao (estudante) - interessado em games - melhorteemo@lol - https://steam",
				pesquisadorController.exibePesquisador("melhorteemo@lol"));
	}

	@Test
	void testAlteraPesquisadorFoto() {
		pesquisadorController.alteraPesquisador("monoteemo@lol", "FOTO", "https://twitch.tv/nayark");
		assertEquals("Joao (estudante) - interessado em games - monoteemo@lol - https://twitch.tv/nayark",
				pesquisadorController.exibePesquisador("monoteemo@lol"));
	}

	@Test
	void testAtivaPesquisador() {
		pesquisadorController.desativaPesquisador("monoteemo@lol");
		pesquisadorController.ativaPesquisador("monoteemo@lol");
		assertEquals(true, pesquisadorController.pesquisadorEhAtivo("monoteemo@lol"));
	}

	@Test
	void testDesativaPesquisador() {
		pesquisadorController.desativaPesquisador("monoteemo@lol");
		assertEquals(false, pesquisadorController.pesquisadorEhAtivo("monoteemo@lol"));
	}

	@Test
	public void testAtivaPesquisadorJaAtivado() {
		try {
			pesquisadorController.ativaPesquisador("monoteemo@lol");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisador ja ativado.", e.getMessage());
		}
	}

	@Test
	public void testDesativaPesquisadorJaDesativado() {
		try {
			pesquisadorController.desativaPesquisador("monoteemo@lol");
			pesquisadorController.desativaPesquisador("monoteemo@lol");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisador inativo.", e.getMessage());
		}
	}

	@Test
	void testExibePesquisador() {
		assertEquals("Joao (estudante) - interessado em games - monoteemo@lol - https://steam",
				pesquisadorController.exibePesquisador("monoteemo@lol"));
	}

	@Test
	public void testExibePesquisadorDesativado() {
		try {
			pesquisadorController.desativaPesquisador("monoteemo@lol");
			pesquisadorController.exibePesquisador("monoteemo@lol");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisador inativo.", e.getMessage());
		}
	}

	@Test
	void testPesquisadorEhAtivo() {
		assertEquals(true, pesquisadorController.pesquisadorEhAtivo("monoteemo@lol"));
		pesquisadorController.desativaPesquisador("monoteemo@lol");
		assertEquals(false, pesquisadorController.pesquisadorEhAtivo("monoteemo@lol"));
	}

	@Test
	public void testBusca() {
		pesquisadorController.cadastraPesquisador("Joao", "estudante", "interessado em games", "monoteemo@lol",
				"https://steam");
		assertEquals("", pesquisadorController.busca("slack"));
	}

	@Test
	public void testBusca2() {
		pesquisadorController.cadastraPesquisador("Joao", "estudante", "interessado em games", "monoteemo@lol",
				"https://steam");
		assertEquals("monoteemo@lol: interessado em games", pesquisadorController.busca("games"));
	}

	@Test
	public void testGetPesquisador() {
		assertEquals("monoteemo@lol", pesquisadorController.getPesquisador("monoteemo@lol").getEmail());
	}

	@Test
	public void testAlteraPesquisadorInexistente() {
		try {
			pesquisadorController.alteraPesquisador("naoexiste@gmail", "FUNCAO", "nenhuma");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisador nao encontrado", e.getMessage());
		}
	}

	@Test
	public void testAlteraPesquisadorInativo() {
		try {
			pesquisadorController.desativaPesquisador("monoteemo@lol");
			pesquisadorController.alteraPesquisador("monoteemo@lol", "FUNCAO", "nenhuma");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisador inativo.", e.getMessage());
		}
	}

	@Test
	public void testAlteraPesquisadorAtributoInvalido() {
		try {
			pesquisadorController.alteraPesquisador("monoteemo@lol", "ATRIBUTOINVALIDO", "novoValor");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Atributo invalido.", e.getMessage());
		}
	}

	@Test
	void testAlteraPesquisadorAlunoSemestre() {
		pesquisadorController.alteraPesquisador("geris@gmail.com", "SEMESTRE", "7");
		assertEquals(
				"Geriscleiton (estudante) - nao e ninguem na vida - geris@gmail.com - https://gerisfoto.com - 7o SEMESTRE - 7.0",
				pesquisadorController.exibePesquisador("geris@gmail.com"));
	}

	@Test
	void testAlteraPesquisadorAlunoIEA() {
		pesquisadorController.alteraPesquisador("geris@gmail.com", "IEA", "8.0");
		assertEquals(
				"Geriscleiton (estudante) - nao e ninguem na vida - geris@gmail.com - https://gerisfoto.com - 8o SEMESTRE - 8.0",
				pesquisadorController.exibePesquisador("geris@gmail.com"));
	}

	@Test
	void testAlteraPesquisadorProfessorFormacao() {
		pesquisadorController.alteraPesquisador("jeris@gmail.com", "FORMACAO", "Bacharel");
		assertEquals(
				"Jeriscleuza (professor) - e quase alguem na vida - jeris@gmail.com - https://jerisfoto.com - Bacharel - DSC - 19/12/2019",
				pesquisadorController.exibePesquisador("jeris@gmail.com"));
	}

	@Test
	void testAlteraPesquisadorProfessorUnidade() {
		pesquisadorController.alteraPesquisador("jeris@gmail.com", "UNIDADE", "LSD");
		assertEquals(
				"Jeriscleuza (professor) - e quase alguem na vida - jeris@gmail.com - https://jerisfoto.com - Doutorado - LSD - 19/12/2019",
				pesquisadorController.exibePesquisador("jeris@gmail.com"));
	}

	@Test
	void testAlteraPesquisadorProfessorData() {
		pesquisadorController.alteraPesquisador("jeris@gmail.com", "DATA", "31/12/2000");
		assertEquals(
				"Jeriscleuza (professor) - e quase alguem na vida - jeris@gmail.com - https://jerisfoto.com - Doutorado - DSC - 31/12/2000",
				pesquisadorController.exibePesquisador("jeris@gmail.com"));
	}
	
	@Test
	public void testCadastraEspecialidadeNaoCompativelAluno() {
		try {
			pesquisadorController.cadastraEspecialidadeAluno("jeris@gmail.com", 8, 7.0);
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisador nao compativel com a especialidade.", e.getMessage());
		}
	}
	
	@Test
	public void testCadastraEspecialidadeNaoCompativelProfessor() {
		try {
			pesquisadorController.cadastraEspecialidadeProfessor("geris@gmail.com", "Doutorado", "DSC", "19/12/2019");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Pesquisador nao compativel com a especialidade.", e.getMessage());
		}
	}
	
	@Test
	public void testListaPesquisadoresTipoInvalido() {
		try {
			pesquisadorController.listaPesquisadores("teste");
			fail("Deveria lancar excecao");
		} catch (IllegalArgumentException e) {
			assertEquals("Tipo teste inexistente.", e.getMessage());
		}
	}
	
	@Test
	public void testListaPesquisadoresTipoExterno() {
		assertEquals("Jhoeicileifranklinsheixe (externo) - sofreu bullying a vida toda - jhoei@gmail.com - https://jhoei.com",pesquisadorController.listaPesquisadores("EXTERNO"));
	}
	
	@Test
	public void testListaPesquisadoresTipoAluna() {
		assertEquals("Joao (estudante) - interessado em games - monoteemo@lol - https://steam | Geriscleiton (estudante) - nao e ninguem na vida - geris@gmail.com - https://gerisfoto.com - 8o SEMESTRE - 7.0",pesquisadorController.listaPesquisadores("ALUNA"));
	}
	
	@Test
	public void testListaPesquisadoresTipoProfessora() {
		assertEquals("Jeriscleuza (professor) - e quase alguem na vida - jeris@gmail.com - https://jerisfoto.com - Doutorado - DSC - 19/12/2019",pesquisadorController.listaPesquisadores("PROFESSORA"));
	}
	
}
