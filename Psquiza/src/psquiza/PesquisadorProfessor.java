package psquiza;

public class PesquisadorProfessor implements Especialidade{
	
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

	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}

	public String getUnidadeAlocada() {
		return unidadeAlocada;
	}

	public void setUnidadeAlocada(String unidadeAlocada) {
		this.unidadeAlocada = unidadeAlocada;
	}

	public String getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(String dataContratacao) {
		this.dataContratacao = dataContratacao;
	}

	@Override
	public String toString() {
		return  " - " + this.formacao + " - " + this.unidadeAlocada + " - " + this.dataContratacao;
	}
}
