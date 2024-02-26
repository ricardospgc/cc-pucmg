
public class Palindromo{
    /*
    * Metodo publico para avaliar se e' fim do input
    * @param String input
    * @return boolean resp
    */
    static public boolean ehFim(String input){
        boolean resp = false;
        if(input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M')
            resp = true;
        return resp;
    }// fim ehFim

    /*
    * Metodo que chama o metodo privado ehPalindromo
    * @param String str
    * @return boolean resp
    */
    static public boolean ehPalindromo(String str){
        return ehPalindromo(str, 0);

    }//fim ehPalindromo

    /**
     * Metodo recursivo que analisa se a string e' palindromo
     * @param str
     * @param i
     * @return
     */
    static private boolean ehPalindromo(String str, int i){
        boolean resp = true;

        if(i < str.length()/2 && resp == true){
            if(str.charAt(i) != str.charAt(str.length() - i - 1)) resp = false;
            else ehPalindromo(str, i++);
        }
        
        return resp;
    }

    public static void main(String[] args){
        String input = MyIO.readLine();

        while (!ehFim(input)) {
            boolean resp = ehPalindromo(input);
            if(resp) MyIO.println("SIM");
            else MyIO.println("NAO");

            input = MyIO.readLine();
        } 

    }// fim main
}