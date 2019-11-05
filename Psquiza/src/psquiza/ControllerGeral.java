package psquiza;

import java.util.ArrayList;
import java.util.List;

public class ControllerGeral {

	private PesquisaController pesquisaController;
	private PesquisadorController pesquisadorController;
	private ProblemaController problemaController;
	private ObjetivoController objetivoController;
	private AtividadeController atividadeController;
	private Validador validador;

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

	public String cadastraAtividade(String descricao, String nivelRisco, String descricaoRisco) {
		return this.atividadeController.cadastraAtividade(descricao, nivelRisco, descricaoRisco);
	}

	public void apagaAtividade(String codigo) {
		this.atividadeController.apagaAtividade(codigo);
	}

	public void cadastraItem(String codigo, String item) {
		this.atividadeController.cadastraItem(codigo, item);
	}

	public String exibeAtividade(String codigo) {
		return this.atividadeController.exibeAtividade(codigo);
	}

	public int contaItensPendentes(String codigo) {
		return this.atividadeController.contaItensPendentes(codigo);
	}

	public int contaItensRealizados(String codigo) {
		return this.atividadeController.contaItensRealizados(codigo);
	}
	
	// METODOS DA US5 (WESLLEY)
	
	public boolean associaProblema(String codigoPesquisa, String codigoProblema) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		Problema problema = this.problemaController.getProblema(codigoProblema);
		return this.pesquisaController.associaProblema(codigoPesquisa, codigoProblema, problema);
	}

	public boolean desassociaProblema(String codigoPesquisa, String codigoProblema) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoProblema, "Campo idProblema nao pode ser nulo ou vazio.");
		return this.pesquisaController.desassociaProblema(codigoPesquisa, codigoProblema);
	}

	public boolean associaObjetivo(String codigoPesquisa, String codigoObjetivo) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		Objetivo objetivo = this.objetivoController.getObjetivo(codigoObjetivo);
		return this.pesquisaController.associaObjetivo(codigoPesquisa, codigoObjetivo, objetivo);
	}

	public boolean desassociaObjetivo(String codigoPesquisa, String codigoObjetivo) {
		validador.verificaEntradaNulaVazia(codigoPesquisa, "Campo idPesquisa nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(codigoObjetivo, "Campo idObjetivo nao pode ser nulo ou vazio.");
		return this.pesquisaController.desassociaObjetivo(codigoPesquisa, codigoObjetivo);
	}

	public String listaPesquisas(String ordem) {
		validador.verificaEntradaNulaVazia(ordem, "Valor invalido da ordem");
		return this.pesquisaController.listaPesquisas(ordem);
	}
	
	// METODS DA US6 (MATHEUS)
	
	
	
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
	
	public int contaResultadosBusca(String termo) {
		String[] resultados = busca(termo).split(" \\| ");
		if (busca(termo).equals("")) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		return resultados.length;
	}
}
