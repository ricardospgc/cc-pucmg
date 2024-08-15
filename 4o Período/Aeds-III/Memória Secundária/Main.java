import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;

public class Main {
     public static void main(String[] args) {
        Livro l1 = new Livro(1, "Eu Robô", "Isaac Asimov", 14.9F);
        Livro l2 = new Livro(2, "Eu Sou A Lenda", "Richard Matheson", 21.99F);

        System.out.println(l1);
        System.out.println(l2);

        FileOutputStream arqOut;
        DataOutputStream dos;

        FileInputStream arqIn;
        DataInputStream dis;

        byte[] ba;

        try {
            /*** Escrita ***/
            arqOut = new FileOutputStream("dados/livros.db");
            dos = new DataOutputStream(arqOut);

            ba = l1.toByteArray(); // armazena em ba a informacao em bytes
            dos.writeInt(ba.length); // escreve o indicador de tamanho de registro
            dos.write(ba); // escreve o registro em si

            ba = l2.toByteArray(); // armazena em ba a informacao em bytes
            dos.writeInt(ba.length); // escreve indicador de tamanho de registro
            dos.write(ba); // escreve o registro em si

            dos.close();
            arqOut.close();

            /*** Leitura ***/

            Livro l3 = new Livro();
            Livro l4 = new Livro();
            int tam; // indicador de tamanho do registro
            arqIn = new FileInputStream("dados/livros.db");
            dis = new DataInputStream(arqIn);

            // atualiza ba para o registro a ser lido
            tam = dis.readInt(); // le o primeiro int do registro, que e' o indicador de tamanho
            ba = new byte[tam];
            dis.read(ba);
            l3.fromByteArray(ba); // armazena em l3 o registro de ba


            // atualiza ba para o registro a ser lido
            tam = dis.readInt(); // le o primeiro int do registro, que e' o indicador de tamanho
            ba = new byte[tam];
            dis.read(ba);
            l4.fromByteArray(ba); // armazena em l4 o registro de ba

            System.out.println(l3);
            System.out.println(l4);

            dis.close();
            arqIn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        
    }
}
