package pesquisa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import atividade.Atividade;
import atividade.OrdenaPorDuracao;
import atividade.OrdenaPorMaiorRisco;
import atividade.OrdenaPorMenosPendencias;
import objetivo.Objetivo;
import pesquisador.Pesquisador;
import problema.Problema;
import psquiza.Validador;

// US1 Matheus

/**
 * Classe Pesquisa que representa uma pesquisa. Toda pesquisa contem as srings
 * descricao, campoDeIntresse, codigo.
 * 
 * @author matheus
 * 
 */
public class Pesquisa implements Comparable<Pesquisa>, Serializable {

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
	 * Validador utilizado para lancar excecoes.
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
	 * Mapa de pesquisadores associados.
	 */
	private Map<String, Pesquisador> pesquisadoresAssociados;

	/**
	 * Constroi uma pesquisa a partir de sua descricao, campo de interesse e codigo
	 * 
	 * @param descricao        String que representa a descricao da pesquisa.
	 * @param campoDeInteresse String que representa o campo de interesse da
	 *                         pesquisa.
	 * @param codigo           String que representa o codigo da pesquisa.
	 */
	public Pesquisa(String descricao, String campoDeInteresse, String codigo) {
		validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.verificaCampoDeInteresse(campoDeInteresse);
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		this.statusPesquisa = true;
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;
		this.codigo = codigo;
		this.atividadesAssociadas = new LinkedHashMap<String, Atividade>();
		this.problemaAssociado = new HashMap<String, Problema>();
		this.objetivosAssociados = new HashMap<String, Objetivo>();
		this.pesquisadoresAssociados = new HashMap<String, Pesquisador>();
	}

	/**
	 * Metodo que retorna a descricao da pesquisa.
	 * 
	 * @return String que representa a descricao da pesquisa.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * Metodo que vai setar um novo valor para a descricao da pesquisa.
	 * 
	 * @param descricao String que representa a nova descricao da pesquisa.
	 */
	public void setDescricao(String descricao) {
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		this.descricao = descricao;
	}

