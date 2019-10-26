package psquiza;

import java.util.HashMap;
import java.util.Map;

public class ProblemaController {

	private Map<String, Problema> problemas;
	private int indiceProblema;
	private Validador validador;
	
	public ProblemaController() {
		this.problemas = new HashMap<String, Problema>();
		this.indiceProblema = 1;
		this.validador = new Validador();
	}

	public String cadastraProblema(String descricao, int viabilidade) {
		validador.verificaEntradaNulaVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.verificaViabilidade(viabilidade);
		Problema problema = new Problema(descricao, viabilidade);
		String chave = "P" + indiceProblema;
		problemas.put(chave, problema);
		indiceProblema += 1;
		return chave;
	}

	private void verificaSeExisteObjetivo(String codigo) {
		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
	}
	
	public void apagarProblema(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteObjetivo(codigo);
		this.problemas.remove(codigo);
	}

	public String exibeProblema(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteObjetivo(codigo);
		return codigo + " - " + this.problemas.get(codigo).toString();
	}
	
	
}
