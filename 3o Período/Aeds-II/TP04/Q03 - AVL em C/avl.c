#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <ctype.h>
#include <time.h>

#define MAX_CHARACTERS 420
#define MAX_INFO_LENGTH 400
#define TOTAL_ATRIBUTOS 18

int numComp = 0;
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
}// printCharacter()

/********* Arvore AVL *********/

typedef struct No {
    struct Character elemento;

    struct No *esq, *dir;
    int nivel;
} No;

struct No* newNo(struct Character newCharacter){
    No *newNo = (No *)malloc(sizeof(No));
    newNo->elemento = newCharacter;
    newNo->dir = NULL;
    newNo->esq = NULL;
    newNo->nivel = 1;

    return newNo;
}// newNo()

struct Arvore {
    No* raiz;

    char filePath[100];
}; // struct Arvore

/**
 * Inicializa a arvore
 * @param struct arvore *arvore ponteiro para o arvore
 * @param int option opcao de inicializacao de path
*/
void newArvore(struct Arvore *arvore, int pathOption){
    if (pathOption == 0)
        strcpy(arvore->filePath, "/home/ricardo/Documents/cc-pucmg/3o Período/Aeds-II/characters.csv");
    else if (pathOption == 1)
        strcpy(arvore->filePath, "../../../../csv/characters.csv");
    else
        strcpy(arvore->filePath, "/tmp/characters.csv");

    arvore->raiz = NULL;
}// newarvore()

/**
 * Retorna se a string e' igual a "FIM"
 * @return bool true se for "FIM", false se nao
*/
bool isFim(char* id){
    return strcmp(id, "FIM") == 0;
}// isFim()

/**
 * Encontra o ID no csv e armazena em uma string
 * @param char* info Local em que a string vai ser armazenada
 * @param char* id Id a ser encontrado
 * @param struct Arvore *arvore Para acessar a filePath
*/
void getCharacterInfo(char* info, const char* id, struct Arvore *arvore) {
    FILE *file = fopen(arvore->filePath, "r");
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
 * Adiciona o personagem na arvore
 * @param char* id Id a ser adicionado
 * @param struct Arvore 
*/
struct Character getCharacter(const char* id, struct Arvore *arvore) {
    // encontrar a informacao do personagem
    char info[MAX_INFO_LENGTH] = {0};
    getCharacterInfo(info, id, arvore);

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

/***************** ARVORE ******************/
// Retorna o maior entre os dois parametrizados
int maiorInt(int a, int b) {
    return (a > b) ? a : b;
}// maiorInt()

// Retorna o nivel do No
int getNivel(No *no){
    return (no == NULL) ? 0 : no->nivel;
}// getNivel()

// Retorna o fator de balanceamento de um no
int getFator(No *no) {
    return (no == NULL) ? 0 : (getNivel(no->dir) - getNivel(no->esq));
}// getFator

// rotaciona o No para direita
No* rotacionaDir(No* no){
    No *noEsq = no->esq;
    No *noEsqDir = noEsq->dir;

    noEsq->dir = no;
    no->esq = noEsqDir;

    // atualiza os niveis
    no->nivel = maiorInt(getNivel(no->esq), getNivel(no->dir)) + 1;
    noEsq->nivel = maiorInt(getNivel(noEsq->esq), getNivel(noEsq->dir)) + 1;

    return noEsq;
}// rotacionaDir()

// rotaciona o No para esquerda
No* rotacionaEsq(No* no){
    No* noDir = no->dir;
    No* noDirEsq = noDir->esq;

    noDir->esq = no;
    no->dir = noDirEsq;
    
    // atualiza os niveis
    no->nivel = maiorInt(getNivel(no->esq), getNivel(no->dir)) + 1;
    noDir->nivel = maiorInt(getNivel(noDir->esq), getNivel(noDir->dir)) + 1;

    return noDir;
}// rotacionaEsq()

// Balanceia a arvore
No *balancear(No *no) {
    no->nivel = 1 + maiorInt(getNivel(no->esq), getNivel(no->dir));
    int fator = getFator(no);
    if (fator > 1){
        if (getFator(no->dir) < 0){
            no->dir = rotacionaDir(no->dir);
        }
        return rotacionaEsq(no);
    }
    if (fator < -1){
        if (getFator(no->esq) > 0){
            no->esq = rotacionaEsq(no->esq);
        }
        return rotacionaDir(no);
    }
    return no;
}// balancear()

// Insere um elemento na arvore
No* inserirRec(struct Character elemento, No* i) {
    if (i == NULL){
        return newNo(elemento);
    }
    int cmp = strcmp(elemento.name, i->elemento.name);
    if (cmp < 0){
        i->esq = inserirRec(elemento, i->esq);
    }
    else if (cmp > 0) {
        i->dir = inserirRec(elemento, i->dir);
    }
    else{
        // Atualiza os valores do Personagem existente
        i->elemento = elemento;
        return i;
    }
    balancear(i);
}// inserirRec()

// Chama a insercao recurssiva
void inserir(struct Arvore* arvore, struct Character elemento) {
    arvore->raiz = inserirRec(elemento, arvore->raiz);
}// inserir()

// Pesquisa se um elemento existe na arvore
bool pesquisarRec(No *no, char *key) {
    if (no == NULL){
        numComp++;
        return false;
    }

    int cmp = strcmp(key, no->elemento.name);
    numComp++; // Incrementa o contador de comparações

    if (cmp == 0) {
        numComp++;
        return true;

    } else if (cmp > 0) {
        numComp++;
        printf(" dir");
        return pesquisarRec(no->dir, key);

    } else {
        printf(" esq");
        return pesquisarRec(no->esq, key);
    }
}// pesquisarRec

// Chama a pesquisa recurssiva na arvore
void pesquisar(struct Arvore* arvore, char *key) {
    printf("%s => raiz", key);

    clock_t start = clock();

    bool resp = pesquisarRec(arvore->raiz, key);

    clock_t end = clock();
    tempoTotal += ((double) end - start) / CLOCKS_PER_SEC;

    if(resp) printf(" SIM\n");
    else printf(" NAO\n");
}// pesquisar()


// cria um arquivo de log com as info necessarias
void criarLog(){
    FILE *arq = fopen("803833_avl.txt", "w");
    if (arq == NULL) {
        perror("Erro ao abrir o arquivo.");
        exit(EXIT_FAILURE); 
    }

    fprintf(arq, "803833\t%.4f ms\t%i", tempoTotal*1000, numComp);

    fclose(arq);
}// criaLog()

/******************************************/
int main(){
    struct Arvore arvore;
    newArvore(&arvore, 2); // 0 = linux / 1 = Windows / 2 = VERDE

    char id[50] = {0};
    scanf("%s", id);

    while(!isFim(id)){
        struct Character character = getCharacter(id, &arvore);
        inserir(&arvore, character);

        scanf("%s", id);
    }


    char input[60];
    scanf(" %[^\n\r]", input);

    while(!isFim(input)){
        pesquisar(&arvore, input);
        scanf(" %[^\n\r]", input);
    }

    return 0;
}// main()