public class Celula {
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