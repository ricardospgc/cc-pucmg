public class Matriz {
    private int lin, col;
    public Celula inicio;

    public Matriz(){
        this(3, 3);
    }

    public Matriz(int lin, int col){
        this.lin = lin;
        this.col = col;

        int countLin = 0;
        this.inicio = new Celula();
        Celula i = inicio;

        while (countLin < lin) {
            Celula ancoraLinha = i;

            // cria a linha
            for(int countCol = 1; countCol < col; i = i.dir, countCol++){
                Celula tmp = new Celula();
                i.dir = tmp;
                tmp.esq = i;
            }

            // retorna o i para o inicio da linha
            i = ancoraLinha;

            // conecta o sup com o inf
            if(countLin > 0 && countLin <= lin){
                int countCol = 1;
                // j e i caminham em paralelo pela linha
                for(Celula j = i.sup; countCol <= col; j = j.dir, i = i.dir, countCol++){
                    j.inf = i;
                    i.sup = j;
                }
                
                // retorna o i para o inicio da linha

                i = ancoraLinha;
            }
            
            // Cria a primeira celula da proxima linha
            if(countLin < lin-1){ // nao cria quando o numero de linhas ja e' o desejado
                i.inf = new Celula();
                i.inf.sup = i;
                i = i.inf;
            }

            countLin++;
        }
    }// Matriz()

    /**
     * Permite que o usuario preencha os elementos da matriz
     */
    public void fillRead(){
        for(Celula i = this.inicio; i != null; i = i.inf){
            for(Celula j = i; j != null; j = j.dir){
                j.elemento = MyIO.readInt();
            }
        }
    }// fillRead()

    /**
     * Preenche a matriz em ordem crescente
     */
    public void fill(){
        int c = 0;
        for(Celula i = this.inicio; i != null; i = i.inf){
            for(Celula j = i; j != null; j = j.dir){
                j.elemento = c++;
            }
        }
    }// fill

    /**
     * Printa todos os elementos da matriz na tela
     */
    public void print(){
        for(Celula i = this.inicio; i != null; i = i.inf){
            for(Celula j = i; j != null; j = j.dir){
                MyIO.print(j.elemento + " ");
            }
            MyIO.print("\n");
        }
    }// print()

    /**
     * Printa todos os elementos da matriz e
     * mostra as conexoes feitas por cada Celula
     */
    public void printPointers(){
        print();
        MyIO.print("\n");

        MyIO.println("inicio: " + this.inicio.elemento + "\n");

        for(Celula i = this.inicio; i != null; i = i.inf){
            for(Celula j = i; j != null; j = j.dir){
                MyIO.println("elem: " + j.elemento);
                
                if(j.esq == null) MyIO.println("esq: null");
                else MyIO.println("esq: " + j.esq.elemento);

                if(j.sup == null) MyIO.println("sup: null");
                else MyIO.println("sup: " + j.sup.elemento);

                if(j.dir == null) MyIO.println("dir: null");
                else MyIO.println("dir: " + j.dir.elemento);

                if(j.inf == null) MyIO.println("inf: null");
                else MyIO.println("inf: " + j.inf.elemento);

                MyIO.print("\n");
            }
        }
    }// printWpointers()

    /**
     * retorna se a matriz e' quadrada (lin = col)
     * @return
     */
    public boolean isQuadrada(){
        return lin == col;
    }// isQuadrada()

    /**
     * Printa a diagonal principal da matriz
     * Superior esquerdo > inferior direito
     */
    public void printDiagPrincipal(){
        if(this.isQuadrada()){
            
            MyIO.print("[ ");
            
            for(Celula i = inicio; i.dir != null; i = i.dir.inf){
                MyIO.print(i.elemento + " ");
            }

            MyIO.println("]");
        }
    }// printDiagPrincipal

}// class Matriz