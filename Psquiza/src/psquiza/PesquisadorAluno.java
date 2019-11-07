package psquiza;

public class PesquisadorAluno implements Especialidade{
	
	private int semestreIngresso;
	private double iea;
	
	public PesquisadorAluno(int semestreIngresso, double iea) {
		this.semestreIngresso = semestreIngresso;
		this.iea = iea;
	}

	public int getSemestreIngresso() {
		return semestreIngresso;
	}

	public void setSemestreIngresso(int semestreIngresso) {
		this.semestreIngresso = semestreIngresso;
	}

	public double getIea() {
		return iea;
	}

	public void setIea(double iea) {
		this.iea = iea;
	}

	@Override
	public String toString() {
		return " - " + this.semestreIngresso + " - " + this.iea;
	}
	
	

	
}
