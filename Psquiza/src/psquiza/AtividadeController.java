package psquiza;

import java.util.HashMap;
import java.util.Map;

/**
 * Representacao de um controller de atividade.
 * 
 * @author Weslley Azevedo - 119111241 weslley
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
		validador.verificaEntradaNulaVazia(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.verificaNivelRisco(nivelRisco);
		validador.verificaEntradaNulaVazia(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		Atividade atividade = new Atividade(descricao, nivelRisco, descricaoRisco);
		String codigo = "A" + this.indice++;
		this.mapaAtividades.put(codigo, atividade);
		return codigo;
	}

	/**
	 * Verifica se a atividade existe a partir de seu codigo.
	 * 
	 * @param codigo Codigo da atividade.
	 */
	private void verificaSeExisteAtividade(String codigo) {
		if (!mapaAtividades.containsKey(codigo)) {
			throw new IllegalArgumentException("Atividade nao encontrada");
		}
	}

	/**
	 * Apaga uma atividade a partir de seu codigo.
	 * 
	 * @param codigo Codigo da atividade.
	 */
	public void apagaAtividade(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		this.mapaAtividades.remove(codigo);
	}

	/**
	 * Cadastra um item na atividade a partir do codigo da atividade e a String que
	 * representa o item.
	 * 
	 * @param codigo Codigo da atividade.
	 * @param item   Representacao em String do item.
	 */
	public void cadastraItem(String codigo, String item) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(item, "Item nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		this.mapaAtividades.get(codigo).cadastraItem(item);
	}

	/**
	 * Retorna a String que representa a atividade.
	 * 
	 * @param codigo Codigo da atividade.
	 * @return a representacao em String da atividade.
	 */
	public String exibeAtividade(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		return this.mapaAtividades.get(codigo).toString();
	}

	/**
	 * Retorna a quantidade de itens pendentes contidos em determinada atividade.
	 * 
	 * @param codigo Codigo da atividade.
	 * @return a representacao em Int da quantidade de itens pendentes.
	 */
	public int contaItensPedentes(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		return this.mapaAtividades.get(codigo).getQtdItensPendentes();
	}

	/**
	 * Retorna a quantidade de itens realizados contidos em determinada atividade.
	 * 
	 * @param codigo Codigo da atividade.
	 * @return a representacao em Int da quantidade de itens realizados.
	 */
	public int contaItensRealizados(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		verificaSeExisteAtividade(codigo);
		return this.mapaAtividades.get(codigo).getQtdItensRealizados();
	}

}
