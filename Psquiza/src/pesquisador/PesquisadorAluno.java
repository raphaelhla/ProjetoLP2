package pesquisador;

import psquiza.Validador;

/**
 * Classe que representa um pesquisador de especialidade aluno.
 * 
 * @author matheus
 */
public class PesquisadorAluno implements Especialidade {

	/**
	 * int que representa o semestre que o pesquisador aluno ingressou.
	 */
	private int semestreIngresso;

	/**
	 * double que representa o Indice de Eficiencia Academica do pesquisador aluno.
	 */
	private double iea;

	/**
	 * Validador utilizado para validar entradas do sistema.
	 */
	private Validador validador;

	/**
	 * Constroi um pesquisador aluno a partir do semestre de ingresso e iea.
	 * 
	 * @param semestreIngresso int que representa o semestre que o pesquisador aluno
	 *                         ingressou.
	 * @param iea              double que representa o Indice de Eficiencia Academia
	 *                         do pesquisador aluno.
	 */
	public PesquisadorAluno(int semestreIngresso, double iea) {
		this.validador = new Validador();
		validador.verificaSemestre(semestreIngresso);
		validador.verificaIEA(iea);
		this.semestreIngresso = semestreIngresso;
		this.iea = iea;
	}

	/**
	 * Metodo que retorna o semestre de ingresso do pesquisador aluno.
	 * 
	 * @return semestre de ingresso do pesquisador aluno.
	 */
	public int getSemestreIngresso() {
		return semestreIngresso;
	}

	/**
	 * Metodo que retorna o iea do pesquisador aluno.
	 * 
	 * @return o iea do pesquisador aluno.
	 */
	public double getIea() {
		return iea;
	}

	/**
	 * Metodo que retorna o toString do pesquisador aluno.
	 * 
	 * @return String que representa o toString do pesquisador aluno.
	 */
	@Override
	public String toString() {
		return " - " + this.semestreIngresso + "o SEMESTRE - " + this.iea;
	}

	/**
	 * Metodo que edita um pesquisador aluno com o atributo a ser editado e um novo
	 * valor para o atributo.
	 * 
	 * @param atributo  Atributo que vai ser editado.
	 * @param novoValor Novo valor para o atributo.
	 */
	public void alteraPesquisador(String atributo, String novoValor) {
		validador.verificaEntradaNulaVazia(atributo, "Atributo nao pode ser vazio ou nulo.");
		validador.verificaEntradaNulaVazia(novoValor, "novoValor nao pode ser vazio ou nulo.");
		switch (atributo) {
		case "SEMESTRE":
			int valorConvertido = Integer.parseInt(novoValor);
			validador.verificaSemestre(valorConvertido);
			this.semestreIngresso = valorConvertido;
			break;
		case "IEA":
			double valorConvertido2 = Double.parseDouble(novoValor);
			validador.verificaIEA(valorConvertido2);
			this.iea = valorConvertido2;
			break;
		}
	}

}
