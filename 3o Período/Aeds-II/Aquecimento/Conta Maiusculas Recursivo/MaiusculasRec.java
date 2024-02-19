class Maiusculas{

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
    * Metodo publico que chama o contador de maisculas
    * @params String str
    * @returns int resp
    */
    public static int contaMaiuscula(String str){
        return contaMaiusculaRec(str, 0);
    }//fim contaMaiuscula

    /*
    * Metodo privado conta o numero de maisculas na string
    * @params String str, Int i
    * @returns int
    */
    private static int contaMaiusculaRec(String str, int i){
        if(i < str.length()){
            if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') 
                return 1 + contaMaiusculaRec(str, i+1);
            else 
                return contaMaiusculaRec(str, i+1);
        }
        return 0;
    }//fim contaMaiusculaRec



    public static void main(String[] args){
        String input = MyIO.readLine();

        while (!ehFim(input)) {
            int resp = contaMaiuscula(input);
            
            MyIO.println(resp);

            input = MyIO.readLine();
        } 


    }// fim main
}