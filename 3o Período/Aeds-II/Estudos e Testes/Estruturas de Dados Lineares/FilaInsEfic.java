/**
 * Fila Estatica - First In, First Out
 * Insercao Eficiente (IF, RI)
 * Ricardo Soares Cerqueira
 */

 class Fila{
    private int n; // Quantidade de elementos na fila
    private int[] fila; // fila propriamente dita

    public Fila(){ this(6); } // Construtor sem parametro

    /**
     * Construtor com parametro para tamanho da fila
     * @param tam tamanho da fila
     */
    public Fila(int tam){
        fila = new int[tam];
        n = 0;
    }// fim fila()

    /*** INSERCOES ***/

    /**
     * Insere o elemento na fila
     * @param newNum Elemento a ser inserido
     * @throws Exception Se a fila estiver cheia
     */
    public void inserir(int newNum) throws Exception{ // Inserir no fim
        // Verifica se e' possivel inserir
        if(n >= fila.length) throw new Exception("Erro ao inserir! fila cheia!");

        fila[n] = newNum;
        n++;
    }// fim inserir()

    /*** REMOCOES ***/

    /**
     * Remove o elemento da fila
     * @return elemento removido
     * @throws Exception se a fila estiver vazia
     */
    public int remover() throws Exception{ // Remover do inicio
        // Verifica se e' possivel remover
        if(n == 0) throw new Exception("Erro ao remover! fila vazia!");

        int removido = fila[0];
        n--;

        // Shift para esquerda, ocupando o espaco do removido
        for(int i = 0; i < n; i++){
            fila[i] = fila[i + 1];
        }

        return removido;
    }// fim remover()

    /**
     * Mostra os elementos da fila
     */
    public void mostrar(){
        MyIO.print("[");
        for(int i = 0; i < n; i++){
            MyIO.print(" " + fila[i] + " ");
        }
        MyIO.print("]");
    }// fim mostrar()

}// fim classe fila