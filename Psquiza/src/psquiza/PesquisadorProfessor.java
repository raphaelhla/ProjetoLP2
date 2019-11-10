package psquiza;

public class PesquisadorProfessor implements Especialidade {

	private String formacao;
	private String unidadeAlocada;
	private String dataContratacao;

	public PesquisadorProfessor(String formacao, String unidadeAlocada, String dataContratacao) {
		this.formacao = formacao;
		this.unidadeAlocada = unidadeAlocada;
		this.dataContratacao = dataContratacao;

	}

	public String getFormacao() {
		return formacao;
	}
	public String getUnidadeAlocada() {
		return unidadeAlocada;
	}

	public String getDataContratacao() {
		return dataContratacao;
	}

	@Override
	public String toString() {
		return " - " + this.formacao + " - " + this.unidadeAlocada + " - " + this.dataContratacao;
	}

	@Override
	public void alteraPesquisador(String atributo, String novoValor) {
		switch (atributo) {
		case "FORMACAO":
			this.formacao = novoValor;
			break;
		case "UNIDADE":
			this.unidadeAlocada = novoValor;
			break;
		case "DATA":
			this.dataContratacao = novoValor;
			break;
		}
	}
}
