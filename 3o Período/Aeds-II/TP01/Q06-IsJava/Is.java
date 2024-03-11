public class Is {
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
    }// fim ehFim()

    /**
     * Metodo que transforma a resposta booleana em "SIM" ou "NAO"
     * @param resp
     * @return string "SIM", se resp = true; "NAO" se resp = false.
     */
    static public String booleanToString(boolean resp){
        if(resp == true) return "SIM";
        else return "NAO";
    }// fim booleanToString()

    /**
     * Metoodo que retorna se a string e' composta somente por vogais
     * @param input
     * @return
     */
    static public boolean isVogal(String input){
        boolean resp = true;
        for(int i = 0; i < input.length() && resp == true; i++){
            if(input.charAt(i) != 'A' && input.charAt(i) != 'E' && input.charAt(i) != 'I' && input.charAt(i) != 'O' && input.charAt(i) != 'U' && 
            input.charAt(i) != 'a' && input.charAt(i) != 'e' && input.charAt(i) != 'i' && input.charAt(i) != 'o' && input.charAt(i) != 'u')
                resp = false;
        }

        return resp;
    }//fim isVogal()

    /**
     * Metoodo que retorna se a string e' composta somente por consoantes
     * @param input
     * @return
     */
    static public boolean isCons(String input){
        boolean resp = true;

        for(int i = 0; i < input.length() && resp == true; i++){
            if(input.charAt(i) == 'a' || input.charAt(i) == 'e' || input.charAt(i) == 'i' || input.charAt(i) == 'o' || input.charAt(i) == 'u' 
            || input.charAt(i) == 'A' || input.charAt(i) == 'E' || input.charAt(i) == 'I' || input.charAt(i) == 'O' || input.charAt(i) == 'U' 
            || (input.charAt(i)>='0' && input.charAt(i)<='9'))
                resp = false;
        }

        return resp;
    }//fim isCons()

    static public boolean isInt(String input){
        boolean resp = true;
        for(int i = 0; i < input.length() && resp == true; i++){
            if(input.charAt(i) < '0' || input.charAt(i) > '9') resp = false;
        }
        return resp;
    }// fim isInt

    static public boolean isFloat(String input){
        boolean resp = true;
        int contaVirgulaPonto = 0;
        for(int i = 0; i < input.length() && resp == true; i++){
            if(input.charAt(i) < '0' || input.charAt(i) > '9'){
                if(input.charAt(i) == '.' || input.charAt(i) == ',') contaVirgulaPonto++;
                else resp = false;
            }
        }

        if(resp && contaVirgulaPonto > 1) resp = false;
        
        return resp;
    }


    public static void main(String[] args){
        
        String input = MyIO.readLine();

        while (!ehFim(input)) {
            boolean isVog = false, isCons = false, isInt = false, isFloat = false;

            isVog = isVogal(input);
            MyIO.print(booleanToString(isVog) + " ");
            
            isCons = isCons(input);
            MyIO.print(booleanToString(isCons) + " ");

            isInt = isInt(input);
            MyIO.print(booleanToString(isInt) + " ");

            isFloat = isFloat(input);
            MyIO.print(booleanToString(isFloat) + "\n");

            input = MyIO.readLine();
        } 

    }// fim main
}
