package psquiza;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import atividade.Atividade;
import atividade.AtividadeController;
import objetivo.Objetivo;
import objetivo.ObjetivoController;
import pesquisa.PesquisaController;
import pesquisador.Pesquisador;
import pesquisador.PesquisadorController;
import problema.Problema;
import problema.ProblemaController;

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
	 * @param descricao        String que representa a descricao da pesquisa.
	 * @param campoDeInteresse String que representa o campo de interesse da
	 *                         pesquisa.
	 * @return codigo gerado da pesquisa.
	 */
	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.verificaCampoDeInteresse(campoDeInteresse);
		return pesquisaController.cadastraPesquisa(descricao, campoDeInteresse);
	}

	/**
	 * Metodo que altera uma pesquisa. E passado um codigo que representa uma
	 * pesquisa, qual atributo vai ser alterado e o seu novo valor.
	 * 
	 * 
	 * @param codigo               String codigo.
	 * @param conteudoASerAlterado String que representa o atributo a ser alterado.
	 * @param novoConteudo         String que representa o novo valor do atributo
	 *                             alterado.
	 */
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(conteudoASerAlterado, "conteudoASerAlterado nao pode ser nulo ou vazio.");
		pesquisaController.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	/**
	 * Metodo que vai encerrar uma pesquisa de acordo com o codigo que representa
	 * ela.Tambem e passado como parametro o motivo do encerramento da pesquisa.
	 * 
	 * @param codigo String codigo.
	 * @param motivo String que representa o motivo do encerramento da pesquisa.
	 */
	public void encerraPesquisa(String codigo, String motivo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(motivo, "Motivo nao pode ser nulo ou vazio.");
		pesquisaController.encerraPesquisa(codigo, motivo);
	}

	/**
	 * Metodo que vai ativar uma pesquisa. Ele vai pegar a pesquisa representada
	 * pelo codigo e vai passar o seu status para true.
	 * 
	 * @param codigo String codigo.
	 */
	public void ativaPesquisa(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		pesquisaController.ativaPesquisa(codigo);
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
		return pesquisaController.exibePesquisa(codigo);
	}

	/**
	 * Metodo que vai verificar se a pesquisa associada ao codigo e ativa ou nao.
	 * Ela retorna o boolean StatusPesquisa(true ou false).
	 * 
	 * @param codigo String codigo.
	 * @return Boolean true ou false.
	 */
	public boolean pesquisaEhAtiva(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(nome, "Campo nome nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(funcao, "Campo funcao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(biografia, "Campo biografia nao pode ser nulo ou vazio.");
		validador.verificaEmail(email);
		validador.verificafotoURL(fotoURL);
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
		validador.verificaEntradaNulaVazia(email, "Email nao pode ser vazio ou nulo.");
		validador.verificaEntradaNulaVazia(atributo, "Atributo nao pode ser vazio ou nulo.");
		this.pesquisadorController.alteraPesquisador(email, atributo, novoValor);
	}

	/**
	 * Metodo responsavel por ativar o pesquisador a partir do seu email.
	 * 
	 * @param email Email do pesquisador
	 */
	public void ativaPesquisador(String email) {
		validador.verificaEntradaNulaVazia(email, "Email nao pode ser vazio ou nulo.");
		this.pesquisadorController.ativaPesquisador(email);
	}

	/**
	 * Metodo responsavel por desativar o pesquisador a partir do seu email.
	 * 
	 * @param email Email do pesquisador
	 */
	public void desativaPesquisador(String email) {
		validador.verificaEntradaNulaVazia(email, "Email nao pode ser vazio ou nulo.");
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
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		return this.pesquisadorController.exibePesquisador(email);
	}

	/**
	 * Metodo que verifica se um pesquisador e ativo ou nao, a partir do seu email.
	 * 
	 * @param email Email do pesquisador.
	 * @return um valor booleano verdade caso o pesquisador esteja ativo, caso
	 *         contrario retorna falso.
	 */
	public boolean pesquisadorEhAtivo(String email) {
		validador.verificaEntradaNulaVazia(email, "Email nao pode ser vazio ou nulo.");
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
		validador.verificaEntradaNulaVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.verificaViabilidade(viabilidade);
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
		validador.verificaTipoObjetivo(tipo);
		validador.verificaEntradaNulaVazia(descricao, "Campo descricao nao pode ser nulo ou vazio.");
		validador.verificaAderencia(aderencia);
		validador.verificaViabilidade(viabilidade);
		return this.objetivoController.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	/**
	 * Metodo que apaga um problema a partir do seu codigo.
	 * 
	 * @param codigo Codigo do problema.
	 */
	public void apagarProblema(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		this.problemaController.apagarProblema(codigo);
	}

	/**
	 * Metodo que apaga um problema a partir do seu codigo.
	 * 
	 * @param codigo Codigo do problema.
	 */
	public void apagarObjetivo(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(descricao, "Campo Descricao nao pode ser nulo ou vazio.");
		validador.verificaNivelRisco(nivelRisco);
		validador.verificaEntradaNulaVazia(descricaoRisco, "Campo descricaoRisco nao pode ser nulo ou vazio.");
		return this.atividadeController.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	/**
	 * Apaga uma atividade a partir de seu codigo.
	 * 
	 * @param codigo Codigo da atividade.
	 */
	public void apagaAtividade(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(item, "Item nao pode ser nulo ou vazio.");
		this.atividadeController.cadastraItem(codigo, item);
	}

	/**
	 * Retorna a String que representa a atividade.
	 * 
	 * @param codigo Codigo da atividade.
	 * @return a representacao em String da atividade.
	 */
	public String exibeAtividade(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		return this.atividadeController.exibeAtividade(codigo);
	}

	/**
	 * Retorna a quantidade de itens pendentes contidos em determinada atividade.
	 * 
	 * @param codigo Codigo da atividade.
	 * @return a representacao em Int da quantidade de itens pendentes.
	 */
	public int contaItensPendentes(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
		return this.atividadeController.contaItensPendentes(codigo);
	}

	/**
	 * Retorna a quantidade de itens realizados contidos em determinada atividade.
	 * 
	 * @param codigo Codigo da atividade.
	 * @return a representacao em Int da quantidade de itens realizados.
	 */
	public int contaItensRealizados(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Campo codigo nao pode ser nulo ou vazio.");
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
	 * @param idPesquisa       o codigo da pesquisa a ser associada.
	 * @param emailPesquisador o email que identifica o pesquisador a ser associado.
	 * @return true ou false, caso a operacao seja realizada com sucesso ou nao.
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
	 * @param idPesquisa       o codigo da pesquisa a ser desassociada.
	 * @param emailPesquisador o email que identifica o pesquisador a ser
	 *                         desassociado.
	 * @return true ou false, caso a operacao seja realizada com sucesso ou nao.
	 */
	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		validador.verificaEntradaNulaVazia(idPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(emailPesquisador, "Campo emailPesquisador nao pode ser nulo ou vazio.");
		return this.pesquisaController.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

	/**
	 * Metodo que vai cadastrar a especialidade pofessor, transformando um
	 * pesquisador externo em pesqusiador professor.
	 * 
	 * @param email    email do pesquisador.
	 * @param formacao formacao do pesquisador professor.
	 * @param unidade  unidade de trabalho do pesquisador professor.
	 * @param data     data do inicio do trabalho do pesquisador professor.
	 */
	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(formacao, "Campo formacao nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(unidade, "Campo unidade nao pode ser nulo ou vazio.");
		validador.verificaData(data);
		pesquisadorController.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	/**
	 * Metodo que vai cadastrar a especialidade aluno, transformando um pesquisador
	 * externo em pesquisador aluno.
	 * 
	 * @param email    email do pesquisador.
	 * @param semestre semestre do pesquisador aluno.
	 * @param IEA      Indice de Eficiencia Academica do pesquisador aluno.
	 */
	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		validador.verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		validador.verificaSemestre(semestre);
		validador.verificaIEA(IEA);
		pesquisadorController.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	/**
	 * Metodo que vai listar os pesquisadores de acordo com a especialidade passada
	 * como parametro.
	 * 
	 * @param tipo tipo dos pesquisadores a serem listados.
	 * @return uma lista com o toString de todos os pesquisadores daquele tipo.
	 */
	public String listaPesquisadores(String tipo) {
		validador.verificaEntradaNulaVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
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
	 * da duracao da atividade. Quando um item e executado, seu status e alterado
	 * para "REALIZADO".
	 * 
	 * @param codigoAtividade Codigo da atividade.
	 * @param item            Item executado.
	 * @param duracao         Duracao da execucao do item.
	 */
	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.verificaNum(item, "Item nao pode ser nulo ou negativo.");
		validador.verificaNum(duracao, "Duracao nao pode ser nula ou negativa.");
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
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(resultado, "Resultado nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
		validador.verificaNum(numeroResultado, "numeroResultado nao pode ser nulo ou negativo.");
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
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
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
		validador.verificaEntradaNulaVazia(codigoAtividade, "Campo codigoAtividade nao pode ser nulo ou vazio.");
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
		validador.verificaNum(numeroDoResultado, "Numero do resultado nao pode ser negativo");
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
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		String[] resultados = busca(termo).split(" \\| ");
		if (busca(termo).equals("")) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		return resultados.length;
	}

	// US9 Raphael

	/**
	 * Metodo que define uma ordem de execucao para representar uma logica de
	 * execucao das atividades a partir de uma atividade precedente e de uma
	 * atividade subsequente.
	 * 
	 * @param idPrecedente Id da atividade precedente.
	 * @param idSubsquente Id da atividade subsequente.
	 */
	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		this.atividadeController.defineProximaAtividade(idPrecedente, idSubsquente);
	}

	/**
	 * Metodo que quebra uma ordem de execucao predefinida anteriormente a partir da
	 * atividade atual.
	 * 
	 * @param idPrecedente Id da atividade precedente.
	 */
	public void tiraProximaAtividade(String idPrecedente) {
		this.atividadeController.tiraProximaAtividade(idPrecedente);
	}

	/**
	 * Metodo que conta quantas atividades estão planejadas depois da atividade
	 * atual a ser consultada.
	 * 
	 * @param idPrecedente Id da atividade precedente.
	 * @return o inteiro que representa a quantidade de atividades planejadas depois
	 *         da atividade atual.
	 */
	public int contaProximos(String idPrecedente) {
		return this.atividadeController.contaProximos(idPrecedente);
	}

	/**
	 * Metodo que retorna a enesima atividade, passada como parametro, associada a
	 * execucao da atividade atual.
	 * 
	 * @param idAtividade      Id da atividade.
	 * @param enesimaAtividade Numero da atividade a ser obtida.
	 * @return o codigo da enesima atividade associada a execucao da atividade
	 *         atual.
	 */
	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return this.atividadeController.pegaProximo(idAtividade, enesimaAtividade);
	}

	/**
	 * Metodo que navega nas atividades buscando, a partir da atividade consultada,
	 * qual o risco mais elevado associado a execucao daquela atividade e as que se
	 * seguem.
	 * 
	 * @param idAtividade Id da atividade.
	 * @return o codigo da atividade com o risco mais elevado associando a execucao
	 *         da atividade atual.
	 */
	public String pegaMaiorRiscoAtividades(String idAtividade) {
		return this.atividadeController.pegaMaiorRiscoAtividades(idAtividade);
	}

	// US10 Alisson

	/**
	 * Metodo que configura uma estrategia para utilizar no metodo proximaAtividade.
	 * 
	 * @param estrategia Estrategia.
	 */
	public void configuraEstrategia(String estrategia) {
		this.pesquisaController.configuraEstrategia(estrategia);
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
		return this.pesquisaController.proximaAtividade(codigoPesquisa);
	}

	// US11 Matheus

	/**
	 * Metodo que grava em um arquivo .txt o resumo da pesquisa.
	 * 
	 * @param codigoPesquisa o codigo da pesquisa a ser gravada.
	 */
	public void gravarResumo(String codigoPesquisa) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Pesquisa nao pode ser nula ou vazia.");
		try {
			PrintWriter gravarArqquivo = new PrintWriter(new FileWriter("CODIGO.txt"));
			gravarArqquivo.print("-Pesquisa: " + pesquisaController.exibePesquisa(codigoPesquisa));
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
		try {
			PrintWriter gravarArquivo = new PrintWriter(new FileWriter(codigoPesquisa + "-Resultados.txt"));
			gravarArquivo.print("-Pesquisa: " + pesquisaController.exibePesquisa(codigoPesquisa));
			gravarArquivo.print("-Resultados:");
			gravarArquivo.print("-Descrição");
			gravarArquivo.print("");
			gravarArquivo.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// US12 Weslley

	/**
	 * Salva o estado atual do sistema.
	 */
	public void salvar() {
		try {
			File diretorio = new File("arquivos");
			if (!diretorio.exists()) {
				diretorio.mkdirs();
			}
			ObjectOutputStream objectOut = new ObjectOutputStream(new FileOutputStream("arquivos/backup.dat"));
			objectOut.writeObject(pesquisaController);
			objectOut.writeObject(pesquisadorController);
			objectOut.writeObject(problemaController);
			objectOut.writeObject(objetivoController);
			objectOut.writeObject(atividadeController);
			objectOut.flush();
			objectOut.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Carrega o estado anteriormente salvo do sistema.
	 */
	public void carregar() {
		try {
			ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream("arquivos/backup.dat"));
			this.pesquisaController = (PesquisaController) objectInput.readObject();
			this.pesquisadorController = (PesquisadorController) objectInput.readObject();
			this.problemaController = (ProblemaController) objectInput.readObject();
			this.objetivoController = (ObjetivoController) objectInput.readObject();
			this.atividadeController = (AtividadeController) objectInput.readObject();
			objectInput.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
