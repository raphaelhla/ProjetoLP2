package psquiza;

public class Pesquisa {

	private String descricao;
	private String campoDeInteresse;
	private boolean statusPesquisa;
	private Validador validador;
	private String codigo;

	public Pesquisa(String descricao, String campoDeInteresse) {
		validador = new Validador();
		validador.verificaEntradaNulaVazia(descricao, "Descricao nao pode ser nula ou vazia.");
		validador.verificaEntradaNulaVazia(campoDeInteresse, "Formato do campo de interesse invalido.");
		this.statusPesquisa = true;
		this.descricao = descricao;
		this.campoDeInteresse = campoDeInteresse;

	}
	
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCampoDeInteresse() {
		return campoDeInteresse;
	}

	public void setCampoDeInteresse(String campoDeInteresse) {
		this.campoDeInteresse = campoDeInteresse;
	}

	public boolean getStatusPesquisa() {
		return statusPesquisa;
	}

	public void ativaPesquisa() {
		this.statusPesquisa = true;
	}
	
	public void desativaPesquisa() {
		this.statusPesquisa = false;
	}

	@Override
	public String toString() {
		return this.codigo + " - " + this.descricao + " - " + this.campoDeInteresse;
	}
	
	

}
