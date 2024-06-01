#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>

#define TAM_FILA 5
#define MAX_INFO_LENGTH 400
#define TOTAL_ATRIBUTOS 18

/********* Character *********/
struct Character {
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
}; // struct Character

// retorna se uma string e' VERDADEIRO
bool stringToBool(char* str){
    return strlen(str) >= strlen("VERDADEIRO");
}

// Separa a string e seta os valores no Character
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

}// setCharacter()

void printCharacter(struct Character character){
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
}

/********* Celula *********/

typedef struct Celula{
    struct Character character;
    struct Celula* prox;
} Celula;

Celula* newCelula(struct Character character){
    Celula* resp = (Celula*) malloc(sizeof(Celula));
    resp->character = character;
    resp->prox = NULL;
    return resp;
}

/********* Fila *********/
struct FilaCirc {
    char filePath[100];

    Celula* primeiro;
    Celula* ultimo;

    int size;

}; // struct FilaCirc

/**
 * Inicializa a lista
 * @param struct Lista *fila ponteiro para o lista
 * @param int option opcao de inicializacao de path
*/
void newFila(struct FilaCirc *fila, int pathOption){
    struct Character c;
    fila->primeiro = newCelula(c);
    fila->ultimo = fila->primeiro;

    fila->size = 0;

    if (pathOption == 0)
        strcpy(fila->filePath, "/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/characters.csv");
    else if (pathOption == 1)
        strcpy(fila->filePath, "../../../../csv/characters.csv");
    else
        strcpy(fila->filePath, "/tmp/characters.csv");

    
}// newFila()

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
 * @param struct FilaCirc *fila Para acessar a filePath
*/
void getCharacterInfo(char* info, const char* id, struct FilaCirc *fila) {
    FILE *file = fopen(fila->filePath, "r");
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
 * Adiciona o personagem na lista
 * @param char* id Id a ser adicionado
 * @param struct FilaCirc 
*/
struct Character getCharacter(const char* id, struct FilaCirc *fila) {
    // encontrar a informacao do personagem
    char info[MAX_INFO_LENGTH] = {0};
    getCharacterInfo(info, id, fila);

    //tratamento da string info para adequar ao personagem
    trataPVduplo(info); //quando for ;; tem que ser vazio
    removeChars(info, '\''); // remove aspas simples
    replaceChar(info, '[', '{');
    replaceChar(info, ']', '}');

    // atribuir os valores ao personagem
    struct Character character;
    setCharacter(info, &character);

    return character;
}// getCharacter()

/***************** FILA CIRCULAR ******************/

// Retorna a media das datas de nascimento dos elementos
int mediaIdade(struct FilaCirc *fila){
    Celula* tmp = fila->primeiro->prox;
    int soma = 0;
    while(tmp!=NULL){
        soma += tmp->character.yearOfBirth;
        tmp = tmp->prox;
    }
    return (soma/fila->size);
}// mediaIdade()

// Remove o elemento da fila
struct Character remover(struct FilaCirc *fila){
    if(fila->primeiro == NULL || fila->primeiro == fila->ultimo){
        printf("Erro ao remover! Fila vazia!");
        exit(1);
    }

    struct Celula* tmp = fila->primeiro->prox;
    struct Character removido = tmp->character;

    fila->primeiro->prox = tmp->prox;
    tmp->prox = NULL;

    free(tmp);
    tmp = NULL;

    fila->size--;
    return removido;
}// remover()

// Insere o elemento na fila
void inserir(struct FilaCirc *fila, struct Character newCharacter){
    
    // Libera espaço caso a fila esteja cheia
    if(fila->size == 5)
        remover(fila);
    // Insere o elemento ao fim da fila
    fila->ultimo->prox = newCelula(newCharacter);
    fila->ultimo = fila->ultimo->prox;

    fila->size++;

    printf(">> Year Birthday Average: %d\n", mediaIdade(fila));
}// inserir()

/***********************/

void escolheOpcao(struct FilaCirc *fila, char* input){
    int inputLen = strlen(input);

    const char delim[2] = " ";
    char* token;

    char acao[3] = {0};
    token = strtok(input, delim);
    strcpy(acao, token);


    char pt2[40] = {0};
    if (inputLen == 38) {
        token = strtok(NULL, delim);
        strcpy(pt2, token);
    }

    struct Character c;

    if(strcmp(acao,"I") == 0) {
        c = getCharacter(pt2, fila);
        inserir(fila, c);

    } else if (strcmp(acao, "R") == 0) {
        c = remover(fila);
        printf("(R) %s\n", c.name);

    } else
        printf("Erro ao manipular a lista!\n");
}// escolheOpcao()

// printa todos os personagens da lista
void printFila(struct FilaCirc *fila){
    Celula* i;
    int j=0;

    printf("[ Head ]\n");
    for (i = fila->primeiro->prox; i != NULL; i = i->prox, j++) {
        printf("[%i ## ", j);
        printCharacter(i->character);
        printf("]\n");
    }
    printf("[ Tail ]\n");
}// printaFila()


/*********************/

int main(){
    struct FilaCirc fila;
    newFila(&fila, 2); // 0 = linux / 1 = Windows / 2 = VERDE
    
    char id[50] = {0};
    scanf("%s", id);

    while(!isFim(id)){
        
        struct Character character = getCharacter(id, &fila);
        inserir(&fila, character);

        scanf("%s", id);
    }

    int count;
    scanf("%i", &count);

    char input[60];
    scanf(" %[^\n\r]", input);

    while(count > 0){
        escolheOpcao(&fila, input);

        count--;

        scanf(" %[^\n\r]", input);
    }

    printFila(&fila);

    return 0;
}// main()