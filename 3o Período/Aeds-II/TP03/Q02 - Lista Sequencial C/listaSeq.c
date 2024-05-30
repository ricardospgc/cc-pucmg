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

/********* Lista *********/
struct ListaSeq {
    char filePath[100];

    struct Character characterList[MAX_CHARACTERS];
    int size;
}; // struct ListaSeq

/**
 * Inicializa a lista
 * @param struct Lista *lista ponteiro para o lista
 * @param int option opcao de inicializacao de path
*/
void newLista(struct ListaSeq *lista, int pathOption){
    lista->size = 0;

    if (pathOption == 0)
        strcpy(lista->filePath, "/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/characters.csv");
    else if (pathOption == 1)
        strcpy(lista->filePath, "../../../../csv/characters.csv");
    else
        strcpy(lista->filePath, "/tmp/characters.csv");
}// newlista()

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
 * @param struct ListaSeq *lista Para acessar a filePath
*/
void getCharacterInfo(char* info, const char* id, struct ListaSeq *lista) {
    FILE *file = fopen(lista->filePath, "r");
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
 * @param struct ListaSeq 
*/
struct Character getCharacter(const char* id, struct ListaSeq *lista) {
    // encontrar a informacao do personagem
    char info[MAX_INFO_LENGTH] = {0};
    getCharacterInfo(info, id, lista);

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

/***************** LISTA SEQUENCIAL ******************/
/****** INSERCOES ******/

// Insere o elemento no inicio da lista
void inserirInicio(struct ListaSeq *lista, struct Character c){
    // Verifica se eh possivel inserir
    if(lista->size >= sizeof(*lista)) {
        printf("Erro ao inserir! Lista cheia!");
    } else {
        // Shift dos elementos para a direita, liberando espaco no inicio
        for(int i = lista->size; i > 0; i--){
            lista->characterList[i] = lista->characterList[i - 1];
        }

        // Insere o elemento na primeira posicao da lista
        lista->characterList[0] = c;
        // atualiza o total
        lista->size++;
    }
}// inserirInicio()

// Insere o elemento no fim da lista
void inserirFim(struct ListaSeq *lista, struct Character c){
    // Verifica se eh possivel inserir
    if(lista->size >= sizeof(*lista)){
        printf("Erro ao inserir! Lista cheia!");
    } else {
        lista->characterList[lista->size] = c;
        lista->size++;
    }
}// inserirFim()

// Insere o elemento na posicao parametrizada
void inserirPos(struct ListaSeq *lista, struct Character c, int pos){
    // Verifica se eh possivel inserir
    if(lista->size >= sizeof(*lista)) printf("Erro ao inserir! Lista cheia!");
    else if(pos > lista->size || pos < 0) printf("Erro ao inserir! Posicao invalida!");
    else {
        // Shift dos elementos para a direita, ate a posicao, liberando espaco
        for(int i = lista->size; i > pos; i--){
            lista->characterList[i] = lista->characterList[i - 1];
        }

        lista->characterList[pos] = c;
        lista->size++;
    }
}// inserirPos()

/****** REMOCOES ******/

// Remove o primeiro elemento da lista
struct Character removerInicio(struct ListaSeq *lista){
    // Verifica se e' possivel remover
    if(lista->size == 0){
        printf("Erro ao remover! Lista vazia!");
        exit(1);
    } else {
        struct Character removido = lista->characterList[0];
        lista->size--;

        // Shift para esquerda, ocupando o espaco do removido
        for(int i = 0; i < lista->size; i++){
            lista->characterList[i] = lista->characterList[i + 1];
        }

        return removido;
    }
}// removerInicio()

// Remove o ultimo elemento da lista
struct Character removerFim(struct ListaSeq *lista){
    // Verifica se e' possivel remover
    if(lista->size == 0) {
        printf("Erro ao remover! Lista vazia!");
        exit(1);
    } else {
        return lista->characterList[--lista->size];
    }
}// removerFim()

// Remove o elemento na posicao indicada
struct Character removerPos(struct ListaSeq *lista, int pos){
     // Verifica se eh possivel inserir
    if (lista->size >= sizeof(*lista)) {
        printf("Erro ao inserir! Lista cheia!");
        exit(1);
    } else if(pos > lista->size || pos < 0) {
        printf("Erro ao inserir! Posicao invalida!");
        exit(1);
    } else {
        struct Character removido = lista->characterList[pos];
        lista->size--;

        // Shift para esquerda, a parir de pos, ocupando o espaco do removido
        for(int i = pos; i < lista->size; i++){
            lista->characterList[i] = lista->characterList[i + 1];
        }

        return removido;
    }
}// removerPos()

/***********************/

void escolheOpcao(struct ListaSeq *lista, char* input){
    char inputOriginal[60] = {0};
    strcpy(inputOriginal, input);

    const char delim[2] = " ";
    char* token;

    char acao[3] = {0};
    token = strtok(input, delim);
    strcpy(acao, token);


    char pt2[40] = {0};
    if (strlen(inputOriginal) == 5 || strlen(inputOriginal) == 39 || strlen(inputOriginal) == 42) {
        token = strtok(NULL, delim);
        strcpy(pt2, token);
    }

    char pt3[40] = {0};

    struct Character c;

    if(strcmp(acao,"II") == 0) {
        c = getCharacter(pt2, lista);
        inserirInicio(lista, c);

    } else if (strcmp(acao, "IF") == 0) {
        c = getCharacter(pt2, lista);
        inserirFim(lista, c);
        
    } else if (strcmp(acao, "I*") == 0) {
        token = strtok(NULL, delim);
        strcpy(pt3, token);

        c = getCharacter(pt3, lista);
        inserirPos(lista, c, atoi(pt2));

    } else if (strcmp(acao, "RI") == 0) {
        c = removerInicio(lista);
        printf("(R) %s\n", c.name);

    } else if (strcmp(acao, "RF") == 0) {
        c = removerFim(lista);
        printf("(R) %s\n", c.name);

    } else if (strcmp(acao, "R*") == 0) {
        c = removerPos(lista, atoi(pt2));
        printf("(R) %s\n", c.name);

    } else
        printf("Erro ao manipular a lista!\n");
}

// printa todos os personagens da lista
void printLista(struct ListaSeq *lista){
    for(int i = 0; i < lista->size; i++){
        printf("[%i ## ", i);
        printCharacter(lista->characterList[i]);
        printf("]\n");
    }
}// printaLista()

int main(){
    struct ListaSeq lista;
    newLista(&lista, 2); // 0 = linux / 1 = Windows / 2 = VERDE

    char id[50] = {0};
    scanf("%s", id);

    while(!isFim(id)){
        struct Character character = getCharacter(id, &lista);
        inserirFim(&lista, character);

        scanf("%s", id);
    }

    int count;
    scanf("%i", &count);

    char input[60];
    scanf(" %[^\n\r]", input);

    while(count > 0){
        escolheOpcao(&lista, input);

        count--;

        scanf(" %[^\n\r]", input);
    }

    printLista(&lista);

    return 0;
}// main()