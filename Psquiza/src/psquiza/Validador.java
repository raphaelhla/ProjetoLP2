package psquiza;

public class Validador {

	public void verificaEntradaNulaVazia(String entrada, String mensagem) {
		if (entrada == null) {
			throw new NullPointerException(mensagem);
		}

		if ("".equals(entrada.trim())) {
			throw new IllegalArgumentException(mensagem);
		}
	}

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

	public void verificafotoURL(String fotoURL) {
		if (fotoURL.length() < 8) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
		String inicialTipo1 = fotoURL.substring(0, 7);
		String inicialTipo2 = fotoURL.substring(0, 8);

		if (!inicialTipo1.equals("http://") && !inicialTipo2.equals("https://")) {
			throw new IllegalArgumentException("Formato de foto invalido.");
		}
	}

	public void verificaNivelRisco(String nivelRisco) {
		if (!nivelRisco.equals("BAIXO") && !nivelRisco.equals("MEDIO") && !nivelRisco.equals("ALTO")) {
			throw new IllegalArgumentException("Valor invalido do nivel do risco.");
		}
	}

	public void verificaTipoObjetivo(String tipo) {
		if (!tipo.equals("GERAL") && !tipo.equals("ESPECIFICO")) {
			throw new IllegalArgumentException("Valor invalido de tipo.");
		}
	}
	
	public void verificaViabilidade(int viabilidade) {
		if (viabilidade < 1 || viabilidade > 5) {
			throw new IllegalArgumentException("Valor invalido de viabilidade.");
		}
	}

	public void verificaAderencia(int aderencia) {
		if (aderencia < 1 || aderencia > 5) {
			throw new IllegalArgumentException("Valor invalido de aderencia");
		}
	}
	
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
