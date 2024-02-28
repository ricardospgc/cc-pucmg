import java.util.Random;

public class Alteracao{

   /**
    * Metodo para avaliar se o input = "FIM"
    * @param input string contendo input do usuario
    * @return booleano se e' fim ou nao
    */
    static public boolean ehFim(String input){
        boolean resp = false;
        if(input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M')
            resp = true;
        return resp;
    }// fim ehFim
    
    /**
     * Metodo que altera uma letra aleatoria do input por outra
     * @param input string original
     * @param gerador gerador aleatório com a seed para correção
     * @return string alterada
     */
    static public String alteracao(String input, Random gerador){
        
        char char1 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));
        char char2 = (char)('a' + (Math.abs(gerador.nextInt()) % 26));

        StringBuilder resp = new StringBuilder();

        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == char1) 
                resp.append(char2);
            else 
                resp.append(input.charAt(i));
        }

        return resp.toString();
    }// fim alteracao()

    public static void main(String[] args)  {
        Random gerador = new Random();
        gerador.setSeed(4);

        String input = MyIO.readLine();

        while (!ehFim(input)) {
             MyIO.println(alteracao(input, gerador));

             input = MyIO.readLine();
        } 

    }// fim main()  
}