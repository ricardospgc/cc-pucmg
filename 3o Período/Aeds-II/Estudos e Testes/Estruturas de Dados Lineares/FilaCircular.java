/**
 * Fila Circular Estatica 
 * (baseada nos slides de Aeds II, PUCMG)
 * Ricardo Soares Cerqueira
 */

class FilaCircular {
    private int[] fila; // fila propriamente dita
    private int primeiro; // indice do primeiro, controla remocoes
    private int ultimo; // indice do ultimo, controla insercoes

    public FilaCircular(){ this(6); } // Construtor sem parametro

    /**
     * Construtor com parametro para tamanho da fila
     * @param tam tamanho da fila
     */
    public FilaCircular(int tam){
        fila = new int[tam + 1]; // +1 para ter espaço de movimentacao
        primeiro = ultimo = 0; // Quando ambos sao iguais, indica fila vazia
    }// fim fila()

    /*** INSERCOES ***/

    /**
     * Insere o elemento parametrizado na fila
     * @param newNum elemento a ser inserido
     * @throws Exception se a fila estiver cheia
     */
    public void inserir(int newNum) throws Exception {
        // Verifica se e' possivel inserir
        if(((ultimo + 1) % fila.length) == primeiro) throw new Exception("Erro ao inserir! Fila cheia!");

        fila[ultimo] = newNum;
        ultimo = (ultimo + 1) % fila.length; // atualiza a pos de ultimo para nova insercao
    } // fim inserir()

    /*** REMOCOES ***/

    /**
     * Remove o primeiro elemento da fila
     * @return o removido
     * @throws Exception se a fila estiver vazia
     */
    public int remover() throws Exception{
        // verifica se e' possivel remover
        if(primeiro == ultimo) throw new Exception("Erro ao remover! Fila vazia!");

        int removido = fila[primeiro];
        primeiro = (primeiro + 1) % fila.length;
        return removido;
    }// fim remover()

    /********/

    /**
     * Chama a funcao recurssiva que mostra os elementos
     */
    public void mostrar(){
        MyIO.print("[ ");
        mostrar(primeiro);
        MyIO.print("]");
    }// fim mostrar()

    /**
     * Funcao recurssiva que msotra os elementos
     * @param i indice que vai ser printado
     */
    private void mostrar(int i){
        if(i != ultimo){
            MyIO.print(fila[i] + " ");
            mostrar((i + 1) % fila.length);
        }
    }// fim mostrar()

}
