// Eventualmente uma classe generica, por enquanto somente de livros

import java.io.IOException;
import java.io.RandomAccessFile;

public class Arquivo {
    final int TAM_CABECALHO = 4;
    RandomAccessFile arquivo;
    String nomeDoArquivo;

    Arquivo(String na) throws IOException {
        this.nomeDoArquivo = na;
        
    }
}
