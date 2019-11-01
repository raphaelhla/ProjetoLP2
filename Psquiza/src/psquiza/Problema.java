package psquiza;

/**
 * Representacao de um problema. Todo problema precisa ter descricao e
 * viabilidade.
 * 
 * @author Jose Alisson -
 *
 */
public class Problema {

	/**
	 * Descricao do problema.
	 */
	private String descricao;

	/**
	 * Viabilidade do problema.
	 */
	private int viabilidade;

	/**
	 * Controi um problema a partir da sua descricao e viabilidade.
	 * 
	 * @param descricao   Descricao do problema.
	 * @param viabilidade Viabilidade do problema.
	 */
	public Problema(String descricao, int viabilidade) {
		this.descricao = descricao;
		this.viabilidade = viabilidade;

	}

	/**
	 * Metodo que retorna a representacao em string de um problema. A representacao
	 * segue o formato "DESCRICAO - VIABILIDADE".
	 * 
	 * @return a representacao em string do problema.
	 */
	@Override
	public String toString() {
		return this.descricao + " - " + this.viabilidade;
	}
	
	public boolean busca(String termo) {
		if (this.descricao.contains(termo)) {
			return true;
		}
		return false;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
