import java.io.*;
import java.util.*; 
import java.time.*;

public class Character{
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

    public static Integer numOfCharacters = 0;


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

        numOfCharacters++;
    }

    /**
    * Construtor que recebe uma string com as informacoes
    */
    Character(String info){
        setCharacter(info);
        numOfCharacters++;
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
     * Separa a string e atribui ao jogador os valores
     * @param info
     */
    public void setCharacter(String info){
        MyIO.println(info);
        String[] split = info.split(",");

        for(int i = 0; i < split.length; i++){ 
            if(split[i].isEmpty())MyIO.println("nao informado");
            else MyIO.println(split[i]);
        }
        
    }// setCharacter()

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
            //file = new FileReader("/tmp/characters.csv");
            file = new FileReader("/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/TP02/characters.csv");
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
        String line = "";
        Boolean hasFoundId = false;
        try{
           
            while((hasFoundId == false) && ((line = buffer.readLine()) != null)){
                String idAtual = line.substring(0, line.indexOf(",")); // Atribui a string ate a primeira virgula

                if(id.equals(idAtual))
                    hasFoundId = true;
            }

        } catch (Exception e) {MyIO.println(e.getMessage());}


        return line;
    }// searchId()

    /**
     * Metodo para avaliar se o input = "FIM"
     * @param input string contendo input do usuario
     * @return booleano se e' fim ou nao
     */
    static public boolean isFim(String input){
        boolean resp = false;
        if(input.charAt(0) == 'F' && input.charAt(1) == 'I' && input.charAt(2) == 'M')
            resp = true;
        return resp;
    }// isFim()

    static public void main(String[] args){
        ArrayList<Character> characterList = new ArrayList<Character>();

        String id = MyIO.readLine();
        while(!isFim(id)){
            String info = getCharacterInfo(id);
            Character character = new Character(info);

            id = MyIO.readLine();
        }

    }// main()

}// class Character