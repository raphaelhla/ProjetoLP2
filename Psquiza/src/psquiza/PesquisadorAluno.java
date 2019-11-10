package psquiza;
/**
 * Classe que representa um pesquisador de especialidade aluno.
 * 
 * 
 * @author matheus
 *
 */
public class PesquisadorAluno implements Especialidade{
	/**
	 * int que representa o semestre que o pesquisador aluno ingressou.
	 */
	private int semestreIngresso;
	
	/**
	 * double que representa o  indice de Eficiência Acadêmica do pesquisador aluno.
	 */
	private double iea;
	
	/**
	 * Constroi um pesquisador aluno a partir do semestre de ingresso e iea.
	 * 
	 * @param semestreIngresso - int que representa o semestre que o pesquisador aluno ingressou.
	 * @param iea - double que representa o  indice de Eficiência Acadêmica do pesquisador aluno.
	 */
	public PesquisadorAluno(int semestreIngresso, double iea) {
		this.semestreIngresso = semestreIngresso;
		this.iea = iea;
	}

	/**
	 * Metodo que retorna o semestre de ingresso do pesquisador aluno.
	 * 
	 * @return - semestre de ingresso do pesquisador aluno.
	 */
	public int getSemestreIngresso() {
		return semestreIngresso;
	}

	/**
	 * Metodo que retorna o iea do pesquisador aluno.
	 * 
	 * @return - o iea do pesquisador aluno.
	 */
	public double getIea() {
		return iea;
	}

	/**
	 * Metodo que retorna o toString do pesquisador aluno.
	 * 
	 * @return - String que representa o toString do pesquisador aluno.
	 */
	@Override
	public String toString() {
		return " - " + this.semestreIngresso + "o SEMESTRE - " + this.iea;
	}
	
	/**
	 * Metodo que edita um pesquisador aluno com o atributo a ser
	 * editado e um novo valor para o atributo.
	 * 
	 * @param atributo  Atributo que vai ser editado.
	 * @param novoValor Novo valor para o atributo.
	 */
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
