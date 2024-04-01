class Algebra{
    static char charA, charB, charC;

    /**
    * Metodo para avaliar se o input = "FIM"
    * @param input string contendo input do usuario
    * @return booleano se e' fim ou nao
    */
    static public boolean isFim(String input){
        return input.charAt(0) == '0' && input.length() == 1;
    }// fim ehFim

    /**
     * Atribui 'as variaveis globais os seus valores booleanos (1 ou 0)
     * @param input
     */
    public static void atribuiBoolean(String input){
        
        charA = input.charAt(2);

        charB = input.charAt(4);
        
        charC = input.charAt(6);
    }// fim atribuiBoolean()

    /**
     * Troca as variaveis pelos seus valores e retira o excesso
     * @param input
     * @return String pronta para resolucao
     */
    public static String preparaInput(String input){
        input = input.trim();
        StringBuilder resp = new StringBuilder();
        
        // troca as variaveis pelos seus valores
        int tam = input.length();
        for(int i = 0; i < tam; i++){ 
            char letra = input.charAt(i);

            if(letra == 'A') resp.append(charA);
            else if(letra == 'B') resp.append(charB);
            else if (letra == 'C') resp.append(charC);
            else resp.append(letra);
        }

        // remove o que nao faz parte da operacao
        if(resp.charAt(0) == '2') resp.replace(0,6,"");
        else if(resp.charAt(0) == '3') resp.replace(0,8,"");
        else MyIO.println("ERRO Preparacao");

        return resp.toString();
    }//fim preparaInput

    /**
     * Metodo que substitui a operacao e seus termos pelo resultado
     * @param input
     * @param valor
     * @param posParentese
     * @return
     */
    public static String substOperacaoFeita(String input, String valor, int posParentese){
        
        int posInicial = posParentese, posFinal = posParentese;
        
        posInicial = encontraPosInicial(input, posInicial, posParentese, posParentese);
        
        posFinal = encontraPosFinal(input, posFinal, posParentese, posParentese);

        StringBuilder resp = new StringBuilder(input);

        resp.replace(posInicial, posFinal, valor);        
        
        return resp.toString();
    }// fim substOperacaoFeita

    private static int encontraPosInicial(String input, int posInicial, int posParentese, int i){
        if(i > 0){
            if(i != posParentese && (input.charAt(i) == '(' || input.charAt(i) == ' ')){
                posInicial = i+1;
            } else if(i == 1) posInicial = 0;
            else posInicial = encontraPosInicial(input, posInicial, posParentese, i-1);
        }
        return posInicial;
    }// fim encontraPosInicial()

    private static int encontraPosFinal(String input, int posFinal, int posParentese, int i){
        if(i < input.length() - 1){
            if(input.charAt(i) == ')'){
                posFinal = i+1;
            } else if(i == input.length()-2) posFinal = input.length();
            else posFinal = encontraPosFinal(input, posFinal, posParentese, i+1);
        }
        return posFinal;
    }// fim encontraPosFinal()

    /**
     * Gerencia qual operacao deve ser feita
     * @param input
     * @return boolean com o resultado
     */
    public static String resolve(String input){
        int tam = input.length();
        int posParentese = -1;
        String resp = "erro";
        String strOperada = "erro";   

        posParentese = encontraPosParentese(input, tam-1);

        // Escolhe a operacao
        switch (input.charAt(posParentese-1)) {
            case 'd'://and 
                resp = and(input, posParentese);
                
                break;
            case 'r'://or
                resp = or(input, posParentese);
                break;
            case 't'://not
                resp = not(input, posParentese);
                break;
            default:
                MyIO.println("ERRO!");
                break;
        }

        strOperada = substOperacaoFeita(input, resp, posParentese);
        return strOperada;
    }// fim resolve()

    private static int encontraPosParentese(String input, int i){
        int resp = -1;
        if(i > 0){
            if(input.charAt(i) == '('){
                resp = i;
            } else resp = encontraPosParentese(input, i-1);

        }
        return resp;
    }// fim encontraPosParentese

    /**
     * Lida com a operacao AND
     * @param input
     * @param pos
     * @return boolean resultado
     */
    public static String and(String input, int i){
        String resp = "1";
        if(i < input.length() && input.charAt(i) != ')'){
            if(input.charAt(i) == '0') resp = "0";
            else resp = and(input, i+1);
        }

        return resp;
    }//fim and()

    /**
     * Lida com a operacao OR
     * @param input
     * @param pos
     * @return boolean resultado
     */
    public static String or(String input, int i){
        String resp = "0";
        if(i < input.length() && input.charAt(i) != ')'){
            if(input.charAt(i) == '1') resp = "1";
            else resp = or(input, i+1);
        }

        return resp;
    }//fim or()

    /**
     * Lida com a operacao NOT
     * @param input
     * @param pos
     * @return boolean resultado
     */
    public static String not(String input, int pos){

        return (input.charAt(pos+1) == '0') ? "1" : "0";

    }//fim not()

    public static String algebra(String input){
        atribuiBoolean(input);
        input = preparaInput(input);
        input = algebraRec(input, input.length());
        return input;
    }

    private static String algebraRec(String input, int tam){
        if(tam > 1){
            input = resolve(input);
            input = algebraRec(input, input.length());
        }
        return input;
    }

    public static void main(String[] args){
        String input = MyIO.readLine();
        
        while(!isFim(input)){
            String resp = algebra(input);
            MyIO.println(resp);
            input = MyIO.readLine();
        }
    
    }// fim main
}