import java.io.*;
import java.net.*;

class Contador {
    public int[] a;
    public int[] e;
    public int[] i;
    public int[] o;
    public int[] u;
    public int consoantes;
    public int br;
    public int table;
    public String nome;

    /**
     * Construtor que inicializa as variaveis
     * @param nome
     */
    Contador(String nome) {
        this.a = new int[5]; // 0 a 5 para incluir todas as variacoes de acentos
        this.e = new int[5];
        this.i = new int[5]; 
        this.o = new int[5]; 
        this.u = new int[5]; 
        this.consoantes = 0; 
        this.br = 0;
        this.table = 0;
        this.nome = nome;
    }//fim Contador()

    /**
     * Transforma o contador em string
     * @return String
     */
    public String toString() {
        return "a(" + this.a[0] + ") " +
                "e(" + this.e[0] + ") " +
                "i(" + this.i[0] + ") " +
                "o(" + this.o[0] + ") " +
                "u(" + this.u[0] + ") " +
                "á(" + this.a[1] + ") " +
                "é(" + this.e[1] + ") " +
                "í(" + this.i[1] + ") " +
                "ó(" + this.o[1] + ") " +
                "ú(" + this.u[1] + ") " +
                "à(" + this.a[2] + ") " +
                "è(" + this.e[2] + ") " +
                "ì(" + this.i[2] + ") " +
                "ò(" + this.o[2] + ") " +
                "ù(" + this.u[2] + ") " +
                "ã(" + this.a[3] + ") " +
                "õ(" + this.o[3] + ") " +
                "â(" + this.a[4] + ") " +
                "ê(" + this.e[4] + ") " +
                "î(" + this.i[4] + ") " +
                "ô(" + this.o[4] + ") " +
                "û(" + this.u[4] + ") " +
                "consoante(" + this.consoantes + ") " +
                "<br>(" + this.br + ") " +
                "<table>(" + this.table + ") " +
                this.nome;
    }//fim toString()
}// fim class Contador

public class Html {
    /**
     * Testa se a entrada e' igual a "FIM"
     * @param input
     * @return
     */
    public static boolean isFim(String input) {
        return input.length() == 3 && input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M';
    }//fim isFim

    /**
     * Metodo que pega o conteudo html de uma pagina e armazena em um string
     * @param endereco
     * @return string com o conteudo html do endereco
     */
    public static String getHtml(String endereco) {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here

        }

        return resp;
    }//fim getHtml()

    /**
     * Passa pelos caracteres do html e incrementa as variaveis
     * @param count
     * @param html
     */
    public static String contaHtml(Contador count, String html){
        for(int i = 0; i < html.length(); i++){

            if(isTable(i, html)){ i += 6; count.table++; } 

            else if(isBr(i, html)){ i += 3; count.br++; }

            else if(isCons(html.charAt(i))) { count.consoantes++; }

            else {
                switch (html.charAt(i)) {
                    case 'a': count.a[0]++; break;
                    case 'e': count.e[0]++; break;
                    case 'i': count.i[0]++; break;
                    case 'o': count.o[0]++; break;
                    case 'u': count.u[0]++; break;
                    case 225: count.a[1]++; break;
                    case 233: count.e[1]++; break;
                    case 237: count.i[1]++; break;
                    case 243: count.o[1]++; break;
                    case 250: count.u[1]++; break;
                    case 224: count.a[2]++; break;
                    case 232: count.e[2]++; break;
                    case 236: count.i[2]++; break;
                    case 242: count.o[2]++; break;
                    case 249: count.u[2]++; break;
                    case 227: count.a[3]++; break;
                    case 245: count.o[3]++; break;
                    case 226: count.a[4]++; break;
                    case 234: count.e[4]++; break;
                    case 238: count.i[4]++; break;
                    case 244: count.o[4]++; break;
                    case 251: count.u[4]++; break;
                    default: break;
                }
            }
        }
        return count.toString();
    }// fim contaHtml()

    /**
     * Testa se e' <table>
     * @param index
     * @param html
     * @return
     */
    public static boolean isTable(int index, String html){
        return html.charAt(index) == '<'
        && html.charAt(index + 1) == 't'
        && html.charAt(index + 2) == 'a'
        && html.charAt(index + 3) == 'b'
        && html.charAt(index + 4) == 'l'
        && html.charAt(index + 5) == 'e'
        && html.charAt(index + 6) == '>';
    }//fim isTable()

    /**
     * Testa se e' <br>
     * @param index
     * @param html
     * @return
     */
    public static boolean isBr(int index, String html){
        return html.charAt(index) == '<'
        && html.charAt(index + 1) == 'b'
        && html.charAt(index + 2) == 'r'
        && html.charAt(index + 3) == '>';
    }//fim isBr()

    /**
     * Testa se c e' consoante
     * @param c
     * @return
     */
    public static boolean isCons(char c){
        return isLetra(c) && !isVogal(c); 
    }//fim isCons()

    public static boolean isLetra(char c){
        return 'a' <= c && c <= 'z';
    }//fim isLetra()

    public static boolean isVogal(char c){
        return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U'
        || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }//fim isVogal

    public static void main(String[] args) {
        String endereco, nome, html, output;

        nome = MyIO.readLine();

        while (!isFim(nome)) {
            Contador count = new Contador(nome); 

            endereco = MyIO.readLine();
            html = getHtml(endereco);
            output = contaHtml(count, html);
            MyIO.println(output);

            nome = MyIO.readLine();
        }
    }//fim main
}//fim class Html
