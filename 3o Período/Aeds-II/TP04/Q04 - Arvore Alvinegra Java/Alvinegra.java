import java.io.*;
import java.util.*; 
import java.time.*;

class Character{
    private String id;
    private String name;
    private ArrayList<String> alternate_names;
    private String house;
    private String ancestry;
    private String species;
    private String patronus;
    private Boolean hogwartsStaff;
    private Boolean hogwartsStudent;
    private String actorName;
    private Boolean alive;
    private ArrayList<String> alternate_actors;
    private LocalDate dateOfBirth;
    private Integer yearOfBirth;
    private String eyeColor;
    private String gender;
    private String hairColor;
    private Boolean wizard;


    /*** CONSTRUTORES ***/

    /**
     * Construtor padrao
     */
    Character(){
        this.id = null;
        this.name = null;
        this.alternate_names = null;
        this.house = null;
        this.ancestry = null;
        this.species = null;
        this.patronus = null;
        //Boolean hogwartsStaff
        //Boolean hogwartsStudent
        this.actorName = null;
        //Boolean alive
        this.dateOfBirth = null;
        this.yearOfBirth = null;
        this.eyeColor = null;
        this.gender = null;
        this.hairColor = null;
        //Boolean wizard;        
    }

    /**
    * Construtor que recebe uma string com as informacoes
    */
    Character(String info){
        setCharacter(info);
    }

    /*** SETS & GETS ***/

    // id
    public void setId(String id){ this.id = id;}
    public String getId(){ return this.id;}

    // name
    public void setName(String name){ this.name = name;}
    public String getName(){ return this.name;}

    // alternate name
    public void setAlternate_names(ArrayList<String> alternate_names){ 
        this.alternate_names = new ArrayList<String>(alternate_names);}
    public ArrayList<String> getAlternate_names(){ return this.alternate_names;}
    public void setAlternate_names(String alternate_names){
        setAlternate_names(StringToArrayList(alternate_names));
    }// setAlternate_names(String)

    // house
    public void setHouse(String house){ this.house = house;}
    public String getHouse(){ return this.house;}

    // ancestry
    public void setAncestry(String ancestry){ this.ancestry = ancestry;}
    public String getAncestry(){ return this.ancestry;}

    // species
    public void setSpecies(String species){ this.species = species;}
    public String getSpecies(){ return this.species;}

    // patronus
    public void setPatronus(String patronus){ this.patronus = patronus;}
    public String getPatronus(){ return this.patronus;}

    // hogwarts staff
    public void setHogwartsStaff(boolean hogwartsStaff){ this.hogwartsStaff = hogwartsStaff;}
    public Boolean getHogwartsStaff(){ return this.hogwartsStaff;}
    public void setHogwartsStaff(String hogwartsStaff){
        setHogwartsStaff(StringToBoolean(hogwartsStaff));
    }// setHogwartsStaff(String)

    // hogwarts student
    public void setHogwartsStudent(boolean hogwartsStudent){ this.hogwartsStudent = hogwartsStudent;}
    public Boolean getHogwartsStudent(){ return this.hogwartsStudent;}
    public void setHogwartsStudent(String hogwartsStudent){
        setHogwartsStudent(StringToBoolean(hogwartsStudent));
    }// setHogwartsStudent(String)

    // actor name
    public void setActorName(String actorName){ this.actorName = actorName;}
    public String getActorName(){ return this.actorName;}

    // alive
    public void setAlive(boolean alive){ this.alive = alive;}
    public Boolean getAlive(){ return this.alive;}
    public void setAlive(String alive){
        setAlive(StringToBoolean(alive));
    }// setAlive(String)

    // alternate actor
    public void setAlternate_actors(ArrayList<String> alternate_actors){ 
        this.alternate_actors = new ArrayList<String>(alternate_actors);}
    public ArrayList<String> getAlternate_actors(){ return this.alternate_actors;}
    public void setAlternate_actors(String alternate_actors){
        setAlternate_actors(StringToArrayList(alternate_actors));
    }// setAlternate_actors(String)

