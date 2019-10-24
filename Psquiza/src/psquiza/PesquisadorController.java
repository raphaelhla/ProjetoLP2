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

}
