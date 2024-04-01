/**
 * Lista Estatica 
 * (baseada nos slides de Aeds II, PUCMG)
 * Ricardo Soares Cerqueira
 */

class Lista{
    private int n; // Quantidade de elementos na Lista
    private int[] lista; // lista propriamente dita

    public Lista(){ this(6); } // Construtor sem parametro

    /**
     * Construtor com parametro para tamanho da lista
     * @param tam tamanho da lista
     */
    public Lista(int tam){
        lista = new int[tam];
        n = 0;
    }// fim Lista()

    /*** INSERCOES ***/

    /**
     * Insere o elemento no inicio da lista
     * @param newNum Elemento a ser inserido
     * @throws Exception Se a lista estiver cheia
     */
    public void inserirInicio(int newNum) throws Exception{
        // Verifica se e' possivel inserir
        if(n >= lista.length) throw new Exception("Erro ao inserir! Lista cheia!");

        // Shift dos elementos para a direita, liberando espaco no inicio
        for(int i = n; i > 0; i--){
            lista[i] = lista[i - 1];
        }

        // Insere o elemento na primeira posicao da lista
        lista[0] = newNum;

        // atualiza o total
        n++;
    }// fim inserirInicio()

    /**
     * Insere o elemento no fim da lista
     * @param newNum Elemento a ser inserido
     * @throws Exception Se a lista estiver cheia
     */
    public void inserirFim(int newNum) throws Exception{
        // Verifica se e' possivel inserir
        if(n >= lista.length) throw new Exception("Erro ao inserir! Lista cheia!");

        lista[n] = newNum;
        n++;
    }// fim inserirFim()

    /**
     * Insere o elemento na posicao indicada
     * @param newNum Elemento a ser inserido
     * @param pos Posicao para inserir
     * @throws Exception Se a lista estiver cheia ou posicao invalida
     */
    public void inserirPos(int newNum, int pos) throws Exception{
        // Verifica se e' possivel inserir
        if(n >= lista.length) throw new Exception("Erro ao inserir! Lista cheia!");
        if(pos > n || pos < 0) throw new Exception("Erro ao inserir! Posicao inválida");

        // Shift dos elementos para a direita, até a posicao, liberando espaco
        for(int i = n; i > pos; i--){
            lista[i] = lista[i - 1];
        }

        lista[pos] = newNum;
        n++;
    }// fim inserirPos()

    /*** REMOCOES ***/

    /**
     * Remove o primeiro elemento da lista
     * @return elemento removido
     * @throws Exception se a lista estiver vazia
     */
    public int removerInicio() throws Exception{
        // Verifica se e' possivel remover
        if(n == 0) throw new Exception("Erro ao remover! Lista vazia!");

        int removido = lista[0];
        n--;

        // Shift para esquerda, ocupando o espaco do removido
        for(int i = 0; i < n; i++){
            lista[i] = lista[i + 1];
        }

        return removido;
    }// fim removerInicio()

    /**
     * Remove o ultimo elemento da lista
     * @return elemento removido
     * @throws Exception se a lista estiver vazia
     */
    public int removerFim() throws Exception{
        // Verifica se e' possivel remover
        if(n == 0) throw new Exception("Erro ao remover! Lista vazia!");

        /*
        int removido = lista[n];    
        n--;
        return removido;
        */

        return lista[--n];
    }// fim removeFim()

    /**
     * Remove o elemento na posicao indicada
     * @param pos posicao do elemento a ser removido
     * @return elemento removido
     * @throws Exception se a lista estiver vazia ou posicao invalida
     */
    public int removerPos(int pos) throws Exception{
        // Verifica se e' possivel inserir
        if(n == 0) throw new Exception("Erro ao remover! Lista vazia!");
        if(pos >= n || pos < 0) throw new Exception("Erro ao remover! Posicao inválida");

        int removido = lista[pos];
        n--;

        // Shift para esquerda, a parir de pos, ocupando o espaco do removido
        for(int i = pos; i < n; i++){
            lista[i] = lista[i + 1];
        }

        return removido;
    }// fim removerPos()

    /**
     * Mostra os elementos da lista
     */
    public void mostrar(){
        MyIO.print("[");
        for(int i = 0; i < n; i++){
            MyIO.print(" " + lista[i] + " ");
        }
        MyIO.print("]");
    }// fim mostrar()

}// fim classe Lista