    // date of birth
    public void setDateOfBirth(LocalDate dateOfBirth){ this.dateOfBirth = dateOfBirth;}
    public LocalDate getDateOfBirth(){ return this.dateOfBirth;}
    public void setDateOfBirth(String dateOfBirth){
        setDateOfBirth(LocalDate.parse(formatDate(dateOfBirth)));
    }// setDateOfBirth(String)

    // year of birth
    public void setYearOfBirth(int yearOfBirth){ this.yearOfBirth = yearOfBirth;}
    public Integer getYearOfBirth(){ return this.yearOfBirth;}
    public void setYearOfBirth(String yearOfBirth){
        setYearOfBirth(Integer.parseInt(yearOfBirth));
    }// setYearOfBirth(String)

    // eye color
    public void setEyeColor(String eyeColor){ this.eyeColor = eyeColor;}
    public String getEyeColor(){ return this.eyeColor;}

    // gender
    public void setGender(String gender){ this.gender = gender;}
    public String getGender(){ return this.gender;}

    // hair color
    public void setHairColor(String hairColor){ this.hairColor = hairColor;}
    public String getHairColor(){ return this.hairColor;}

    // wizard
    public void setWizard(boolean wizard){ this.wizard = wizard;}
    public Boolean getWizard(){ return this.wizard; }
    public void setWizard(String wizard){setWizard(StringToBoolean(wizard));}
    
    /**
     * Converte string para booleano (parseBoolean)
     * @param str
     * @return true se for "True", else false
     */
    static public Boolean StringToBoolean(String str){
        return (str.equals("VERDADEIRO")) ? true : false;
    }// StringToBoolean()

    /**
     * Converte string para ArrayList
     * @param str
     * @return ArrayList com as strings separadas
     */
    static public ArrayList<String> StringToArrayList(String str){        
        str = str.replace("[","");
        str = str.replace("]", "");

        ArrayList<String> list = new ArrayList<String>(Arrays.asList(str.split(";")));

        return list;
    }// StringToArrayList()

    /**
     * Converte DMY -> YMD e vice-versa
     * @param str
     * @return string de data invertida
     */
    static public String formatDate(String str){
        String[] split = str.split("-");
        if(split[1].length() == 1) split[1] = "0" + split[1];
        str = split[2] + "-" + split[1] + "-" + split[0];
        return str;
    }// formatDate()

    /**
     * Converte o arraylist em string no formato do Verde
     * @param arr
     * @return
     */
    public String ArrayListToString(ArrayList<String> arr){
        String str = "";
        
        for(int i = 0; i < arr.size(); i++){
            str += arr.get(i) + ",";
        }

        str = str.substring(0, str.length()-1);
        if(str.equals(" ")) str = "";
        return str;
    }// ArrayListToString()

    /**
     * separa a string info em um array
     * @param strInfo string com todas as informacoes
     * @return array com as informacoes separadas
     */
    private String[] splitInfo(String strInfo){
        String[] arrInfos = new String[18]; // 18 espaços para os 18 atributos
        String[] alternateInfos = new String[2]; // alternate names e alternate actors
        
        strInfo = strInfo.replace("'", "");
        StringBuilder sbAux = new StringBuilder(strInfo);

        // Trata alternate names e alternate actors
        int c = 2;
        do{
            int idxOfOpenBracket = strInfo.indexOf("[");
            int idxOfCloseBracket = strInfo.indexOf("]");
    
            if(idxOfOpenBracket+1 != idxOfCloseBracket) // Se true, tem conteudo
                alternateInfos[alternateInfos.length - c] = strInfo.substring(idxOfOpenBracket, idxOfCloseBracket+1);
            else // nao tem / nao informado
                alternateInfos[alternateInfos.length - c] = "";
            // remove a string, +2 para incluir a virgula
            strInfo = sbAux.delete(idxOfOpenBracket, idxOfCloseBracket+2).toString();

            c--;
        } while(c > 0);

        //Preenche arrInfos com os atributos
        strInfo += "; ; ";
        arrInfos = strInfo.split(";");
        arrInfos[16] = alternateInfos[0];
        arrInfos[17] = alternateInfos[1];

        return arrInfos;
        
    }//splitInfo()

