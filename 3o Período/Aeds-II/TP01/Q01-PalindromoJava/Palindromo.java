import java.util.Scanner;

public class Palindromo{
    /*
    * Metodo publico para avaliar se e' fim do input
    * @params String input
    * @returns boolean resp
    */
    static public boolean ehFim(String input){
        boolean resp = false;
        if(input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M')
            resp = true;
        return resp;
    }// fim ehFim

    /*
    * Metodo que analisa a string e retorna se e' um palindromo
    * @params String str
    * @returns boolean resp
    */
    static public boolean ehPalindromo(String str){
        boolean resp = true;
        int tam = str.length();

        for(int i = 0; i < tam/2; i++){
            if(str.charAt(i) != str.charAt(tam - 1 - i)) resp = false;
        }

        return resp;
    }//fim ehPalindromo


    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        while (!ehFim(input)) {
            boolean resp = ehPalindromo(input);
            if(resp) System.out.println("SIM");
            else System.out.println("NAO");

            input = scanner.nextLine();
        } 

        scanner.close();

    }// fim main
}