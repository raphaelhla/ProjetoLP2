package pesquisa;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import atividade.Atividade;
import objetivo.Objetivo;
import pesquisador.Pesquisador;
import problema.Problema;
import psquiza.Validador;

/**
 * Classe que representa um controller de pesquisa.
 * 
 */
public class PesquisaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7770112965757922431L;

	/**
	 * Mapa com todas as pesquisas cadastradas no sistema.
	 */
	private Map<String, Pesquisa> mapPesquisas;

	/**
	 * Mapa com o codigo de identificacao das pesquisas.
	 */
	private Map<String, Integer> codigos;

	private String estrategia;

	/**
	 * Validador utilizado para lancar excecoes.
	 */
	private Validador validador;

	/**
	 * Constroi um controller de pesquisa.
	 */
	public PesquisaController() {
		this.mapPesquisas = new HashMap<String, Pesquisa>();
		this.codigos = new HashMap<String, Integer>();
		this.validador = new Validador();
		this.estrategia = "MAIS_ANTIGA";
	}

	/***
	 * Metodo que cadastra uma pesquisa, usando como parametro duas
	 * Strings(descricao e campo de interesse). Nesse metodo tambem sera gerado um
	 * codigo que representa os 3 primeiros caracteres do campo de interesse. O
	 * metodo retorna esse codigo caso o cadastro seja realizado com sucesso.
	 * 
	 * @param descricao        String que representa a descricao da pesquisa.
	 * @param campoDeInteresse String que representa o campo de interesse da
	 *                         pesquisa.
	 * @return codigo gerado da pesquisa.
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
	 * @param codigo String que representa o codigo da pesquisa.
	 */
	private void verificaSeExistePesquisa(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		if (!mapPesquisas.containsKey(codigo)) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}
	}

	/**
	 * Metodo que altera uma pesquisa. E passado um codigo que representa uma
	 * pesquisa, qual atributo vai ser alterado e o seu novo valor.
	 * 
	 * @param codigo               String codigo.
	 * @param conteudoASerAlterado String que representa o atributo a ser alterado.
	 * @param novoConteudo         String que representa o novo valor do atributo
	 *                             alterado.
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(conteudoASerAlterado, "conteudoASerAlterado nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigo);
		if (mapPesquisas.get(codigo).getStatusPesquisa() == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		switch (conteudoASerAlterado) {
		case "DESCRICAO":
			validador.verificaEntradaNulaVazia(novoConteudo, "Descricao nao pode ser nula ou vazia.");
			mapPesquisas.get(codigo).setDescricao(novoConteudo);
			break;
		case "CAMPO":
			validador.verificaEntradaNulaVazia(novoConteudo, "Formato do campo de interesse invalido.");
			mapPesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
			break;
		default:
			throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
		}
	}

	/**
	 * Metodo que encerra uma pesquisa a partir do codigo da pesquisa e do motivo de
	 * encerramento.
	 * 
	 * @param codigo String codigo.
	 * @param motivo String que representa o motivo do encerramento da pesquisa.
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
	 * @param codigo String codigo.
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
	 * @param codigo String codigo.
	 * @return String que contem o codigo mais o toString da pesquisa a ser exibida.
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
	 * @param codigo String codigo.
	 * @return Boolean true ou false.
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
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(codigoPesquisa);
		return this.mapPesquisas.get(codigoPesquisa).desassociaAtividade(codigoAtividade);
	}

	/**
	 * Retorna um boolean que representa se a atividade esta associada a uma
	 * pesquisa.
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @return retorna true se a atividade esta associada a alguma pesquisa e false
	 *         se nao.
	 */
	public boolean atividadeEstaAssociada(String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
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
	 * @return retorna true se o problema foi associado e false se nao.
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
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Campo codigoObjetivo nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(ordem, "Valor invalido da ordem");
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
	 * @param idPesquisa       o codigo da pesquisa a ser associada.
	 * @param emailPesquisador o email que identifica o pesquisador a ser associado.
	 * @param pesquisador      objeto de pesquisador a ser criado para ser
	 *                         associado.
	 * @return true ou false, caso a operacao seja realizada com sucesso ou nao.
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador, Pesquisador pesquisador) {
		validador.verificaEntradaNulaVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(idPesquisa);
		return this.mapPesquisas.get(idPesquisa).associaPesquisador(emailPesquisador, pesquisador);
	}

	/**
	 * Metodo que vai desassociar um pesquisador com uma pesquisa.
	 * 
	 * @param idPesquisa       o codigo da pesquisa a ser desassociada.
	 * @param emailPesquisador o email que identifica o pesquisador a ser
	 *                         desassociado.
	 * @return true ou false, caso a operacao seja realizada com sucesso ou nao.
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.verificaEntradaNulaVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		verificaSeExistePesquisa(idPesquisa);
		return this.mapPesquisas.get(idPesquisa).desassociaPesquisador(emailPesquisador);
	}

	// US10 Alisson

	/**
	 * Metodo que configura uma estrategia para utilizar no metodo proximaAtividade.
	 * 
	 * @param estrategia Estrategia.
	 */
	public void configuraEstrategia(String estrategia) {
		validador.verificaEstrategia(estrategia);
		this.estrategia = estrategia;
	}

	/**
	 * Metodo que retorna a o codigo da proxima atividade de acordo com uma
	 * estrategia configurada anteriormente a partir do codigo de uma pesquisa. Caso
	 * a estrategia nao tenha sido configurada o metodo utiliza a estrategia
	 * "MAIS_ANTIGA".
	 * 
	 * @param codigoPesquisa Codigo da pesquisa.
	 * @return o codigo da proxima atividade de acordo com uma estrategia
	 *         configurada anteriormente.
	 */
	public String proximaAtividade(String codigoPesquisa) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		verificaSeExistePesquisa(codigoPesquisa);
		Pesquisa pesquisa = mapPesquisas.get(codigoPesquisa);
		if (!pesquisa.getStatusPesquisa())
			throw new IllegalArgumentException("Pesquisa desativada.");
		if (!pesquisa.verificaSeTemPendencia())
			throw new IllegalArgumentException("Pesquisa sem atividades com pendencias.");

		switch (this.estrategia) {
		case "MAIS_ANTIGA":
			return pesquisa.estrategiaMaisAntiga();
		case "MENOS_PENDENCIAS":
			return pesquisa.estrategiaMenosPendencias();
		case "MAIOR_RISCO":
			return pesquisa.estrategiaMaiorRisco();
		case "MAIOR_DURACAO":
			return pesquisa.estrategiaMaiorDuracao();
		default:
			return pesquisa.estrategiaMaisAntiga();
		}
	}

	// US11 Matheus
	
	

	/**
	* Metodo que grava em um arquivo .txt o resumo da pesquisa.
	* 
	* @param codigoPesquisa o codigo da pesquisa a ser gravada.
	*/
	public void gravarResumo(String codigoPesquisa) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		verificaSeExistePesquisa(codigoPesquisa);
		try {
			PrintWriter gravarArqquivo = new PrintWriter(new FileWriter("CODIGO.txt"));
			gravarArqquivo.print(mapPesquisas.get(codigoPesquisa).exibePesquisa());
			gravarArqquivo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que grava em um arquivo .txt o resultado da pesquisa.
	 * 
	 * @param codigoPesquisa O codigo da pesquisa a ser gravada.
	 */
	public void gravarResultado(String codigoPesquisa) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		verificaSeExistePesquisa(codigoPesquisa);
		try {
			PrintWriter gravarArquivo = new PrintWriter(new FileWriter(codigoPesquisa + "-Resultados.txt"));
			gravarArquivo.print("-Pesquisa: " + exibePesquisa(codigoPesquisa));
			gravarArquivo.print("-Resultados:");
			gravarArquivo.print("-Descrição");
			gravarArquivo.print("");
			gravarArquivo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