    /**
     * atribui os atributos ao personagem
     * @param info
     */
    private void setCharacter(String strInfo){
        
        String[] arrInfos = new String[18];
        arrInfos = splitInfo(strInfo);

        setId(arrInfos[0]);
        setName(arrInfos[1]);
        setHouse(arrInfos[2]);
        setAncestry(arrInfos[3]);
        setSpecies(arrInfos[4]);
        setPatronus(arrInfos[5]);
        setHogwartsStaff(arrInfos[6]);
        setHogwartsStudent(arrInfos[7]);
        setActorName(arrInfos[8]);
        setAlive(arrInfos[9]);
        setDateOfBirth(arrInfos[10]);
        setYearOfBirth(arrInfos[11]);
        setEyeColor(arrInfos[12]);
        setGender(arrInfos[13]);
        setHairColor(arrInfos[14]);
        setWizard(arrInfos[15]);

        setAlternate_names(arrInfos[16]);
        setAlternate_actors(arrInfos[17]);

    }// setCharacter()

    /**
     * printa um personagem da lista, baseado no index
     * @param index do personagem printado
     */
    public void printCharacter(){
        MyIO.print(getId() + " ## ");
        MyIO.print(getName() + " ## ");

        MyIO.print("{" + ArrayListToString(getAlternate_names()) + "}" + " ## ");

        MyIO.print(getHouse() + " ## ");
        MyIO.print(getAncestry() + " ## ");
        MyIO.print(getSpecies() + " ## ");
        MyIO.print(getPatronus() + " ## ");
        MyIO.print(getHogwartsStaff() + " ## ");
        MyIO.print(getHogwartsStudent() + " ## ");
        MyIO.print(getActorName() + " ## ");
        MyIO.print(getAlive() + " ## ");

        MyIO.print(formatDate(getDateOfBirth().toString()) + " ## ");

        MyIO.print(getYearOfBirth() + " ## ");
        MyIO.print(getEyeColor() + " ## ");
        MyIO.print(getGender() + " ## ");
        MyIO.print(getHairColor() + " ## ");
        MyIO.print(getWizard());
        
    }// printCharacter()

    /**
     * Retorna um clone do character corrente
     * @return Character clone
     */
    public Character clone() {
        Character clone = new Character();

        clone.setId(this.getId());
        clone.setName(this.getName());
        clone.setHouse(this.getHouse());
        clone.setAncestry(this.getAncestry());
        clone.setSpecies(this.getSpecies());
        clone.setPatronus(this.getPatronus());
        clone.setHogwartsStaff(this.getHogwartsStaff());
        clone.setHogwartsStudent(this.getHogwartsStudent());
        clone.setActorName(this.getActorName());
        clone.setAlive(this.getAlive());
        clone.setDateOfBirth(this.getDateOfBirth());
        clone.setYearOfBirth(this.getYearOfBirth());
        clone.setEyeColor(this.getEyeColor());
        clone.setGender(this.getGender());
        clone.setHairColor(this.getHairColor());
        clone.setWizard(this.getWizard());

        // Deep copy for ArrayLists
        if (this.alternate_names != null) {
            clone.setAlternate_names(new ArrayList<>(this.getAlternate_names()));
        }
        if (this.alternate_actors != null) {
            clone.setAlternate_actors(new ArrayList<>(this.getAlternate_actors()));
        }

        return clone;
    }// clone()

}// class Character

class NoAN {
    public Character elemento;
    public NoAN dir, esq;
    public boolean cor;

    NoAN(){
        this(new Character());
    }

    NoAN(Character elemento){
        this(elemento, false);
    }

    NoAN(Character elemento, boolean cor){
        this(elemento, null, null, cor);
    }

    NoAN(Character elemento, NoAN dir, NoAN esq, boolean cor){
        this.elemento = elemento;
        this.dir = dir;
        this.esq = esq;
        this.cor = cor;
    }
}

public class Alvinegra {
    private NoAN raiz;

    private static String filePath;

    public static int numComparacoes = 0;
    public static float time = 0;
    public static String logFileName = "803833_alvinegra.txt";

    /**** CONSTRUTORES  ****/

    Alvinegra(){ this(2); }

    /**
     * Construtor que recebe uma opcao de filepath e inicializa as variaveis
     * @param option
     */
    Alvinegra(int option){
        if(option == 0) filePath = "/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/characters.csv";
        else if (option == 1) filePath = "../../../../csv/characters.csv";
        else filePath = "/tmp/characters.csv";

        raiz = null;
    }// Lista()

