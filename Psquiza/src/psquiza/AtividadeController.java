package psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Representacao de um controller de atividade.
 * 
 * @author Weslley Azevedo 119111241
 *
 */
public class AtividadeController {

	/**
	 * Validador utilizado para validar entradas do sistema.
	 */
	private Validador validador;

	/**
	 * Mapa que contem todas as atividades cadastradas no sistema. As atividades sao
	 * acessadas por meio de um codigo em String gerador pelo sistema.
	 */
	private Map<String, Atividade> mapaAtividades;

	/**
	 * Indice utilizado para gerar o codigo de identificacao das atividades.
	 */
	private int indice;

	/**
	 * Constroi um controller de atividades.
	 */
	public AtividadeController() {
		this.validador = new Validador();
		this.mapaAtividades = new HashMap<String, Atividade>();
		this.indice = 1;
	}

	/**
	 * Metodo que cadastra uma atividade a partir da sua descricao, nivel de risco e
	 * descricao do risco.
	 * 
	 * @param descricao      Descricao da atividade.
	 * @param nivelRisco     Nivel de risco da atividade.
	 * @param descricaoRisco Descricao do risco da atividade.
	 * @return a representacao em String do codigo de identificacao da atividade.
	 */
	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		validador.verificaEntradaNulaVazia(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.verificaNivelRisco(nivelRisco);
		validador.verificaEntradaNulaVazia(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		Atividade atividade = new Atividade(descricao, nivelRisco, descricaoRisco);
		String codigoAtividade = "A" + this.indice++;
		this.mapaAtividades.put(codigoAtividade, atividade);
		return codigoAtividade;
	}

	/**
	 * Verifica se a atividade existe a partir de seu codigoAtividade.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 */
	private void verificaSeExisteAtividade(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if (!mapaAtividades.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Apaga uma atividade a partir de seu codigoAtividade.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 */
	public void apagaAtividade(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigoAtividade);
		this.mapaAtividades.remove(codigoAtividade);
	}

	/**
	 * Cadastra um item na atividade a partir do codigoAtividade da atividade e a String que
	 * representa o item.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @param item   Representacao em String do item.
	 */
	public void cadastraItem(String codigoAtividade, String item) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(item, "Item nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigoAtividade);
		this.mapaAtividades.get(codigoAtividade).cadastraItem(item);
	}

	/**
	 * Retorna a String que representa a atividade.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @return a representacao em String da atividade.
	 */
	public String exibeAtividade(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigoAtividade);
		return this.mapaAtividades.get(codigoAtividade).toString();
	}

	/**
	 * Retorna a quantidade de itens pendentes contidos em determinada atividade.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @return a representacao em Int da quantidade de itens pendentes.
	 */
	public int contaItensPendentes(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigoAtividade);
		return this.mapaAtividades.get(codigoAtividade).getQtdItensPendentes();
	}

	/**
	 * Retorna a quantidade de itens realizados contidos em determinada atividade.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @return a representacao em Int da quantidade de itens realizados.
	 */
	public int contaItensRealizados(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigoAtividade);
		return this.mapaAtividades.get(codigoAtividade).getQtdItensRealizados();
	}

	/**
	 * Metodo que retorna uma atividade, a partir do codigoAtividade da atividade.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @return uma atividade
	 */
	public Atividade getAtividade(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigoAtividade);
		return this.mapaAtividades.get(codigoAtividade);
	}

	/**
	 * Metodo que executa uma atividade a partir do codigoAtividade da atividade, do item e
	 * da duracao da atividade. Quando um item e executado, seu status e alterado
	 * para "REALIZADO".
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @param item            Item executado.
	 * @param duracao         Duracao da execucao do item.
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.verificaNum(item,"Item nao pode ser nulo ou negativo.");
		validador.verificaNum(duracao,"Duracao nao pode ser nula ou negativa.");
		this.mapaAtividades.get(codigoAtividade).executaAtividade(item, duracao);

	}

	/**
	 * Metodo que cadastra um resultado para uma atividade a partir do codigoAtividade da
	 * atividade e do resultado a ser cadastrado.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @param resultado       Resultado a ser cadastrado.
	 * @return o inteiro que representa o resultado
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(resultado, "Resultado nao pode ser nulo ou vazio.");
		return this.mapaAtividades.get(codigoAtividade).cadastraResultado(resultado);
	}

	/**
	 * Metodo que remove um resultado de uma atividade a partir do codigoAtividade da
	 * atividade e do numero que representa o resultado a ser removido.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @param numeroResultado Numero do resultado.
	 * @return verdadeiro se a remocao for bem sucedida, caso contrario retorna
	 *         falso.
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.verificaNum(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
		verificaSeExisteAtividade(codigoAtividade);
		return this.mapaAtividades.get(codigoAtividade).removeResultado(numeroResultado);
	}

	/**
	 * Metodo que retorna uma string com todos o resultados de uma atividade passada
	 * como parametro.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @return uma string com todos o resultados de uma atividade.
	 */
	public String listaResultados(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigoAtividade);
		return this.mapaAtividades.get(codigoAtividade).listaResultados();
	}

	/**
	 * Metodo que retorna o inteiro que representa a duracao da execucao de todos os
	 * itens de uma atividade passada como parametro.
	 * 
	 * @param codigoAtividade codigo da atividade.
	 * @return o inteiro que representa a duracao da execucao de todos os itens de
	 *         uma atividade
	 */
	public int getDuracao(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigoAtividade);
		return this.mapaAtividades.get(codigoAtividade).getDuracao();
	}

	/**
	 * Metodo que recebe um termo como parametro e retorna uma string representando
	 * as atividades que possuem o termo.
	 * 
	 * @param termo Termo a ser procurado
	 * @return uma string que representa as atividades que possuem o termo
	 */
	public String busca(String termo) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		String saida = "";
		List<String> stringAtividades = new ArrayList<>();
		List<String> chaves = new ArrayList<>(mapaAtividades.keySet());
		Collections.sort(chaves);
		Collections.reverse(chaves);
		for (String e : chaves) {
			if (mapaAtividades.get(e).buscaDescricao(termo)) {
				stringAtividades.add(e + ": " + mapaAtividades.get(e).getDescricao());
			}
			if (mapaAtividades.get(e).buscaDescricaoRisco(termo)) {
				stringAtividades.add(e + ": " + mapaAtividades.get(e).getDescricaoRisco());
			}
		}
		saida = String.join(" | ", stringAtividades);
		return saida;
	}
}
