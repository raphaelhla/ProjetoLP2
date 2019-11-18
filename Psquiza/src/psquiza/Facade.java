package psquiza;

import java.io.IOException;

import easyaccept.EasyAccept;

public class Facade {

	public static void main(String[] args) {
		args = new String[] { "psquiza.Facade",

				"easyaccept/use_case_1.txt", "easyaccept/use_case_2.txt",
				"easyaccept/use_case_3.txt", "easyaccept/use_case_4.txt",
				"easyaccept/use_case_5.txt", "easyaccept/use_case_6.txt",
				"easyaccept/use_case_7.txt", "easyaccept/use_case_8.txt",
				"easyaccept/use_case_9.txt", "easyaccept/use_case_10.txt",
				"easyaccept/use_case_11.txt"

				/*"easyaccept/use_case_12CARREGAR.txt"*/ };
		EasyAccept.main(args);
	}

	private ControllerGeral controllerGeral;

	public Facade() {
		this.controllerGeral = new ControllerGeral();
	}

	// METODOS DA US1 (MATHEUS)

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {
		return controllerGeral.cadastraPesquisa(descricao, campoDeInteresse);
	}

	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		controllerGeral.alteraPesquisa(codigo, conteudoASerAlterado, novoConteudo);
	}

	public void encerraPesquisa(String codigo, String motivo) {
		controllerGeral.encerraPesquisa(codigo, motivo);
	}

	public void ativaPesquisa(String codigo) {
		controllerGeral.ativaPesquisa(codigo);
	}

	public String exibePesquisa(String codigo) {
		return controllerGeral.exibePesquisa(codigo);
	}

	public boolean pesquisaEhAtiva(String codigo) {
		return controllerGeral.pesquisaEhAtiva(codigo);
	}

	// METODOS DA US2 (RAPHAEL)

	public void cadastraPesquisador(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.controllerGeral.cadastraPesquisador(nome, funcao, biografia, email, fotoURL);
	}

	public void alteraPesquisador(String email, String atributo, String novoValor) {
		this.controllerGeral.alteraPesquisador(email, atributo, novoValor);
	}

	public void ativaPesquisador(String email) {
		this.controllerGeral.ativaPesquisador(email);
	}

	public void desativaPesquisador(String email) {
		this.controllerGeral.desativaPesquisador(email);
	}

	public String exibePesquisador(String email) {
		return this.controllerGeral.exibePesquisador(email);
	}

	public boolean pesquisadorEhAtivo(String email) {
		return this.controllerGeral.pesquisadorEhAtivo(email);
	}

	// METODOS DA US3 (ALISSON)

	public String cadastraProblema(String descricao, int viabilidade) {
		return this.controllerGeral.cadastraProblema(descricao, viabilidade);
	}

	public String cadastraObjetivo(String tipo, String descricao, int aderencia, int viabilidade) {
		return this.controllerGeral.cadastraObjetivo(tipo, descricao, aderencia, viabilidade);
	}

	public void apagarProblema(String codigo) {
		this.controllerGeral.apagarProblema(codigo);
	}

	public void apagarObjetivo(String codigo) {
		this.controllerGeral.apagarObjetivo(codigo);
	}

	public String exibeProblema(String codigo) {
		return this.controllerGeral.exibeProblema(codigo);
	}

	public String exibeObjetivo(String codigo) {
		return this.controllerGeral.exibeObjetivo(codigo);
	}

	// METODOS DA US4 (WESLLEY)

	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return this.controllerGeral.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		this.controllerGeral.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		this.controllerGeral.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return this.controllerGeral.exibeAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return this.controllerGeral.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return this.controllerGeral.contaItensRealizados(codigo);
	}

	// METODOS DA US5 (WESLLEY)

	public boolean associaProblema(String idPesquisa, String idProblema) {
		return controllerGeral.associaProblema(idPesquisa, idProblema);
	}

	public boolean desassociaProblema(String idPesquisa) {
		return controllerGeral.desassociaProblema(idPesquisa);
	}

	public boolean associaObjetivo(String idPesquisa, String idObjetivo) {
		return controllerGeral.associaObjetivo(idPesquisa, idObjetivo);
	}

	public boolean desassociaObjetivo(String idPesquisa, String idObjetivo) {
		return controllerGeral.desassociaObjetivo(idPesquisa, idObjetivo);
	}

	public String listaPesquisas(String ordem) {
		return controllerGeral.listaPesquisas(ordem);
	}

	// METODOS DA US6 (MATHEUS)

	public boolean associaPesquisador(String idPesquisa, String emailPesquisador) {
		return controllerGeral.associaPesquisador(idPesquisa, emailPesquisador);
	}

	public boolean desassociaPesquisador(String idPesquisa, String emailPesquisador) {
		return controllerGeral.desassociaPesquisador(idPesquisa, emailPesquisador);
	}

	public void cadastraEspecialidadeProfessor(String email, String formacao, String unidade, String data) {
		controllerGeral.cadastraEspecialidadeProfessor(email, formacao, unidade, data);
	}

	public void cadastraEspecialidadeAluno(String email, int semestre, double IEA) {
		controllerGeral.cadastraEspecialidadeAluno(email, semestre, IEA);
	}

	public String listaPesquisadores(String tipo) {
		return controllerGeral.listaPesquisadores(tipo);
	}

	// METODOS DA US7 (ALISSON)

	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		return this.controllerGeral.associaAtividade(codigoPesquisa, codigoAtividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		return this.controllerGeral.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
		this.controllerGeral.executaAtividade(codigoAtividade, item, duracao);
	}

	public int cadastraResultado(String codigoAtividade, String resultado) {
		return this.controllerGeral.cadastraResultado(codigoAtividade, resultado);
	}

	public boolean removeResultado(String codigoAtividade, int numeroResultado) {
		return this.controllerGeral.removeResultado(codigoAtividade, numeroResultado);
	}

	public String listaResultados(String codigoAtividade) {
		return this.controllerGeral.listaResultados(codigoAtividade);
	}

	public int getDuracao(String codigoAtividade) {
		return this.controllerGeral.getDuracao(codigoAtividade);
	}

	// METODOS DA US8 (RAPHAEL)

	public String busca(String termo) {
		return this.controllerGeral.busca(termo);
	}

	public String busca(String termo, int numeroDoResultado) {
		return this.controllerGeral.busca(termo, numeroDoResultado);
	}

	public int contaResultadosBusca(String termo) {
		return this.controllerGeral.contaResultadosBusca(termo);
	}

	// US9 Raphael

	public void defineProximaAtividade(String idPrecedente, String idSubsquente) {
		this.controllerGeral.defineProximaAtividade(idPrecedente, idSubsquente);
	}

	public void tiraProximaAtividade(String idPrecedente) {
		this.controllerGeral.tiraProximaAtividade(idPrecedente);
	}

	public int contaProximos(String idPrecedente) {
		return this.controllerGeral.contaProximos(idPrecedente);
	}

	public String pegaProximo(String idAtividade, int enesimaAtividade) {
		return this.controllerGeral.pegaProximo(idAtividade, enesimaAtividade);
	}

	public String pegaMaiorRiscoAtividades(String idAtividade) {
		return this.controllerGeral.pegaMaiorRiscoAtividades(idAtividade);
	}

	// US10 Alisson

	public void configuraEstrategia(String estrategia) {
		this.controllerGeral.configuraEstrategia(estrategia);
	}

	public String proximaAtividade(String codigoPesquisa) {
		return this.controllerGeral.proximaAtividade(codigoPesquisa);
	}

	// US11 Matheus

	public void gravarResumo(String codigoPesquisa) throws IOException {
		this.controllerGeral.gravarResumo(codigoPesquisa);
	}

	public void gravarResultados(String codigoPesquisa) throws IOException {
		this.controllerGeral.gravarResultado(codigoPesquisa);
	}

	// US12 Weslley

	public void salvar() {
		this.controllerGeral.salvar();
	}

	public void carregar() {
		this.controllerGeral.carregar();
	}
}
