package objetivo;

import psquiza.Validador;

/**
 * Representacao de um objetivo. Todo objetivo precisa ter tipo, descricao,
 * aderencia e viabilidade.
 * 
 * @author Jose Alisson 119110645
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
	 * Validador utilizado para validar entradas do sistema.
	 */
	private Validador validador;

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
		this.validador = new Validador();
		validador.verificaTipoObjetivo(tipo);
		validador.verificaEntradaNulaVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.verificaAderencia(aderencia);
		validador.verificaViabilidade(viabilidade);
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

	/**
	 * Metodo que verifica se a descricao do objetivo contem uma string passada
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
	 * Metodo que retorna a descricao do objetivo.
	 * 
	 * @return a descricao do objetivo.
	 */
	public String getDescricao() {
		return this.descricao;
	}
}
