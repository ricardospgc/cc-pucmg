class Celula {
    public int elemento;
    public Celula prox;

    Celula(){
        this(-1);
    }

    Celula(int elemento){
        this.elemento = elemento;
        this.prox = null;
    }
}

public class ListaSimples {
    private Celula primeiro; // Celula cabeca, sempre vazia, aponta para o primeiro
    private Celula ultimo; // Indica o fim da estrutura, aponta para o ultimo ("eh o ultimo")
    private int size; // contagem de elementos

    ListaSimples(){
        primeiro = new Celula();
        ultimo = primeiro;
        size = 0;
    }

    /****** INSERCOES ******/

    /**
     * Insere um elemento no inicio da lista
     * @param elem
     */
    public void inserirInicio(int elem){
        Celula tmp = new Celula(elem);
        tmp.prox = primeiro.prox; // o novo elemento aponta para o antigo primeiro
        primeiro.prox = tmp; // primeiro aponta para o novo elemento
        if(primeiro == ultimo) ultimo = tmp; // caso a lista estivesse vazia, ultimo e primeiro.prox apontam para o mesmo lugar

        size++;
    }// inserirInicio()

    /**
     * Insere um elemento no fim da lista
     * @param elem
     */    
    public void inserirFim(int elem){
        ultimo.prox = new Celula(elem); // novo elemento apos o antigo ultimo
        ultimo = ultimo.prox; // atualiza o ultimo, movimenta ele para frente

        size++;
    }// inserirFim()

    /**
     * Insere novo elemento na posicao parametrizada
     * @param elem
     * @param pos
     * @throws Exception caso pos seja invalida
     */
    public void inserirPos(int elem, int pos) throws Exception {
        if(pos < 0 || pos > size) 
            throw new Exception("Erro ao inserir, posicao invalida!");
        else if(pos == 0) 
            inserirInicio(elem);
        else if(pos == size) 
            inserirFim(elem);
        else{
            Celula i = primeiro; // ponteiro de contagem
            // Caminha ate a posicao anterior da insercao
            for(int j = 0; j < pos; i = i.prox); 
            
            // insere o novo elemento entre i e i.prox
            Celula tmp = new Celula(elem);
            tmp.prox = i.prox;
            i.prox = tmp;

            tmp = i = null;
        }
    }// inserirPos()

    /****** REMOCOES ******/
    
    /**
     * Remove o primeiro elemento da lista
     * @return o removido
     * @throws Exception
     */
    public int removerInicio() throws Exception {
        if(primeiro == ultimo) throw new Exception("Erro ao remover no inicio, Lista vazia!");
        
        Celula tmp = primeiro;
        primeiro = primeiro.prox; // move o primeiro para frente, removendo logicamente o elemento
        int removido = primeiro.elemento;

        tmp.prox = null; // desconecta o ponteiro do antigo primeiro
        tmp = null; // desconecta o antigo primeiro

        return removido;
    }// removerInicio()

    /**
     * Remove o ultimo elemento da lista
     * @return o removido
     * @throws Exception caso a lista esteja vazia
     */
    public int removerFim() throws Exception {
        if(primeiro == ultimo) throw new Exception("Erro ao remover no fim, Lista vazia!");

        Celula i; // ponteiro de contagem
        // caminha ate a penultima posicao
        for(i = primeiro; i.prox != ultimo; i = i.prox);

        int removido = ultimo.elemento;
        ultimo = i; // volta o ultimo uma posicao
        i = ultimo.prox  = null; // desconecta o ponteiro de contagem e o antigo ultimo da estrutura

        return removido;
    }// removerFim()

    /**
     * Remove o elemento em uma posicao especifica
     * @param pos
     * @return o removido
     * @throws Exception caso a lista esteja vazia, ou posicao seja invalida
     */
    public int removerPos(int pos) throws Exception {
        int removido;
        if(primeiro == ultimo) throw new Exception("Erro ao remover no fim, Lista vazia!");
        else if(pos < 0 || pos >= size)
            throw new Exception("Erro ao remover, posicao invalida!");
        else if(pos == 0) 
            removido = removerInicio();
        else if(pos == size) 
            removido = removerFim();
        else {
            Celula i = primeiro; // ponteiro de contagem
            // Caminha ate a posicao anterior da remocao
            for(int j = 0; j < pos; i = i.prox); 

            Celula tmp = i.prox;
            i.prox = tmp.prox;
            
            removido = tmp.elemento;

            tmp.prox = null; // desconecta o prox do removido
            tmp = i = null; // desconecta o ponteiro de contagem e o tmp
        }

        return removido;
    }// removerPos()

    /**** OUTROS *****/

    /**
     * Mostra todos os elementos da lista no console
     */
    public void mostrar(){
        MyIO.print("[ ");

        for(Celula i = primeiro.prox; i != null; i = i.prox){
            MyIO.print(i.elemento + " ");
        }
        MyIO.println("]");
    }// mostrar()

    /**
     * Pesquisa se ha um elemento na lista
     * @param key
     * @return
     */
    public boolean pesquisa(int key){
        boolean resp = false;
        for(Celula i = primeiro.prox; i != null && resp == false; i = i.prox){
            if(i.elemento == key){
                resp = true;
            }
        }

        return resp;
    }// pesquisa()

}// class ListaSimples
