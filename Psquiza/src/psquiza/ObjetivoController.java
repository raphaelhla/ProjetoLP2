package psquiza;

import java.util.HashMap;
import java.util.Map;

public class ObjetivoController {

	private Map<String, Objetivo> objetivos;
	private int indiceObjetivo;
	private Validador validador;
	

	public ObjetivoController() {
		this.objetivos = new HashMap<String, Objetivo>();
		this.indiceObjetivo = 1;
		this.validador = new Validador();
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		validador.verificaEntradaNulaVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.verificaAderencia(aderencia);
		validador.verificaViabilidade(viabilidade);
		if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}
		Objetivo objetivo = new Objetivo(tipo, descricao, aderencia, viabilidade);
		String chave = "O" + indiceObjetivo;
		objetivos.put(chave, objetivo);
		indiceObjetivo += 1;
		return chave;
	}

	private void verificaSeExisteObjetivo(String codigo) {
		if (!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
	}
	public void apagarObjetivo(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteObjetivo(codigo);
		this.objetivos.remove(codigo);
	}

	public String exibeObjetivo(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteObjetivo(codigo);
		return codigo + " - " + this.objetivos.get(codigo).toString();
	}

}
