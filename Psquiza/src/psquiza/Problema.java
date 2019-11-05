package psquiza;

/**
 * Representacao de um problema. Todo problema precisa ter descricao e
 * viabilidade.
 * 
 * @author Jose Alisson - 119110645
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

	/**
	 * Metodo que verifica se a descricao do pesquisador contem uma string passada
	 * como parametro e retorna um valor booleano.
	 * 
	 * @param termo Termo ser procurado.
	 * @return verdade se a descricao contem o termo, caso contrario retorna falso.
	 */
	public boolean busca(String termo) {
		return this.descricao.contains(termo);
	}

	/**
	 * Metodo que retorna a descricao do problema.
	 * 
	 * @return a descricao do problema.
	 */
	public String getDescricao() {
		return this.descricao;
	}
}
