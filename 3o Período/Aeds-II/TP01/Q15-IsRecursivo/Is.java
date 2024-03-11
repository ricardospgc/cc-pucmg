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
    static public boolean isVogalRec(String input,int i){
        boolean resp = true;
        if(i < input.length()){
            if(input.charAt(i) != 'A' && input.charAt(i) != 'E' && input.charAt(i) != 'I' && input.charAt(i) != 'O' && input.charAt(i) != 'U' && 
            input.charAt(i) != 'a' && input.charAt(i) != 'e' && input.charAt(i) != 'i' && input.charAt(i) != 'o' && input.charAt(i) != 'u')
                resp = false;
            else resp = isVogalRec(input, i+1);
        }

        return resp;
    }//fim isVogalRec()

    /**
     * Metoodo que retorna se a string e' composta somente por consoantes
     * @param input string
     * @param i index
     * @return boolean
     */
    static public boolean isConsRec(String input, int i){
        boolean resp = true;
        if(i < input.length()){
            if(input.charAt(i) == 'a' || input.charAt(i) == 'e' || input.charAt(i) == 'i' || input.charAt(i) == 'o' || input.charAt(i) == 'u' 
            || input.charAt(i) == 'A' || input.charAt(i) == 'E' || input.charAt(i) == 'I' || input.charAt(i) == 'O' || input.charAt(i) == 'U' 
            || (input.charAt(i)>='0' && input.charAt(i)<='9'))
                resp = false;
            else  resp =isConsRec(input, i+1);
        } 

        return resp;
    }//fim isConsRec()

    /**
     * Metoodo que retorna se a string e' composta somente por inteiros
     * @param input string
     * @param i index
     * @return boolean
     */
    static public boolean isIntRec(String input, int i){
        boolean resp = true;
        if(i < input.length()){
            if(input.charAt(i) < '0' || input.charAt(i) > '9') resp = false;
            else resp = isIntRec(input, i+1);
        }
        return resp;
    }// fim isIntRec

    /**
     * Metoodo que retorna se a string e' composta somente por reais
     * @param input string
     * @param i index
     * @return boolean
     */
    static public boolean isFloatRec(String input, int i, int contaVirgulaPonto){
        boolean resp = true;
        if(i < input.length()){
            if(input.charAt(i) < '0' || input.charAt(i) > '9'){
                if(input.charAt(i) == '.' || input.charAt(i) == ',') 
                    resp = isFloatRec(input, i+1, contaVirgulaPonto+1);
                else resp = false;
            }
            else resp = isFloatRec(input, i+1, contaVirgulaPonto);
        }

        if(contaVirgulaPonto > 1) resp = false;
        
        return resp;
    }// fim isFloatRec


    public static void main(String[] args){
        
        String input = MyIO.readLine();

        while (!ehFim(input)) {
            boolean isVog = false, isCons = false, isInt = false, isFloat = false;

            isVog = isVogalRec(input, 0);
            MyIO.print(booleanToString(isVog) + " ");
            
            isCons = isConsRec(input, 0);
            MyIO.print(booleanToString(isCons) + " ");

            isInt = isIntRec(input, 0);
            MyIO.print(booleanToString(isInt) + " ");

            isFloat = isFloatRec(input, 0, 0);
            MyIO.print(booleanToString(isFloat) + "\n");

            input = MyIO.readLine();
        } 

    }// fim main
}
