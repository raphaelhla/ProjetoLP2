package objetivo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import psquiza.Validador;

/**
 * Representacao de um controller de objetivos
 * 
 * @author Jose Alisson 119110645
 *
 */
public class ObjetivoController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3142360673813314170L;

	private Map<String, Objetivo> mapObjetivos;

	/**
	 * Indice utilizado para saber o numero do proximo objetivo a ser cadastrado.
	 */
	private int indiceObjetivo;

	/**
	 * Validador utilizado para lancar excecoes.
	 */
	private Validador validador;

	/**
	 * Controi um controller de objetivos.
	 */
	public ObjetivoController() {
		this.mapObjetivos = new LinkedHashMap<String, Objetivo>();
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
		validador.verificaTipoObjetivo(tipo);
		validador.verificaEntradaNulaVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.verificaAderencia(aderencia);
		validador.verificaViabilidade(viabilidade);
		
		String chave = "O" + indiceObjetivo;
		Objetivo objetivo = new Objetivo(tipo, descricao, aderencia, viabilidade, chave);
		mapObjetivos.put(chave, objetivo);
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
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		if (!mapObjetivos.containsKey(codigo)) {
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
		this.mapObjetivos.remove(codigo);
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
		return codigo + " - " + this.mapObjetivos.get(codigo).toString();
	}

	/**
	 * Retorna o objeto Objetivo a partir de seu codigo.
	 * 
	 * @param codigo O codigo do Objetivo.
	 * @return retorna o Objetivo.
	 */
	public Objetivo getObjetivo(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteObjetivo(codigo);
		return this.mapObjetivos.get(codigo);
	}
	
	/**
	 * Metodo que recebe um termo como parametro e retorna uma string representando
	 * os objetivos que possuem o termo.
	 * 
	 * @param termo Termo a ser procurado
	 * @return uma string que representa os objetivos que possuem o termo
	 */
	public String busca(String termo) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		String saida = "";
		List<String> stringObjetivos = new ArrayList<>();
		List<String> chaves = new ArrayList<>(mapObjetivos.keySet());
		Collections.sort(chaves);
		Collections.reverse(chaves);
		for (String e : chaves) {
			if (mapObjetivos.get(e).busca(termo)) {
				stringObjetivos.add(e + ": " + mapObjetivos.get(e).getDescricao());
			}
		}
		saida = String.join(" | ", stringObjetivos);
		return saida;
	}

}
