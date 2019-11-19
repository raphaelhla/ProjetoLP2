package atividade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import psquiza.Validador;

/**
 * Representacao de uma atividade. Toda atividade precisa ter uma descricao,
 * nivel do risco, descricao do risco
 * 
 * @author Weslley Azevedo 11911241
 *
 */
public class Atividade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7285396888359965774L;

	private Atividade subsquente;

	/**
	 * Validador utilizado para validar entradas do sistema.
	 */
	private Validador validador;

	/**
	 * Descricao da atividade.
	 */
	private String descricao;

	/**
	 * Nivel do risco da atividade.
	 */
	private String nivelRisco;

	/**
	 * Descricao do risco da atividade.
	 */
	private String descricaoRisco;

	/**
	 * Codigo de identificacao da atividade.
	 */
	private String codigoAtividade;

	/**
	 * Lista que contem todos os itens cadastrados na atividade.
	 */
	private Map<Integer, Item> itens;

	/**
	 * Mapa com todos os resultados cadastrados na atividade.
	 */
	private Map<Integer, String> resultados;

	/**
	 * Contador utilizado para o cadastro de itens.
	 */
	private int indiceItens;

	/**
	 * Contador utilizado para o cadastro de resultados.
	 */
	private int indiceResultados;

	/**
	 * Constroi uma atividade a partir da sua descricao, nivel de risco e descricao
	 * do risco.
	 * 
	 * @param descricao      Descricao da atividade.
	 * @param nivelRisco     Nivel do risco da atividade.
	 * @param descricaoRisco Descricao do risco da atividade.
	 */
	public Atividade(String descricao, String nivelRisco, String descricaoRisco, String codigoAtividade) {
		this.validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.verificaNivelRisco(nivelRisco);
		validador.verificaEntradaNulaVazia(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.codigoAtividade = codigoAtividade;
		this.itens = new HashMap<Integer, Item>();
		this.resultados = new HashMap<Integer, String>();
		this.indiceItens = 0;
		this.indiceResultados = 0;
	}

	/**
	 * Cadastra um item na atividade a partir da String de representacao do item.
	 * 
	 * @param item a representacao em String do item.
	 */
	public void cadastraItem(String item) {
		validador.verificaEntradaNulaVazia(item, "Item nao pode ser nulo ou vazio.");
		this.indiceItens += 1;
		this.itens.put(indiceItens, new Item(item));

	}

	/**
	 * Retorna a quantidade de itens pendentes contidos nessa atividade.
	 * 
	 * @return a representacao em Int da quantidade de itens pendentes.
	 */
	public int getQtdItensPendentes() {
		int saida = 0;
		for (Item item : itens.values()) {
			if (item.getStatus().equals("PENDENTE")) {
				saida++;
			}
		}
		return saida;
	}

	/**
	 * Retorna a quantidade de itens realizados contidos nessa atividade.
	 * 
	 * @return a representacao em Int da quantidade de itens realizados.
	 */
	public int getQtdItensRealizados() {
		int saida = 0;
		for (Item item : itens.values()) {
			if (item.getStatus().equals("REALIZADO")) {
				saida++;
			}
		}
		return saida;
	}

	/**
	 * Metodo que retorna a descricao da atividade.
	 * 
	 * @return a descricao da atividade.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo que retorna a descricao de risco da atividade.
	 * 
	 * @return a descricao de risco da atividade.
	 */
	public String getDescricaoRisco() {
		return descricaoRisco;
	}

	/**
	 * Retorna a String que representa a atividade. A representacao segue o formato
	 * " descricao (nivelRisco - descricaoRisco ) | ESTADOITEM - ITEM1 | ESTADOITEM
	 * - ITEM2 ..."
	 * 
	 * @return a representacao em String de uma atividade.
	 */
	@Override
	public String toString() {
		String saida = this.descricao + " (" + this.nivelRisco + " - " + this.descricaoRisco + ")";
		for (int i = 1; i <= this.itens.size(); i++) {
			saida += " | " + this.itens.get(i).toString();
		}
		return saida;
	}

	/**
	 * Metodo que executa uma atividade a partir do item e da duracao da atividade.
	 * Quando um item e executado, seu status e alterado para "REALIZADO".
	 * 
	 * @param item    Item executado.
	 * @param duracao Duracao da execucao do item.
	 */
	public void executaAtividade(int item, int duracao) {
		validador.verificaNum(item, "Item nao pode ser nulo ou negativo.");
		validador.verificaNum(duracao, "Duracao nao pode ser nula ou negativa.");
		if (!this.itens.containsKey(item))
			throw new IllegalArgumentException("Item nao encontrado.");
		if (this.itens.get(item).getStatus().equals("REALIZADO"))
			throw new IllegalArgumentException("Item ja executado.");
		this.itens.get(item).executaItem(duracao);

	}

	/**
	 * Metodo que cadastra um resultado para a atividade a partir do resultado a ser
	 * cadastrado.
	 * 
	 * @param resultado Resultado a ser cadastrado.
	 * @return o inteiro que representa o resultado
	 */
	public int cadastraResultado(String resultado) {
		validador.verificaEntradaNulaVazia(resultado, "Resultado nao pode ser nulo ou vazio.");
		this.indiceResultados += 1;
		this.resultados.put(this.indiceResultados, resultado);
		return this.indiceResultados;
	}

	/**
	 * Metodo que remove um resultado a partir do numero que representa o resultado
	 * a ser removido.
	 * 
	 * @param numeroResultado Numero do resultado.
	 * @return verdadeiro se a remocao for bem sucedida, caso contrario retorna
	 *         falso.
	 */
	public boolean removeResultado(int numeroResultado) {
		validador.verificaNum(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		if (numeroResultado > indiceResultados)
			throw new IllegalArgumentException("Resultado nao encontrado.");
		if (!this.resultados.containsKey(numeroResultado))
			return false;
		this.resultados.remove(numeroResultado);
		return true;
	}

	/**
	 * Metodo que retorna uma string com todos o resultados da atividade.
	 * 
	 * @return uma string com todos o resultados da atividade.
	 */
	public String listaResultados() {
		String saida = "";
		List<Integer> chaves = new ArrayList<Integer>(resultados.keySet());
		Collections.sort(chaves);
		List<String> stringResultados = new ArrayList<String>();
		for (Integer e : chaves) {
			stringResultados.add(resultados.get(e));
		}
		saida = String.join(" | ", stringResultados);
		return saida;
	}

	/**
	 * Metodo que retorna o inteiro que representa a duracao da execucao de todos os
	 * itens da atividade.
	 * 
	 * @return o inteiro que representa a duracao da execucao de todos os itens da
	 *         atividade
	 */
	public int getDuracao() {
		int duracao = 0;
		for (Item item : itens.values()) {
			duracao += item.getDuracao();
		}
		return duracao;
	}

	/**
	 * Metodo que verifica se a descricao da atividade contem uma string passada
	 * como parametro e retorna um valor booleano.
	 * 
	 * @param termo Termo ser procurado.
	 * @return verdade se a descricao contem o termo, caso contrario retorna falso.
	 */
	public boolean buscaDescricao(String termo) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		return this.descricao.contains(termo);
	}

	/**
	 * Metodo que verifica se a descricao de risco da atividade contem uma string
	 * passada como parametro e retorna um valor booleano.
	 * 
	 * @param termo Termo ser procurado.
	 * @return verdade se a descricao de risco contem o termo, caso contrario
	 *         retorna falso.
	 */
	public boolean buscaDescricaoRisco(String termo) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		return this.descricaoRisco.contains(termo);
	}

	// US9 Raphael

	/**
	 * Metodo que define uma ordem de execucao para representar uma logica de
	 * execucao da atividade a partir da proxima atividade
	 * 
	 * @param proxima Atividade subsequente.
	 */
	public void next(Atividade proxima) {
		if (this.subsquente != null)
			throw new IllegalArgumentException("Atividade ja possui uma subsequente.");
		if (proxima.pegaUltimo().equals(this.codigoAtividade))
			throw new IllegalArgumentException("Criacao de loops negada.");
		this.subsquente = proxima;
	}

	/**
	 * Metodo que quebra uma ordem de execucao predefinida anteriormente.
	 */
	public void tiraProximaAtividade() {
		this.subsquente = null;
	}

	/**
	 * Metodo que conta quantas atividades estão planejadas.
	 * 
	 * @return o inteiro que representa a quantidade de atividades planejadas.
	 */
	public int contaProximos() {
		if (this.subsquente == null)
			return 0;
		return 1 + this.subsquente.contaProximos();
	}

	/**
	 * Metodo que retorna o codigo da atividade.
	 * 
	 * @return o codigo da atividade.
	 */
	public String getCodigo() {
		return this.codigoAtividade;
	}

	/**
	 * Metodo que retorna a atividade susequente.
	 * 
	 * @return a atividade subsequente.
	 */
	public Atividade getSusquente() {
		return this.subsquente;
	}

	/**
	 * Metodo que retorna a enesima atividade, passada como parametro, associada a
	 * execucao da atividade atual.
	 * 
	 * @param enesimaAtividade Numero da enesima atividade.
	 * @return o codigo da enesima atividade associada a execucao da atividade
	 *         atual.
	 */
	public String pegaProximo(int enesimaAtividade) {
		if (enesimaAtividade == 0) {
			return this.codigoAtividade;
		}

		return this.subsquente.pegaProximo(enesimaAtividade - 1);
	}

	/**
	 * Metodo que retorna a ultima atividade associada a execucao da atividade atual
	 * e as que se seguem.
	 * 
	 * @return o codigo da ultima atividade associada a execucao da atividade atual
	 *         e as que se seguem.
	 */
	public String pegaUltimo() {
		if (this.subsquente == null)
			return this.codigoAtividade;
		return this.subsquente.pegaUltimo();
	}

	/**
	 * Metodo que mapeia o risco da atividade de acordo com seu nivel de risco.
	 * 
	 * @param risco Nivel de risco da atividade.
	 * @return um inteiro que representa o risco da atividade de acordo com seu
	 *         nivel de risco.
	 */
	public int mapRisco(String risco) {
		switch (risco) {
		case "ALTO":
			return 2;
		case "MEDIO":
			return 1;
		default:
			return 0;
		}
		
	}

	/**
	 * Metodo que navega nas atividades subsequentes buscando qual o risco mais
	 * elevado associado a execucao da atividade e as que se seguem.
	 * 
	 * @param altoRisco Atividade com o maior risco
	 * @return o codigo da atividade com o risco mais elevado associando a execucao
	 *         da atividade atual.
	 */
	private String pegaAtividadeRiscoAux(Atividade altoRisco) {
		if (this.subsquente != null) {
			if (mapRisco(altoRisco.nivelRisco) <= mapRisco(this.subsquente.nivelRisco)) {
				altoRisco = this.subsquente;
			}
			return this.subsquente.pegaAtividadeRiscoAux(altoRisco);
		}
		return altoRisco.codigoAtividade;
	}

	/**
	 * Metodo que navega nas atividades subsequentes buscando qual o risco mais
	 * elevado associado a execucao da atividade e as que se seguem.
	 * 
	 * @return o codigo da atividade com o risco mais elevado associando a execucao
	 *         da atividade atual.
	 */
	public String pegaMaiorRiscoAtividade() {
		return pegaAtividadeRiscoAux(this.subsquente);
	}

	// US10 Alisson

	/**
	 * Metodo que verifica se a atividade tem itens pendentes.
	 * 
	 * @return verdade se a atividade tiver itens pendentes, caso contrario retorna
	 *         falso.
	 */
	public boolean temPendencia() {
		boolean saida = false;
		for (Item e : itens.values()) {
			if (e.getStatus().equals("PENDENTE")) {
				saida = true;
				break;
			}
		}
		return saida;
	}

	/**
	 * Metodo que retorna o nivel de risco da atividade.
	 * 
	 * @return o nivel de risco da atividade.
	 */
	public String getNivelRisco() {
		return this.nivelRisco;
	}
}
