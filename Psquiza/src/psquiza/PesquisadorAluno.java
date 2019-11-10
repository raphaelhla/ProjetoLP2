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

	public double getIea() {
		return iea;
	}

	@Override
	public String toString() {
		return " - " + this.semestreIngresso + "o SEMESTRE - " + this.iea;
	}
	
	public void alteraPesquisador(String atributo, String novoValor) {
		switch (atributo) {
		case "SEMESTRE":
			int valorConvertido = Integer.parseInt(novoValor);
			this.semestreIngresso = valorConvertido;
			break;
		case "IEA":
			double valorConvertido2 = Double.parseDouble(novoValor);
			this.iea = valorConvertido2;
			break;
		}
	}
}
