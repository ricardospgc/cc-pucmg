/**
 * Pilha Estatica - First In, Last Out
 * Ricardo Soares Cerqueira
 */

 class Pilha{
    private int n; // Quantidade de elementos na pilha
    private int[] pilha; // pilha propriamente dita

    public Pilha(){ this(6); } // Construtor sem parametro

    /**
     * Construtor com parametro para tamanho da pilha
     * @param tam tamanho da pilha
     */
    public Pilha(int tam){
        pilha = new int[tam];
        n = 0;
    }// fim Pilha()

    /*** INSERCOES ***/

    /**
     * Insere elemento na pilha
     * @param newNum Elemento a ser inserido
     * @throws Exception Se a pilha estiver cheia
     */
    public void inserir(int newNum) throws Exception{ // Insere no fim
        // Verifica se e' possivel inserir
        if(n >= pilha.length) throw new Exception("Erro ao inserir! pilha cheia!");

        pilha[n] = newNum;
        n++;
    }// fim inserir()

    /*** REMOCOES ***/

    /**
     * Remove elemento da pilha
     * @return elemento removido
     * @throws Exception se a pilha estiver vazia
     */
    public int remover() throws Exception{ // Remove no fim
        // Verifica se e' possivel remover
        if(n == 0) throw new Exception("Erro ao remover! pilha vazia!");

        return pilha[--n];
    }// fim remover()

    /**
     * Mostra os elementos da pilha
     */
    public void mostrar(){
        MyIO.print("[");
        for(int i = 0; i < n; i++){
            MyIO.print(" " + pilha[i] + " ");
        }
        MyIO.print("]");
    }// fim mostrar()

}// fim classe pilha