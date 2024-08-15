import java.text.DecimalFormat;

public class Livro {
    protected int idLivro;
    protected String titulo;
    protected String autor;
    protected float preco;

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
}
