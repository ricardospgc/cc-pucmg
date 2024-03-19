public class Pokemon{
    static int totalPkm = 151;
    static int n = 0;

    public static boolean pokemonJaExiste(String[] arr, String atual){
        boolean resp = false;
      
        for(int i = 0; i < n; i++){
            if(arr[i].equals(atual)){
                resp = true;
                i = n;
            }
        }

        return resp;
    }

    public static void inserir(String[] arr, String atual){
        arr[n] = atual;
        n++;
    }

    static public void main(String[] args){
        int numCapturados = MyIO.readInt();
        int numUnicos = 0;

        String[] arr = new String[200];

        for(int i = 0; i < numCapturados; i++){
            String pokeAtual = MyIO.readLine();
            boolean existe = pokemonJaExiste(arr, pokeAtual);
            if(!existe){
                inserir(arr, pokeAtual);
                numUnicos++;
            }
        }

        MyIO.println("Falta(m) " + (totalPkm - numUnicos) + " pomekon(s).");
        
    }
}