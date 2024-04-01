/**
 * Fila Estatica - First In, First Out
 * Remocao Eficiente (II, RF)
 * Ricardo Soares Cerqueira
 */

 class FilaRemEfic{
    private int n; // Quantidade de elementos na fila
    private int[] fila; // fila propriamente dita

    public FilaRemEfic(){ this(6); } // Construtor sem parametro

    /**
     * Construtor com parametro para tamanho da fila
     * @param tam tamanho da fila
     */
    public FilaRemEfic(int tam){
        fila = new int[tam];
        n = 0;
    }// fim fila()

    /*** INSERCOES ***/

    /**
     * Insere o elemento na fila
     * @param newNum Elemento a ser inserido
     * @throws Exception Se a fila estiver cheia
     */
    public void inserir(int newNum) throws Exception{// Inserir no inicio
        // Verifica se e' possivel inserir
        if(n >= fila.length) throw new Exception("Erro ao inserir! fila cheia!");

        // Shift dos elementos para a direita, liberando espaco no inicio
        for(int i = n; i > 0; i--){
            fila[i] = fila[i - 1];
        }

        // Insere o elemento na primeira posicao da fila
        fila[0] = newNum;

        // atualiza o total
        n++;
    }// fim inserir()

    /*** REMOCOES ***/

    /**
     * Remove elemento da fila
     * @return elemento removido
     * @throws Exception se a fila estiver vazia
     */
    public int remover() throws Exception{ // Remover no fim
        // Verifica se e' possivel remover
        if(n == 0) throw new Exception("Erro ao remover! fila vazia!");

        return fila[--n];
    }// fim remove()

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