#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

#define MAX_CHARACTERS 420
#define MAX_INFO_LENGTH 400
#define TOTAL_ATRIBUTOS 18

/********* Character *********/
struct Character {
    char id[50];
    char name[50];
    char house[50];
    char ancestry[50];
    char species[50];
    char patronus[50];
    bool hogwartsStaff;
    bool hogwartsStudent;
    char actorName[50];
    bool alive;
    char dateOfBirth[20];
    int yearOfBirth;
    char eyeColor[20];
    char gender[10];
    char hairColor[20];
    bool wizard;

    
}; // struct Character

void setCharacter(char* info, struct Character *character){
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
                strcpy(character->house, token);
                break;
            case 3:
                strcpy(character->ancestry, token);
                break;
            case 4:
                strcpy(character->species, token);
                break;
            case 5:
                strcpy(character->patronus, token);
                break;
            case 6:
                character->hogwartsStaff = atoi(token);
                break;
            case 7:
                character->hogwartsStudent = atoi(token);
                break;
            case 8:
                strcpy(character->actorName, token);
                break;
            case 9:
                character->alive = atoi(token);
                break;
            case 10:
                strcpy(character->dateOfBirth, token);
                break;
            case 11:
                character->yearOfBirth = atoi(token);
                break;
            case 12:
                strcpy(character->eyeColor, token);
                break;
            case 13:
                strcpy(character->gender, token);
                break;
            case 14:
                strcpy(character->hairColor, token);
                break;
            case 15:
                character->wizard = atoi(token);
                break;
            default:
                printf("Atributo não reconhecido: %s\n", token);
                break;
        }

        // Atualiza o token e o contador
        token = strtok(NULL, ";");
        count++;
    }
}// setCharacter()

/********* Registro *********/
struct Registro {
    char filePath[100];
    struct Character *characterList;
    int size;
}; // struct Registro

/**
 * Inicializa o registro
 * @param struct Registro *registro ponteiro para o registro
 * @param int option opcao de inicializacao de path
*/
void newRegistro(struct Registro *registro, int pathOption){
    registro->characterList = NULL;
    registro->size = 0;

    if (pathOption == 0)
        strcpy(registro->filePath, "/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/TP02/characters.csv");
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
 * @param struct Registro *registro Para acessar a filePath
*/
void getCharacterInfo(char* info, const char* id, struct Registro *registro) {
    FILE *file = fopen(registro->filePath, "r");
    if (file == NULL) {
        perror("Error opening file");
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

/**
 * Adiciona o personagem no registro
 * @param char* id Id a ser adicionado
 * @param struct Registro 
*/
void addCharacter(const char* id, struct Registro *registro) {
    char info[MAX_INFO_LENGTH] = {0};
    getCharacterInfo(info, id, registro);
    struct Character character;
    setCharacter(info, &character);
} // addCharacter()

int main(){
    struct Registro registro;
    newRegistro(&registro, 0); // 0 = pc / 1 = VERDE

    char id[50] = {0};
    scanf("%s", id);

    while(!isFim(id)){
        addCharacter(id, &registro);

        scanf("%s", id);
    }

    return 0;
}// main()