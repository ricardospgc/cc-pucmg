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

    // hogwarts student
    public void setHogwartsStudent(boolean hogwartsStudent){ this.hogwartsStudent = hogwartsStudent;}
    public Boolean getHogwartsStudent(){ return this.hogwartsStudent;}

    // actor name
    public void setActorName(String actorName){ this.actorName = actorName;}
    public String getActorName(){ return this.actorName;}

    // alive
    public void setAlive(boolean alive){ this.alive = alive;}
    public Boolean getAlive(){ return this.alive;}

    // alternate actor
    public void setAlternate_actors(ArrayList<String> alternate_actors){ 
        this.alternate_actors = new ArrayList<String>(alternate_actors);}
    public ArrayList<String> getAlternate_actors(){ return this.alternate_actors;}

    // date of birth
    public void setDateOfBirth(LocalDate dateOfBirth){ this.dateOfBirth = dateOfBirth;}
    public LocalDate getDateOfBirth(){ return this.dateOfBirth;}

    // year of birth
    public void setYearOfBirth(int yearOfBirth){ this.yearOfBirth = yearOfBirth;}
    public Integer getYearOfBirth(){ return this.yearOfBirth;}

    // eye color
    public void setEyeColor(String eyeColor){ this.eyeColor = eyeColor;}
    public String getEyeOfColor(){ return this.eyeColor;}

    // gender
    public void setGender(String gender){ this.gender = gender;}
    public String getGender(){ return this.gender;}

    // hair color
    public void setHairColor(String hairColor){ this.hairColor = hairColor;}
    public String getHairColor(){ return this.hairColor;}

    // wizard
    public void setWizard(boolean wizard){ this.wizard = wizard;}
    public Boolean getWizard(){ return this.wizard; }

    /**
     * separa a string info em um array
     * @param strInfo string com todas as informacoes
     * @return array com as informacoes separadas
     */
    private String[] splitInfo(String strInfo){
        String[] arrInfos = new String[18];

        

        return arrInfos;
    }

    /**
     * atribui os atributos ao personagem
     * @param info
     */
    private void setCharacter(String strInfo){

        MyIO.println(strInfo);
        
        String[] arrInfos = new String[18];
        arrInfos = splitInfo(strInfo);

        /* 
        setId(split[0]);
        setName(split[1]);
        //setAlternate_names(split[2]);
        setHouse(split[3]);
        setAncestry(split[4]);
        setSpecies(split[5]);
        setPatronus(split[6]);
        set*/
         

    }// setCharacter()

}// class Character

public class Registro {
    static private String filePath;
    public ArrayList<Character> characterList;
    
    /*** CONSTRUTORES ***/
    Registro(){ this(1); }

    Registro(int option){
        characterList = new ArrayList<Character>();
        if(option == 0) filePath = "/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/TP02/characters.csv";
        else filePath = "/tmp/characters.csv";
    }

    /******************/

    /**
     * Metodo para avaliar se o input = "FIM"
     * @param input string contendo input do usuario
     * @return booleano se e' fim ou nao
     */
    static public boolean isFim(String input){
        return input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M';
    }// isFim()

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
                String idAtual = csvLine.substring(0, csvLine.indexOf(",")); // Atribui a string ate a primeira virgula

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
        //characterList.add(character);
    }// addCharacter()

    /**
     * printa um personagem da lista, baseado no index
     * @param index do personagem printado
     */
    public void printCharacter(int index){
        MyIO.println(characterList.get(0).toString());
    }// printCharacter()

    /**
     * Printa todos os personagens da lista
     */
    public void printCharacterList(){
        for(int i = 0; i < characterList.size(); i++){
            MyIO.println(characterList.get(i).toString());
        }
    }// printCharacterList()

    static public void main(String[] args){
        Registro registro = new Registro(0); // 0 = pc / void = VERDE

        String id = MyIO.readLine();
        while(!isFim(id)){
            registro.addCharacter(id);
            //registro.printCharacterList();

            id = MyIO.readLine();
        }

    }// main()
}