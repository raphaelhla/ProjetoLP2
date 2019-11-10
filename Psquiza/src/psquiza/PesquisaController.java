package psquiza;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import psquiza.Pesquisa;
import java.util.Map;
import psquiza.Validador;

public class PesquisaController {
	// US1 Matheus
	/**
	 * Classe que representa um controller de pesquisa.
	 * 
	 * @author matheus
	 * 
	 */

	/**
	 * Mapa com todas as pesquisas cadastradas no sistema.
	 */
	private Map<String, Pesquisa> mapPesquisas;

	/**
	 * Mapa com o codigo de identificacao das pesquisas.
	 */
	private Map<String, Integer> codigos;

	/**
	 * Validador utilizado para lançar excecoes.
	 */
	private Validador validador;

	public PesquisaController() {
		this.mapPesquisas = new HashMap<String, Pesquisa>();
		this.codigos = new HashMap<String, Integer>();
		this.validador = new Validador();
	}

	/***
	 * Metodo que vai cadastrar uma pesquisa, usando como parametro duas
	 * strings(descricao e campo de interesse).Nesse metodo tambem sera gerado um
	 * codigo que representa os 3 primeiros caracteres do campo de interesse.O
	 * metodo retorna esse codigo caso o cadastro seja realizado com sucesso.
	 * 
	 * @param descricao        - String que representa a descricao da pesquisa.
	 * @param campoDeInteresse - String que representa o campo de interesse da
	 *                         pesquisa.
	 * @return - codigo gerado da pesquisa.
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.verificaCampoDeInteresse(campoDeInteresse);
		String codigo = campoDeInteresse.substring(0, 3).toUpperCase();
		if (codigos.containsKey(codigo)) {
			int v = codigos.get(codigo);
			v += 1;
			codigos.remove(codigo);
			codigos.put(codigo, v);
		} else {
			codigos.put(codigo, 1);
		}
		String chave = codigo + codigos.get(codigo);
		mapPesquisas.put(chave, new Pesquisa(descricao, campoDeInteresse, chave));
		return chave;
	}

	/**
	 * Metodo que vai verificar se o codigo ja existe no mapPesquisas. Caso nao
	 * exista, ele mostrar a mensagem "Pesquisa nao encontrada.".
	 * 
	 * @param codigo - String que representa o codigo da pesquisa.
	 */
	private void verificaSeExistePesquisa(String codigo) {
		if (!mapPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Metodo que altera uma pesquisa. E passado um codigo que representa uma
	 * pesquisa, qual atributo vai ser alterado e o seu novo valor.
	 * 
	 * 
	 * @param codigo               - String codigo.
	 * @param conteudoASerAlterado - String que representa o atributo a ser
	 *                             alterado.
	 * @param novoConteudo         - String que representa o novo valor do atributo
	 *                             alterado.
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		if (mapPesquisas.get(codigo).getStatusPesquisa() == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		} else {
			if (conteudoASerAlterado.equals("DESCRICAO")) {
				validador.verificaEntradaNulaVazia(novoConteudo, "Descricao nao pode ser nula ou vazia.");
				mapPesquisas.get(codigo).setDescricao(novoConteudo);
			} else if (conteudoASerAlterado.equals("CAMPO")) {
				validador.verificaEntradaNulaVazia(novoConteudo, "Formato do campo de interesse invalido.");
				mapPesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
			} else {
				throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
			}
		}
	}

	/**
	 * Metodo que vai encerrar uma pesquisa de acordo com o codigo que representa
	 * ela.Tambem e passado como parametro o motivo do encerramento da pesquisa.
	 * 
	 * 
	 * @param codigo - String codigo.
	 * @param motivo - String que representa o motivo do encerramento da pesquisa.
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(motivo, "Motivo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		if (mapPesquisas.get(codigo).getStatusPesquisa() == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		mapPesquisas.get(codigo).desativaPesquisa();
	}

	/**
	 * Metodo que vai ativar uma pesquisa. Ele vai pegar a pesquisa representada
	 * pelo codigo e vai passar o seu status para true.
	 * 
	 * @param codigo - String codigo.
	 */
	public void ativaPesquisa(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		if (mapPesquisas.get(codigo).getStatusPesquisa() == true) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		}
		mapPesquisas.get(codigo).ativaPesquisa();
	}

	/**
	 * Metodo que vai exibir uma pesquisa de acordo com o codigo passado como
	 * parametro. Ele vai retornar esse codigo, caso exista, seguido do toString da
	 * pesquisa representada por esse codigo.
	 * 
	 * @param codigo - String codigo.
	 * @return - String que contem o codigo mais o toString da pesquisa a ser
	 *         exibida.
	 */
	public String exibePesquisa(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		return codigo + " - " + mapPesquisas.get(codigo).toString();
	}

	/**
	 * Metodo que vai verificar se a pesquisa associada ao codigo e ativa ou nao.
	 * Ela retorna o boolean StatusPesquisa(true ou false).
	 * 
	 * @param codigo - String codigo.
	 * @return - Boolean true ou false.
	 */
	public boolean ehAtiva(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		return mapPesquisas.get(codigo).getStatusPesquisa();
	}

