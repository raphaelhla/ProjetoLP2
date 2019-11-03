package psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representacao de um controller de problemas.
 * 
 * @author Jose Alisson -
 *
 */
public class ProblemaController {

	/**
	 * Mapa com todos os problemas do sistema, identificados unicamente por um
	 * codigo.
	 */
	private Map<String, Problema> problemas;

	/**
	 * Indice utilizado para saber o numero do proximo problema a ser cadastrado.
	 */
	private int indiceProblema;

	/**
	 * Validador utilizado para lançar excecoes.
	 */
	private Validador validador;

	/**
	 * Constroi um controller de problemas.
	 */
	public ProblemaController() {
		this.problemas = new HashMap<String, Problema>();
		this.indiceProblema = 1;
		this.validador = new Validador();
	}

	/**
	 * Metodo que cadastra um problema a partir de sua descricao e sua viabilidade.
	 * 
	 * @param descricao   Descricao do problema.
	 * @param viabilidade Viabilidade do problema.
	 * @return o codigo de identificacao do problema.
	 */
	public String cadastraProblema(String descricao, int viabilidade) {
		validador.verificaEntradaNulaVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.verificaViabilidade(viabilidade);
		Problema problema = new Problema(descricao, viabilidade);
		String chave = "P" + indiceProblema;
		problemas.put(chave, problema);
		indiceProblema += 1;
		return chave;
	}

	/**
	 * Metodo que verifica se existe um problema ou nao, a partir do seu codigo.
	 * 
	 * @param codigo Codigo do problema.
	 * @return um valor booleano verdade caso exista o problema, caso contrario
	 *         retorna falso.
	 */
	private void verificaSeExisteProblema(String codigo) {
		if (!problemas.containsKey(codigo)) {
			throw new IllegalArgumentException("Problema nao encontrado");
		}
	}

	/**
	 * Metodo que apaga um problema a partir do seu codigo.
	 * 
	 * @param codigo Codigo do problema.
	 */
	public void apagarProblema(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteProblema(codigo);
		this.problemas.remove(codigo);
	}

	/**
	 * Metodo que retorna a representacao em string do problema a partir do seu
	 * codigo.
	 * 
	 * @param codigo Codigo do problema.
	 * @return retorna a representacao em string do problema.
	 */
	public String exibeProblema(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteProblema(codigo);
		return codigo + " - " + this.problemas.get(codigo).toString();
	}
	
	public String busca(String termo) {
		String saida = "";
		List<String> stringProblemas = new ArrayList<>();
		List<String> chaves = new ArrayList<>(problemas.keySet());
		Collections.sort(chaves);
		Collections.reverse(chaves);
		for (String e : chaves) {
			if (problemas.get(e).busca(termo)) {
				stringProblemas.add(e + ": " + problemas.get(e).getDescricao());
			}
		}
		saida = String.join(" | ", stringProblemas);
		return saida;
	}

}
