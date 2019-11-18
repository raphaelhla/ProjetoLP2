package atividade;

import java.io.Serializable;

import psquiza.Validador;

/**
 * Representacao de um item. Todo item precisa ter uma descricao, um status e a
 * duracao da execucao do item.
 * 
 * @author Raphael Agra 119110413
 *
 */
public class Item implements Serializable{

	/**
	 * Descricao do item.
	 */
	private String descricao;

	/**
	 * Status do item.
	 */
	private String status;

	/**
	 * Tempo de execucao do item.
	 */
	private int duracao;
	
	/**
	 * Validador utilizado para validar entradas do sistema.
	 */
	private Validador validador;

	/**
	 * Constroi um item a partir de sua descricao.
	 * 
	 * @param descricao Descricao do item.
	 */
	public Item(String descricao) {
		this.validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		this.descricao = descricao;
		this.status = "PENDENTE";
		this.duracao = 0;
	}

	/**
	 * Metodo que retorna a string que representa o item. A representacao segue o
	 * formato "STATUS - DESCRICAO".
	 * 
	 * @return a string que representa o item.
	 */
	public String toString() {
		return this.status + " - " + this.descricao;
	}

	/**
	 * Metodo que executa um item a partir do tempo de execucao.
	 * 
	 * @param duracao Duracao de execucao do item.
	 */
	public void executaItem(int duracao) {
		validador.verificaNum(duracao, "Duracao nao pode ser nula ou negativa.");
		this.status = "REALIZADO";
		this.duracao = duracao;
	}

	/**
	 * Metodo que retorna o status do item.
	 * 
	 * @return o status do item.
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Metodo que retorna o tempo de execucao do item.
	 * 
	 * @return o tempo de execucao do item.
	 */
	public int getDuracao() {
		return this.duracao;
	}

	/**
	 * Metodo que retorna a descricao do item.
	 * 
	 * @return a descricao do item.
	 */
	public String getDescricao() {
		return this.descricao;
	}

	/**
	 * Metodo que verifica se a descricao do item contem uma string passada como
	 * parametro e retorna um valor booleano.
	 * 
	 * @param termo Termo ser procurado.
	 * @return verdade se a descricao contem o termo, caso contrario retorna falso.
	 */
	public boolean busca(String termo) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		return this.descricao.contains(termo);
	}
}
