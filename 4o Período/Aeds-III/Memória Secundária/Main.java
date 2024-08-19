// import java.io.DataOutputStream;
// import java.io.FileOutputStream;
// import java.io.DataInputStream;
// import java.io.FileInputStream;

import java.io.RandomAccessFile;

public class Main {
     public static void main(String[] args) {
        Livro l1 = new Livro(1, "Eu Robô", "Isaac Asimov", 14.9F);
        Livro l2 = new Livro(2, "Eu Sou A Lenda", "Richard Matheson", 21.99F);

        System.out.println(l1);
        System.out.println(l2);


        RandomAccessFile arq;
        byte[] ba;

        try {
            /* LEITURA E ESCRITA DE ACESSO ALEATORIO ********/
            
            arq = new RandomAccessFile("dados/livros.db", "rw"); // o RAF permite leitura, escrita e movimentacoes
            
            /* ESCRITA */

            long pos1 = arq.getFilePointer(); // ponteiro do primeiro registro
            ba = l1.toByteArray(); // armazena em ba a informacao em bytes
            arq.writeInt(ba.length); // escreve o indicador de tamanho de registro
            arq.write(ba); // escreve o registro em si

            long pos2 = arq.getFilePointer(); // ponteiro do segundo registro
            ba = l2.toByteArray(); // armazena em ba a informacao em bytes
            arq.writeInt(ba.length); // escreve o indicador de tamanho de registro
            arq.write(ba); // escreve o registro em si

            /* LEITURA */

            // como o RAF ja permite tudo que precisamos, nao e' necessario fechar e reabrir o arquivo para leitura

            Livro l3 = new Livro();
            Livro l4 = new Livro();
            int tam; // indicador de tamanho do registro
            
            // o ponteiro esta no fim do arquivo, onde a escrita terminou

            // atualiza ba para o registro a ser lido
            arq.seek(pos2); // envia o ponteiro para a posicao do registro 2
            tam = arq.readInt(); // le o primeiro int do registro, que e' o indicador de tamanho
            ba = new byte[tam];
            arq.read(ba);
            l3.fromByteArray(ba); // armazena em l3 o registro de ba

            // atualiza ba para o registro a ser lido
            arq.seek(pos1); // envia o ponteiro para a posicao do registro 1
            tam = arq.readInt(); // le o primeiro int do registro, que e' o indicador de tamanho
            ba = new byte[tam];
            arq.read(ba);
            l4.fromByteArray(ba); // armazena em l3 o registro de ba

            System.out.println("---------");
            System.out.println(l3);
            System.out.println(l4);

            arq.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
