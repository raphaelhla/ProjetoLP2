package psquiza;

import java.util.ArrayList;
import java.util.List;

/**
 * Representacao de um controller geral.
 */
public class ControllerGeral {

	/**
	 * Controller de pesquisa.
	 */
	private PesquisaController pesquisaController;

	/**
	 * Controler de pesquisador.
	 */
	private PesquisadorController pesquisadorController;

	/**
	 * Controller de problema.
	 */
	private ProblemaController problemaController;

	/**
	 * Controller de objetivo.
	 */
	private ObjetivoController objetivoController;

	/**
	 * Controller de Atividade.
	 */
	private AtividadeController atividadeController;

	/**
	 * Validador utilizado para validar entradas do sistema.
	 */
	private Validador validador;

	/**
	 * Constroi um controller geral.
	 */
	public ControllerGeral() {
		this.pesquisaController = new PesquisaController();
		this.pesquisadorController = new PesquisadorController();
		this.problemaController = new ProblemaController();
		this.objetivoController = new ObjetivoController();
		this.atividadeController = new AtividadeController();
		this.validador = new Validador();

	}

	// METODOS DA US1 (MATHEUS)

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return pesquisaController.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		pesquisaController.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		pesquisaController.encerraPesquisa(codigo, motivo);
	}

	public void ativaPesquisa(String codigo) {
		pesquisaController.ativaPesquisa(codigo);
	}

	public String exibePesquisa(String codigo) {
		return pesquisaController.exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return pesquisaController.ehAtiva(codigo);
	}

	// METODOS DA US2 (RAPHAEL)

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.pesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		this.pesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	public void ativaPesquisador(String email) {
		this.pesquisadorController.ativaPesquisador(email);
	}

	public void desativaPesquisador(String email) {
		this.pesquisadorController.desativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return this.pesquisadorController.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return this.pesquisadorController.pesquisadorEhAtivo(email);
	}

	// METODOS DA US3 (ALISSON)

	public String cadastraProblema(String descricao, int viabilidade) {
		return this.problemaController.cadastraProblema(descricao, viabilidade);
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return this.objetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		this.problemaController.apagarProblema(codigo);
	}

	public void apagarObjetivo(String codigo) {
		this.objetivoController.apagarObjetivo(codigo);
	}

	public String exibeProblema(String codigo) {
		return this.problemaController.exibeProblema(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return this.objetivoController.exibeObjetivo(codigo);
	}

	// METODOS DA US4 (WESLLEY)

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
		return this.atividadeController.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	/**
	 * Apaga uma atividade a partir de seu codigo.
	 * 
	 * @param codigo Codigo da atividade.
	 */
	public void apagaAtividade(String codigo) {
		this.atividadeController.apagaAtividade(codigo);
	}

	/**
	 * Cadastra um item na atividade a partir do codigo da atividade e a String que
	 * representa o item.
	 * 
	 * @param codigo Codigo da atividade.
	 * @param item   Representacao em String do item.
	 */
	public void cadastraItem(String codigo, String item) {
		this.atividadeController.cadastraItem(codigo, item);
	}

	/**
	 * Retorna a String que representa a atividade.
	 * 
	 * @param codigo Codigo da atividade.
	 * @return a representacao em String da atividade.
	 */
	public String exibeAtividade(String codigo) {
		return this.atividadeController.exibeAtividade(codigo);
	}

	/**
	 * Retorna a quantidade de itens pendentes contidos em determinada atividade.
	 * 
	 * @param codigo Codigo da atividade.
	 * @return a representacao em Int da quantidade de itens pendentes.
	 */
	public int contaItensPendentes(String codigo) {
		return this.atividadeController.contaItensPendentes(codigo);
	}

	/**
	 * Retorna a quantidade de itens realizados contidos em determinada atividade.
	 * 
	 * @param codigo Codigo da atividade.
	 * @return a representacao em Int da quantidade de itens realizados.
	 */
	public int contaItensRealizados(String codigo) {
		return this.atividadeController.contaItensRealizados(codigo);
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
	public boolean associaProblema(String codigoPesquisa, String codigoProblema) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		Problema problema = this.problemaController.getProblema(codigoProblema);
		return this.pesquisaController.associaProblema(codigoPesquisa, codigoProblema, problema);
	}

	/**
	 * Desassocia um problema da pesquisa a partir do codigo da pesquisa e do codigo
	 * do problema.
	 * 
	 * @param codigoPesquisa Codigo da pesquisa.
	 * @param codigoProblema Codigo do problema.
	 * @return retorna true se foi desassociado e false se nao.
	 */
	public boolean desassociaProblema(String codigoPesquisa, String codigoProblema) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		return this.pesquisaController.desassociaProblema(codigoPesquisa, codigoProblema);
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
	public boolean associaObjetivo(String codigoPesquisa, String codigoObjetivo) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Objetivo objetivo = this.objetivoController.getObjetivo(codigoObjetivo);
		return this.pesquisaController.associaObjetivo(codigoPesquisa, codigoObjetivo, objetivo);
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
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		return this.pesquisaController.desassociaObjetivo(codigoPesquisa, codigoObjetivo);
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
		return this.pesquisaController.listaPesquisas(ordem);
	}

	// METODS DA US6 (MATHEUS)

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.verificaEntradaNulaVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Pesquisador pesquisador = this.pesquisadorController.getPesquisador(emailPesquisador);
		return this.pesquisaController.associaPesquisador(idPesquisa, emailPesquisador, pesquisador);
	}
	
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.verificaEntradaNulaVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		return this.pesquisaController.desassociaPesquisador(idPesquisa, emailPesquisador);
	}
	
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		validador.verificaData(data);
		
		pesquisadorController.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		pesquisadorController.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	public String listaPesquisadores(String tipo) {
		return pesquisadorController.listaPesquisadores(tipo);
	}
	
	// METODOS DA US7 (ALISSON)

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = this.atividadeController.getAtividade(codigoAtividade);
		return this.pesquisaController.associaAtividade(codigoPesquisa, codigoAtividade, atividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		this.atividadeController.getAtividade(codigoAtividade);
		return this.pesquisaController.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		this.atividadeController.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return this.atividadeController.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return this.atividadeController.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return this.atividadeController.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return this.atividadeController.getDuracao(codigoAtividade);
	}

	// METODOS DA US8 (RAPHAEL)

	/**
	 * Metodo que recebe um termo como parametro e retorna uma string representando
	 * todas as entidades que possuem o termo.
	 * 
	 * @param termo Termo a ser procurado
	 * @return uma string que representa todas as entidades que possuem o termo
	 */
	public String busca(String termo) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		String saida = "";
		List<String> saidasStrings = new ArrayList<>();
		if (!"".equals(pesquisaController.busca(termo))) {
			saidasStrings.add(pesquisaController.busca(termo));
		}
		if (!"".equals(pesquisadorController.busca(termo))) {
			saidasStrings.add(pesquisadorController.busca(termo));
		}
		if (!"".equals(problemaController.busca(termo))) {
			saidasStrings.add(problemaController.busca(termo));
		}
		if (!"".equals(objetivoController.busca(termo))) {
			saidasStrings.add(objetivoController.busca(termo));
		}
		if (!"".equals(atividadeController.busca(termo))) {
			saidasStrings.add(atividadeController.busca(termo));
		}

		saida = String.join(" | ", saidasStrings);
		return saida;
	}

	/**
	 * Metodo que busca um termo em todas as entidades do sistema e retorna a string
	 * que representa o resultado de acordo com o numero passado como parametro, a
	 * partir do termo e do numero do resultado.
	 *
	 * @param termo             Termo a ser procurado.
	 * @param numeroDoResultado Numero do resultado a ser exibido.
	 * @return a string que representa o resultado de acordo com o numero passado
	 *         como parametro
	 */
	public String busca(String termo, int numeroDoResultado) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		if (numeroDoResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}

		String saida = busca(termo);
		String[] resultados = saida.split(" \\| ");
		if (numeroDoResultado > resultados.length) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		return resultados[numeroDoResultado - 1];
	}

	/**
	 * Metodo que conta a quantidade de resultados obtidos em uma busca de um
	 * determinado termo passado como parametro.
	 * 
	 * @param termo Termo a ser procurado.
	 * @return a quantidade de resultados obtido na busca
	 */
	public int contaResultadosBusca(String termo) {
		String[] resultados = busca(termo).split(" \\| ");
		if (busca(termo).equals("")) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		return resultados.length;
	}
}
