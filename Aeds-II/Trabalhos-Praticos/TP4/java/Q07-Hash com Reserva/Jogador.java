import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
* Nome: Ricardo Soares Cerqueira
* Matricula: 803833
* Trabalho Prático 4 - Aeds II - Q07
*/

class Hash { // Organizada baseada no nome do Jogador
    public Jogador[] tabela;

    public int tamTabela;
    public int tamReserva;
    public int tamTotal;

    public int reservaOcupada;

    public final Jogador NULO = new Jogador();

    static public Long tempoTotal = (long) 0;
    static public int nComp = 0;


    /***** CONSTRUTORES *****/
    Hash(){
        this(21, 9);
    }

    Hash(int tamTabela, int tamReserva){
        this.tamTabela = tamTabela;
        this.tamReserva = tamReserva;
        this.tamTotal = tamTabela + tamReserva;
        this.reservaOcupada = 0;

        this.tabela = new Jogador[this.tamTotal];

        for(int i = 0; i < tamTabela; i++){
            tabela[i] = null;    
        }
        
    }// fim construtores

    /***** INSERCOES *****/
    public int hashAltura(Jogador jogador){
        return jogador.getAltura() % tamTabela;
    }//fim hashAltura()

    public void inserir(Jogador jogador){
        int pos = hashAltura(jogador);
        
        if(tabela[pos] == null) tabela[pos] = jogador.cloneJogador();
        else if (reservaOcupada < tamReserva){
            tabela[tamTabela + reservaOcupada] = jogador.cloneJogador();
            reservaOcupada++;
        }

    }//fim inserir()

    /**** PESQUISA *****/

    /**
     * Metodo publico para pesquisar um nome na tabela
     */
    public void pesquisa(){
        String chave = MyIO.readLine();
        boolean isFim = false;

        while (!isFim) {            
            
            long tempoInicio = System.nanoTime();
            
            MyIO.print(chave); // informa o nome do jogador a ser pesquisado
            boolean resp = pesquisa(chave);
            MyIO.println((resp == true) ? " SIM" : " NAO"); // informa se foi encontrado

            long tempoFim = System.nanoTime();
            tempoTotal += (tempoFim - tempoInicio) / 1000000;
    

            chave = MyIO.readLine();
            if (chave.equals("FIM")) isFim = true;
        }
    }// fim pesquisar()

    /**
     * Metodo privado de pesquisa na tabela
     * @param chave
     * @return
     */
    private boolean pesquisa(String chave){
        boolean resp = false;

        for(int i = 0; i < tamTotal && resp == false; i++){
            if(tabela[i] != null && tabela[i].getNome().equals(chave)) resp = true;
            nComp++;
        }

        return resp;
    }// fim pesquisar()

    /*** OUTROS ***/

    /**
     * armazena os jogadores na Tabela
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

        String fileName = "hashReserva.txt";
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

}// fim class Hash


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
        Hash tabela = new Hash(21, 9);

        tabela.adicionaJogadores();
        tabela.pesquisa();
    }// fim main

}// fim classe Jogador