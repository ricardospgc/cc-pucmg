public class Hash {
    public static int tam = 7;
    public static int reserva = 3;
    public static int toda = tam + reserva;

    public static int resUsada = 0;

    public static int hash(int elem){
        return elem % 7;
    }

    public static boolean inserir(int elem, int[] tabela){
        int pos = hash(elem);
        boolean resultado = false;

        if(tabela[pos] == -1){
            tabela[pos] = elem;
            resultado = true;
        }
        else if(resUsada < reserva){
            tabela[tam+resUsada] = elem;
            resUsada++;
            resultado = true;
        }

        return resultado;
    }

    public static void mostraHash(int[] tabela){
        
        for(int i = 0; i < toda; i++){
            if(i == tam) MyIO.print(" r");
            MyIO.print(" |" + tabela[i] + "| ");
        }
    }

    public static void main(String[] args) {
        int[] tabela = new int[toda]; // 7 de espaço + 3 reserva

        for(int i = 0; i < toda; i++){
            tabela[i] = -1;
        }
        
        MyIO.print("\n> ");
        int elem = MyIO.readInt();

        while(elem != -1){
            boolean resultado = inserir(elem, tabela);
            MyIO.println((resultado)? "SIM" : "SIM");

            mostraHash(tabela);

            MyIO.print("\n> ");
            elem = MyIO.readInt();
        }
        
        
    }
}
