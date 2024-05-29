public class ListaDupla {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;
    private int size;

    ListaDupla(){
        primeiro = new CelulaDupla();
        ultimo = primeiro;
        size = 0;
    }

    /*** INSERCOES ***/

    /**
     * Insere elemento no inicio da lista
     * @param elemento
     */
    public void inserirInicio(char elemento){
        CelulaDupla tmp = new CelulaDupla(elemento);

        tmp.ant = primeiro;
        tmp.prox = primeiro.prox;
        primeiro.prox = tmp;

        if(primeiro == ultimo)
            ultimo = tmp;
        else
            tmp.prox.ant = tmp;
        tmp = null;
        size++;
    }// inserirInicio()

    /**
     * Insere o elemento no fim da lista
     * @param elemento
     */
    public void inserirFim(char elemento){
        ultimo.prox = new CelulaDupla(elemento);
        ultimo.prox.ant = ultimo;
        ultimo = ultimo.prox;
        size++;
    }// inserirFim()

    /*** REMOCOES ***/

    /**
     * remove o primeiro elemento da lista
     * @return
     */
    public char removerInicio(){
        if(primeiro == ultimo){
            System.out.println("Erro ao remover, lista vazia");
            return '-';
        } else {
            CelulaDupla tmp = primeiro;

            primeiro = primeiro.prox;
            char resp = tmp.elemento;
            tmp.prox = primeiro.ant = null; // desconecta os ponteiros
            tmp = null;
            size--;

            return resp;
        }
    }// removerInicio()

    /**
     * remove o ultimo elemento da lista
     * @return
     */
    public char removerFim(){
        if(primeiro == ultimo){
            System.out.println("Erro ao remover, lista vazia");
            return '-';
        } else {
            char resp = ultimo.elemento;

            ultimo = ultimo.ant;
            ultimo.prox.ant = ultimo.prox = null;

            return resp;
        }
    }

    /**
     * mostra todos os elementos da lista
     */
    public void mostrar(){
        System.out.print("[");
        for(CelulaDupla i = primeiro.prox; i != null; i = i.prox){
            System.out.print(i.elemento);
        }
        System.out.println("]");
    }// mostrar()

    /**
     * mostra todos os elementos da lista, de traz pra frnte
     */
    public void mostrarInverso(){
        System.out.print("[");
        for(CelulaDupla i = ultimo; i != null; i = i.ant){
            System.out.print(i.elemento);
        }
        System.out.println("]");
    }// mostrar()

    public static boolean ehPalindromo(String str){
        boolean resp = true;

        ListaDupla word = new ListaDupla();
        // preenche a lista com os caracteres
        for(int i = 0; i < str.length(); i++){
            word.inserirInicio(str.charAt(i));
        }
        
        //verifica se eh palindromo
        CelulaDupla esq = word.primeiro.prox;
        CelulaDupla dir = word.ultimo;

        for(int i = 0; i < str.length() && resp == true; i++){
            if(dir.elemento != esq.elemento) resp = false;
            dir = dir.ant;
            esq = esq.prox;            
        }
        
        return resp;
    }
    public static void main(String[] args) {
        String str = "salve";

        System.out.println(ehPalindromo(str));
    }
}