	/**
	 * Metodo que retorna o campo de interesse da pesquisa.
	 * 
	 * @return String que representa o campo de interesse da pesquisa.
	 */
	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}

	/**
	 * Metodo que vai setar um novo valor para o campo de interrese da pesquisa.
	 * 
	 * @param campoDeInteresse String que representa o novo campo de interesse da
	 *                         pesquisa.
	 */
	public void setCampoDeInteresse(String campoDeInteresse) {
		validador.verificaCampoDeInteresse(campoDeInteresse);
		this.campoDeInteresse = campoDeInteresse;
	}

	/**
	 * Metodo que retorna o status da pesquisa.
	 * 
	 * @return Boolean que representa o status da pesquisa.
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

	// US6 Matheus

	/**
	 * Metodo que associa uma atividade a uma pesquisa a partir do codigo da
	 * atividade e da atividade.
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @param atividade       Atividade a ser associada.
	 * @return verdadeiro se for associada com sucesso, caso contrario retorna falso
	 */
	public boolean associaAtividade(String codigoAtividade, Atividade atividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Codigo nao pode ser nulo ou vazio.");
		if (atividadesAssociadas.containsKey(codigoAtividade)) {
			return false;
		}
		if (!this.statusPesquisa) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		this.atividadesAssociadas.put(codigoAtividade, atividade);
		return true;
	}

	/**
	 * Metodo que desassocia uma atividade a uma pesquisa a partir do codigo da
	 * atividade.
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @return verdadeiro se for desassociada com sucesso, caso contrario retorna
	 *         falso
	 */
	public boolean desassociaAtividade(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Codigo nao pode ser nulo ou vazio.");
		if (!this.statusPesquisa) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		if (!atividadesAssociadas.containsKey(codigoAtividade)) {
			return false;
		}
		if (!atividadesAssociadas.containsKey(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade nao encontrada.");
		}

		this.atividadesAssociadas.remove(codigoAtividade);
		return true;
	}

	/**
	 * Metodo que vai verificar se a atividade esta associada ou nao.
	 * 
	 * @param codigoAtividade codigo da atividade a ser procurada.
	 * @return true ou false, caso a atividade esteja associada ou nao.
	 */
	public boolean atividadeEstaAssociada(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Codigo nao pode ser nulo ou vazio.");
		if (atividadesAssociadas.containsKey(codigoAtividade)) {
			return true;
		}
		return false;
	}

	// US8 Raphael

	/**
	 * Metodo que verifica se a descricao da pesquisa contem uma string passada como
	 * parametro e retorna um valor booleano.
	 * 
	 * @param termo Termo ser procurado.
	 * @return verdade se a descricao contem o termo, caso contrario retorna falso.
	 */
	public boolean buscaDescricao(String termo) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		return this.campoDeInteresse.contains(termo);
	}

	// METODOS DA US5 (WESLLEY)

	/**
	 * Associa um problema a uma pesquisa a partir do codigo do problema, e o objeto
	 * problema.
	 * 
	 * @param codigoProblema Codigo do problema.
	 * @param problema       Objeto problema.
	 * @return retorna true se o problema foi associado, e false se nao.
	 */
	public boolean associaProblema(String codigoProblema, Problema problema) {
		validador.verificaEntradaNulaVazia(codigoProblema, "Codigo nao pode ser nulo ou vazio.");
		if (problemaAssociado.containsKey(codigoProblema)) {
			return false;
		} else if (!this.statusPesquisa) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (problemaAssociado.size() > 0) {
			throw new IllegalArgumentException("Pesquisa ja associada a um problema.");
		}
		this.problemaAssociado.put(codigoProblema, problema);
		return true;

	}

	/**
	 * Desassocia o problema da pesquisa.
	 * 
	 * @return retorna true se foi desassociado e false se nao.
	 */
	public boolean desassociaProblema() {
		if (!this.statusPesquisa) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (problemaAssociado.size() == 0) {
			return false;
		}
		this.problemaAssociado.clear();
		return true;

	}

	/**
	 * Retorna se o objetivo esta associado ou nao a pesquisa a partir de seu
	 * codigo.
	 * 
	 * @param codigoObjetivo Codigo do objetivo.
	 * @return retorna true se o objetivo esta associado ou false se nao.
	 */
	public boolean getAssociacaoObjetivo(String codigoObjetivo) {
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Codigo nao pode ser nulo ou vazio.");
		return this.objetivosAssociados.containsKey(codigoObjetivo);
	}

	/**
	 * Associa um objetivo a uma pesquisa a partir do codigo do objetivo e o objeto
	 * objetivo.
	 * 
	 * @param codigoObjetivo Codigo do objetivo.
	 * @param objetivo       Objeto objetivo.
	 * @return retorna true se o objetivo foi associado e false se nao.
	 */
	public boolean associaObjetivo(String codigoObjetivo, Objetivo objetivo) {
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Codigo nao pode ser nulo ou vazio.");
		if (!this.statusPesquisa) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (objetivosAssociados.containsKey(codigoObjetivo)) {
			return false;
		}
		this.objetivosAssociados.put(codigoObjetivo, objetivo);
		return true;

	}

	/**
	 * Desassocia o objetivo da pesquisa a partir de seu codigo.
	 * 
	 * @param codigoObjetivo Codigo do objetivo.
	 * @return retorna true se o objetivo foi desassociado e false se nao.
	 */
	public boolean desassociaObjetivo(String codigoObjetivo) {
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Codigo nao pode ser nulo ou vazio.");
		if (!this.statusPesquisa) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (!objetivosAssociados.containsKey(codigoObjetivo)) {
			return false;
		}
		this.objetivosAssociados.remove(codigoObjetivo);
		return true;

	}

	/**
	 * Retorna a String que representa o codigo da pesquisa.
	 * 
	 * @return a representacao em String do codigo da pesquisa.
	 */
	public String getCodigo() {
		return this.codigo;
	}

	/**
	 * Retorna um numero do tipo int que representa a quantidade de objetivos
	 * associados a pesquisa.
	 * 
	 * @return a representacao em int da quantidade de objetivos associados a
	 *         pesquisa.
	 */
	public int getQtdObjetivo() {
		return this.objetivosAssociados.size();
	}

	/**
	 * Compara o codigo de duas pesquisas, necessario para ordenacao.
	 */
	@Override
	public int compareTo(Pesquisa p) {
		return this.codigo.compareTo(p.getCodigo());
	}

	/**
	 * Retorna se a pesquisa possui problema associado.
	 * 
	 * @return retorna true se a pesquisa possui problema associados e false se nao.
	 */
	public boolean getTemProblemaAssociado() {
		return (this.problemaAssociado.size() > 0);
	}

	/**
	 * Retorna o codigo do problema associado a pesquisa.
	 * 
	 * @return a representacao em String do codigo associado a pesquisa.
	 */
	public String getCodigoProblema() {
		List<String> lista;
		if (this.problemaAssociado.size() > 0) {
			lista = new ArrayList<>(problemaAssociado.keySet());
			return lista.get(0);
		} else {
			throw new IllegalArgumentException("Pesquisa sem problema associado.");
		}
	}

	// US6 Matheus

	/**
	 * Metodo que vai associar um pesquisador com uma pesquisa.
	 * 
	 * @param emailPesquisador o email que identifica o pesquisador a ser associado.
	 * @param pesquisador      objeto de pesquisador que representa o pesquisador a
	 *                         ser associado.
	 * @return true ou false, caso a operacao seja realizada com sucesso ou nao.
	 */
	public boolean associaPesquisador(String emailPesquisador, Pesquisador pesquisador) {
		validador.verificaEntradaNulaVazia(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		if (!this.statusPesquisa) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (this.pesquisadoresAssociados.containsKey(emailPesquisador)) {
			return false;
		}
		this.pesquisadoresAssociados.put(emailPesquisador, pesquisador);
		return true;
	}

	/**
	 * Metodo que vai desassociar um pesquisador com uma pesquisa.
	 * 
	 * @param emailPesquisador o email que identifica o pesquisador a ser
	 *                         desassociado.
	 * @return true ou false, caso a operacao seja realizada com sucesso ou nao.
	 */
	public boolean desassociaPesquisador(String emailPesquisador) {
		validador.verificaEntradaNulaVazia(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		if (!this.statusPesquisa) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else if (!this.pesquisadoresAssociados.containsKey(emailPesquisador)) {
			return false;
		}
		this.pesquisadoresAssociados.remove(emailPesquisador);
		return true;
	}

	// US10 Alisson

	/**
	 * Metodo que verifica se a pesquisa possui atividades pendentes.
	 * 
	 * @return o valor booleano verdade se a pesquisa possuir atividades pendentes,
	 *         caso contrario retorna falso.
	 */
	public boolean verificaSeTemPendencia() {
		boolean saida = false;
		for (Atividade e : atividadesAssociadas.values()) {
			if (e.temPendencia()) {
				saida = true;
				break;
			}
		}
		return saida;
	}

	/**
	 * Metodo que retorna o codigo da atividade mais antiga associada a pesquisa e
	 * que tenha itens pendentes.
	 * 
	 * @return o codigo da atividade mais antiga associada a pesquisa e que tenha
	 *         itens pendentes.
	 */
	public String estrategiaMaisAntiga() {
		Atividade tmp = null;
		for (Atividade atividade : atividadesAssociadas.values()) {
			if (atividade.temPendencia()) {
				tmp = atividade;
				break;
			}
		}
		return tmp.getCodigo();
	}

	/**
	 * Metodo que retorna o codigo da atividade que tenha a menor quantidade de
	 * itens pendentes associada a pesquisa.
	 * 
	 * @return o codigo da atividade que tenha a menor quantidade de itens pendentes
	 *         associada a pesquisa.
	 */
	public String estrategiaMenosPendencias() {
		List<Atividade> atividadesOrdenadas = new ArrayList<>(atividadesAssociadas.values());
		Comparator<Atividade> comparator = new OrdenaPorMenosPendencias();
		Collections.sort(atividadesOrdenadas, comparator);
		Atividade tmp = null;
		for (Atividade atividade : atividadesOrdenadas) {
			if (atividade.temPendencia()) {
				tmp = atividade;
				break;
			}
		}
		return tmp.getCodigo();
	}

	/**
	 * Metodo que retorna o codigo da proxima atividade que tenha o maior nivel de
	 * risco e que tenha itens pendentes.
	 * 
	 * @return o codigo da proxima atividade que tenha o maior nivel de risco e que
	 *         tenha itens pendentes.
	 */
	public String estrategiaMaiorRisco() {
		List<Atividade> atividadesOrdenadas = new ArrayList<>(atividadesAssociadas.values());
		Comparator<Atividade> comparator = new OrdenaPorMaiorRisco();
		Collections.sort(atividadesOrdenadas, comparator);
		Atividade atividadeMaior = null;
		for (Atividade atividade : atividadesOrdenadas) {
			if (atividade.temPendencia()) {
				atividadeMaior = atividade;
				break;
			}
		}
		return atividadeMaior.getCodigo();
	}

	/**
	 * Metodo que retorna o codigo da proxima atividade que tenha a maior duracao e
	 * que tenha itens pendentes.
	 * 
	 * @return o codigo da proxima atividade que tenha a maior duracao e que tenha
	 *         itens pendentes.
	 */
	public String estrategiaMaiorDuracao() {
		List<Atividade> atividadesOrdenadas = new ArrayList<>(atividadesAssociadas.values());
		Comparator<Atividade> comparator = new OrdenaPorDuracao();
		Collections.sort(atividadesOrdenadas, comparator);
		Atividade tmp = null;
		for (Atividade atividade : atividadesOrdenadas) {
			if (atividade.temPendencia()) {
				tmp = atividade;
				break;
			}
		}
		return tmp.getCodigo();
	}
}
