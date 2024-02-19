import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
* Nome: Ricardo Soares Cerqueira
* Matricula: 803833
* Trabalho Prático 4 - Aeds II - Q05
*/

class No {
    public Jogador jogador;
    public No esq, dir;

    /* Construtores */
    public No(Jogador jogador) {
        this(jogador, null, null);
    }

    public No(Jogador jogador, No esq, No dir) {
        this.jogador = jogador.cloneJogador();
        this.esq = esq;
        this.dir = dir;
    }
}// fim class No

class Treesort { // Organizada baseada no nome do Jogador
    private No raiz;

    static Long tempoTotal = (long) 0;
    static int nComp = 0;

    private int n;

    /* Construtor */
    Treesort() {
        raiz = null;
        n = 0;
    }

    /*** Inserir ***/

    /**
     * Metodo publico para inserir um valor
     * 
     * @param valor
     * @throws Exception se o jogador já existir
     */
    public void inserir(Jogador jog) throws Exception {
        n++;
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

    /**
     * metodo publico que faz a ordenação de um array usando a Treesort
     * @return array de Jogadores
     */
    public Jogador[] treeSort(){
        Jogador[] array = new Jogador[n];
        n = 0;
        treeSort(raiz, array);
        return array;
    }//fim treeSort()

    /**
     * metodo privado que faz o caminhamento central na Treesort e preenche o array
     * @param i
     * @param array
     */
    private void treeSort(No i, Jogador[] array){
        if(i != null){
            treeSort(i.esq, array);
            array[n++] = i.jogador;
            treeSort(i.dir, array);
        }
    }// fim treeSort()

    /**
     * armazena os jogadores na Treesort
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

        String fileName = "803833_treesort.txt";
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

}// fim class Treesort


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
        Treesort arvore = new Treesort();

        arvore.adicionaJogadores();
        Jogador array[] = arvore.treeSort();

        for(Jogador i : array){
            MyIO.println(i.getNome());
        }

        arvore.criaLog();
    }// fim main

}// fim classe Jogador