     /**
     * Cria um arquivo com informacoes da execucao do algoritmos
     */
    public static void criarLog(){
        try {
            FileWriter writer = new FileWriter(logFileName);
            writer.write
            ("803833\t " + String.format("%.3f", time/1000000) + "ms\t Comp: " + numComparacoes);
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever o log no arquivo: " + e.getMessage());
        }
    }// criarLog()

    /****** CSV ******/

    /**
     * Retorna as informacoes do personagem com o id parametrizado
     * @param id id do personagem desejado
     * @return String info com os dados do personagem
     */
    public static String getCharacterInfo(String id){
        String info = "";

        FileReader file;
        BufferedReader buffer;

        try{
            file = new FileReader(filePath);
            buffer = new BufferedReader(file);

            info = searchId(id, buffer);

            buffer.close();
            file.close();

        } catch(Exception e){
            e.printStackTrace();
        }

        return info;
    }// getCharacterInfo()

    /**
     * Metodo que procura a string do id parametrizado
     * @param id id desejado
     * @param buffer que le o arquivo csv
     * @return a string do personagem
     */
    public static String searchId(String id, BufferedReader buffer) {
        String csvLine = "";
        Boolean hasFoundId = false;
        try{
            while((hasFoundId == false) && ((csvLine = buffer.readLine()) != null)){
                String idAtual = csvLine.substring(0, csvLine.indexOf(";")); // Atribui a string ate a primeira virgula

                if(id.equals(idAtual))
                    hasFoundId = true;
            }
        } catch (Exception e) {MyIO.println(e.getMessage());}
        return csvLine;
    }// searchId()

    /**
     * Metodo que controla a insercao de um personagem no registro
     * @param id Id do personagem a ser inserido
     */
    public static Character getCharacter(String id){
        String info = getCharacterInfo(id);
        Character character = new Character(info);
        return character;
    }// getCharacter()

   /**
    * Metodo publico que pesquisa a key na arvore
    * @param key a ser pesquisada
    * @throws Exception quando a arvore estiver vazia
    */
    public void pesquisar(String key) throws Exception{
        if(raiz == null) throw new Exception("Erro ao pesquisar! Arvore vazia!");
        Instant start = Instant.now();

        MyIO.print(key + " => raiz ");
        pesquisar(key, raiz);

        Instant end = Instant.now();

        time += (float)Duration.between(start, end).toNanos();
    }// pesquisar()

    /**
     * Metodo privado que pesquisa se a key existe e printa o caminho na tela
     * @param key a ser pesquisada
     * @param i no atual 
     */
    private void pesquisar(String key, NoAN i){

        if(i == null){ // Chegou em uma folha sem encontrar
            MyIO.println("NAO");
            numComparacoes++;

        } else if (i.elemento.getName().equals(key)) { // Se o elemento for o procurado
            MyIO.println("SIM");
            numComparacoes++;

        } else if (key.compareTo(i.elemento.getName()) < 0) { // Se a key for menor que o atual
            MyIO.print("esq ");
            numComparacoes++;
            pesquisar(key, i.esq); 

        } else {  // Se a key for maior que a atual
            MyIO.print("dir ");
            numComparacoes++;
            pesquisar(key, i.dir);
        }
    }// pesquisar()

