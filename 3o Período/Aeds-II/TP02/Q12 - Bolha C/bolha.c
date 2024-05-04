#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

#define MAX_CHARACTERS 420
#define MAX_INFO_LENGTH 400
#define TOTAL_ATRIBUTOS 18

// variaveis globais
int numComp = 0;
int numMov = 0;
double tempoTotal = 0;


/********* Character *********/
typedef struct{
    char id[50];
    char name[50];
    char alternateNames[130];
    char house[50];
    char ancestry[50];
    char species[50];
    char patronus[50];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[50];
    bool alive;
    char alternateActors[100];
    char dateOfBirth[10];
    int yearOfBirth;
    char eyeColor[20];
    char gender[10];
    char hairColor[20];
    bool wizard;
} Character; // struct Character

// retorna se uma string e' VERDADEIRO
bool stringToBool(char* str){
    return strlen(str) >= strlen("VERDADEIRO");
}// stringToBool()

// formata o DateOfBirth para armazenar
void formatDOB(char* dob){
    int count = 0;
    char formated[15] = {0};

    if(strlen(dob) < 10){
        char *token = strtok(dob, "-");

        while(token != NULL && count < 3){
            switch(count){
                case 0:
                    strcpy(formated, token);
                    break;
                case 1:
                    strcat(formated, "-0");
                    strcat(formated, token);
                    break;
                case 2:
                    strcat(formated, "-");
                    strcat(formated, token);
                    break;
                default:
                    printf("Erro formatDOB token: %s\n", token);
                    break;
            }

            token = strtok(NULL, "-");
            count++;
        }

        strcpy(dob, formated);
    }
}// formatDOB

// 
void strToLower(char* str){
    for(int i = 0; str[i]; i++){
        str[i] = tolower(str[i]);
    }
}

// Separa a string e seta os valores no Character
void setCharacter(char* info, Character *character){
    int count = 0;

    char *token = strtok(info, ";");

    // Cada iteração preenche um atributo, aumenta o contador e passa para o próximo caso
    while (token != NULL && count < TOTAL_ATRIBUTOS) {
        // Atribui os tokens aos atributos do struct Character
        switch (count) {
            case 0:
                strcpy(character->id, token);
                break;
            case 1:
                strcpy(character->name, token);
                break;
            case 2:
                strcpy(character->alternateNames, token);
            case 3:
                strcpy(character->house, token);
                break;
            case 4:
                strcpy(character->ancestry, token);
                break;
            case 5:
                strcpy(character->species, token);
                break;
            case 6:
                strcpy(character->patronus, token);
                break;
            case 7:
                character->hogwartsStaff = stringToBool(token);
                break;
            case 8:
                character->hogwartsStudent = stringToBool(token);
                break;
            case 9:
                strcpy(character->actorName, token);
                break;
            case 10:
                character->alive = stringToBool(token);
                break;
            case 11:
                strcpy(character->alternateActors, token);
                break;
            case 12:
                strcpy(character->dateOfBirth, token);
                break;
            case 13:
                character->yearOfBirth = atoi(token);
                break;
            case 14:
                strcpy(character->eyeColor, token);
                break;
            case 15:
                strcpy(character->gender, token);
                break;
            case 16:
                strcpy(character->hairColor, token);
                break;
            case 17:
                character->wizard = stringToBool(token);
                break;
            default:
                printf("Atributo não reconhecido: %s\n", token);
                break;
        }

        // Atualiza o token e o contador
        count++;
        token = strtok(NULL, ";");
    }

    formatDOB(character->dateOfBirth);

}// setCharacter()

// printa um personagem na formatacao
void printCharacter(Character character){
    printf("[");
    printf("%s ## ", character.id);
    printf("%s ## ", character.name);
    printf("%s ## ", character.alternateNames);
    printf("%s ## ", character.house);
    printf("%s ## ", character.ancestry);
    printf("%s ## ", character.species);

    // caso seja vazio
    if(strlen(character.patronus) > 1) printf("%s", character.patronus);
    printf(" ## ");
    
    printf("%s ## ", character.hogwartsStaff ? "true" : "false");
    printf("%s ## ", character.hogwartsStudent ? "true" : "false");
    
    // caso seja vazio
    if(strlen(character.actorName) > 1) printf("%s", character.actorName);
    printf(" ## ");

    printf("%s ## ", character.alive ? "true" : "false");
    //printf("{%s} ## ", character.alternateActors);
    printf("%s ## ", character.dateOfBirth);
    printf("%d ## ", character.yearOfBirth);
    printf("%s ## ", character.eyeColor);
    printf("%s ## ", character.gender);
    printf("%s ## ", character.hairColor);
    printf("%s", character.wizard ? "true" : "false");
    printf("]\n");
}

// troca dois personagens
void swap( Character *c1,  Character *c2 ){
     Character tmp = *c1;
    *c1 = *c2;
    *c2 = tmp;
    numMov++;
}// swap()

/********* Registro *********/
 typedef struct {
    char filePath[100];
    Character characterList[MAX_CHARACTERS];
    int size;
} Registro; // struct Registro

/**
 * Inicializa o registro
 * @param struct Registro *registro ponteiro para o registro
 * @param int option opcao de inicializacao de path
*/
void newRegistro(Registro *registro, int pathOption){
    registro->size = 0;

    if (pathOption == 0)
        strcpy(registro->filePath, "/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/characters.csv");
    else if (pathOption == 1)
        strcpy(registro->filePath, "../../../../csv/characters.csv");
    else
        strcpy(registro->filePath, "/tmp/characters.csv");
}// newRegistro

