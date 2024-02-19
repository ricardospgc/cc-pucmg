import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
* Nome: Ricardo Soares Cerqueira
* Matricula: 803833
* Trabalho Prático 4 - Aeds II - Q01
*/

class No {
    public int alturaChave;
    public No esq, dir;
    public No2 raiz2;

    /* Construtores */

    public No(int alturaChave) {
        this(alturaChave, null, null, null);
    }

    public No(int altura, No esq, No dir, No2 raiz2) {
        this.alturaChave = altura;
        this.esq = esq;
        this.dir = dir;
        this.raiz2 = raiz2;
    }
}// fim class No

class No2 {
	public Jogador jogador; // Conteudo do no.
	public No2 esq; // No da esquerda.
	public No2 dir; // No da direita.
	
    /* Construtores */
    
	No2(Jogador jogador) {
		this(jogador, null, null);
	}

	No2(Jogador jogador, No2 esq, No2 dir) {
		this.jogador = jogador.cloneJogador();
        this.esq = esq;
        this.dir = dir;
	}
}

class ArvoreArvore { // Externamente baseada na altura e internamente no nome
    private No raiz;

    static Long tempoTotal = (long) 0;
    static Integer nComp = 0;

    /* Construtor */
    ArvoreArvore() {
        raiz = null;
        inserir(7);
        inserir(3);
        inserir(11);
        inserir(1);
        inserir(5);
        inserir(9);
        inserir(12);
        inserir(0);
        inserir(2);
        inserir(4);
        inserir(6);
        inserir(8);
        inserir(10);
        inserir(13);
        inserir(14);
    }

    /*** Inserir ***/

    /**
     * Metodo publico para inserir a chave das alturas na arvore externa.
     * @param int alturaChave
     * @throws Exception
     */
     public void inserir(int alturaChave) {
        raiz = inserir(alturaChave, raiz);
    }// fim inserir()

    /**
     * Metodo privado para inserir as alturas recursivamente na arvore externa
     * @param altura
     * @param i
     * @return
     */
    private No inserir(int altura, No i) {
        if (i == null) {
            i = new No(altura);
        } else if (altura < i.alturaChave) {
            i.esq = inserir(altura, i.esq);
        } else if (altura > i.alturaChave) {
            i.dir = inserir(altura, i.dir);
        } else {
            MyIO.println("Erro ao inserir Nó!");
        }
        return i;
    }// fim inserir()

    /**
     * Metodo publico para inserir um jogador na arvore
     * @param j - Jogador a ser inserido
     */
    public void inserir(Jogador j) {
        inserir(j, raiz);
    }// fim inserir()

    /**
     * Metodo privado que procura qual No tem a altura correspondente
     * a do Jogador a ser inserido
     * @param j - jogador a ser inserido
     * @param i - no atual
     */
    private void inserir(Jogador j, No i) {
        if (i == null) {
            MyIO.println("Erro ao inserir");
        } else if ((j.getAltura() % 15) < (i.alturaChave)) {
            nComp++;
            inserir(j, i.esq);
        } else if ((j.getAltura() % 15) > i.alturaChave) {
            nComp++;
            inserir(j, i.dir);
        } else {
            i.raiz2 = inserirAA(j, i.raiz2);
        }
    }// fim inserir()

    /**
     * Metodo privado que insere o jogador na arvore interna baseado no nome
     * @param j - Jogador a ser inserido
     * @param i - no atual
     * @return - posicao que o jogador foi inserido
     */
    private No2 inserirAA(Jogador j, No2 i) {
        if (i == null) {
            i = new No2(j);
        } else if (j.getNome().compareTo(i.jogador.getNome()) < 0) {
            i.esq = inserirAA(j, i.esq);
        } else if (j.getNome().compareTo(i.jogador.getNome()) > 0) {
            i.dir = inserirAA(j, i.dir);
        } else {
            MyIO.println("Erro ao inserir, elemento existente");
        }
        return i;
    }// fimn inserirAA()

    /*** Pesquisas ***/

    /**
     * Metodo publico de pesquisa
     * Informa o caminho percorrido e se o jogador foi encontrado
     */
    public void pesquisar() {
        String chave = MyIO.readLine();
        boolean isFim = false;

        while (!isFim) {
            
            long tempoInicio = System.nanoTime();
            
            MyIO.print(chave + " raiz "); // informa o nome do jogador a ser pesquisado
            boolean resp = pesquisar(chave, raiz);
            MyIO.println((resp == true) ? "SIM" : "NAO"); // informa se foi encontrado

            long tempoFim = System.nanoTime();
            tempoTotal += (tempoFim - tempoInicio) / 1000000;

            chave = MyIO.readLine();
            if (chave.equals("FIM")) isFim = true;
        }
    } // fim pesquisar()

    /**
     * Metodo privado de pesquisa, recursivo
     * 
     * @param String chave - jogador a ser pesquisado
     * @param No     raiz - raiz da Arvore
     * @return boolean com o resultado da pesquisa
     */
    private boolean pesquisar(String chave, No i) {        
        boolean resp = false;

        if (i != null) {
            resp = pesquisarAA(chave, i.raiz2);
            if(!resp){
                MyIO.print("esq ");
                resp = pesquisar(chave, i.esq);
            }
            if(!resp){
                MyIO.print("dir ");
                resp = pesquisar(chave, i.dir);
            }
        }

        return resp;
    } // fim pesquisar()

