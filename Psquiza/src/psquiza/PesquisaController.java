package psquiza;

import java.util.HashMap;
import psquiza.Pesquisa;
import java.util.Map;
import psquiza.Validador;

/**
 * Laborat�rio de Programa��o 2 - Lab 1
 * 
 * @author Matheus Vinicius Benevides Lima - 119210384
 */
public class PesquisaController {

	private Map<String, Pesquisa> mapPesquisas = new HashMap<String, Pesquisa>();
	private Validador validador;

	public String cadastraPesquisa(String descricao, String campoDeInteresse) {

		String codigo = "";
		char[] charPesquisas = new char[255];
		String[] arrayCodigo = campoDeInteresse.split(", ");
		int numero = 0;
		for (String nome : arrayCodigo) {
			for (int i = 0; i < 3;) {
				charPesquisas[0] = nome.charAt(0);
				charPesquisas[1] = nome.charAt(1);
				charPesquisas[2] = nome.charAt(2);

				break;

			}
			break;

		}

		for (int i = 0; i < 3; i++) {
			if (i < 3) {
				codigo += charPesquisas[i];
			} else {
				codigo += numero;
				numero++;
			}

		}

		mapPesquisas.put(codigo, new Pesquisa(descricao, campoDeInteresse));

		return codigo;

	}
	
	
	public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		boolean contemPesquisa = mapPesquisas.containsKey(codigo);
		if(mapPesquisas.get(codigo).getStatusPesquisa() == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");
		}
		
		else if (!contemPesquisa) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		}else {
			if(conteudoASerAlterado.equals("DESCRICAO")) {
				validador.verificaEntradaNulaVazia(novoConteudo, "Descricao nao pode ser nula ou vazia.");
				mapPesquisas.get(codigo).setDescricao(novoConteudo);
				
			}
			else if(conteudoASerAlterado.equals("CAMPO")) {
				validador.verificaEntradaNulaVazia(novoConteudo, "Formato do campo de interesse invalido.");
				mapPesquisas.get(codigo).setCampoDeInteresse(novoConteudo);
			}else {
				throw new IllegalArgumentException("Nao e possivel alterar esse valor de pesquisa.");
			}
		}
			
		
		
	}

	public void encerraPesquisa(String codigo, String motivo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		validador.verificaEntradaNulaVazia(motivo, "Motivo nao pode ser nulo ou vazio.");
		boolean contemPesquisa = mapPesquisas.containsKey(codigo);
		if (mapPesquisas.get(codigo).getStatusPesquisa() == false) {
			throw new IllegalArgumentException("Pesquisa desativada.");

		} else if (!contemPesquisa) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");

		} else {
			String motidoDoCancelamento = motivo;
			mapPesquisas.get(codigo).desativaPesquisa();
		}

	}

	public void ativaPesquisa(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		boolean contemPesquisa = mapPesquisas.containsKey(codigo);
		if (mapPesquisas.get(codigo).getStatusPesquisa() == true) {
			throw new IllegalArgumentException("Pesquisa ja ativada.");
		} else if (!contemPesquisa) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		} else {
			mapPesquisas.get(codigo).ativaPesquisa();
		}

	}

	public String exibePesquisa(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		boolean contemPesquisa = mapPesquisas.containsKey(codigo);
		if (!contemPesquisa) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");

		}else {
			return mapPesquisas.get(codigo).toString();
		}
	}

	public boolean ehAtiva(String codigo) {
		validador.verificaEntradaNulaVazia(codigo, "Codigo nao pode ser nulo ou vazio.");
		boolean contemPesquisa = mapPesquisas.containsKey(codigo);
		if (!contemPesquisa) {
			throw new IllegalArgumentException("Pesquisa nao encontrada.");
		} else {

			return mapPesquisas.get(codigo).getStatusPesquisa();
		}

	}

}
