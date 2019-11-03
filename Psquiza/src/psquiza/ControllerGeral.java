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

	// METODOS DA PARTE 1 (WESLLEY) ABAIXO DESSE COMENTARIO
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

	// METODOS DA PARTE 2 (MATHEUS) ABAIXO DESSE COMENTARIO

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

	// METODOS DA PARTE 3 (RAPHAEL) ABAIXO DESSE COMENTARIO

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
	
	public String busca(String termo) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		String saida = "";
		List<String> saidasStrings = new ArrayList<>();
		saidasStrings.add(pesquisaController.busca(termo));
		saidasStrings.add(pesquisadorController.busca(termo));
		saidasStrings.add(problemaController.busca(termo));
		saidasStrings.add(objetivoController.busca(termo));
		saidasStrings.add(atividadeController.busca(termo));
		
		saida = String.join(" | ", saidasStrings);
		return saida;
	}
	
	public String busca(String termo, int numeroDoResultado) {
		validador.verificaEntradaNulaVazia(termo, "Campo termo nao pode ser nulo ou vazio.");
		if (numeroDoResultado < 0) {
			throw new IllegalArgumentException("Numero do resultado nao pode ser negativo");
		}
		
		String saida = "";
		List<String> saidasStrings = new ArrayList<>();
		saidasStrings.add(pesquisaController.busca(termo));
		saidasStrings.add(pesquisadorController.busca(termo));
		saidasStrings.add(problemaController.busca(termo));
		saidasStrings.add(objetivoController.busca(termo));
		saidasStrings.add(atividadeController.busca(termo));
		
		saida = String.join(" | ", saidasStrings);
		String[] resultados = saida.split(" \\| ");
		if (numeroDoResultado > resultados.length) {
			throw new IllegalArgumentException("Entidade nao encontrada.");
		}
		return resultados[numeroDoResultado - 1];
	}
	
	public int contaResultadosBusca(String termo) {
		String[] resultados = busca(termo).split(" \\| ");
		if (resultados.length == 0) {
			throw new IllegalArgumentException("Nenhum resultado encontrado");
		}
		return resultados.length;
	}

	// METODOS DA PARTE 4 (ALISSON) ABAIXO DESSE COMENTARIO

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
	
	public boolean associaAtividade(String codigoPesquisa, String codigoAtividade) {
		Atividade atividade = this.atividadeController.getAtividade(codigoAtividade);
		return this.pesquisaController.associaAtividade(codigoPesquisa, codigoAtividade, atividade);
	}

	public boolean desassociaAtividade(String codigoPesquisa, String codigoAtividade) {
		this.atividadeController.getAtividade(codigoAtividade);
		return this.pesquisaController.desassociaAtividade(codigoPesquisa, codigoAtividade);
	}

	public void executaAtividade(String codigoAtividade, int item, int duracao) {
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
	
//	COM2: Autoavaliacao na Disciplina de Programacao Orientada a Objeto. |
//	COM1: Homofobia em mensagens online de alunos de computacao do primeiro periodo. |
//	COM1: Homofobia em mensagens online de alunos de computacao do primeiro periodo. |
//	gaaraxrocklee@12LinkinPark: Interessado na computacao com areia e em lutar com o rock lee ao som de linkin park. |
//	P4: O problema causado pela areia na segunda guerra mundial ninja de computacao. |
//	P1: O problema do discurso homofobico em chats online de alunos de computacao de primeiro periodo |
//	O6: Diminuir a dor no coracao dos estudantes de computacao depois de ver rock lee vs gaara ao som de linkin park. |
//	O1: Diminuir a frequencia de mensagens homofobicas trocadas em chats online entre alunos de primeiro periodo de computacao. |
//	A5: Monitoramento por ninjas de computacao na guerra ninja. |
//	A1: Monitoramento de chats dos alunos de computacao do primeiro periodo." busca termo="computacao"
}
