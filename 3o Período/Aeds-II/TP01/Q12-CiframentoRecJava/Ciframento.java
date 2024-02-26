
public class Ciframento{
    public static int chave = 3;

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
     * chama a funcao recursiva que faz a cifra do input
     * @param input string a ser modificada
     * @return string cifrada
     */
    static public String cifraCesarRec(String input){
        return cifraCesarRec(input, 0, new StringBuilder());        
    }// fim cifraCesar

    static private String cifraCesarRec(String input, int index, StringBuilder resp){
        if (index < input.length()) {
            char letra = input.charAt(index);
            letra += chave;
            resp.append(letra);
            cifraCesarRec(input, index + 1, resp);
        }
        return resp.toString();
    }// fim cifraCesarRec

    public static void main(String[] args)  {

        String input = MyIO.readLine();

        while (!ehFim(input)) {
            MyIO.println(cifraCesarRec(input));

            input = MyIO.readLine();
        } 

    }// fim main()  
}