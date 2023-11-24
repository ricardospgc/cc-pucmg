import java.io.BufferedReader;
import java.io.FileReader;

/*
* Nome: Ricardo Soares Cerqueira
* Matricula: 803833
* Trabalho Prático 3 - Aeds II - Q01
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

	static Integer sizeLista = 0;

	Jogador() {
		this.id = null;
		this.nome = null;
		this.altura = null;
		this.peso = null;
		this.universidade = null;
		this.anoNascimento = null;
		this.cidadeNascimento = null;
		this.estadoNascimento = null;

	}

	Jogador(String infos) {
		setJogador(infos);
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
	public static void adicionaJogadores(Jogador[] lista) {
		String id = MyIO.readLine();
		boolean isFim = false;

		while (!isFim) {

			if ((Integer.parseInt(id) >= 0) && (Integer.parseInt(id) <= 3921)
					&& !idExiste(lista, Integer.parseInt(id))) { // Verifica se o ID é valido
				try {
					Jogador jogador = new Jogador();
					jogador.procuraIDeSalva(id);
					inserirFim(lista, jogador);
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
	 * Insere um elemento na primeira posicao da lista e move os demais
	 * elementos para o fim da lista.
	 * 
	 * @param Jogador j - elemento a ser inserido.
	 * 
	 * @throws Exception Se a lista estiver cheia.
	 */
	public static void inserirInicio(Jogador[] lista, Jogador j) throws Exception {

		// validar insercao
		if (sizeLista >= lista.length) {
			throw new Exception("Erro ao inserir inicio!");
		}

		// levar elementos para o fim do array
		for (int i = sizeLista; i > 0; i--) {
			lista[i] = lista[i - 1].cloneJogador();
		}

		lista[0] = j.cloneJogador();
		sizeLista++;
	} // fim inserirInicio()
	
	/**
	 * Insere um elemento na ultima posicao da lista.
	 * 
	 * @param Jogador j - elemento a ser inserido.
	 * @throws Exception Se a lista estiver cheia.
	 */
	public static void inserirFim(Jogador[] lista, Jogador j) throws Exception {
		// validar insercao
		if (sizeLista > lista.length) {
			throw new Exception("Erro ao inserir fim!");
		}
		lista[sizeLista] = j.cloneJogador();
		sizeLista++;
	} // fim inserirFim()

	/**
	 * Insere um elemento em uma posicao especifica e move os demais
	 * elementos para o fim da lista.
	 * 
	 * @param Jogador j - elemento a ser inserido.
	 * @param int     pos - Posicao de insercao.
	 * @throws Exception Se a lista estiver cheia ou a posicao invalida.
	 */
	public static void inserirPos(Jogador[] lista, Jogador j, int pos) throws Exception {
		// validar insercao
		if (sizeLista >= lista.length || pos < 0 || pos > sizeLista) {
			throw new Exception("Erro ao inserir pos!");
		}

		// levar elementos para o fim do array
		for (int i = sizeLista; i > pos; i--) {
			lista[i] = lista[i - 1].cloneJogador();
		}

		lista[pos] = j.cloneJogador();
		sizeLista++;
	}// fim inserirPos()

	/* *************** Remocoes *************** */

	/**
	 * Remove um elemento da primeira posicao da lista e movimenta
	 * os demais elementos para o inicio da mesma.
	 * 
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista estiver vazia.
	 */
	public static Jogador removerInicio(Jogador[] lista) throws Exception {

		// validar remocao
		if (sizeLista == 0) {
			throw new Exception("Erro ao remover inicio!");
		}

		Jogador resp = lista[0].cloneJogador();
		sizeLista--;

		for (int i = 0; i < sizeLista; i++) {
			lista[i] = lista[i + 1].cloneJogador();
		}

		return resp;
	} // fim removerInicio()

	/**
	 * Remove um elemento da ultima posicao da lista.
	 * 
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista estiver vazia.
	 */
	public static Jogador removerFim(Jogador[] lista) throws Exception {

		// validar remocao
		if (sizeLista == 0) {
			throw new Exception("Erro ao remover fim!");
		}

		return lista[--sizeLista];
	}// fim removerFim()

	/**
	 * Remove um elemento de uma posicao especifica da lista e
	 * movimenta os demais elementos para o inicio da mesma.
	 * 
	 * @param pos Posicao de remocao.
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a lista estiver vazia ou a posicao for invalida.
	 */
	public static Jogador removerPos(Jogador[] lista, int pos) throws Exception {

		// validar remocao
		if (sizeLista == 0 || pos < 0 || pos >= sizeLista) {
			throw new Exception("Erro ao remover pos!");
		}

		Jogador resp = lista[pos].cloneJogador();
		sizeLista--;

		for (int i = pos; i < sizeLista; i++) {
			lista[i] = lista[i + 1].cloneJogador();
		}

		return resp;
	}// fim removerPos()

	/* *************** Outros *************** */

	/**
	 * Mostra os elementos da lista com a formatacao adequada
	 */
	public static void mostraLista(Jogador[] lista) {
		for (int i = 0; i < sizeLista; i++) {
			MyIO.println(lista[i].toStringSemID(i));
		}
	}// fim mostrar()

	/**
	 * Procura um elemento pelo id e retorna se ele existe.
	 * 
	 * @param int idChave - id do elemento a ser pesquisado.
	 * @return true se o array existir, false em caso contrario.
	 */
	public static boolean idExiste(Jogador[] lista, int idChave) {
		boolean resp = false;
		for (int i = 0; i < sizeLista && resp == false; i++) {
			resp = (lista[i].getId() == idChave);
		}
		return resp;
	}// fim idExiste()

		/**
	 * Realiza diferentes ações à lista baseado no parametro
	 * @param String[] input - String informada na entrada
	 */
	private static void switchInput(Jogador[] lista, String[] input) {
		Jogador jogador = new Jogador();
		try {
			switch(input[0]){
				case "II":
					jogador.procuraIDeSalva(input[1]);
					inserirInicio(lista, jogador);
					break;
				case "IF":
					jogador.procuraIDeSalva(input[1]);
					inserirFim(lista, jogador);
					break;
				case "I*":
					jogador.procuraIDeSalva(input[2]);
					inserirPos(lista, jogador, Integer.parseInt(input[1]));
					break;
				case "RI":
					jogador = removerInicio(lista);
					MyIO.println("(R) " + jogador.getNome());
					break;
				case "RF":
					jogador = removerFim(lista);
					MyIO.println("(R) " + jogador.getNome());
					break;
				case "R*":
					jogador = removerPos(lista, Integer.parseInt(input[1]));
					MyIO.println("(R) " + jogador.getNome());
					break;
				default:
					MyIO.println("Erro ao manipular lista!");
			}//fim switch
				
		} catch (Exception e) {
				MyIO.println(e.getMessage());
		}



	}// fim swithcInput()

	/**
	 * Manipula a lista na quantidade informada, baseado na string de entrada
	 */
	public static void manipulaLista(Jogador[] lista){
		int count = MyIO.readInt();
		while(count > 0){
			String[] input = MyIO.readLine().split(" ");
			switchInput(lista, input);			
			count--;
		}//fim while

	}// fim manipulaLista()



	/***************************************************************************************/
	/***************************************************************************************/

	public static void main(String[] args) throws Exception {

		Jogador[] lista = new Jogador[1000];

		adicionaJogadores(lista);
		manipulaLista(lista);
		mostraLista(lista);
		

	}// fim main

}// fim classe Jogador
