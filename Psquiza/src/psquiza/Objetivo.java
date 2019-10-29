package psquiza;

/**
 * Representacao de um objetivo. Todo objetivo precisa ter tipo, descricao,
 * aderencia e viabilidade.
 * 
 * @author Jose Alisson -
 *
 */
public class Objetivo {

	/**
	 * Tipo do objetivo.
	 */
	private String tipo;

	/**
	 * Descricao do objetivo
	 */
	private String descricao;

	/**
	 * Aderencia do objetivo.
	 */
	private int aderencia;

	/**
	 * Viabilidade do objetivo.
	 */
	private int viabilidade;

	/**
	 * Constroi um objetivo a partir do seu tipo, descricao, aderencia e
	 * viabilidade.
	 * 
	 * @param tipo        Tipo do objetivo.
	 * @param descricao   Descricao do objetivo.
	 * @param aderencia   Aderencia do objetivo.
	 * @param viabilidade Viabilidade do objetivo.
	 */
	public Objetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		this.tipo = tipo;
		this.descricao = descricao;
		this.aderencia = aderencia;
		this.viabilidade = viabilidade;
	}

	/**
	 * Metodo que retorna a representacao em string do objetivo. A representacao
	 * segue o formato "TIPO - DESCRICAO - VALOR"
	 * 
	 * @return a representacao em string do objetivo
	 */
	@Override
	public String toString() {
		int valor = aderencia + viabilidade;
		return this.tipo + " - " + this.descricao + " - " + valor;
	}

}
