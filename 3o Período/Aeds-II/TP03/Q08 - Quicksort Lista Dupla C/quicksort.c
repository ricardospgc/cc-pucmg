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

typedef struct CelulaDupla{
    struct Character character;

    struct CelulaDupla* prox;
    struct CelulaDupla* ant;
} CelulaDupla;

CelulaDupla* newCelula(struct Character character){
    CelulaDupla* resp = (CelulaDupla*) malloc(sizeof(CelulaDupla));
    resp->character = character;
    resp->prox = NULL; resp->ant = NULL;
    return resp;
}

/********* lista *********/

struct ListaDupla {
    char filePath[100];

    CelulaDupla* primeiro;
    CelulaDupla* ultimo;

    int size;
}; // struct ListaDupla

/**
 * Inicializa a lista
 * @param struct Lista *lista ponteiro para o lista
 * @param int option opcao de inicializacao de path
*/
void newlista(struct ListaDupla *lista, int pathOption){
    lista->primeiro = lista->ultimo = NULL;
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
 * @param struct ListaDupla *lista Para acessar a filePath
*/
void getCharacterInfo(char* info, const char* id, struct ListaDupla *lista) {
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
 * @param struct ListaDupla 
*/
struct Character getCharacter(const char* id, struct ListaDupla *lista) {
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

/***************** lista ******************/

// Insere o elemento na lista
void inserir(struct ListaDupla *lista, struct Character newCharacter){
    if(lista->size < MAX_CHARACTERS){
        CelulaDupla* novaCelula = newCelula(newCharacter);

        if(lista->size == 0) {
            lista->primeiro = lista->ultimo = novaCelula;
        } else {
            lista->ultimo->prox = novaCelula;
            novaCelula->ant = lista->ultimo;
            lista->ultimo = novaCelula;
        }
    }
        lista->size++;

}// inserir()

// printa todos os personagens da lista
void printLista(struct ListaDupla *lista){
    CelulaDupla* i;

    for (i = lista->primeiro; i != NULL; i = i->prox) {
        printf("[");
        printCharacter(i->character);
        printf("]\n");
    }
}// printalista()

/*********************/

void swap(CelulaDupla *i, CelulaDupla *j){
    struct Character aux = i->character;
    i->character = j->character;
    j->character = aux;
    numMov += 3;
}// swap()

int cmpCharacter(struct Character a, struct Character b){
    int cmp = strcmp(a.house, b.house);
    numComp++;

    // desempata por nome caso House for igual
    if(cmp == 0){
        cmp = strcmp(a.name, b.name);
        numComp++;
    }
    
    return cmp;
}// cmpCharacter()

bool isLessOrEqualTo(CelulaDupla* i, CelulaDupla* j){
    bool resp = false;

    if(i == j) resp = true;

    while(resp == false && i != NULL){
        i = i->prox;
        if(i == j) resp = true;
    }
    return resp;
}// isLessOrEqualTo()

// quicksort por house
void quicksort(struct ListaDupla* lista, CelulaDupla* esq, CelulaDupla* dir){
    if(esq != dir && esq != dir->prox){
        CelulaDupla* i = esq, *j = dir;
        CelulaDupla* pivo = esq;

        while(isLessOrEqualTo(i, j)){
            while(cmpCharacter(i->character, pivo->character) < 0) i = i->prox;
            while(cmpCharacter(j->character, pivo->character) > 0) j = j->ant;

            if(isLessOrEqualTo(i, j)){
                swap(i, j);
                i = i->prox;
                j = j->ant;
            }
        }
        quicksort(lista, esq, j);
        quicksort(lista, i, dir);
    }
}// quicksort()


// Controla o fluxo de chamada da Selection Sort Recurssiva
void sort(struct ListaDupla* lista){
    clock_t start = clock();

    quicksort(lista, lista->primeiro->prox, lista->ultimo);

    clock_t end = clock();
    tempoTotal += ((double) end - start) / CLOCKS_PER_SEC;
}// sort()

// cria um arquivo de log com as info necessarias
void criarLog(){
    FILE *arq = fopen("803833_quicksort2.txt", "w");
    if (arq == NULL) {
        perror("Erro ao abrir o arquivo.");
        exit(EXIT_FAILURE); 
    }

    fprintf(arq, "803833\t%.4f ms\t Comp: %i\t Mov: %i", tempoTotal*1000, numComp, numMov);

    fclose(arq);
}// criaLog()

int main(){
    struct ListaDupla lista;
    newlista(&lista, 2); // 0 = linux / 1 = Windows / 2 = VERDE
    
    char id[50] = {0};
    scanf("%s", id);

    while(!isFim(id)){
        
        struct Character character = getCharacter(id, &lista);
        inserir(&lista, character);

        scanf("%s", id);
    }

    sort(&lista);
    printLista(&lista);
    criarLog();

    return 0;
}// main()