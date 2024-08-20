package entidades;
import java.text.DecimalFormat;

public class Livro {
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
}