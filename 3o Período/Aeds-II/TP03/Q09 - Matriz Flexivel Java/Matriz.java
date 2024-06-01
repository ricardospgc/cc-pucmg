class Celula {
    public int elemento;
    public Celula esq, dir, sup, inf;

    public Celula(){
        this(-1, null, null, null, null);
    }

    public Celula(int elemento, Celula esq, Celula dir, Celula sup, Celula inf){
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.sup = sup;
        this.inf = inf;
    }
}// class Celula

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
            MyIO.print(i.elemento + " ");

            while(i.dir != null){
                i = i.dir;
                i = i.inf;

                MyIO.print(i.elemento + " ");
            }
            MyIO.print("\n");
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

            MyIO.print(i.elemento + " ");
            while(i.esq != null){
                i = i.esq;
                i = i.inf;

                MyIO.print(i.elemento + " ");
            }
            MyIO.print("\n");
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
    public Matriz multiplica(Matriz m2){
        if(!(this.col == m2.lin)){
            MyIO.println("Nao e' possivel multiplicar matrizes dessas proporcoes. Retornando 'm2'.");
            return m2;
        } else {

        Matriz result = new Matriz(this.lin, m2.col);

        // Ponteiros para percorrer as linhas da matriz atual e da matriz resultante
        Celula linhaAtualA = this.inicio;
        Celula linhaAtualC = result.inicio;

        // Percorre as linhas da matriz atual
        for (int i = 0; i < this.lin; i++) {
            // Ponteiro para percorrer as colunas da linha atual result
            Celula colunaAtualC = linhaAtualC;

            // Percorre as colunas da result
            for (int j = 0; j < m2.col; j++) {
                // Ponteiros para percorrer os elementos da linha da matriz atual e a coluna correspondente de m2
                Celula celulaAtualA = linhaAtualA;
                Celula celulaAtualB = m2.inicio;
                
                // Move celulaAtualB para a coluna correta na matriz m2
                for (int moveB = 0; moveB < j; moveB++) {
                    celulaAtualB = celulaAtualB.dir;
                }
                
                int soma = 0;
                
                // Calcula o valor do elemento [i][j] na matriz result
                for (int k = 0; k < this.col; k++) {
                    soma += celulaAtualA.elemento * celulaAtualB.elemento;
                    // Move para o próximo elemento na linha de A e na coluna de B
                    celulaAtualA = celulaAtualA.dir;
                    celulaAtualB = celulaAtualB.inf;
                }
                
                colunaAtualC.elemento = soma;
                // Move para a próxima coluna
                colunaAtualC = colunaAtualC.dir;
            }

            // Move para a próxima linha
            linhaAtualA = linhaAtualA.inf;
            linhaAtualC = linhaAtualC.inf;
        }

        return result;
        }
    }//multiplica()

    public static void main(String[] args){
        int casos = MyIO.readInt();

        while(casos > 0){
            // Monta a primeira matriz
            int lin = MyIO.readInt();
            int col = MyIO.readInt();
            Matriz m1 = new Matriz(lin, col);
            m1.fillRead();

            // Monta a segunda matriz
            lin = MyIO.readInt();
            col = MyIO.readInt();
            Matriz m2 = new Matriz(lin, col);
            m2.fillRead();

            m1.printDiagPrincipal();
            m1.printDiagSecundaria();

            Matriz soma = m1.soma(m2);
            soma.print();

            Matriz mult = m1.multiplica(m2);
            mult.print();

            casos--;
        }
    }
}// class Matriz
