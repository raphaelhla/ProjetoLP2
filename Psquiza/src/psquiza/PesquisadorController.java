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

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		Pesquisador pesquisador = pesquisadores.get(email);
		switch (atributo) {
		case "nome":
			pesquisador.setNome(novoValor);
			break;
		case "funcao":
			pesquisador.setFuncao(novoValor);
			break;
		case "biografia":
			pesquisador.setBiografia(novoValor);
		case "email":
			pesquisador.setEmail(novoValor);
			pesquisadores.remove(email);
			pesquisadores.put(novoValor, pesquisador);
		case "fotoURL":
			pesquisador.setFotoURL(novoValor);
		default:
			break;
		}
	}

	public void ativaPesquisador(String email) {
		pesquisadores.get(email).ativaPesquisador();
	}
	
	public void desativaPesquisador(String email) {
		pesquisadores.get(email).desativaPesquisador();
	}

}
