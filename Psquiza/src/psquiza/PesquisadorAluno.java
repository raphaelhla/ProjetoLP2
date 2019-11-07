package psquiza;

public class PesquisadorAluno implements Especialidade{
	
	private String semestreIngresso;
	private String iea;
	
	public PesquisadorAluno(String nome, String funcao, String biografia, String email, String fotoURL) {
		this.semestreIngresso = semestreIngresso;
		this.iea = iea;
	}

	public String getSemestreIngresso() {
		return semestreIngresso;
	}

	public void setSemestreIngresso(String semestreIngresso) {
		this.semestreIngresso = semestreIngresso;
	}

	public String getIea() {
		return iea;
	}

	public void setIea(String iea) {
		this.iea = iea;
	}

	@Override
	public String toString() {
		return " - " + this.semestreIngresso + " - " + this.iea;
	}
	
	

	
}
