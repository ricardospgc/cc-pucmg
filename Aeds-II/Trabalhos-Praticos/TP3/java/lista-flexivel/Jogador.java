import java.io.BufferedReader;
import java.io.FileReader;

/*
* Nome: Ricardo Soares Cerqueira
* Matricula: 803833
* Trabalho Prático 3 - Aeds II - Q05
*/

public class Jogador {
	private Integer id;
	private String nome;
	private Integer altura; // em centímetros
	private Integer peso; // somente quilos
	private String universidade;
	private Integer anoNascimento;
	private String cidadeNascimento;
	private String estadoNascimento;

	public Jogador prox;

	static public Integer sizeLista = 0;
	public static Jogador primeiro; // indica o no cabeça da lista
	public static Jogador ultimo; // indica o ultimo jogador da lista

	Jogador() {
		this.id = null;
		this.nome = null;
		this.altura = null;
		this.peso = null;
		this.universidade = null;
		this.anoNascimento = null;
		this.cidadeNascimento = null;
		this.estadoNascimento = null;
		this.prox = null;

	}

	Jogador(String infos) {
		setJogador(infos);
		this.prox = null;
	}

	public static void startLista() {
		primeiro = new Jogador();
		ultimo = primeiro;
	}

	/* Gets */
	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public int getAltura() {
		return this.altura;
	}

	public int getPeso() {
		return this.peso;
	}

	public String getUniversidade() {
		return this.universidade;
	}

	public int getAnoNascimento() {
		return this.anoNascimento;
	}

	public String getCidadeNascimento() {
		return this.cidadeNascimento;
	}

	public String getEstadoNascimento() {
		return this.estadoNascimento;
	}

