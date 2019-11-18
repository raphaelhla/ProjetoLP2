package psquiza;

import java.io.Serializable;

/**
 * Representacao de um validador para verificar entradas.
 * 
 * @author Raphael Agra 119110413
 *
 */
public class Validador implements Serializable{

	/**
	 * Metodo que verifica se uma entrada e vazia ou nula a partir da entrada e de
	 * uma mensagem de excecao.
	 * 
	 * @param entrada  Entrada a ser verificada.
	 * @param mensagem Mensagem de excecao.
	 */
	public void verificaEntradaNulaVazia(String entrada, String mensagem) {
		if (entrada == null) {
			throw new NullPointerException(mensagem);
		}

		if ("".equals(entrada.trim())) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Metodo que verifica se um email segue os padroes corretos a partir de um
	 * email.
	 * 
	 * @param email Email
	 */
	public void verificaEmail(String email) {
		verificaEntradaNulaVazia(email, "Campo email nao pode ser nulo ou vazio.");
		String primeiraLetra = email.substring(0, 1);
		String ultimaLetra = email.substring(email.length() - 1);

		if (primeiraLetra.equals("@")) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
		if (ultimaLetra.equals("@")) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
		if (!email.contains("@")) {
			throw new IllegalArgumentException("Formato de email invalido.");
		}
	}

	/**
	 * Metodo que verifica se o link de uma foto segue os padroes corretor a partir
	 * do link da foto
	 * 
	 * @param fotoURL Link da foto.
	 */
	public void verificafotoURL(String fotoURL) {
		verificaEntradaNulaVazia(fotoURL, "Campo fotoURL nao pode ser nulo ou vazio.");
		if (fotoURL.length() < 7) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
		String inicialTipo1 = fotoURL.substring(0, 7);
		String inicialTipo2 = fotoURL.substring(0, 8);

		if (!inicialTipo1.equals("http://") && !inicialTipo2.equals("https://")) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

	/**
	 * Metodo que verifica se o nivel de risco segue os padroes corretos a partir do
	 * nivel de risco.
	 * 
	 * @param nivelRisco Nivel de risco.
	 */
	public void verificaNivelRisco(String nivelRisco) {
		verificaEntradaNulaVazia(nivelRisco, "Campo nivelRisco nao pode ser nulo ou vazio.");
		if (!nivelRisco.equals("BAIXO") && !nivelRisco.equals("MEDIO") && !nivelRisco.equals("ALTO")) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
	}

	/**
	 * Metodo que verifica se o tipo de um objetivo segue os padroes corretos a
	 * partir de um tipo de um objetivo.
	 * 
	 * @param tipo Tipo de um objetivo.
	 */
	public void verificaTipoObjetivo(String tipo) {
		verificaEntradaNulaVazia(tipo, "Campo tipo nao pode ser nulo ou vazio.");
		if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}
	}

	/**
	 * Metodo que verifica se a viabilidade segue os padroes corretos a partir de
	 * uma viabilidade.
	 * 
	 * @param viabilidade Viabilidade
	 */
	public void verificaViabilidade(int viabilidade) {
		if (viabilidade < 1 || viabilidade > 5) {
			throw new IllegalArgumentException("Valor invalido de viabilidade.");
		}
	}

	/**
	 * Metodo que verifica se uma aderencia segue os padroes corretos a partir de
	 * uma aderencia.
	 * 
	 * @param aderencia Aderencia
	 */
	public void verificaAderencia(int aderencia) {
		if (aderencia < 1 || aderencia > 5) {
			throw new IllegalArgumentException("Valor invalido de aderencia");
		}
	}

	/**
	 * Metodo que verifica se o campo de interesse segue os padroes corretos a
	 * partir de um campo de interesse.
	 * 
	 * @param campoDeInteresse Campo de interesse.
	 */
	public void verificaCampoDeInteresse(String campoDeInteresse) {
		verificaEntradaNulaVazia(campoDeInteresse, "Formato do campo de interesse invalido.");

		if (campoDeInteresse.length() > 255) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}

		String[] topicos = campoDeInteresse.split(",");
		if (topicos.length > 4) {
			throw new IllegalArgumentException("Formato do campo de interesse invalido.");
		}
		for (String e : topicos) {
			if (e.length() < 3) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
			if ("".equals(e.trim())) {
				throw new IllegalArgumentException("Formato do campo de interesse invalido.");
			}
		}
	}

	/**
	 * Metodo que verifica se data segue os padroes de formato corretos.
	 * 
	 * @param data Data a ser verificada.
	 */
	public void verificaData(String data) {
		verificaEntradaNulaVazia(data, "Campo data nao pode ser nulo ou vazio.");
		String[] dataSeparada = data.split("/");

		if (dataSeparada.length < 3) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}

		if (dataSeparada[0].length() != 2 || dataSeparada[1].length() != 2 || dataSeparada[2].length() != 4) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}

		int dia = Integer.parseInt(dataSeparada[0]);
		int mes = Integer.parseInt(dataSeparada[1]);
		if ((dia < 1 || dia > 31) || (mes < 1 || mes > 12)) {
			throw new IllegalArgumentException("Atributo data com formato invalido.");
		}
	}

	/**
	 * Metodo que verifica se o semestre segue os padroes de formato corretos.
	 * 
	 * @param semestre Semestre a ser verificado.
	 */
	public void verificaSemestre(int semestre) {
		if (semestre < 1) {
			throw new IllegalArgumentException("Atributo semestre com formato invalido.");
		}
	}

	/**
	 * Metodo que verifica se a IEA segue os padroes de formato corretos.
	 * 
	 * @param iea IEA a ser verificada.
	 */
	public void verificaIEA(double iea) {
		if (iea < 0 || iea > 10) {
			throw new IllegalArgumentException("Atributo IEA com formato invalido.");
		}
	}

	/**
	 * Metodo que verifica se um numero segue os padroes de formato corretos.
	 * 
	 * @param num Numero a ser verificado.
	 * @param msg Mensagem de erro da exception.
	 */
	public void verificaNum(int num, String msg) {
		if (num < 0) {
			throw new IllegalArgumentException(msg);
		}
	}

	public void verificaEstrategia(String estrategia) {
		verificaEntradaNulaVazia(estrategia, "Estrategia nao pode ser nula ou vazia.");
		if (!estrategia.equals("MAIS_ANTIGA") && !estrategia.equals("MENOS_PENDENCIAS") && !estrategia.equals("MAIOR_RISCO") && !estrategia.equals("MAIOR_DURACAO")) {
			throw new IllegalArgumentException("Valor invalido da estrategia");
		}
	}
}
