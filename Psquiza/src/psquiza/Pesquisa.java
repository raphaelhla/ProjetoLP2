package psquiza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// US1 Matheus

/**
 * Classe Pesquisa que representa uma pesquisa. Toda pesquisa contem as srings
 * descricao, campoDeIntresse, codigo.
 * 
 */
public class Pesquisa implements Comparable<Pesquisa> {

	/**
	 * String que representa a descricao de uma pesquisa.
	 */
	private String descricao;

	/**
	 * String que representa o campo de interesse de uma pesquisa.
	 */
	private String campoDeInteresse;

	/**
	 * String que representa o codigo de uma pesquisa.
	 */
	private String codigo;

	/**
	 * Boolean que representa o status da pesquisa.
	 */
	private boolean statusPesquisa;

	/**
	 * Validador utilizado para lançar excecoes.
	 */
	private Validador validador;

	/**
	 * Mapa de atividades associadas.
	 */
	private Map<String, Atividade> atividadesAssociadas;

	/**
	 * Mapa de problemas associados.
	 */
	private Map<String, Problema> problemaAssociado;

	/**
	 * Mapa de objetivos associados.
	 */
	private Map<String, Objetivo> objetivosAssociados;

	/**
	 * Constroi uma pesquisa a partir de sua descricao, campo de interesse e codigo
	 * 
	 * @param descricao        - String que representa a descricao da pesquisa.
	 * @param campoDeInteresse - String que representa o campo de interesse da
	 *                         pesquisa.
	 * @param codigo           - String que representa o codigo da pesquisa.
	 */
	public Pesquisa(String descricao, String campoDeInteresse, String codigo) {
		validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		this.statusPesquisa = true;
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
		this.atividadesAssociadas = new HashMap<String, Atividade>();
		this.problemaAssociado = new HashMap<String, Problema>();
		this.objetivosAssociados = new HashMap<String, Objetivo>();
	}

	/**
	 * Metodo que retorna a descricao da pesquisa.
	 * 
	 * @return - String que representa a descricao da pesquisa.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo que vai setar um novo valor para a descricao da pesquisa.
	 * 
	 * @param descricao - String que representa a nova descricao da pesquisa.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Metodo que retorna o campo de interesse da pesquisa.
	 * 
	 * @return - String que representa o campo de interesse da pesquisa.
	 */
	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}

	/**
	 * Metodo que vai setar um novo valor para o campo de interrese da pesquisa.
	 * 
	 * @param campoDeInteresse - String que representa o novo campo de interesse da
	 *                         pesquisa.
	 */
	public void setCampoDeInteresse(String campoDeInteresse) {
		this.campoDeInteresse = campoDeInteresse;
	}

	/**
	 * Metodo que retorna o status da pesquisa.
	 * 
	 * @return - Boolean que representa o status da pesquisa.
	 */
	public boolean getStatusPesquisa() {
		return statusPesquisa;
	}

	/**
	 * Metodo que vai ativar a pesquisa. Ele seta seu status para true.
	 * 
	 */
	public void ativaPesquisa() {
		this.statusPesquisa = true;
	}

	/**
	 * Metodo que vai desativar uma pesquisa. Ele seta seu status para false.
	 * 
	 */
	public void desativaPesquisa() {
		this.statusPesquisa = false;
	}

	/**
	 * Metodo toString que vai retornar uma string contendo a descricao da pesquisa
	 * mais o campo de interesse da pesquisa.
	 * 
	 */
	@Override
	public String toString() {
		return this.descricao + " - " + this.campoDeInteresse;
	}

	// US8 Raphael
	
	/**
	 * Metodo que associa uma atividade a uma pesquisa a partir do codigo da atividade e da atividade.
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @param atividade       Atividade a ser associada.
	 * @return verdadeiro se for associada com sucesso, caso contrario retorna falso
	 */
	public boolean associaAtividade(String codigoAtividade, Atividade atividade) {
		if (atividadesAssociadas.containsKey(codigoAtividade)) {
			return false;
		}
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		this.atividadesAssociadas.put(codigoAtividade, atividade);
		return true;
	}

	/**
	 * Metodo que desassocia uma atividade a uma pesquisa a partir do codigo da atividade.
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @return verdadeiro se for desassociada com sucesso, caso contrario retorna falso
	 */
	public boolean desassociaAtividade(String codigoAtividade) {
		if (!atividadesAssociadas.containsKey(codigoAtividade)) {
			return false;
		}
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (!atividadesAssociadas.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}
		this.atividadesAssociadas.remove(codigoAtividade);
		return true;
	}

	/**
	 * Metodo que verifica se a descricao da pesquisa contem uma string passada como
	 * parametro e retorna um valor booleano.
	 * 
	 * @param termo Termo ser procurado.
	 * @return verdade se a descricao contem o termo, caso contrario retorna falso.
	 */
	public boolean buscaDescricao(String termo) {
		return this.descricao.contains(termo);
	}

	/**
	 * Metodo que verifica se o campo de interesse da pesquisa contem uma string
	 * passada como parametro e retorna um valor booleano.
	 * 
	 * @param termo Termo ser procurado.
	 * @return verdade se o campo de interesse contem o termo, caso contrario
	 *         retorna falso.
	 */
	public boolean buscaCampoInteresse(String termo) {
		return this.campoDeInteresse.contains(termo);
	}

	// US5 WESLLEY

	public boolean associaProblema(String codigoProblema, Problema problema) {
		if (problemaAssociado.containsKey(codigoProblema)) {
			return false;
		} else if (problemaAssociado.size() > 0) {
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		} else if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else {
			this.problemaAssociado.put(codigoProblema, problema);
			return true;
		}
	}

	public boolean desassociaProblema(String codigoProblema) {
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (!problemaAssociado.containsKey(codigoProblema)) {
			return false;
		} else {
			this.problemaAssociado.remove(codigoProblema);
			return true;
		}
	}

	public boolean getAssociacaoObjetivo(String codigoObjetivo) {
		return this.objetivosAssociados.containsKey(codigoObjetivo);
	}

	public boolean associaObjetivo(String codigoObjetivo, Objetivo objetivo) {
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (objetivosAssociados.containsKey(codigoObjetivo)) {
			return false;
		} else {
			this.objetivosAssociados.put(codigoObjetivo, objetivo);
			return true;
		}
	}

	public boolean desassociaObjetivo(String codigoObjetivo) {
		if (this.statusPesquisa == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (!objetivosAssociados.containsKey(codigoObjetivo)) {
			return false;
		} else {
			this.objetivosAssociados.remove(codigoObjetivo);
			return true;
		}
	}

	public String getCodigo() {
		return this.codigo;
	}

	public int getQtdObjetivo() {
		return this.objetivosAssociados.size();
	}

	@Override
	public int compareTo(Pesquisa p) {
		return this.codigo.compareTo(p.getCodigo());
	}

	public boolean getTemProblemaAssociado() {
		return (this.problemaAssociado.size() > 0);
	}

	public String getCodigoProblema() {
		List<String> lista;
		if (this.problemaAssociado.size() > 0) {
			lista = new ArrayList<>(problemaAssociado.keySet());
			return lista.get(0);
		}
		return "Sem problemas associados.";
	}
}