	// US8 Raphael

	/**
	 * Metodo que associa uma atividade a uma pesquisa a partir do codigo da
	 * pesquisa, do codigo da atividade e da atividade.
	 * 
	 * @param codigoPesquisa  Codigo da pesquisa.
	 * @param codigoAtividade Codigo da atividade.
	 * @param atividade       Atividade a ser associada.
	 * @return verdadeiro se for associada com sucesso, caso contrario retorna falso
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade, Atividade atividade) {
		verificaSeExistePesquisa(codigoPesquisa);
		return this.mapPesquisas.get(codigoPesquisa).associaAtividade(codigoAtividade, atividade);
	}

	/**
	 * Metodo que desassocia uma atividade de uma pesquisa a partir do codigo da
	 * pesquisa e do codigo da atividade.
	 * 
	 * @param codigoPesquisa  Codigo da pesquisa.
	 * @param codigoAtividade Codigo da atividade.
	 * @return verdadeiro se for desassociada com sucesso, caso contrario retorna
	 *         falso
	 */
	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		verificaSeExistePesquisa(codigoPesquisa);
		return this.mapPesquisas.get(codigoPesquisa).desassociaAtividade(codigoAtividade);
	}
	
	public boolean atividadeEstaAssociada(String codigoAtividade) {
		boolean retorno = false;
		for (Pesquisa pesquisa : mapPesquisas.values()) {
			if (pesquisa.atividadeEstaAssociada(codigoAtividade)) {
				retorno = true;
				break;
			}
		}
		return retorno;
	}

	/**
	 * Metodo que recebe um termo como parametro e retorna uma string representando
	 * as pesquisas que possuem o termo.
	 * 
	 * @param termo Termo a ser procurado
	 * @return uma string que representa as pesquisas que possuem o termo
	 */
	public String busca(String termo) {
		String saida = "";
		List<String> stringAtividades = new ArrayList<>();
		List<String> chaves = new ArrayList<>(mapPesquisas.keySet());
		Collections.sort(chaves);
		Collections.reverse(chaves);
		for (String e : chaves) {
			if (mapPesquisas.get(e).buscaDescricao(termo)) {
				stringAtividades.add(e + ": " + mapPesquisas.get(e).getDescricao());
			}
			if (mapPesquisas.get(e).buscaCampoInteresse(termo)) {
				stringAtividades.add(e + ": " + mapPesquisas.get(e).getCampoDeInteresse());
			}
		}
		saida = String.join(" | ", stringAtividades);
		return saida;
	}

	// METODOS DA US5 (WESLLEY)

	/**
	 * Associa um problema a uma pesquisa a partir do codigo da pesquisa, codigo do
	 * problema, e o objeto problema.
	 * 
	 * @param codigoPesquisa Codigo da pesquisa.
	 * @param codigoProblema Codigo do problema.
	 * @param problema       Objeto problema.
	 * @return retorna true se o problema foi associado, e false se nao.
	 */
	public boolean associaProblema(String codigoPesquisa, String codigoProblema, Problema problema) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoProblema, "Campo codigoProblema nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigoPesquisa);
		return this.mapPesquisas.get(codigoPesquisa).associaProblema(codigoProblema, problema);
	}

	/**
	 * Desassocia um problema da pesquisa a partir do codigo da pesquisa.
	 * 
	 * @param codigoPesquisa Codigo da pesquisa.
	 * @return retorna true se foi desassociado e false se nao.
	 */
	public boolean desassociaProblema(String codigoPesquisa) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigoPesquisa);
		return this.mapPesquisas.get(codigoPesquisa).desassociaProblema();
	}

	/**
	 * Verifica se o objetivo ja esta associado a alguma pesquisa a partir do codigo
	 * do objetivo e do codigo da pesquisa.
	 * 
	 * @param codigoObjetivo Codigo do objetivo.
	 * @param codigoPesquisa Codigo da pesquisa.
	 */
	private void verificaAssociacaoObjetivo(String codigoObjetivo, String codigoPesquisa) {
		verificaSeExistePesquisa(codigoPesquisa);
		for (String chave : this.mapPesquisas.keySet()) {
			if (!chave.equals(codigoPesquisa)) {
				if (this.mapPesquisas.get(chave).getAssociacaoObjetivo(codigoObjetivo)) {
					throw new IllegalArgumentException("Objetivo ja associado a uma pesquisa.");
				}
			}
		}
	}

	/**
	 * Associa um objetivo a uma pesquisa a partir do codigo do objetivo e o objeto
	 * objetivo.
	 * 
	 * @param codigoPesquisa Codigo da pesquisa.
	 * @param codigoObjetivo Codigo do objetivo.
	 * @param objetivo       Objeto objetivo.
	 * @return retorna true se o objetivo foi associado e false se nao.
	 */
	public boolean associaObjetivo(String codigoPesquisa, String codigoObjetivo, Objetivo objetivo) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Campo codigoObjetivo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigoPesquisa);
		verificaAssociacaoObjetivo(codigoObjetivo, codigoPesquisa);
		return this.mapPesquisas.get(codigoPesquisa).associaObjetivo(codigoObjetivo, objetivo);
	}

	/**
	 * Desassocia o objetivo da pesquisa a partir do codigo da pesquisa e o codigo
	 * do objetivo.
	 * 
	 * @param codigoPesquisa Codigo da pesquisa.
	 * @param codigoObjetivo Codigo do objetivo.
	 * @return retorna true se o objetivo foi desassociado e false se nao.
	 */
	public boolean desassociaObjetivo(String codigoPesquisa, String codigoObjetivo) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Campo codigoObjetivo nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigoPesquisa);
		return this.mapPesquisas.get(codigoPesquisa).desassociaObjetivo(codigoObjetivo);
	}

	/**
	 * Retorna a representacao em String de todas as pesquisas de uma lista.
	 * 
	 * @param lista Lista de pesquisas.
	 * @return a representacao em String de todas as pesquisas da lista.
	 */
	private String getListToString(List<Pesquisa> lista) {
		List<String> saida = new ArrayList<>();
		for (Pesquisa p : lista) {
			saida.add(p.getCodigo() + " - " + p.toString());
		}
		return String.join(" | ", saida);
	}

	/**
	 * Metodo que ordena as pesquisas utilizando como criterio o codigo do problema
	 * associado e retorna a String que representa todas as pesquisas ordenadas.
	 * 
	 * @return a representacao em String de todas as pesquisas.
	 */
	private String ordernaPorProblema() {
		Comparator<Pesquisa> comparator = new OrdenarPorProblema();
		List<Pesquisa> listaPesquisas = new ArrayList<>(this.mapPesquisas.values());
		Collections.sort(listaPesquisas, comparator);
		Collections.reverse(listaPesquisas);
		return getListToString(listaPesquisas);
	}

	/**
	 * Metodo que ordena as pesquisas utilizando como criterio a quantidade de
	 * objetivos associados a pesquisa e retorna a String que representa todas as
	 * pesquisas ordenadas.
	 * 
	 * @return a representacao em String de todas as pesquisas.
	 */
	private String ordenaPorObjetivos() {
		Comparator<Pesquisa> comparator = new OrdenarPorObjetivos();
		List<Pesquisa> listaPesquisas = new ArrayList<>(this.mapPesquisas.values());
		Collections.sort(listaPesquisas, comparator);
		Collections.reverse(listaPesquisas);
		return getListToString(listaPesquisas);
	}

	/**
	 * Metodo que ordena as pesquisas utilizando como criterio o codigo das
	 * pesquisas e retorna a String que representa todas as pesquisas ordenadas.
	 * 
	 * @return a representacao em String de todas as pesquisas.
	 */
	private String ordenaPesquisa() {
		List<Pesquisa> listaPesquisas = new ArrayList<>(this.mapPesquisas.values());
		Collections.sort(listaPesquisas);
		Collections.reverse(listaPesquisas);
		return getListToString(listaPesquisas);
	}

	/**
	 * Metodo que ordena e retorna a representacao em String das pesquisas a partir
	 * do parametro ordem.
	 * 
	 * @param ordem A ordem de ordenacao.
	 * @return a representacao em String de todas as pesquisas.
	 */
	public String listaPesquisas(String ordem) {
		switch (ordem) {
		case "PROBLEMA":
			return ordernaPorProblema();
		case "OBJETIVOS":
			return ordenaPorObjetivos();
		case "PESQUISA":
			return ordenaPesquisa();
		default:
			throw new IllegalArgumentException("Valor invalido da ordem");
		}
	}

	// US6 MATHEUS
	
	/**
	 * Metodo que vai associar um pesquisador com uma pesquisa.
	 * 
	 * @param idPesquisa - o código da pesquisa a ser associada.
	 * @param emailPesquisador - o email que identifica o pesquisador a ser associado.
	 * @param pesquisador - objeto de pesquisador a ser criado para ser associado.
	 * @return - true ou false, caso a operaçao seja realizada com sucesso ou nao.
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador, Pesquisador pesquisador) {
		verificaSeExistePesquisa(idPesquisa);
		return this.mapPesquisas.get(idPesquisa).associaPesquisador(emailPesquisador, pesquisador);
	}

	/**
	 * Metodo que vai desassociar um pesquisador com uma pesquisa.
	 * 
	 * @param idPesquisa - o código da pesquisa a ser desassociada.
	 * @param emailPesquisador - o email que identifica o pesquisador a ser desassociado.
	 * @return - true ou false, caso a operaçao seja realizada com sucesso ou nao.
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		verificaSeExistePesquisa(idPesquisa);
		return this.mapPesquisas.get(idPesquisa).desassociaPesquisador(emailPesquisador);
	}
}
