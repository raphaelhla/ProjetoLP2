package psquiza;

/**
 * Representacao de um validador para verificar entradas.
 * 
 * @author Raphael Agra - 119110413
 *
 */
public class Validador {

	/**
	 * Metodo que verifica se uma entrada é vazia ou nula a partir da entrada e de
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

}
