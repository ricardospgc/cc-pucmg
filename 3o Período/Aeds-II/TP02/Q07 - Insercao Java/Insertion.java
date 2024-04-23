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
        MyIO.print("[");
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
        MyIO.println("]");   
        
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

class Registro {
    static private String filePath;
    public ArrayList<Character> characterList;
    
    /*** CONSTRUTORES ***/
    Registro(){ this(1); }

    Registro(int option){
        characterList = new ArrayList<Character>();
        if(option == 0) filePath = "/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/characters.csv";
        else filePath = "/tmp/characters.csv";
    }

    /******************/

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
    public void addCharacter(String id){
        String info = getCharacterInfo(id);
        Character character = new Character(info);
        characterList.add(character);
    }// addCharacter()

    /**
     * Printa todos os personagens da lista
     */
    public void printCharacterList(){
        for(int i = 0; i < characterList.size(); i++){
            characterList.get(i).printCharacter();
        }
    }// printCharacterList()

    /**
     * Printa somente um atributo de todos os personagens da lista
     */
    public void printListAttribute(){
        characterList.forEach(n -> { MyIO.println(n.getName() + " " + n.getDateOfBirth()) ;});
    }// printListAttribute()

}// class Registro


public class Insertion{
    public static int numComparacoes = 0;
    public static int numMovimentacoes = 0;

    public static float time = 0;

    public static String logFileName = "803833_insercao.txt";

    /**
     * Cria um arquivo com informacoes da execucao do algoritmos
     */
    public static void criarLog(){
        try {
            FileWriter writer = new FileWriter(logFileName);
            writer.write
            ("803833\t " + String.format("%.3f", time/1000000) + "ms\t Comp: " + numComparacoes + 
            "\t Mov: " + numMovimentacoes);
            writer.close();
        } catch (IOException e) {
            System.out.println("Erro ao escrever o log no arquivo: " + e.getMessage());
        }
    }// criarLog()

    /**
     * Metodo para avaliar se o input = "FIM"
     * @param input string contendo input
     * @return booleano se e' fim ou nao
     */
    public static boolean isFim(String input){
        return input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M';
    }// isFim()

    /**
     * Metodo para dar swap em dois Personagens no arrayList
     * @param a
     * @param b
     */
    public static void swap(ArrayList<Character> list, int a, int b){
        Character aux = list.get(a).clone();
        list.set(a, list.get(b));
        list.set(b, aux);
        numMovimentacoes += 3;
    }// swap()

    /****** QUESTAO *******/

    /**
     * Ordena o array usando Insertion Sort, pelo nome do personagem
     * @param list ArrayList de personagens
     */
    public static void insertion(ArrayList<Character> list){
        for (int i = 1; i < list.size(); i++) {
            Character tmp = list.get(i).clone();
            numMovimentacoes++;
            int j = i - 1;

            // Ordena por DateOfBirth
            while ((j >= 0) && (list.get(j).getDateOfBirth().isAfter(tmp.getDateOfBirth()))) {
                numComparacoes++;
                list.set(j + 1, list.get(j));
                numMovimentacoes++;
                j--;
            }
            list.set(j + 1, tmp);
            numMovimentacoes++;

            // Ordena por nome, caso DateOfBirth seja igual
            while(
            (j >= 0) && 
            (list.get(j).getDateOfBirth().equals(tmp.getDateOfBirth())) &&
            (list.get(j).getName().compareTo(tmp.getName()) > 0)){
                numComparacoes++;
                list.set(j + 1, list.get(j));
                numMovimentacoes++;
                j--;
            }
            list.set(j + 1, tmp);
            numMovimentacoes++;
        }
    }// insertion()

    /**
     * Controla o fluxo para ordenar o registro
     * @param registro
     */
    public static void sort(Registro registro){
        Instant start = Instant.now();

        insertion(registro.characterList);

        Instant end = Instant.now();
        time += (float)Duration.between(start, end).toNanos();
    }// sort()

    static public void main(String[] args){
        Scanner scn = new Scanner(System.in);
        Registro registro = new Registro(); // 0 = pc / void = VERDE

        // Leitura e armazenamento de personagens
        String id = scn.nextLine();
        while(!isFim(id)){
            registro.addCharacter(id);
            id = scn.nextLine();
        }
        sort(registro);
        registro.printCharacterList();

        criarLog();
        scn.close();

    }// main()
}// class PesqSeq
