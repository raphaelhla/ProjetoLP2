package psquiza;

import java.util.ArrayList;
import java.util.List;

/**
 * Representacao de uma atividade. Toda atividade precisa ter uma descricao,
 * nivel do risco, descricao do risco
 * 
 * @author Weslley Azevedo - 11911241
 *
 */
public class Atividade {

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
	 * Lista que contem todos os itens cadastrados na atividade.
	 */
	private List<Item> itens;

	/**
	 * Constroi uma atividade a partir da sua descricao, nivel de risco e descricao
	 * do risco.
	 * 
	 * @param descricao      Descricao da atividade.
	 * @param nivelRisco     Nivel do risco da atividade.
	 * @param descricaoRisco Descricao do risco da atividade.
	 */
	public Atividade(String descricao, String nivelRisco, String descricaoRisco) {
		this.validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		validador.verificaNivelRisco(nivelRisco);
		validador.verificaEntradaNulaVazia(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		this.descricao = descricao;
		this.nivelRisco = nivelRisco;
		this.descricaoRisco = descricaoRisco;
		this.itens = new ArrayList<>();
	}

	/**
	 * Cadastra um item na atividade a partir da String de representacao do item.
	 * 
	 * @param item a representacao em String do item.
	 */
	public void cadastraItem(String item) {
		validador.verificaEntradaNulaVazia(item, "Item nao pode ser nulo ou vazio.");
		this.itens.add(new Item(item));
	}

	/**
	 * Retorna a quantidade de itens pendentes contidos nessa atividade.
	 * 
	 * @return a representacao em Int da quantidade de itens pendentes.
	 */
	public int getQtdItensPendentes() {
		int saida = 0;
		for (Item item : itens) {
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
		for (Item item : itens) {
			if (item.getStatus().equals("REALIZADO")) {
				saida++;
			}
		}
		return saida;
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
		for (int i = 0; i < this.itens.size(); i++) {
			saida += " | " + this.itens.get(i).toString();
		}
		return saida;
	}

	public void executaAtividade(int item, int duracao) {
		this.itens.get(item -1).executaItem(duracao);
		
	}

	public int getDuracao() {
		int duracao = 0;
		for (Item item : itens) {
			duracao += item.getDuracao();
		}
		return duracao;
	}

	public String buscaItem(String termo) {
		String saida = "";
		List<String> stringItens = new ArrayList<>();
		for (Item e : itens) {
			if (e.busca(termo)) {
				stringItens.add(e.getDescricao());
			}
		}
		saida = String.join(" | ", stringItens);
		return saida;
	}
	
	public boolean buscaDescricao(String termo) {
		if (this.descricao.contains(termo)) {
			return true;
		}
		return false;
	}
	
	public boolean buscaDescricaoRisco(String termo) {
		if (this.descricaoRisco.contains(termo)) {
			return true;
		}
		return false;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public String getDescricaoRisco() {
		return descricaoRisco;
	}

}