	/* Sets */
	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}

	public void setUniversidade(String universidade) {
		this.universidade = universidade;
	}

	public void setAnoNascimento(int anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public void setCidadeNascimento(String cidadeNascimento) {
		this.cidadeNascimento = cidadeNascimento;
	}

	public void setEstadoNascimento(String estadoNascimento) {
		this.estadoNascimento = estadoNascimento;
	}

	/*
	 * Clone
	 * Retorna um clone exato do Jogador corrente
	 * 
	 * @return Jogador
	 */
	public Jogador cloneJogador() {
		Jogador clone = new Jogador();

		clone.setId(this.getId());
		clone.nome = this.getNome();
		clone.altura = this.getAltura();
		clone.peso = this.getPeso();
		clone.universidade = this.getUniversidade();
		clone.anoNascimento = this.getAnoNascimento();
		clone.cidadeNascimento = this.getCidadeNascimento();
		clone.estadoNascimento = this.getEstadoNascimento();

		return clone;
	}

	/*
	 * CopiaJogador
	 * Altera todos os dados do jogador corrente para os dados do jogador
	 * parametrizado
	 * 
	 * @params Jogador
	 */
	public void copiaJogador(Jogador copia) {
		this.id = copia.getId();
		this.nome = copia.getNome();
		this.altura = copia.getAltura();
		this.peso = copia.getPeso();
		this.universidade = copia.getUniversidade();
		this.anoNascimento = copia.getAnoNascimento();
		this.cidadeNascimento = copia.getCidadeNascimento();
		this.estadoNascimento = copia.getEstadoNascimento();

	}

	/*
	 * toString
	 * Transforma o objeto corrente em uma string com o Charset adequado
	 * 
	 * @return String
	 */
	public String toString() {
		String str = "[";

		str += this.getId() + "] ## ";
		str += this.getNome() + " ## ";
		str += this.getAltura() + " ## ";
		str += this.getPeso() + " ## ";
		str += this.getAnoNascimento() + " ## ";
		str += this.getUniversidade() + " ## ";
		str += this.getCidadeNascimento() + " ## ";
		str += this.getEstadoNascimento() + " ##";

		return str;
	}

	/*
	 * Transforma o objeto corrente em uma string com o Charset adequado,
	 * trocando o ID pela posicao
	 * 
	 * @return String
	 */
	public String toStringSemID(int pos) {
		String str = "[";

		str += pos + "] ## ";
		str += this.getNome() + " ## ";
		str += this.getAltura() + " ## ";
		str += this.getPeso() + " ## ";
		str += this.getAnoNascimento() + " ## ";
		str += this.getUniversidade() + " ## ";
		str += this.getCidadeNascimento() + " ## ";
		str += this.getEstadoNascimento() + " ##";

		return str;
	}

	/*
	 * Procura no arquivo o Id Chave e salva o objeto relacionado
	 * 
	 * @params Integer
	 */
	public void procuraIDeSalva(String idString) throws Exception {

		Integer idChave = Integer.parseInt(idString);

		//FileReader file = new FileReader("players.csv");
		FileReader file = new FileReader("/tmp/players.csv");
		BufferedReader buffer = new BufferedReader(file);
		String linha;

		while ((linha = buffer.readLine()) != null) {
			String idAtual = linha.substring(0, linha.indexOf(","));

			try {
				if (Integer.parseInt(idAtual) == idChave) {
					this.setJogador(linha);
					break;
				}
			} catch (NumberFormatException e) {
			}
		}

		buffer.close();
		file.close();
	}

	/*
	 * Seta todos os atributos baseado na String do arquivo lido na função
	 * procuraIDnoArquivo()
	 * 
	 * @param String
	 */
	public void setJogador(String linha) {
		String[] split = linha.split(",");

		this.setId(Integer.parseInt(split[0]));
		this.setNome(split[1]);
		this.setAltura(Integer.parseInt(split[2]));
		this.setPeso(Integer.parseInt(split[3]));

		if (split[4].isEmpty())
			this.setUniversidade("nao informado");
		else
			this.setUniversidade(split[4]);

		this.setAnoNascimento(Integer.parseInt(split[5]));

		if (split.length > 6 && !split[6].isEmpty())
			this.setCidadeNascimento(split[6]);
		else
			this.setCidadeNascimento("nao informado");

		if (split.length > 7 && !split[7].isEmpty())
			this.setEstadoNascimento(split[7]);
		else
			this.setEstadoNascimento("nao informado");

	}

	/*
	 * armazena os jogadores na Lista
	 * 
	 * @params Lista listaJogador
	 */
	public static void adicionaJogadores() {
		String id = MyIO.readLine();
		boolean isFim = false;

		while (!isFim) {

			if ((Integer.parseInt(id) >= 0) && (Integer.parseInt(id) <= 3921)
					&& !idExiste(Integer.parseInt(id))) { // Verifica se o ID é valido
				try {
					Jogador jogador = new Jogador();
					jogador.procuraIDeSalva(id);
					inserirFim(jogador);
				} catch (Exception e) {
					MyIO.println(e.getMessage());
				}
			}

			id = MyIO.readLine();
			if (id.equals("FIM"))
				isFim = true;
		}
	} // fim adicionaJogadores()

	/**
	 * Insere um elemento na primeira posicao da lista
	 * 
	 * @param Jogador j - elemento a ser inserido.
	 */
	public static void inserirInicio(Jogador j) {
		Jogador tmp = j.cloneJogador();
		tmp.prox = primeiro.prox;
		primeiro.prox = tmp;
		if (primeiro == ultimo) {
			ultimo = tmp;
		}
		tmp = null;
	}// fim inserirInicio()

	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param Jogador j - elemento a ser inserido.
	 */
	public static void inserirFim(Jogador j) {
		ultimo.prox = j.cloneJogador();
		ultimo = ultimo.prox;
	} // fim inserirFim()

	/**
	 * Insere um elemento em uma posicao especifica e move os demais
	 * elementos para o fim da lista.
	 * 
	 * @param Jogador j - elemento a ser inserido.
	 * @param int     pos - Posicao de insercao.
	 * @throws Exception Se a lista estiver cheia ou a posicao invalida.
	 */
	public static void inserirPos(Jogador jog, int pos) throws Exception {
		int tamanho = tamanhoLista();

		if (pos < 0 || pos > tamanho) {
			throw new Exception("Erro ao inserir posicao (" + pos + " / tamanho = " + tamanho + ") invalida!");
		} else if (pos == 0) {
			inserirInicio(jog);
		} else if (pos == tamanho) {
			inserirFim(jog);
		} else {
			// Caminhar ate a posicao anterior a insercao
			Jogador i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox)
				;

			Jogador tmp = jog.cloneJogador();
			tmp.prox = i.prox;
			i.prox = tmp;
			tmp = i = null;
		}
	}// fim inserirPos()

	/* *************** Remocoes *************** */

	/**
	 * Remove um elemento da primeira posicao da lista e movimenta
	 * os demais elementos para o inicio da mesma.
	 * 
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista estiver vazia.
	 */
	public static Jogador removerInicio() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

		Jogador tmp = primeiro;
		primeiro = primeiro.prox;
		Jogador resp = primeiro;
		tmp.prox = null;
		tmp = null;

		return resp;
	} // fim removerInicio()

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * 
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista estiver vazia.
	 */
	public static Jogador removerFim() throws Exception {
		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");
		}

		// Caminhar ate a penultima celula:
		Jogador i;
		for (i = primeiro; i.prox != ultimo; i = i.prox)
			;

		Jogador resp = ultimo;
		ultimo = i;
		i = ultimo.prox = null;

		return resp;
	}// fim removerFim()

	/**
	 * Remove um elemento de uma posicao especifica da lista e
	 * movimenta os demais elementos para o inicio da mesma.
	 * 
	 * @param pos Posicao de remocao.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
	 */
	public static Jogador removerPos(int pos) throws Exception {
		Jogador resp;
		int tamanho = tamanhoLista();

		if (primeiro == ultimo) {
			throw new Exception("Erro ao remover (vazia)!");

		} else if (pos < 0 || pos >= tamanho) {
			throw new Exception("Erro ao remover (posicao " + pos + " / " + tamanho + " invalida!");
		} else if (pos == 0) {
			resp = removerInicio();
		} else if (pos == tamanho - 1) {
			resp = removerFim();
		} else {
			// Caminhar ate a posicao anterior a insercao
			Jogador i = primeiro;
			for (int j = 0; j < pos; j++, i = i.prox)
				;

			Jogador tmp = i.prox;
			resp = tmp;
			i.prox = tmp.prox;
			tmp.prox = null;
			i = tmp = null;
		}

		return resp;
	}// fim removerPos()

	/* *************** Outros *************** */

	public static int tamanhoLista() {
		int tamanho = 0;
		for (Jogador i = primeiro; i != ultimo; i = i.prox, tamanho++)
			;
		return tamanho;
	}

	/**
	 * Mostra os elementos da lista com a formatacao adequada
	 */
	public static void mostraLista() {
		int num = 0;
		for (Jogador i = primeiro.prox; i != null; i = i.prox, num++) {
			MyIO.println(i.toStringSemID(num));
		}
	}// fim mostrar()

	/**
	 * Procura um elemento pelo id e retorna se ele existe.
	 * 
	 * @param int idChave - id do elemento a ser pesquisado.
	 * @return true se o array existir, false em caso contrario.
	 */
	public static boolean idExiste(int idChave) {
		boolean resp = false;
		for (Jogador i = primeiro.prox; i != null; i = i.prox) {
			if (i.id == idChave) {
				resp = true;
				i = ultimo;
			}
		}
		return resp;
	}// fim idExiste()

	/**
	 * Realiza diferentes ações à lista baseado no parametro
	 * 
	 * @param String[] input - String informada na entrada
	 */
	private static void switchInput(String[] input) {
		Jogador jogador = new Jogador();
		try {
			switch (input[0]) {
				case "II":
					jogador.procuraIDeSalva(input[1]);
					inserirInicio(jogador);
					break;
				case "IF":
					jogador.procuraIDeSalva(input[1]);
					inserirFim(jogador);
					break;
				case "I*":
					jogador.procuraIDeSalva(input[2]);
					inserirPos(jogador, Integer.parseInt(input[1]));
					break;
				case "RI":
					jogador = removerInicio();
					MyIO.println("(R) " + jogador.getNome());
					break;
				case "RF":
					jogador = removerFim();
					MyIO.println("(R) " + jogador.getNome());
					break;
				case "R*":
					jogador = removerPos(Integer.parseInt(input[1]));
					MyIO.println("(R) " + jogador.getNome());
					break;
				default:
					MyIO.println("Erro ao manipular lista!");
			}// fim switch

		} catch (Exception e) {
			MyIO.println(e.getMessage());
		}

	}// fim swithcInput()

	/**
	 * Manipula a lista na quantidade informada, baseado na string de entrada
	 */
	public static void manipulaLista() {
		int count = MyIO.readInt();
		while (count > 0) {
			String[] input = MyIO.readLine().split(" ");
			switchInput(input);
			count--;
		} // fim while

	}// fim manipulaLista()

	/***************************************************************************************/
	/***************************************************************************************/

	public static void main(String[] args) throws Exception {

		startLista();

		adicionaJogadores();
		manipulaLista();
		mostraLista();

	}// fim main

}// fim classe Jogador
