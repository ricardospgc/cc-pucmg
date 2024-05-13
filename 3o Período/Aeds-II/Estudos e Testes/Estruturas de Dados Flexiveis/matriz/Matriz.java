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
            Celula i = this.inicio;
            MyIO.print("[ " + i.elemento);

            while(i.dir != null){
                i = i.dir;
                i = i.inf;

                MyIO.print(" " + i.elemento);
            }
            MyIO.println(" ]");
        } else 
            MyIO.println("Matriz nao quadrada, nao ha diagonal principal!");
    }// printDiagPrincipal()

    /**
     * Printa a diagonal secundaria da matriz
     * Superior direito > inferior esquerdo
     */
    public void printDiagSecundaria(){
        if(this.isQuadrada()){
            Celula i = this.inicio;
            // leva o i para a ultima coluna
            while(i.dir != null){ i = i.dir; }

            MyIO.print("[ " + i.elemento);
            while(i.esq != null){
                i = i.esq;
                i = i.inf;

                MyIO.print(" " + i.elemento);
            }
            MyIO.println(" ]");
        } else 
            MyIO.println("Matriz nao quadrada, nao ha diagonal secundaria!");
    }// printDiagSecundaria

    /**
     * Retorna a matriz resultante da 
     * soma entre a matriz corrente e a parametrizada
     * @param m Matriz parcela
     * @return Matriz resultante
     */
    public Matriz soma(Matriz m){
        if(!((this.lin == m.lin) && (this.col == m.col))){ // As matrizes precisam ter as mesmas dimensoes
            MyIO.println("Nao e' possivel somar matrizes de tipos diferentes. Retornando 'm'.");
            return m;
        } else {

            Matriz resp = new Matriz(this.lin, this.col);
            
            Celula thisLin = this.inicio;
            Celula thisCol = thisLin;

            Celula mLin = m.inicio;
            Celula mCol = mLin;
            
            for(Celula respLin = resp.inicio; respLin != null; respLin = respLin.inf){
                thisCol = thisLin; // retorna thisCol para o inicio da linha
                mCol = mLin; // retorna mCol para o inicio da linha

                for(Celula respCol = respLin; respCol != null; respCol = respCol.dir){
                    respCol.elemento = thisCol.elemento + mCol.elemento;

                    //proxima coluna
                    thisCol = thisCol.dir; 
                    mCol = mCol.dir;
                }

                // proxima linha
                thisLin = thisLin.inf;
                mLin = mLin.inf;
            }
            
            return resp;
        }
    }// soma()

    /**
     * Retorna a matriz resultante da 
     * multiplicacao entre a matriz corrente e a parametrizada
     * @param m Matriz fator
     * @return Matriz resultante
     */
    public Matriz multiplica(Matriz m){
        if(!(this.col == m.lin)){
            MyIO.println("Nao e' possivel multiplicar matrizes dessas proporcoes. Retornando 'm'.");
            return m;
        } else {
            Matriz m3 = new Matriz(this.lin, m.col);

            /*
            * Para achar o elemento da posicao (i,j) da matriz resultante
            * multiplica cada elemento da linha i da primeira matriz pelos
            * elementos correspondentes da coluna j da matriz parametrizada 
            * e soma os produtos
            * 
            * Ex:
            *     2 3
            *     9 1   5 7 4
            *     0 8   6 2 3
            *
            * (0,0) da m3 = (2*5)+(3*6)
            * (1,0) = (9*5)+(1*6)
            * ...
            * (3,3) = (0*4)+(8*3) 
            */

            

            return m3;
        }
    }//multiplica()

}// class Matriz