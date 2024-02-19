import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
* Nome: Ricardo Soares Cerqueira
* Matricula: 803833
* Trabalho Prático 4 - Aeds II - Q04
*/

class No {
    public Jogador jogador;
    public No esq, dir;
    public boolean cor;

    /* Construtores */
    public No(Jogador jogador) {
        this(jogador, null, null, false);
    }

    public No(Jogador jogador, No esq, No dir, boolean cor) {
        this.jogador = jogador.cloneJogador();
        this.esq = esq;
        this.dir = dir;
        this.cor = cor;
    }
}// fim class No

class Arvore { // Organizada baseada no nome do Jogador
    private No raiz;

    static Long tempoTotal = (long) 0;
    static Integer nComp = 0;

    /* Construtor */
    Arvore() {
        raiz = null;
    }

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
    public boolean pesquisar(String chave, No raiz) {        
        boolean resp = false;

        if (raiz == null){
            resp = false;
            nComp++;
        }
        else if (chave.equals(raiz.jogador.getNome())){
            resp = true;
            nComp++;
        }

        else if (chave.compareTo(raiz.jogador.getNome()) < 0){
            MyIO.print("esq ");
            nComp++;
            resp = pesquisar(chave, raiz.esq);
        }

        else{
            MyIO.print("dir ");
            nComp++;
            resp = pesquisar(chave, raiz.dir);
        }


        return resp;
    } // fim pesquisar()

    /*** Inserir ***/

    /**
     * Metodo publico para inserir um valor
     * 
     * @param valor
     * @throws Exception se o jogador já existir
     */
    public void inserir(Jogador jog) throws Exception {
        raiz = inserir(jog, raiz);
    } // fim inserir()

    /**
     * Metodo privado recursivo para inserir um jogador
     * 
     * @param Jogador jog
     * @param No  i
     * @throws Exception se o jogador já existir
     */
    private No inserir(Jogador jog, No i) throws Exception {

        if (i == null){ 
            i = new No(jog);
        }
        else if (jog.getNome().compareTo(i.jogador.getNome()) < 0) {
            i.esq = inserir(jog, i.esq);
        }
        else if (jog.getNome().compareTo(i.jogador.getNome()) > 0) {
            i.dir = inserir(jog, i.dir);
        }
        else 
            throw new Exception("jogador ja existe!");

        return i;
    } // fim inserir()

    /*** Remocoes ***/

    /**
     * Metodo publico para remover o jogador parametrizado
     * 
     * @param valor a ser removido
     * @throws Exception se o valor nao existe
     */
    public void remover(Jogador valor) throws Exception {
        raiz = remover(valor, raiz);
    } // fim remover()

    /**
     * Metodo privado recursivo para remover jogador.
     * 
     * @param valor jogador a ser removido.
     * @param i     No em analise.
     * @return No em analise, alterado ou nao.
     * @throws Exception Se nao encontrar jogador.
     */
    private No remover(Jogador valor, No i) throws Exception {
        if (i == null)
            throw new Exception("jogador nao existe!");

        else if (valor.getNome().compareTo(raiz.jogador.getNome()) > 0)
            i.dir = remover(valor, i.dir);

        else if (valor.getNome().compareTo(raiz.jogador.getNome()) < 0)
            i.esq = remover(valor, i.esq);

        // A partir daqui, tratamento de quando o jogador foi encontrado;
        // É preciso identificar se o jogador a ser removido tem 0 filhos,
        // 1 filho e seu lado, ou 2 filhos;

        // No a ser removido tem 0 filhos ou 1 filho à esquerda, 
        // "sobe" com o filho à esquerda;
        else if (i.dir == null)
            i = i.esq;

        // No a ser removido tem 1 filho à direita, "sobe" com o filho à direita;
        else if (i.esq == null)
            i = i.dir;

        /*
         * No a ser removido tem 2 filhos.
         * Nesse caso, o jogador removido deve ser substituido pelo
         * maior no da subarvore esquerda ou menor no da subarvore direita
         * a fim de manter a propriedade de AB de pesquisa.
         * Aqui, substitui pelo maior da esquerda.
         */
        else
            i.esq = maiorEsq(i, i.esq); // encontra o substituto, o maior no da subarvore esquerda.

        return i;

    } // fim remover()

    /**
     * Metodo para trocar o jogador "removido" pelo maior da esquerda.
     * 
     * @param i No que teve o jogador removido.
     * @param k No da subarvore esquerda.
     * @return No em analise, alterado ou nao.
     */
    private No maiorEsq(No i, No k) {

        // Encontrou o maximo da subarvore esquerda.
        if (k.dir == null) {
            i.jogador = k.jogador.cloneJogador(); // Substitui i por k.
            k = k.esq; // Substitui k por k.ESQ.

            // Existe no a direita.
        } else {
            // Caminha para direita.
            k.dir = maiorEsq(i, k.dir);
        }
        return k;
    }// fim maiorEsq()

    /**
     * Retorna o maior jogador da árvore
     * @return resp
     */
    public Jogador getMaior() {
        Jogador resp = new Jogador();

        if (raiz != null) {
            No i;
            for (i = raiz; i.dir != null; i = i.dir); // caminha até o jogador mais à direita
            resp = i.jogador.cloneJogador();
        }

        return resp;
    } // fim getMaior()

    /**
     * Retorna o menor jogador da árvore
     * @return resp
     */
    public Jogador getMenor() {
        Jogador resp = new Jogador();

        if (raiz != null) {
            No i;
            for (i = raiz; i.esq != null; i = i.esq); // caminha até o jogador mais à esquerda
            resp = i.jogador.cloneJogador();
        }

        return resp;
    } // fim getMaior()

    /**
     * Metodo que retorna a altura da árvore
     * 
     * @return int altura da árvore
     */
    public int getAltura() {
        return getAltura(raiz, 0);
    } // fim getAltura()

    /**
     * Metodo que retorna a altura da árvore
     * 
     * @return int altura da árvore
     */
    public int getAltura(No i, int altura) {
        if (i == null) {
            altura--;
        } else {
            int alturaEsq = getAltura(i.esq, altura + 1);
            int alturaDir = getAltura(i.dir, altura + 1);
            altura = (alturaEsq > alturaDir) ? alturaEsq : alturaDir;
        }
        return altura;
    } // fim getAltura()

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

    public void caminharCentral() {
		caminharCentral(raiz);

	} // fim caminharCentral()

	private void caminharCentral(No i) {
		if (i != null) {
            caminharCentral(i.esq);
			MyIO.println(i.jogador.getNome());
			caminharCentral(i.dir);
		}
	} // fim caminharCentral()

    /**
     * Cria um log com o tempo de execucao da pesquisa
     * e quantidade de movimentacoes
     */
    public void criaLog(){

        String fileName = "803833_arvoreBinaria.txt";
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
        Arvore arvoreAB = new Arvore();

        arvoreAB.adicionaJogadores();
        arvoreAB.pesquisar();

        arvoreAB.criaLog();
    }// fim main

}// fim classe Jogador