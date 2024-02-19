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
    * Metodo publico para contar quantas maiusculas tem na string
    * @params String str
    * @returns int resp
    */
    public static int contaMaiuscula(String str){
        int resp = 0;

        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') resp++;
        }

        return resp;
    }//fim contaMaiuscula



    public static void main(String[] args){
        String input = MyIO.readLine();

        while (!ehFim(input)) {
            int resp = contaMaiuscula(input);
            
            MyIO.println(resp);

            input = MyIO.readLine();
        } 


    }// fim main
}