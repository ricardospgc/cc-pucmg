import java.io.*;

public class Arquivo {

    /**
     * Metodo que imprime os numeros de um arquivo, do fim para o início
     * @param index
     * @param n
     * @param arquivo
     */
    static void printReverso(int n, RandomAccessFile arquivo) throws IOException{
        for(int i = 0; i < n; i++){
            arquivo.seek((n - i - 1)* Double.BYTES);
            double num = arquivo.readDouble();
            int numInt = (int) num;
            if(num == numInt){
        	    MyIO.println(numInt);
            } else {
        	    MyIO.println(num);
            }
        }
    }//fim printReverso

    public static void main(String[] args){
        int n = MyIO.readInt();

        try{
            RandomAccessFile arquivo = new RandomAccessFile("valores.txt", "rw"); // Abre do arquivo para escrita
            
            for (int i = 0; i < n; i++) {
                double valor = MyIO.readDouble();
                arquivo.writeDouble(valor);
            }
            arquivo.close();


            arquivo = new RandomAccessFile("valores.txt", "r"); // Abre o arquivo para leitura
            arquivo.seek(arquivo.length()- 8); // Move para o fim do arquivo

            printReverso(n, arquivo);

        } catch (IOException e){
            e.printStackTrace();
        }
    }// fim main
}
