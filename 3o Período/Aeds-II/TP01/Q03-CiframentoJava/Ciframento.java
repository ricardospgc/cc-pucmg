import java.util.Scanner;

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
     * Retorna a string passada por parametro depois de cifrada
     * @param input string a ser modificada
     * @return string cifrada
     */
    static public String cifraCesar(String input){
        StringBuilder resp = new StringBuilder();

        for(int i = 0; i < input.length(); i++){
            char letra = input.charAt(i);
            letra += chave;
            resp.append(letra);
        }

        return resp.toString();
    }// fim cifraCesar

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!ehFim(input)) {
            System.out.println(cifraCesar(input));

            input = scanner.nextLine();
        } 

        scanner.close();
    }// fim main()
}