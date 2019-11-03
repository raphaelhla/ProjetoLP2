package psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representacao de um controller de objetivos
 * 
 * @author Jose Alisson -
 *
 */
public class ObjetivoController {

	private Map<String, Objetivo> objetivos;

	/**
	 * Indice utilizado para saber o numero do proximo objetivo a ser cadastrado.
	 */
	private int indiceObjetivo;

	/**
	 * Validador utilizado para lançar excecoes.
	 */
	private Validador validador;

	/**
	 * Controi um controller de objetivos.
	 */
	public ObjetivoController() {
		this.objetivos = new HashMap<String, Objetivo>();
		this.indiceObjetivo = 1;
		this.validador = new Validador();
	}

	/**
	 * Metodo que cadastra um objetivo a partir de seu tipo, sua descricao, sua
	 * aderencia e sua viabilidade.
	 * 
	 * @param tipo        Tipo do objetivo.
	 * @param descricao   Descricao do objetivo.
	 * @param aderencia   Aderencia do objetivo.
	 * @param viabilidade Viabilidade do objetivo.
	 * @return o codigo de identificacao do objetivo.
	 */
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		validador.verificaEntradaNulaVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		validador.verificaTipoObjetivo(tipo);
		validador.verificaEntradaNulaVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.verificaAderencia(aderencia);
		validador.verificaViabilidade(viabilidade);

		Objetivo objetivo = new Objetivo(tipo, descricao, aderencia, viabilidade);
		String chave = "O" + indiceObjetivo;
		objetivos.put(chave, objetivo);
		indiceObjetivo += 1;
		return chave;
	}

	/**
	 * Metodo que verifica se existe um objetivo ou nao, a partir do seu codigo.
	 * 
	 * @param codigo Codigo do objetivo.
	 * @return um valor booleano verdade caso exista o objetivo, caso contrario
	 *         retorna falso.
	 */
	private void verificaSeExisteObjetivo(String codigo) {
		if (!objetivos.containsKey(codigo)) {
			throw new IllegalArgumentException("Objetivo nao encontrado");
		}
	}

	/**
	 * Metodo que apaga um problema a partir do seu codigo.
	 * 
	 * @param codigo Codigo do problema.
	 */
	public void apagarObjetivo(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteObjetivo(codigo);
		this.objetivos.remove(codigo);
	}

	/**
	 * Metodo que retorna a representacao em string do problema a partir do seu
	 * codigo.
	 * 
	 * @param codigo Codigo do problema.
	 * @return retorna a representacao em string do problema.
	 */
	public String exibeObjetivo(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteObjetivo(codigo);
		return codigo + " - " + this.objetivos.get(codigo).toString();
	}

	public String busca(String termo) {
		String saida = "";
		List<String> stringObjetivos = new ArrayList<>();
		List<String> chaves = new ArrayList<>(objetivos.keySet());
		Collections.sort(chaves);
		Collections.reverse(chaves);
		for (String e : chaves) {
			if (objetivos.get(e).busca(termo)) {
				stringObjetivos.add(e + ": " + objetivos.get(e).getDescricao());
			}
		}
		saida = String.join(" | ", stringObjetivos);
		return saida;
	}
}
