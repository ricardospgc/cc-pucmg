public class CelulaDupla {
    public char elemento;
    public CelulaDupla ant, prox;

    CelulaDupla(){ this('-'); }

    CelulaDupla(char elemento){
        this.elemento = elemento;
        this.prox = this.ant = null;
    }
}
