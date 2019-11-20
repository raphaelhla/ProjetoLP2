package problema;

import java.io.Serializable;

import psquiza.Validador;

/**
 * Representacao de um problema. Todo problema precisa ter descricao e
 * viabilidade.
 * 
 * @author Jose Alisson 119110645
 *
 */
public class Problema implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -27069089149027792L;

	/**
	 * Descricao do problema.
	 */
	private String descricao;

	/**
	 * Viabilidade do problema.
	 */
	private int viabilidade;

	/**
	 * Codigo do problema.
	 */
	private String codigo;

	/**
	 * Validador utilizado para validar entradas do sistema.
	 */
	private Validador validador;

	/**
	 * Controi um problema a partir da sua descricao e viabilidade.
	 * 
	 * @param descricao   Descricao do problema.
	 * @param viabilidade Viabilidade do problema.
	 * @param codigo      Codigo do problema.
	 */
	public Problema(String descricao, int viabilidade, String codigo) {
		this.validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.verificaViabilidade(viabilidade);
		this.descricao = descricao;
		this.viabilidade = viabilidade;
		this.codigo = codigo;

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
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
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

	public String getCodigo() {
		return this.codigo;
	}
}
