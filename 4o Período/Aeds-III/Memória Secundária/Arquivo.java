import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;

import registros.*;

/* ORGANIZACAO DO ARQUIVO:
 * Cabecalho: INT indicando o maior ID registrado, funciona como contador de registros
 * 
 * ESTRUTURA DO REGISTRO:
 *  - 1o byte: lapide - * = delete / ' ' = existente
 *  - short: Indicador de tamanho do registro (varia de acordo com o tipo de registro)
 *  - sequencia de bytes com os dados do registro 
 */

public class Arquivo<T extends Registro> {
    final int TAM_CABECALHO = 4;
    RandomAccessFile arquivo;
    String nomeArquivo;
    Constructor<T> construtor;

    public Arquivo(String na, Constructor<T> c) throws IOException {
        File d = new File("./dados");
        if(!d.exists())
            d.mkdir();

        this.nomeArquivo = "./dados/"+na;
        this.construtor = c;
        arquivo = new RandomAccessFile(this.nomeArquivo, "rw");
        if(arquivo.length() < TAM_CABECALHO) {
            // inicializa o arquivo, criando seu cabecalho
            arquivo.writeInt(0);
        }
    }

    public int create(T obj) throws IOException {
        // atualiza o numero do maior ID, serve como contador de registros
        arquivo.seek(0);
        int proximoID = arquivo.readInt()+1;
        arquivo.seek(0);
        arquivo.writeInt(proximoID);

        arquivo.seek(arquivo.length());
        obj.setId(proximoID); // atualiza o ID do objeto
        byte[] b = obj.toByteArray();

        // escreve o indicador de tamanho do objeto
        arquivo.writeByte(' ');
        arquivo.writeShort(b.length);

        arquivo.write(b);
        
        return obj.getId();
    }
    
    public T read(int id) throws Exception {
        T obj;
        short tam;
        byte[] b;
        byte lapide;

        arquivo.seek(TAM_CABECALHO); // pula o cabecalho para pesquisar
        while(arquivo.getFilePointer() < arquivo.length()) { // "enquanto nao for o fim do arquivo"
            obj = construtor.newInstance();
            lapide = arquivo.readByte();
            tam = arquivo.readShort();
            b = new byte[tam];
            arquivo.read(b); // read nativo

            if(lapide == ' ') { // caso seja *, foi deletado
                obj.fromByteArray(b); // constroi o objeto usando o byte array
                if(obj.getId() == id)
                    return obj;
            }
        }
        return null;
    }

    public boolean delete(int id) throws Exception {
        T obj;
        short tam;
        byte[] b;
        byte lapide;
        Long endereco;

        arquivo.seek(TAM_CABECALHO); // pula o cabecalho para pesquisar
        while(arquivo.getFilePointer() < arquivo.length()) { // "enquanto nao for o fim do arquivo"
            obj = construtor.newInstance();
            endereco = arquivo.getFilePointer();
            lapide = arquivo.readByte();
            tam = arquivo.readShort();
            b = new byte[tam];
            arquivo.read(b); // read nativo

            if(lapide == ' ') {
                obj.fromByteArray(b);
                if(obj.getId() == id) {
                    arquivo.seek(endereco);
                    arquivo.write('*'); // atualiza a lapide para *, indicando o delete logico
                    return true;
                }
            }
        }
        return false;
    }

    public boolean update(T novoObj) throws Exception {
        T obj;
        short tam;
        byte[] b;
        byte lapide;
        Long endereco;

        arquivo.seek(TAM_CABECALHO);
        while(arquivo.getFilePointer()<arquivo.length()) {
            obj = construtor.newInstance();
            endereco = arquivo.getFilePointer();
            lapide = arquivo.readByte();
            tam = arquivo.readShort();
            b = new byte[tam];
            arquivo.read(b);

            if(lapide == ' ') {
                obj.fromByteArray(b);
                if(obj.getId() == novoObj.getId()) { // registro alvo encontrado

                    byte[] b2 = novoObj.toByteArray();
                    short tam2 = (short)b2.length;

                    // sobrescreve o registro
                    if(tam2 <= tam) {
                        arquivo.seek(endereco + 3);
                        arquivo.write(b2);
                    }

                    // move o novo registro para o fim
                    else {
                        arquivo.seek(endereco);
                        arquivo.write('*');
                        arquivo.seek(arquivo.length());
                        arquivo.writeByte(' ');
                        arquivo.writeShort(tam2);
                        arquivo.write(b2);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void close() throws IOException {
        arquivo.close();
    }


}
