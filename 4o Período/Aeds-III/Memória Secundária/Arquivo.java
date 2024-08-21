import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.time.LocalDate;

import entidades.Cliente;
import entidades.Livro;

// Eventualmente uma classe generica, por enquanto somente Clientes

public class Arquivo {
    final int TAM_CABECALHO = 4;
    RandomAccessFile arquivo;
    String nomeArquivo;

    public Arquivo(String na) throws IOException {
        File d = new File("dados");
        if(!d.exists())
            d.mkdir();

        this.nomeArquivo = "dados/"+na;
        arquivo = new RandomAccessFile(this.nomeArquivo, "rw");
        if(arquivo.length() < TAM_CABECALHO) {
            // inicializa o arquivo, criando seu cabecalho
            arquivo.writeInt(0);
        }
    }

    public int createCliente(Cliente c) throws IOException {
        arquivo.seek(0);
        int proximoID = arquivo.readInt()+1;
        arquivo.seek(0);
        arquivo.writeInt(proximoID);
        c.setId(proximoID);

        arquivo.seek(arquivo.length());
        arquivo.writeInt(c.id);
        arquivo.writeUTF(c.nome);
        arquivo.write(c.cpf.getBytes());
        arquivo.writeFloat(c.salario);
        arquivo.writeInt((int) c.nascimento.toEpochDay());

        return c.id;
    }

    public int createLivro(Livro l) throws IOException{
        arquivo.seek(0);
        int proximoID = arquivo.readInt()+1;
        arquivo.seek(0);
        arquivo.writeInt(proximoID);
        l.setId(proximoID);

        arquivo.seek(arquivo.length());
        arquivo.writeInt(l.id);
        arquivo.writeUTF(l.titulo);
        arquivo.writeUTF(l.autor);
        arquivo.writeFloat(l.preco);

        return l.id;
    }
    
    public Cliente readCliente(String cpf) throws IOException {
        Cliente c;
        byte[] aux;
        arquivo.seek(TAM_CABECALHO);
        while(arquivo.getFilePointer() < arquivo.length()) {
            c = new Cliente();
            
            c.id = arquivo.readInt();
            c.nome = arquivo.readUTF();
            
            // Como CPF tem tamanho fixo, nao foram usados bytes de tamanho.
            // Por isso, foi usado um vetor de bytes de tamanho fixo ao inves de readUTF (que exige byte de tamanho)
            aux = new byte[11];
            arquivo.read(aux);
            c.cpf = new String(aux);

            c.salario = arquivo.readFloat();
            c.nascimento = LocalDate.ofEpochDay(arquivo.readInt());
            if(c.cpf.compareTo(cpf) == 0)
                return c;
        }
        return null;
    }

    public Livro readLivro(String titulo) throws IOException {
        Livro l;
        arquivo.seek(TAM_CABECALHO);
        while(arquivo.getFilePointer() < arquivo.length()) {
            l = new Livro();

            l.id = arquivo.readInt();
            l.titulo = arquivo.readUTF();
            l.autor = arquivo.readUTF();
            l.preco = arquivo.readFloat();

            if(l.titulo.compareTo(titulo) == 0)
                return l;
        }
        return null;
    }

    public void close() throws IOException {
        arquivo.close();
    }


}
