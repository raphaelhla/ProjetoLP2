package psquiza;

import java.util.HashMap;
import java.util.Map;

public class PesquisadorController {

	private Map<String, Pesquisador> pesquisadores;
	private Validador validador;
	
	public PesquisadorController() {
		this.pesquisadores = new HashMap<String, Pesquisador>();
		this.validador = new Validador();
	}
	
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		validador.verificaEntradaNulaVazia(nome, "Campo nome nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		validador.verificaEmail(email);
		this.pesquisadores.put(email, new Pesquisador(nome, funcao, biografia, email, fotoURL));
	}

	private void verificaSeExistePesquisador(String email) {
		if (!pesquisadores.containsKey(email)) {
			throw new IllegalArgumentException("Pesquisador nao encontrado");
		}
	}
	
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		verificaSeExistePesquisador(email);
		Pesquisador pesquisador = pesquisadores.get(email);
		switch (atributo) {
		case "nome":
			validador.verificaEntradaNulaVazia(novoValor, "Campo nome nao pode ser nulo ou vazio.");
			pesquisador.setNome(novoValor);
			break;
		case "funcao":
			validador.verificaEntradaNulaVazia(novoValor, "Campo funcao nao pode ser nulo ou vazio.");
			pesquisador.setFuncao(novoValor);
			break;
		case "biografia":
			validador.verificaEntradaNulaVazia(novoValor, "Campo biografia nao pode ser nulo ou vazio.");
			pesquisador.setBiografia(novoValor);
		case "email":
			validador.verificaEntradaNulaVazia(novoValor, "Campo email nao pode ser nulo ou vazio.");
			validador.verificaEmail(novoValor);
			pesquisador.setEmail(novoValor);
			pesquisadores.remove(email);
			pesquisadores.put(novoValor, pesquisador);
		case "fotoURL":
			validador.verificaEntradaNulaVazia(novoValor, "Campo fotoURL nao pode ser nulo ou vazio.");
			validador.verificafotoURL(novoValor);
			pesquisador.setFotoURL(novoValor);
		default:
			break;
		}
	}

	public void ativaPesquisador(String email) {
		verificaSeExistePesquisador(email);
		if (pesquisadores.get(email).getStatus().equals("ativado")) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		pesquisadores.get(email).ativaPesquisador();
	}
	
	public void desativaPesquisador(String email) {
		verificaSeExistePesquisador(email);
		if (pesquisadores.get(email).getStatus().equals("desativado")) {
			throw new IllegalArgumentException("Pesquisador ja ativado.");
		}
		pesquisadores.get(email).desativaPesquisador();
	}

	public String exibePesquisador(String email) {
		verificaSeExistePesquisador(email);
		return pesquisadores.get(email).toString();
	}

}
