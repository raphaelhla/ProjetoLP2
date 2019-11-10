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
		return pesquisaController.cadastraPesquisa(descricao, campoDeInteresse);
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
		pesquisaController.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
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
		pesquisaController.encerraPesquisa(codigo, motivo);
	}

	/**
	 * Metodo que vai ativar uma pesquisa. Ele vai pegar a pesquisa representada
	 * pelo codigo e vai passar o seu status para true.
	 * 
	 * @param codigo - String codigo.
	 */
	public void ativaPesquisa(String codigo) {
		pesquisaController.ativaPesquisa(codigo);
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
		return pesquisaController.exibePesquisa(codigo);
	}

	/**
	 * Metodo que vai verificar se a pesquisa associada ao codigo e ativa ou nao.
	 * Ela retorna o boolean StatusPesquisa(true ou false).
	 * 
	 * @param codigo - String codigo.
	 * @return - Boolean true ou false.
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		return pesquisaController.ehAtiva(codigo);
	}

	// METODOS DA US2 (RAPHAEL)

	/**
	 * Metodo que cadastra um pesquisador a partir do seu nome, funcao, biografia,
	 * email e fotoURL.
	 * 
	 * @param nome      Nome do pesquisador.
	 * @param funcao    Funcao do pesquisador.
	 * @param biografia Biografia do pesquisador.
	 * @param email     Email do pesquisador.
	 * @param fotoURL   Url da foto do pesquisador.
	 */
	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.pesquisadorController.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	/**
	 * Metodo que edita um pesquisador a partir do seu email, do atributo a ser
	 * editado e um novo valor para o atributo.
	 * 
	 * @param email     Email do pesquisador.
	 * @param atributo  Atributo que vai ser editado.
	 * @param novoValor Novo valor para o atributo.
	 */
	public void alteraPesquisador(String email, String atributo, String novoValor) {
		this.pesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	/**
	 * Metodo responsavel por ativar o pesquisador a partir do seu email.
	 * 
	 * @param email Email do pesquisador
	 */
	public void ativaPesquisador(String email) {
		this.pesquisadorController.ativaPesquisador(email);
	}

	/**
	 * Metodo responsavel por desativar o pesquisador a partir do seu email.
	 * 
	 * @param email Email do pesquisador
	 */
	public void desativaPesquisador(String email) {
		this.pesquisadorController.desativaPesquisador(email);
	}
	/**
	 * Metodo que retorna a representacao em string do pesquisador a partir do seu
	 * cpf.
	 * 
	 * @param email Email do pesquisador
	 * @return retorna a representacao em string do pesquisador
	 */
	public String exibePesquisador(String email) {
		return this.pesquisadorController.exibePesquisador(email);
	}

	/**
	 * Metodo que verifica se um pesquisador é ativo ou nao, a partir do seu email.
	 * 
	 * @param email Email do pesquisador.
	 * @return um valor booleano verdade caso o pesquisador esteja ativo, caso
	 *         contrario retorna falso.
	 */
	public boolean pesquisadorEhAtivo(String email) {
		return this.pesquisadorController.pesquisadorEhAtivo(email);
	}

	// METODOS DA US3 (ALISSON)

	/**
	 * Metodo que cadastra um problema a partir de sua descricao e sua viabilidade.
	 * 
	 * @param descricao   Descricao do problema.
	 * @param viabilidade Viabilidade do problema.
	 * @return o codigo de identificacao do problema.
	 */
	public String cadastraProblema(String descricao, int viabilidade) {
		return this.problemaController.cadastraProblema(descricao, viabilidade);
	}

	/**
	 * Metodo que cadastra um objetivo a partir de seu tipo, sua descricao, sua
	 * aderencia e sua viabilidade.
	 * 
	 * @param tipo        Tipo do objetivo.
	 * @param descricao   Descricao do objetivo.
	 * @param aderencia   Aderencia do objetivo.
	 * @param viabilidade Viabilidade do objetivo.
	 * @return o codigo de identificacao do objetivo.
	 */
	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return this.objetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	/**
	 * Metodo que apaga um problema a partir do seu codigo.
	 * 
	 * @param codigo Codigo do problema.
	 */
	public void apagarProblema(String codigo) {
		this.problemaController.apagarProblema(codigo);
	}

	/**
	 * Metodo que apaga um problema a partir do seu codigo.
	 * 
	 * @param codigo Codigo do problema.
	 */
	public void apagarObjetivo(String codigo) {
		this.objetivoController.apagarObjetivo(codigo);
	}


	/**
	 * Metodo que retorna a representacao em string do problema a partir do seu
	 * codigo.
	 * 
	 * @param codigo Codigo do problema.
	 * @return retorna a representacao em string do problema.
	 */
	public String exibeProblema(String codigo) {
		return this.problemaController.exibeProblema(codigo);
	}

	/**
	 * Metodo que retorna a representacao em string do problema a partir do seu
	 * codigo.
	 * 
	 * @param codigo Codigo do problema.
	 * @return retorna a representacao em string do problema.
	 */
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
	 * Desassocia um problema da pesquisa a partir do codigo da pesquisa.
	 * 
	 * @param codigoPesquisa Codigo da pesquisa.
	 * @return retorna true se foi desassociado e false se nao.
	 */
	public boolean desassociaProblema(String codigoPesquisa) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		return this.pesquisaController.desassociaProblema(codigoPesquisa);
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
	
	
	/**
	 * Metodo que vai associar um pesquisador com uma pesquisa.
	 * 
	 * @param idPesquisa - o código da pesquisa a ser associada.
	 * @param emailPesquisador - o email que identifica o pesquisador a ser associado.
	 * @return - true ou false, caso a operaçao seja realizada com sucesso ou nao.
	 */
	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.verificaEntradaNulaVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		Pesquisador pesquisador = this.pesquisadorController.getPesquisador(emailPesquisador);
		return this.pesquisaController.associaPesquisador(idPesquisa, emailPesquisador, pesquisador);
	}
	
	
	/**
	 * Metodo que vai desassociar um pesquisador com uma pesquisa.
	 * 
	 * @param idPesquisa - o código da pesquisa a ser desassociada.
	 * @param emailPesquisador - o email que identifica o pesquisador a ser desassociado.
	 * @return - true ou false, caso a operaçao seja realizada com sucesso ou nao.
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.verificaEntradaNulaVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		return this.pesquisaController.desassociaPesquisador(idPesquisa, emailPesquisador);
	}
	
	/**
	 * Metodo que vai cadastrar a especialidade pofessor, transformando um pesquisador externo em pesqusiador professor.
	 * 
	 * @param email - email do pesquisador.
	 * @param formacao - formaçao do pesquisador professor.
	 * @param unidade - unidade de trabalho do pesquisador professor.
	 * @param data - data do inicio do trabalho do pesquisador professor.
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		validador.verificaData(data);
		
		pesquisadorController.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	/**
	 * Metodo que vai cadastrar a especialidade aluno, transformando um pesquisador externo em pesqusiador aluno.
	 *  
	 * @param email - email do pesquisador.
	 * @param semestre - semestre do pesquisador aluno.
	 * @param IEA - Índice de Eficiência Acadêmica do pesquisador aluno.
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		pesquisadorController.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	/**
	 * Metodo que vai listar os pesquisadores de acordo com a especialidade passada como parametro.
	 *  
	 * @param tipo - tipo dos pesquisadores a serem listados.
	 * @return - uma lista com o toString de todos os pesquisadores daquele tipo.
	 */
	public String listaPesquisadores(String tipo) {
		return pesquisadorController.listaPesquisadores(tipo);
	}
	
	// METODOS DA US7 (ALISSON)

	/**
	 * Metodo que associa uma atividade a uma pesquisa a partir do codigo da
	 * pesquisa, do codigo da atividade e da atividade.
	 * 
	 * @param codigoPesquisa  Codigo da pesquisa.
	 * @param codigoAtividade Codigo da atividade.
	 * @param atividade       Atividade a ser associada.
	 * @return verdadeiro se for associada com sucesso, caso contrario retorna falso
	 */
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo codigoPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		Atividade atividade = this.atividadeController.getAtividade(codigoAtividade);
		return this.pesquisaController.associaAtividade(codigoPesquisa, codigoAtividade, atividade);
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
		this.atividadeController.getAtividade(codigoAtividade);
		return this.pesquisaController.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	/**
	 * Metodo que executa uma atividade a partir do codigo da atividade, do item e
	 * da duracao da atividade. Quando um item é executado, seu status é alterado
	 * para "REALIZADO".
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @param item            Item executado.
	 * @param duracao         Duracao da execucao do item.
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		if (!pesquisaController.atividadeEstaAssociada(codigoAtividade)) {
			throw new IllegalArgumentException("Atividade sem associacoes com pesquisas.");
		}
		this.atividadeController.executaAtividade(codigoAtividade, item, duracao);
	}

	/**
	 * Metodo que cadastra um resultado para uma atividade a partir do codigo da
	 * atividade e do resultado a ser cadastrado.
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @param resultado       Resultado a ser cadastrado.
	 * @return o inteiro que representa o resultado
	 */
	public int cadastraResultado(String codigoAtividade, String resultado) {
		return this.atividadeController.cadastraResultado(codigoAtividade, resultado);
	}

	/**
	 * Metodo que remove um resultado de uma atividade a partir do codigo da
	 * atividade e do numero que representa o resultado a ser removido.
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @param numeroResultado Numero do resultado.
	 * @return verdadeiro se a remocao for bem sucedida, caso contrario retorna
	 *         falso.
	 */
	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return this.atividadeController.removeResultado(codigoAtividade, numeroResultado);
	}

	/**
	 * Metodo que retorna uma string com todos o resultados de uma atividade passada
	 * como parametro.
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @return uma string com todos o resultados de uma atividade.
	 */
	public String listaResultados(String codigoAtividade) {
		return this.atividadeController.listaResultados(codigoAtividade);
	}

	/**
	 * Metodo que retorna o inteiro que representa a duracao da execucao de todos os
	 * itens de uma atividade passada como parametro.
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @return o inteiro que representa a duracao da execucao de todos os itens de
	 *         uma atividade
	 */
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