    private boolean pesquisarAA(String chave, No2 i){
        boolean resp = false;
        if (i != null) {
            resp = i.jogador.getNome().equals(chave);
            if (!resp) {
                MyIO.print("ESQ ");
                resp = pesquisarAA(chave, i.esq);
            }
            if (!resp) {
                MyIO.print("DIR ");
                resp = pesquisarAA(chave, i.dir);
            }       
        }
        return resp;
    }//fim pesquisar()

    /**
     * armazena os jogadores na Arvore
     */
    public void adicionaJogadores() {
        String id = MyIO.readLine();
        boolean isFim = false;

        while (!isFim) {

            if ((Integer.parseInt(id) >= 0) && (Integer.parseInt(id) <= 3921)) { // VERIFICAR SE ID É VÁLIDO
                try {
                    Jogador jogador = new Jogador();
                    jogador.procuraIDeSalva(id);
                    inserir(jogador);
                } catch (Exception e) {
                    MyIO.println(e.getMessage());
                }
            }

            id = MyIO.readLine();
            if (id.equals("FIM"))
                isFim = true;
        }
    } // fim adicionaJogadores()

    /**
     * Cria um log com o tempo de execucao da pesquisa
     * e quantidade de movimentacoes
     */
    public void criaLog(){

        String fileName = "803833_arvoreArvore.txt";
        String matricula = "803833";

        try{
            File file = new File(fileName);
            
            file.createNewFile();

            try{
                FileWriter fw = new FileWriter(fileName, false); // false = rewrite
                BufferedWriter bw = new BufferedWriter(fw);

                bw.write(matricula + '\t' + nComp + '\t' + tempoTotal+"ms");

                bw.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }// fim criaLog()

}// fim class Arvore


public class Jogador {
    private Integer id;
    private String nome;
    private Integer altura; // em centímetros
    private Integer peso; // somente quilos
    private String universidade;
    private Integer anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    Jogador() {
        this.id = null;
        this.nome = null;
        this.altura = null;
        this.peso = null;
        this.universidade = null;
        this.anoNascimento = null;
        this.cidadeNascimento = null;
        this.estadoNascimento = null;
    }

    Jogador(String infos) {
        setJogador(infos);
    }

    /* Gets */
    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public int getAltura() {
        return this.altura;
    }

    public int getPeso() {
        return this.peso;
    }

    public String getUniversidade() {
        return this.universidade;
    }

    public int getAnoNascimento() {
        return this.anoNascimento;
    }

    public String getCidadeNascimento() {
        return this.cidadeNascimento;
    }

    public String getEstadoNascimento() {
        return this.estadoNascimento;
    }

    /* Sets */
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }

    public void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }

    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }

    /*
     * Clone
     * Retorna um clone exato do Jogador corrente
     * 
     * @return Jogador
     */
    public Jogador cloneJogador() {
        Jogador clone = new Jogador();

        clone.setId(this.getId());
        clone.nome = this.getNome();
        clone.altura = this.getAltura();
        clone.peso = this.getPeso();
        clone.universidade = this.getUniversidade();
        clone.anoNascimento = this.getAnoNascimento();
        clone.cidadeNascimento = this.getCidadeNascimento();
        clone.estadoNascimento = this.getEstadoNascimento();

        return clone;
    }

    /*
     * Procura no arquivo o Id Chave e salva o objeto relacionado
     * 
     * @params Integer
     */
    public void procuraIDeSalva(String idString) throws Exception {

        Integer idChave = Integer.parseInt(idString);

        //FileReader file = new FileReader("players.csv");
        FileReader file = new FileReader("/tmp/players.csv");
        BufferedReader buffer = new BufferedReader(file);
        String linha;

        while ((linha = buffer.readLine()) != null) {
            String idAtual = linha.substring(0, linha.indexOf(","));

            try {
                if (Integer.parseInt(idAtual) == idChave) {
                    this.setJogador(linha);
                    break;
                }
            } catch (NumberFormatException e) {
            }
        }

        buffer.close();
        file.close();
    }

    /*
     * Seta todos os atributos baseado na String do arquivo lido na função
     * procuraIDnoArquivo()
     * 
     * @param String
     */
    public void setJogador(String linha) {
        String[] split = linha.split(",");

        this.setId(Integer.parseInt(split[0]));
        this.setNome(split[1]);
        this.setAltura(Integer.parseInt(split[2]));
        this.setPeso(Integer.parseInt(split[3]));

        if (split[4].isEmpty())
            this.setUniversidade("nao informado");
        else
            this.setUniversidade(split[4]);

        this.setAnoNascimento(Integer.parseInt(split[5]));

        if (split.length > 6 && !split[6].isEmpty())
            this.setCidadeNascimento(split[6]);
        else
            this.setCidadeNascimento("nao informado");

        if (split.length > 7 && !split[7].isEmpty())
            this.setEstadoNascimento(split[7]);
        else
            this.setEstadoNascimento("nao informado");

    }// fim setJogador()

    /***************************************************************************************/
    /***************************************************************************************/

    public static void main(String[] args) throws Exception {
        ArvoreArvore arvore = new ArvoreArvore();

        arvore.adicionaJogadores();
        arvore.pesquisar();

        arvore.criaLog();
    }// fim main

}// fim classe Jogador