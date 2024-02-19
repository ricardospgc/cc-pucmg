import java.io.BufferedReader;
import java.io.FileReader;

/*
* Nome: Ricardo Soares Cerqueira
* Matricula: 803833
* Trabalho Prático 3 - Aeds II - Q03
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

	static Integer sizePilha = 0;

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
	 * armazena os jogadores na pilha
	 * 
	 * @params pilha pilhaJogador
	 */
	public static void adicionaJogadores(Jogador[] pilha) {
		String id = MyIO.readLine();
		boolean isFim = false;

		while (!isFim) {

			if ((Integer.parseInt(id) >= 0) && (Integer.parseInt(id) <= 3921)
					&& !idExiste(pilha, Integer.parseInt(id))) { // Verifica se o ID é valido
				try {
					Jogador jogador = new Jogador();
					jogador.procuraIDeSalva(id);
					inserirFim(pilha, jogador);
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
	 * @throws Exception Se a pilha estiver cheia.
	 */
	public static void inserirFim(Jogador[] pilha, Jogador j) throws Exception {
		// validar insercao
		if (sizePilha > pilha.length) {
			throw new Exception("Erro ao inserir fim!");
		}
		pilha[sizePilha] = j.cloneJogador();
		sizePilha++;
	} // fim inserirFim()

	/* *************** Remocoes *************** */

	/**
	 * Remove um elemento da ultima posicao da pilha.
	 * 
	 * @return resp int elemento a ser removido.
	 * @throws Exception Se a pilha estiver vazia.
	 */
	public static Jogador removerFim(Jogador[] pilha) throws Exception {

		// validar remocao
		if (sizePilha == 0) {
			throw new Exception("Erro ao remover fim!");
		}

		return pilha[--sizePilha];
	}// fim removerFim()

	/* *************** Outros *************** */

	/**
	 * Mostra os elementos da pilha com a formatacao adequada
	 */
	public static void mostraPilha(Jogador[] pilha) {
		for (int i = 0; i < sizePilha; i++) {
			MyIO.println(pilha[i].toStringSemID(i));
		}
	}// fim mostrar()

	/**
	 * Procura um elemento pelo id e retorna se ele existe.
	 * 
	 * @param int idChave - id do elemento a ser pesquisado.
	 * @return true se o array existir, false em caso contrario.
	 */
	public static boolean idExiste(Jogador[] pilha, int idChave) {
		boolean resp = false;
		for (int i = 0; i < sizePilha && resp == false; i++) {
			resp = (pilha[i].getId() == idChave);
		}
		return resp;
	}// fim idExiste()

	/**
	 * Interpreta o input e age de acordo
	 * @param pilha
	 * @param input
	 */
	public static void decodifica(Jogador[] pilha, String[] input){
		Jogador jogador = new Jogador();

		try{
			if(input[0].equals("I")){
					jogador.procuraIDeSalva(input[1]);
					inserirFim(pilha, jogador);
				}
				else if(input[0].equals("R")){
					jogador = removerFim(pilha);
					MyIO.println("(R) " + jogador.getNome());
				}
				else MyIO.println("Erro ao manipular lista!");
		} catch (Exception e) {
			MyIO.println(e.getMessage());
	}


	}
	/**
	 * Manipula a pilha na quantidade informada, baseado na string de entrada
	 * @param Jogador[] pilha
	 */
	public static void manipulaPilha(Jogador[] pilha){
		int count = MyIO.readInt();
		while(count > 0){
			
			String[] input = MyIO.readLine().split(" ");
			decodifica(pilha, input);

			count--;
		}//fim while

	}// fim manipulaPilha()



	/***************************************************************************************/
	/***************************************************************************************/

	public static void main(String[] args) throws Exception {

		Jogador[] pilha = new Jogador[1000];

		adicionaJogadores(pilha);
		manipulaPilha(pilha);
		mostraPilha(pilha);
		

	}// fim main

}// fim classe Jogador
