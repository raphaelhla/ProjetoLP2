	package psquiza;

import java.util.HashMap;
import java.util.Map;

/**
	* Laborat�rio de Programa��o 2 - Lab 1
	* 
	* @author Matheus Vinicius Benevides Lima - 119210384
	*/
	public class PesquisaController {

		private Map<String, Pesquisa> mapPesquisas = new HashMap<String, Pesquisa>();

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
			
			mapPesquisas.put(codigo, new Pesquisa(descricao, campoDeInteresse, codigo));

			return codigo;

		}

		public void alteraPesquisa(String codigo, String conteudoASerAlterado, String novoConteudo) {

		}

		public void encerraPesquisa(String codigo, String motivo) {

		}

		public void ativaPesquisa(String codigo) {

		}

		public String exibePesquisa(String codigo) {
			return "";
		}

		public boolean ehAtiva(String codigo) {
			return true;
		}

	}