import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Livro {
    protected int idLivro;
    protected String titulo;
    protected String autor;
    protected float preco;

    public Livro(){
        this.idLivro = -1;
        this.titulo = "";
        this.autor = "";
        this.preco = 0F;
    }

    public Livro(int id, String titulo, String autor, float preco){
        this.idLivro = id;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }

    DecimalFormat df = new DecimalFormat("#,##0.00");
    
    public String toString(){
        return "\n ID: " + idLivro +
               "\n Titulo: " + titulo +
               "\n Autor: " + autor +
               "\n Preco: R$" + df.format(preco);
    }

    /**
     * converte os atributos do objeto corrente em bytes
     * @return byte[] com os dados convertidos
     * @throws IOException
     */
    public byte[] toByteArray() throws IOException{
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);

        dos.writeInt(idLivro);
        dos.writeUTF(titulo);
        dos.writeUTF(autor);
        dos.writeFloat(preco);

        return baos.toByteArray();
    }

    /**
     * armazena os campos do byte[] nos atributos equivalentes do objeto corrente
     * @param ba byte[] a ser interpretado
     * @throws IOException
     */
    public void fromByteArray(byte[] ba) throws IOException{
        ByteArrayInputStream bais = new ByteArrayInputStream(ba);
        DataInputStream dis = new DataInputStream(bais);

        idLivro = dis.readInt();
        titulo = dis.readUTF();
        autor = dis.readUTF();
        preco = dis.readFloat();
    }
}
