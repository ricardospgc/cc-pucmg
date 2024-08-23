package registros;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Livro implements Registro {
    public int id;
    public String titulo;
    public String autor;
    public float preco;

    public Livro(){
        this("", "", 0F);
    }

    public Livro(String titulo, String autor, float preco){
        this(-1, titulo, autor, preco);
    }

    public Livro(int id, String titulo, String autor, float preco){
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.preco = preco;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    DecimalFormat df = new DecimalFormat("#,##0.00");
    
    public String toString(){
        return "\n ID: " + id +
               "\n Titulo: " + titulo +
               "\n Autor: " + autor +
               "\n Preco: R$" + df.format(preco);
    }

    public byte[] toByteArray() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(baos);
        
        dos.writeInt(this.id);
        dos.writeUTF(this.titulo);
        dos.writeUTF(this.autor);
        dos.writeFloat(this.preco);

        return baos.toByteArray();
    }


    public void fromByteArray(byte[] b) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(b);
        DataInputStream dis = new DataInputStream(bais);

        this.id = dis.readInt();
        this.titulo = dis.readUTF();
        this.autor = dis.readUTF();
        this.preco = dis.readFloat();
    }
}