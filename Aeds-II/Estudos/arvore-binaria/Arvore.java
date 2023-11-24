class Arvore {
	private No raiz;

	/* Construtor */
	Arvore() {
		raiz = null;
	}

	/*** Pesquisas ***/

	/**
	 * Metodo publico de pesquisa
	 * 
	 * @param int chave - elemento a ser pesquisado
	 * @return boolean com o resultado da pesquisa;
	 */
	public boolean pesquisar(int chave) {
		return pesquisar(chave, raiz);
	} // fim pesquisar()

	/**
	 * Metodo privado de pesquisa, recursivo
	 * 
	 * @param int chave - elemento a ser pesquisado
	 * @param No  raiz - raiz da Arvore
	 * @return boolean com o resultado da pesquisa
	 */
	public boolean pesquisar(int chave, No raiz) {
		boolean resp = false;

		if (raiz == null)
			resp = false;

		else if (chave == raiz.elemento)
			resp = true;

		else if (chave < raiz.elemento)
			pesquisar(chave, raiz.esq);

		else
			pesquisar(chave, raiz.dir);

		return resp;
	} // fim pesquisar()

	/*** Caminhamentos ***/

	public void caminharCentral() {
		MyIO.print("[");
		caminharCentral(raiz);
		MyIO.print("]");

	} // fim caminharCentral()

	private void caminharCentral(No i) {
		if (i != null) {
			caminharCentral(i.esq);
			MyIO.print(i.elemento + " ");
			caminharCentral(i.dir);
		}
	} // fim caminharCentral()

	public void caminharPre() {
		MyIO.print("[");
		caminharPre(raiz);
		MyIO.print("]");

	} // fim caminharPre()

	private void caminharPre(No i) {
		if (i != null) {
			MyIO.print(i.elemento + " ");
			caminharPre(i.esq);
			caminharPre(i.dir);
		}
	} // fim caminharPre()

	public void caminharPos() {
		MyIO.print("[");
		caminharPos(raiz);
		MyIO.print("]");

	} // fim caminharPos()

	private void caminharPos(No i) {
		if (i != null) {
			caminharPos(i.esq);
			caminharPos(i.dir);
			MyIO.print(i.elemento + " ");
		}
	} // fim caminharPos()

	/*** Inserir ***/

	/**
	 * Metodo publico para inserir um valor
	 * 
	 * @param valor
	 * @throws Exception se o elemento já existir
	 */
	public void inserir(int valor) throws Exception {
		raiz = inserir(valor, raiz);
	} // fim inserir()

	/**
	 * Metodo privado recursivo para inserir um valor
	 * 
	 * @param int valor
	 * @param No  i
	 * @throws Exception se o elemento já existir
	 */
	private No inserir(int valor, No i) throws Exception {
		if (i == null)
			i = new No(valor);

		else if (valor > i.elemento)
			i.dir = inserir(valor, i.dir);

		else if (valor < i.elemento)
			i.esq = inserir(valor, i.esq);

		else
			throw new Exception("Elemento ja existe!");

		return i;
	} // fim inserir()

	/*** Remocoes ***/

	/**
	 * Metodo publico para remover o elemento parametrizado
	 * 
	 * @param valor a ser removido
	 * @throws Exception se o valor nao existe
	 */
	public void remover(int valor) throws Exception {
		raiz = remover(valor, raiz);
	} // fim remover()

	/**
	 * Metodo privado recursivo para remover elemento.
	 * 
	 * @param valor Elemento a ser removido.
	 * @param i     No em analise.
	 * @return No em analise, alterado ou nao.
	 * @throws Exception Se nao encontrar elemento.
	 */
	private No remover(int valor, No i) throws Exception {
		if (i == null)
			throw new Exception("Elemento nao existe!");

		else if (valor > i.elemento)
			i.dir = remover(valor, i.dir);

		else if (valor < i.elemento)
			i.esq = remover(valor, i.esq);

		// A partir daqui, tratamento de quando o elemento foi encontrado;
		// É preciso identificar se o elemento a ser removido tem 0 filhos, 1 filho e
		// seu lado ou 2 filhos;

		// No a ser removido tem 0 filhos ou 1 filho à esquerda, "sobe" com o filho à
		// esquerda;
		else if (i.dir == null)
			i = i.esq;

		// No a ser removido tem 1 filho à direita, "sobe" com o filho à direita;
		else if (i.esq == null)
			i = i.dir;

		/*
		 * No a ser removido tem 2 filhos.
		 * Nesse caso, o elemento removido deve ser substituido pelo
		 * maior no da subarvore esquerda ou menor no da subarvore direita
		 * a fim de manter a propriedade de AB de pesquisa
		 */
		else
			i.esq = maiorEsq(i, i.esq); // encontra o substituto, o maior no da subarvore esquerda.

		return i;

	} // fim remover()

	/**
	 * Metodo para trocar o elemento "removido" pelo maior da esquerda.
	 * 
	 * @param i No que teve o elemento removido.
	 * @param j No da subarvore esquerda.
	 * @return No em analise, alterado ou nao.
	 */
	private No maiorEsq(No i, No j) {

		// Encontrou o maximo da subarvore esquerda.
		if (j.dir == null) {
			i.elemento = j.elemento; // Substitui i por j.
			j = j.esq; // Substitui j por j.ESQ.

			// Existe no a direita.
		} else {
			// Caminha para direita.
			j.dir = maiorEsq(i, j.dir);
		}
		return j;
	}// fim maiorEsq()

	/**
	 * Retorna o maior elemento da árvore
	 * 
	 * @return resp
	 */
	public int getMaior() {
		int resp = -1;

		if (raiz != null) {
			No i;
			for (i = raiz; i.dir != null; i = i.dir)
				;
			resp = i.elemento;
		}

		return resp;
	} // fim getMaior()

	/**
	 * Retorna o menor elemento da árvore
	 * 
	 * @return resp
	 */
	public int getMenor() {
		int resp = -1;

		if (raiz != null) {
			No i;
			for (i = raiz; i.esq != null; i = i.esq)
				;
			resp = i.elemento;
		}

		return resp;
	} // fim getMaior()

	/**
	 * Metodo que retorna a altura da árvore
	 * @return int altura da árvore
	 */
	public int getAltura() {
		return getAltura(raiz, 0);
	} // fim getAltura()

	/**
	 * Metodo que retorna a altura da árvore
	 * @return int altura da árvore
	 */
	public int getAltura(No i, int altura) {
		if (i == null) {
			altura--;
		} else {
			int alturaEsq = getAltura(i.esq, altura + 1);
			int alturaDir = getAltura(i.dir, altura + 1);
			altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
		}
		return altura;
	} // fim getAltura()


	public static void main(String[] args) throws Exception{
		Arvore arvoreBinaria = new Arvore();
        for(int i = 0; i < 15; i++){
			int j = MyIO.readInt();
            arvoreBinaria.inserir(j);
        }
		
		arvoreBinaria.caminharCentral();
		System.out.print("\t Altura: " + arvoreBinaria.getAltura());
	}// fim main()
}