/**
 * Retorna se a string e' igual a "FIM"
 * @return bool true se for "FIM", false se nao
*/
bool isFim(char* id){
    return strcmp(id, "FIM") == 0;
}// isFim

/**
 * Encontra o ID no csv e armazena em uma string
 * @param char* info Local em que a string vai ser armazenada
 * @param char* id Id a ser encontrado
 * @param Registro *registro Para acessar a filePath
*/
void getCharacterInfo(char* info, const char* id, Registro *registro) {

    FILE *file = fopen(registro->filePath, "r");
    if (file == NULL) {
        printf("\ninfo: %s", info);
        perror("Error opening file characters.csv");
        return;
    }

    bool hasFoundId = false;

    char csvLine[MAX_INFO_LENGTH];
    while ((hasFoundId == false) && (fgets(csvLine, MAX_INFO_LENGTH, file) != NULL)) {
        char tmp[MAX_INFO_LENGTH] = {0};
        strcpy(tmp, csvLine);
        char *idAtual = strtok(tmp, ";");

        if (strcmp(idAtual, id) == 0) { // Comparando com o ID fornecido
            hasFoundId = true;
            strcpy(info, csvLine);
        }
    }

    fclose(file);
}// getCharacterInfo()

// remove o caractere desejado da string
void removeChars(char *str, char c)
{
    int writer = 0, reader = 0;

    while (str[reader]) {
        if (str[reader] != c) { // se o caractere for igual, atualiza 
            str[writer++] = str[reader];
        }
        reader++;       
    }

    str[writer]=0;
}// removeChars

// Retira as ; duplas e coloca caractere vazio
void trataPVduplo(char *info){
    int i = 0; // index da letra

    while(info[i] != '\0'){
        if(info[i] == ';' && info[i+1] == ';'){
        
        //Shift das letras para a direita, abrindo espaço para o char ' '
        for(int j = strlen(info); j > i+1; j--){
            info[j] = info[j - 1];
        }
        
        info[i+1] = ' ';
        }
    i++;
  }
}// trataPvduplo

// Troca a primeira ocorrencia de um char pelo outro
void replaceChar(char* str, char chOrigin, char chReplace){
    char* ret = strchr(str, chOrigin);
    str[strlen(str) - strlen(ret)] = chReplace;
}// replaceChar 

/**
 * Adiciona o personagem no registro
 * @param char* id Id a ser adicionado
 * @param Registro 
*/
void addCharacter(const char* id, Registro *registro) {
    // encontrar a informacao do personagem
    char info[MAX_INFO_LENGTH] = {0};
    getCharacterInfo(info, id, registro);

    //tratamento da string info para adequar ao personagem
    trataPVduplo(info); //quando for ;; tem que ser vazio
    removeChars(info, '\''); // remove aspas simples
    replaceChar(info, '[', '{');
    replaceChar(info, ']', '}');

    // atribuir os valores ao personagem
    Character character;
    setCharacter(info, &character);

    // insercao no registro
    if (registro->size >= MAX_CHARACTERS) {
        printf("Registro cheio!\n");
        return;
    }
    registro->characterList[registro->size] = character;
    registro->size++;

}// addCharacter()

// printa todos os personagens do registro
void printRegistro(Registro registro){
    for(int i = 0; i < registro.size; i++){
        printCharacter(registro.characterList[i]);
    }
}// printaRegistro()

// printa um atributo especifico
void printListAttribute(Registro registro){
    for(int i = 0; i < registro.size; i++){
        printf("%s: %s\n", registro.characterList[i].name, registro.characterList[i].hairColor);
    }
}// printListAttribute()

int cmpCharacter(Character a, Character b){
    int cmp = strcmp(a.hairColor, b.hairColor);
    numComp++;

    // desempata por nome caso HairColor for igual
    if(cmp == 0){
        cmp = strcmp(a.name, b.name);
        numComp++;
    }

    return cmp;
}// cmpCharacter()

/***** QUESTAO ******/

// Bubblesort com HairColor de atribnuto
void bubblesort(Character *list, int size) {
    int i, j;
    for (i = 0; i < (size-1); i++) {
        for (j = 0; j < (size-1); j++) {
            if (cmpCharacter(list[j], list[j+1]) > 0)
                swap(&list[j], &list[j + 1]);
        }
    }
}// bubblesort()

// Controla o fluxo de chamada da Selection Sort Recurssiva
void sort(Character *list, int size){
    clock_t start = clock();

    bubblesort(list, size);

    clock_t end = clock();
    tempoTotal += ((double) end - start) / CLOCKS_PER_SEC;
}// sort()

// cria um arquivo de log com as info necessarias
void criarLog(){
    FILE *arq = fopen("803833_bolha.txt", "w");
    if (arq == NULL) {
        perror("Erro ao abrir o arquivo.");
        exit(EXIT_FAILURE); 
    }

    fprintf(arq, "803833\t%.4f ms\t Comp: %i\t Mov: %i", tempoTotal*1000, numComp, numMov);

    fclose(arq);
}// criaLog()

int main(){
    Registro registro;
    newRegistro(&registro, 2); // 0 = linux / 1 = Windows / 2 = VERDE

    char id[50] = {0};

    // Leitura e armazenamento dos personagens
    scanf(" %s", id);
    while(!isFim(id)){
        addCharacter(id, &registro);
        scanf(" %s", id);
    }
    
    sort(registro.characterList, registro.size);

    printRegistro(registro);
    //printListAttribute(registro);
    
    criarLog();

    return 0;
}// main()