import java.io.BufferedReader;
import java.io.FileReader;

/*
* Nome: Ricardo Soares Cerqueira
* Matricula: 803833
* Trabalho Prático 3 - Aeds II - Q06
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

	public static Jogador topo; // Aponta para o topo da fila
	public static int sizePilha = 0;

	Jogador() {
		this.id = -1;
		this.nome = null;
		this.altura = -1;
		this.peso = -1;
		this.universidade = null;
		this.anoNascimento = -1;
		this.cidadeNascimento = null;
		this.estadoNascimento = null;
		this.prox = null;

	}

	Jogador(String infos) {
		setJogador(infos);
		this.prox = null;
	}

	public static void startPilha() {
		topo = new Jogador();
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
		String str = "";

		str += "[" + pos + "] ## ";
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

		FileReader file = new FileReader("players.csv");
		//FileReader file = new FileReader("/tmp/players.csv");
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
	 * armazena os jogadores na pilha
	 * 
	 * @params pilha listaJogador
	 */
	public static void adicionaJogadores() {
		String id = MyIO.readLine();
		boolean isFim = false;

		while (!isFim) {

			if ((Integer.parseInt(id) >= 0) && (Integer.parseInt(id) <= 3921)
					/*&& !idExiste(Integer.parseInt(id))*/) { // Verifica se o ID é valido
				try {
					Jogador jogador = new Jogador();
					jogador.procuraIDeSalva(id);
					inserir(jogador);
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
	 * Insere um elemento na ultima posicao da pilha.
	 * 
	 * @param Jogador j - elemento a ser inserido.
	 */
	public static void inserir(Jogador j) {
		Jogador tmp = j.cloneJogador();
		tmp.prox = topo;
		topo = tmp;
		tmp = null;
		sizePilha++;
	} // fim inserir()


	/* *************** Remocoes *************** */

	/**
	 * Remove um elemento da primeira posicao da pilha e movimenta
	 * os demais elementos para o inicio da mesma.
	 * 
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a pilha estiver vazia.
	 */
	public static Jogador remover() throws Exception {
		if (topo == null) {
			throw new Exception("Erro ao remover!");
		}

		Jogador resp = topo;
		Jogador tmp = topo;
		topo = topo.prox;
		tmp.prox = null;
		tmp = null;
		sizePilha--;
		return resp;

	} // fim removerInicio()


	/* *************** Outros *************** */

	/**
	 * Chama o método recursivo mostraPilha()
	 */
	public static void mostraPilha() {
		mostraPilha(topo, sizePilha);
	}// fim mostraPilha()

	/**
	 * Mostra todos os elementos da pilha em ordem de insercao
	 * @param i
	 * @param num
	 */
	private static void mostraPilha(Jogador i, int num) {
		if (i != null && num > 0) {
			mostraPilha(i.prox, --num);
			MyIO.println(i.toStringSemID(num));
		}
	}// mostraPilha()

	/**
	 * Procura um elemento pelo id e retorna se ele existe.
	 * 
	 * @param int idChave - id do elemento a ser pesquisado.
	 * @return true se o array existir, false em caso contrario.
	 */
	public static boolean idExiste(int idChave) {
		boolean resp = false;
		for (Jogador i = topo.prox; i != null; i = i.prox) {
			if (i.id == idChave) {
				resp = true;
				i = null;
			}
		}
		return resp;
	}// fim idExiste()

	/**
	 * Realiza diferentes ações à pilha baseado no parametro
	 * 
	 * @param String[] input - String informada na entrada
	 */
	private static void decodifica(String[] input) {
		Jogador jogador = new Jogador();

		try{
			if(input[0].equals("I")){
					jogador.procuraIDeSalva(input[1]);
					inserir(jogador);
				}
				else if(input[0].equals("R")){
					jogador = remover();
					MyIO.println("(R) " + jogador.getNome());
				}
				else MyIO.println("Erro ao manipular pilha!");
		} catch (Exception e) {
			MyIO.println(e.getMessage());
		}

	}// fim swithcInput()

	/**
	 * Manipula a pilha na quantidade informada, baseado na string de entrada
	 */
	public static void manipulaPilha() {
		int count = MyIO.readInt();
		while (count > 0) {
			String[] input = MyIO.readLine().split(" ");
			decodifica(input);
			count--;
		} // fim while

	}// fim manipulaLista()

	/***************************************************************************************/
	/***************************************************************************************/

	public static void main(String[] args) throws Exception {

		startPilha();

		adicionaJogadores();
		manipulaPilha();
		mostraPilha();

	}// fim main

}// fim classe Jogador