    /**
     * Insere os 3 primeiros elementos na arvore, facilitando as proximas insercoes
     * @param elemento
     * @throws Exception
     */
    public void inserir(Character elemento) throws Exception {
        // Se a arvore estiver vazia
        if (raiz == null) {
            raiz = new NoAN(elemento);
            // Senao, se a arvore tiver um elemento
        } else if (raiz.esq == null && raiz.dir == null) {
            if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
                raiz.esq = new NoAN(elemento);
            } else {
                raiz.dir = new NoAN(elemento);
            }
            // Senao, se a arvore tiver dois elementos (raiz e dir)
        } else if (raiz.esq == null) {
            if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
                raiz.esq = new NoAN(elemento);
            } else if (elemento.getName().compareTo(raiz.dir.elemento.getName()) < 0) {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
            } else {
                raiz.esq = new NoAN(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;
            // Senao, se a arvore tiver dois elementos (raiz e esq)
        } else if (raiz.dir == null) {
            if (elemento.getName().compareTo(raiz.elemento.getName()) > 0) {
                raiz.dir = new NoAN(elemento);
            } else if (elemento.getName().compareTo(raiz.esq.elemento.getName()) > 0) {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = elemento;
            } else {
                raiz.dir = new NoAN(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = elemento;
            }
            raiz.esq.cor = raiz.dir.cor = false;

            // Senao, a arvore tem tres ou mais elementos
        } else {
            inserir(elemento, null, null, null, raiz);
        }
        raiz.cor = false;
    }// inserir()

    /**
     * Metodo que insere os elementos alem dos 3 primeiros, mantendo o balanceamento
     * @param elemento novo a ser inserido
     * @param bisavo ponteiro para bisavo
     * @param avo ponteiro para avo
     * @param pai ponteiro para pai
     * @param i ponteiro para o atual
     * @throws Exception
     */
    private void inserir(Character elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
        if (i == null) {
            if (elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                i = pai.esq = new NoAN(elemento, true);
            } else {
                i = pai.dir = new NoAN(elemento, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
                inserir(elemento, avo, pai, i, i.esq);
            } else if (elemento.getName().compareTo(i.elemento.getName()) > 0) {
                inserir(elemento, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }// inserir()

    /**
     * Balanceia a arvore
     * @param bisavo
     * @param avo
     * @param pai
     * @param i
     */
    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
        // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
        if (pai.cor == true) {
            // 4 tipos de reequilibrios e acoplamento
            if (pai.elemento.getName().compareTo(avo.elemento.getName()) > 0) { // rotacao a esquerda ou direita-esquerda
                if (i.elemento.getName().compareTo(pai.elemento.getName()) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else { // rotacao a direita ou esquerda-direita
                if (i.elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }
            // reestabelecer as cores apos a rotacao
            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;
        } // if(pai.cor == true)
    }// balancear()

    /**
     * Rotacao simples para a direita
     * @param no
     * @return
     */
    private NoAN rotacaoDir(NoAN no) {
        NoAN noEsq = no.esq;
        NoAN noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }// rotacaoDir()

    /**
     * Rotacao simples para a esquerda
     * @param no
     * @return
     */
    private NoAN rotacaoEsq(NoAN no) {
        NoAN noDir = no.dir;
        NoAN noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        
        return noDir;
    }// rotacaoEsq()

    /**
     * Rotacao dupla Dir-Esq
     * @param no
     * @return
     */
    private NoAN rotacaoDirEsq(NoAN no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }// rotacaoDirEsq()

    /**
     * Rotacao dupla Esq-Dir
     * @param no
     * @return
     */
    private NoAN rotacaoEsqDir(NoAN no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }// rotacaoEsqDir

    /**
     * Metodo publico para mostrar os elementos
     */
    public void mostrarCentral(){
		mostrarCentral(raiz);
    }// mostrarCentral()

    /**
     * Metodo privado que mostra os elementos, do menor para o maior
     * @param i No atual
     */
    private void mostrarCentral(NoAN i){
        if(i != null){
            mostrarCentral(i.esq);
            MyIO.println(i.elemento.getName());
            mostrarCentral(i.dir);
        }
    }// mostrarCentral()

    /**
     * Metodo para avaliar se o input = "FIM"
     * @param input string contendo input
     * @return booleano se e' fim ou nao
     */
    public static boolean isFim(String input){
        return input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M';
    }// isFim()
    
    static public void main(String[] args){
        Scanner scn = new Scanner(System.in);
        Alvinegra arvore = new Alvinegra(); // 0 = linux / 1 = Windows / void = VERDE
        
        try{
            // Leitura e armazenamento de personagens
            String id = scn.nextLine();
            while(!isFim(id)){
                Character newCharacter = getCharacter(id);
                arvore.inserir(newCharacter);
    
                id = scn.nextLine();
            }

            // Pesquisa se as entradas existem
            String key = scn.nextLine();
            while(!isFim(key)){
                arvore.pesquisar(key);
                key = scn.nextLine();
            }
            
            criarLog();

            scn.close();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }// main()
}// class Registro
