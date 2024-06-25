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

class No {
    public int valor;
    public No esq, dir;

    public No2 interno;

    No(int valor){
        this(valor, null, null);
    }

    No(int valor, No esq, No dir){
        this.valor = valor;
		this.esq = esq;
		this.dir = dir;
        this.interno = null;
    }
}

class No2 {
    public Character elemento;
    public No2 dir, esq;

    No2(){
        this(new Character());
    }

    No2(Character elemento){
        this(elemento, null, null);
    }

    No2(Character elemento, No2 dir, No2 esq){
        this.elemento = elemento;
        this.dir = dir;
        this.esq = esq;
    }
}// class No2

public class ArvoreArvore {
    private No raiz; // Celula cabeca, sempre vazia, o prox aponta para o primeiro

    private static String filePath;

    public static int numComparacoes = 0;
    public static float time = 0;
    public static String logFileName = "803833_arvoreArvore.txt";

    /**** CONSTRUTORES  ****/

    ArvoreArvore(){ this(2); }

    /**
     * Construtor que recebe uma opcao de filepath e inicializa as variaveis
     * @param option
     */
    ArvoreArvore(int option){
        if(option == 0) filePath = "/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/characters.csv";
        else if (option == 1) filePath = "../../../../csv/characters.csv";
        else filePath = "/tmp/characters.csv";

        raiz = null;

        // Insere os primeiros numeros da arvore externa, preparando para inserir personagens na interna
        try{
            // 7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12 e 14
            inserir(7);
            inserir(3);
            inserir(11);
            inserir(1);
            inserir(5);
            inserir(9);
            inserir(13);
            inserir(0);
            inserir(2);
            inserir(4);
            inserir(6);
            inserir(8);
            inserir(10);
            inserir(12);
            inserir(14);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
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
     * Metodo publico para inserir um numero na arvore externa
     * @param int numero a ser inserido
     */
    public void inserir(int newNum) throws Exception{
        raiz = inserir(newNum, raiz);
    }// inserir()

    /**
     * Metodo publico que encontra a posicao correta e insere o numero na externa
     * @param newNum numero sendo inserido
     * @param i No atual
     * @return No em que foi inserido
     * @throws Exception quando o numero ja existir
     */
    private No inserir(int newNum, No i) throws Exception{
        if(i == null) // Folha encontrada, inserindo
            i = new No(newNum);
        else if (newNum < i.valor) // Numero eh menor que o atual
            i.esq = inserir(newNum, i.esq);
        else if (newNum > i.valor) // Numero eh maior que o atual
            i.dir = inserir(newNum, i.dir);
        else // Numero ja existe
            throw new Exception("Erro ao inserir! Numero ja existe!");

        return i;
    }// inserir()
    

   /**
    * Metodo publico que pesquisa a key na arvore
    * @param key a ser pesquisada
    * @throws Exception quando a arvore estiver vazia
    */
    public void pesquisar(String key) throws Exception{
        if(raiz == null) throw new Exception("Erro ao pesquisar! Arvore vazia!");
        Instant start = Instant.now();

        MyIO.print(key + " => raiz");
        boolean resp = pesquisar(key, raiz);

        Instant end = Instant.now();
        time += (float)Duration.between(start, end).toNanos();

        if(resp) MyIO.println(" SIM");
        else MyIO.println(" NAO");
    }// pesquisar()

    /**
     * Metodo privado que pesquisa se a key existe e printa o caminho na tela (MOSTRAR-PRE)
     * @param key a ser pesquisada
     * @param i no atual 
     * @return resposta
     */
    private boolean pesquisar(String key, No i){
        boolean resp = false;
        if(i != null){
            resp = pesquisarInterno(key, i.interno);

            if(!resp){
                MyIO.print(" ESQ");
                pesquisar(key, i.esq);
            }
            if(!resp){
                MyIO.print(" DIR");
                pesquisar(key, i.dir);
            }
        }
        return resp;
    }// pesquisar()

    /**
     * Percorre a arvore interna buscando o Character (MOSTRAR-PRE)
     * @param key a ser pesquisada
     * @param i no atual
     * @return resposta
     */
    private boolean pesquisarInterno(String key, No2 i){
        boolean resp = false;

        if(i == null) { 
            resp = false; 
            numComparacoes++;
        }

        else if(key.compareTo(i.elemento.getName()) < 0){ 
            System.out.print("->esq"); 
            numComparacoes++;
            resp = pesquisarInterno(key, i.esq);
        }

        else if(key.compareTo(i.elemento.getName()) > 0){ 
            System.out.print("->dir"); 
            numComparacoes++;
            resp = pesquisarInterno(key, i.dir);
        }

        else 
            resp = true;

        return resp;
    }// pesquisarInterno()

    /**
     * Metodo publico para inserir um personagem na arvore
     * @param newCharacter personagem a ser inserido
     */
    public void inserir(Character newCharacter) throws Exception{
        raiz = inserir(newCharacter, raiz);
    }// inserir()

    /**
     * Metodo privado que encontra a posicao correta na arovore externa para inserir
     * @param newCharacter personagem sendo inserido
     * @param i no atual
     * @return No em que foi inserido
     * @throws Exception quando a posicao nao existir
     */
    private No inserir(Character newCharacter, No i) throws Exception{
        if(i == null) // Folha encontrada, numero nao esta na externa
            throw new Exception("Erro ao inserir! Numero invalido!");
        else if ((newCharacter.getYearOfBirth() % 15) < i.valor) // Personagem eh menor que o atual
            i.esq = inserir(newCharacter, i.esq);
        else if ((newCharacter.getYearOfBirth() % 15) > i.valor) // Personagem eh maior que o atual
            i.dir = inserir(newCharacter, i.dir);
        else // posicao na externa, inserindo na interna
            i.interno = inserir(newCharacter, i.interno);

        return i;
    }// inserir()

    /**
     * Encontra a posicao do personagem na arvore interna e insere o personagem
     * @param newCharacter personagem sendo inserido
     * @param i No atual
     * @return No em que foi inserido
    * @throws Exception quando o personagem ja existir
     */
    private No2 inserir(Character newCharacter, No2 i) throws Exception{
        if(i == null) // posicao encontrada, inserindo o personagem
            i = new No2(newCharacter);
        else if (newCharacter.getName().compareTo(i.elemento.getName()) < 0) // Personagem eh menor que o atual
            i.esq = inserir(newCharacter, i.esq);
        else if (newCharacter.getName().compareTo(i.elemento.getName()) > 0) // Personagem eh maior que o atual
            i.dir = inserir(newCharacter, i.dir);
        else // personagem ja existe
        throw new Exception("Erro ao inserir! Personagem ja existe!");

        return i;
    }// inserir()

    public void mostrar(){
        mostrar(raiz);
    }// mostrar()
  
    public void mostrar(No i){
        if (i != null){
            mostrar(i.esq);
            System.out.println("Num: " + i.valor);
            mostrar(i.interno);
            mostrar(i.dir);
        }
    }// mostrar()
  
    public void mostrar(No2 i){
        if (i != null){
            mostrar(i.esq);
            System.out.println(i.elemento.getName());
            mostrar(i.dir);
        }
    }// mostrar()

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
        ArvoreArvore arvore = new ArvoreArvore(0); // 0 = linux / 1 = Windows / void = VERDE
        
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
