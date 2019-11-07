package psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representacao de um controller de pesquisadores.
 * 
 * @author Raphael Agra - 119110413
 *
 */
public class PesquisadorController {

	/**
	 * Mapa com todos os pesquisadores do sistema, identificados unicamente por seu
	 * email.
	 * 
	 */
	private Map<String, Pesquisador> pesquisadores;

	/**
	 * Validador utilizado para lançar excecoes.
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
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		validador.verificaEmail(email);
		validador.verificafotoURL(fotoURL);
		this.pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
	}
	
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
	private void verificaSeExistePesquisador(String email) {
		if (!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
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
		verificaSeExistePesquisador(email);
		validador.verificaEntradaNulaVazia(atributo, "Atributo nao pode ser vazio ou nulo.");
		;
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
			validador.verificaEntradaNulaVazia(novoValor, "Campo email nao pode ser nulo ou vazio.");
			validador.verificaEmail(novoValor);
			pesquisadores.remove(email);
			pesquisador.setEmail(novoValor);
			pesquisadores.put(novoValor, pesquisador);
			break;
		case "FOTO":
			validador.verificaEntradaNulaVazia(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
			validador.verificafotoURL(novoValor);
			pesquisador.setFotoURL(novoValor);
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
		verificaSeExistePesquisador(email);
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
		verificaSeExistePesquisador(email);
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
		verificaSeExistePesquisador(email);
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
		verificaSeExistePesquisador(email);
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

}
