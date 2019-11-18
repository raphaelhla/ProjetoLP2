package pesquisador;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import psquiza.Validador;

/**
 * Representacao de um controller de pesquisadores.
 * 
 * @author Raphael Agra 119110413
 *
 */
public class PesquisadorController implements Serializable{

	/**
	 * Mapa com todos os pesquisadores do sistema, identificados unicamente por seu
	 * email.
	 * 
	 */
	private Map<String, Pesquisador> pesquisadores;

	/**
	 * Validador utilizado para lancar excecoes.
	 */
	private Validador validador;

	/**
	 * Constroi um controller de pesquisadores.
	 */
	public PesquisadorController() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
		this.validador = new Validador();
	}

	/**
	 * Metodo que cadastra um pesquisador a partir do seu nome, funcao, biografia,
	 * email e fotoURL.
	 * 
	 * @param nome      Nome do pesquisador.
	 * @param funcao    Funcao do pesquisador.
	 * @param biografia Biografia do pesquisador.
	 * @param email     Email do pesquisador.
	 * @param fotoURL   Url da foto do pesquisador.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		validador.verificaEntradaNulaVazia(nome, "Campo nome nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		validador.verificaEmail(email);
		validador.verificafotoURL(fotoURL);
		this.pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
	}

	/**
	 * Metodo que vai buscar um pesquisador no map pesquisadores.
	 * 
	 * 
	 * @param email email do pesquisador a ser retornado.
	 * @return objeto pesquisador que possui o email passado como parametro.
	 */
	public Pesquisador getPesquisador(String email) {
		return pesquisadores.get(email);
	}

	/**
	 * Metodo que verifica se existe um pesquisador ou nao, a partir do seu email.
	 * 
	 * @param email Email do pesquisador.
	 * @return um valor booleano verdade caso exista o pesquisador, caso contrario
	 *         retorna falso.
	 */
	private void verificaSeExistePesquisador(String email, String mensagem) {
		if (!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Metodo que edita um pesquisador a partir do seu email, do atributo a ser
	 * editado e um novo valor para o atributo.
	 * 
	 * @param email     Email do pesquisador.
	 * @param atributo  Atributo que vai ser editado.
	 * @param novoValor Novo valor para o atributo.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		validador.verificaEntradaNulaVazia(email, "Email nao pode ser vazio ou nulo.");
		validador.verificaEntradaNulaVazia(atributo, "Atributo nao pode ser vazio ou nulo.");
		verificaSeExistePesquisador(email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		Pesquisador pesquisador = pesquisadores.get(email);

		switch (atributo) {
		case "NOME":
			validador.verificaEntradaNulaVazia(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			pesquisador.setNome(novoValor);
			break;
		case "FUNCAO":
			validador.verificaEntradaNulaVazia(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
			pesquisador.setFuncao(novoValor);
			break;
		case "BIOGRAFIA":
			validador.verificaEntradaNulaVazia(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
			pesquisador.setBiografia(novoValor);
			break;
		case "EMAIL":
			validador.verificaEmail(novoValor);
			pesquisadores.remove(email);
			pesquisador.setEmail(novoValor);
			pesquisadores.put(novoValor, pesquisador);
			break;
		case "FOTO":
			validador.verificafotoURL(novoValor);
			pesquisador.setFotoURL(novoValor);
			break;
		case "SEMESTRE":
			pesquisador.alteraAtributoEspecialidade(atributo, novoValor);
			break;
		case "IEA":
			pesquisador.alteraAtributoEspecialidade(atributo, novoValor);
			break;
		case "FORMACAO":
			validador.verificaEntradaNulaVazia(novoValor, "Campo formacao nao pode ser nulo ou vazio.");
			pesquisador.alteraAtributoEspecialidade(atributo, novoValor);
			break;
		case "UNIDADE":
			validador.verificaEntradaNulaVazia(novoValor, "Campo unidade nao pode ser nulo ou vazio.");
			pesquisador.alteraAtributoEspecialidade(atributo, novoValor);
			break;
		case "DATA":
			validador.verificaData(novoValor);
			pesquisador.alteraAtributoEspecialidade(atributo, novoValor);
			break;
		default:
			throw new IllegalArgumentException("Atributo invalido.");
		}
	}

	/**
	 * Metodo responsavel por ativar o pesquisador a partir do seu email.
	 * 
	 * @param email Email do pesquisador
	 */
	public void ativaPesquisador(String email) {
		validador.verificaEntradaNulaVazia(email, "Email nao pode ser vazio ou nulo.");
		verificaSeExistePesquisador(email, "Pesquisador nao encontrado");
		if (pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		pesquisadores.get(email).ativaPesquisador();
	}

	/**
	 * Metodo responsavel por desativar o pesquisador a partir do seu email.
	 * 
	 * @param email Email do pesquisador
	 */
	public void desativaPesquisador(String email) {
		validador.verificaEntradaNulaVazia(email, "Email nao pode ser vazio ou nulo.");
		verificaSeExistePesquisador(email, "Pesquisador nao encontrado");
		if (!pesquisadorEhAtivo(email)) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		pesquisadores.get(email).desativaPesquisador();
	}

	/**
	 * Metodo que retorna a representacao em string do pesquisador a partir do seu
	 * cpf.
	 * 
	 * @param email Email do pesquisador
	 * @return retorna a representacao em string do pesquisador
	 */
	public String exibePesquisador(String email) {
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		verificaSeExistePesquisador(email, "Pesquisador nao encontrado");
		if (pesquisadores.get(email).getStatus().equals("inativo")) {
			throw new IllegalArgumentException("Pesquisador inativo.");
		}
		return pesquisadores.get(email).toString();
	}

	/**
	 * Metodo que verifica se um pesquisador é ativo ou nao, a partir do seu email.
	 * 
	 * @param email Email do pesquisador.
	 * @return um valor booleano verdade caso o pesquisador esteja ativo, caso
	 *         contrario retorna falso.
	 */
	public boolean pesquisadorEhAtivo(String email) {
		validador.verificaEntradaNulaVazia(email, "Email nao pode ser vazio ou nulo.");
		verificaSeExistePesquisador(email, "Pesquisador nao encontrado");
		if (pesquisadores.get(email).getStatus().equals("ativo")) {
			return true;
		}
		return false;
	}

	/**
	 * Metodo que recebe um termo como parametro e retorna uma string representando
	 * os pesquisadores que possuem o termo.
	 * 
	 * @param termo Termo a ser procurado
	 * @return uma string que representa os pesquisadores que possuem o termo
	 */
	public String busca(String termo) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		String saida = "";
		List<String> stringPesquisadores = new ArrayList<>();
		List<String> chaves = new ArrayList<>(pesquisadores.keySet());
		Collections.sort(chaves);
		Collections.reverse(chaves);
		for (String e : chaves) {
			if (pesquisadores.get(e).busca(termo)) {
				stringPesquisadores.add(pesquisadores.get(e).getEmail() + ": " + pesquisadores.get(e).getBiografia());
			}
		}
		saida = String.join(" | ", stringPesquisadores);
		return saida;
	}

	// US6 Matheus
	/**
	 * Metodo que vai cadastrar a especialidade pofessor, transformando um
	 * pesquisador externo em pesqusiador professor.
	 * 
	 * @param email    email do pesquisador.
	 * @param formacao formacao do pesquisador professor.
	 * @param unidade  unidade de trabalho do pesquisador professor.
	 * @param data     data do inicio do trabalho do pesquisador professor.
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		validador.verificaData(data);
		verificaSeExistePesquisador(email, "Pesquisadora nao encontrada.");
		Pesquisador pesquisador = getPesquisador(email);
		if (!pesquisador.getFuncao().equals("professor")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}
		pesquisador.setEspecialidade(new PesquisadorProfessor(formacao, unidade, data));
	}

	/**
	 * Metodo que vai cadastrar a especialidade aluno, transformando um pesquisador
	 * externo em pesqusiador aluno.
	 * 
	 * @param email    email do pesquisador.
	 * @param semestre semestre do pesquisador aluno.
	 * @param IEA      Indice de Eficiencia Academica do pesquisador aluno.
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		validador.verificaSemestre(semestre);
		validador.verificaIEA(IEA);
		verificaSeExistePesquisador(email, "Pesquisadora nao encontrada.");
		Pesquisador pesquisador = getPesquisador(email);
		if (!pesquisador.getFuncao().equals("estudante")) {
			throw new IllegalArgumentException("Pesquisador nao compativel com a especialidade.");
		}
		pesquisador.setEspecialidade(new PesquisadorAluno(semestre, IEA));
	}

	/**
	 * Metodo que vai listar os pesquisadores de acordo com a especialidade passada
	 * como parametro.
	 * 
	 * @param tipo tipo dos pesquisadores a serem listados.
	 * @return retorna a representacao em String de todos os pesquisadores do tipo.
	 */
	public String listaPesquisadores(String tipo) {
		validador.verificaEntradaNulaVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		String string = "";

		switch (tipo) {

		case "EXTERNO":
			List<String> pesquisadoresExternos = new ArrayList<String>();
			for (Pesquisador pesquisador : pesquisadores.values()) {
				if (pesquisador.getFuncao().equals("externo")) {
					pesquisadoresExternos.add(pesquisador.toString());
				}
			}
			string = String.join(" | ", pesquisadoresExternos);
			return string;

		case "ALUNA":
			List<String> pesquisadoresAlunos = new ArrayList<String>();
			for (Pesquisador pesquisador : pesquisadores.values()) {
				if (pesquisador.getFuncao().equals("estudante")) {
					pesquisadoresAlunos.add(pesquisador.toString());
				}
			}
			string = String.join(" | ", pesquisadoresAlunos);
			return string;

		case "PROFESSORA":
			List<String> pesquisadoresProfessores = new ArrayList<String>();
			for (Pesquisador pesquisador : pesquisadores.values()) {
				if (pesquisador.getFuncao().equals("professor")) {
					pesquisadoresProfessores.add(pesquisador.toString());
				}
			}
			string = String.join(" | ", pesquisadoresProfessores);
			return string;

		default:
			throw new IllegalArgumentException("Tipo " + tipo + " inexistente.");
		}
	}

}
