/*
* Nome: Ricardo Soares Cerqueira
* Matricula: 803833
* Trabalho Prático 3 - Aeds II - Q02
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

#define maxJogadores 4000
#define MAXTAM 5
int primeiro = 0, ultimo = 0;
char resp[20] = {0};

void limpaBuffer(){
    int c;
    while ((c = getchar()) != '\n' && c != EOF);
}


/**********************************************************************/
/********************** - Definição Jogador - ************************/

// Struct Jogador
typedef struct {
    int id, altura, peso, anoNascimento;
    char nome[100], universidade[100], cidadeNascimento[100], estadoNascimento[100];
} Jogador;

Jogador startjogador();
Jogador clone(Jogador j);
void removeCharsEspeciais(char *str);
void trataVirgulaDupla(char* original, char* nova);
void setJogador(char *linha, Jogador *j);
void salvaPorId(Jogador *pJog, int idChave);
void addJogadores(Jogador jogadores[]);
void inserir(Jogador fila[], Jogador j);
char* remover(Jogador fila[]);
void mostraJogador(Jogador j, int i);
void mostraFila(Jogador fila[]);
bool idExiste(Jogador fila[], int idChave);
void decodifica(Jogador fila[], char* comando);
void manipulaFila(Jogador* fila);
int mediaAlturaFila(Jogador fila[]);

/**
* Inicializa o jogador com valores placeholders
* @returns Jogador *j
*/
Jogador startJogador() {
    Jogador j;
    j.id = -1;
    strcpy(j.nome, "nao informado");
    j.altura = -1;
    j.peso = -1;
    strcpy(j.universidade, "nao informado");
    j.anoNascimento = -1;
    strcpy(j.cidadeNascimento, "nao informado");
    strcpy(j.estadoNascimento, "nao informado");

    return j;
} // fim startJogador

/**
* Retorna um clone do jogador parametrizado
* @param Jogador j
* @returns Jogador clone
*/
Jogador clone(Jogador j){
    Jogador clone = startJogador();

    clone.id = j.id;
    strcpy(clone.nome, j.nome);
    clone.altura = j.altura;
    clone.peso = j.peso;
    strcpy(clone.universidade, j.universidade);
    clone.anoNascimento = j.anoNascimento;
    strcpy(clone.cidadeNascimento, j.cidadeNascimento);
    strcpy(clone.estadoNascimento, j.estadoNascimento);

    return clone;
}// fim clone

/***********************************************************************/
/********************** - Armazenar Jogadores - ***********************/

/**
* Remove caracteres especiais (' " ', ',', '\n') da string
* @param char *str
*/
void removeCharsEspeciais(char *str) {
    int i, j = 0;
    char tmp[strlen(str) + 1];

    for(i = 0; str[i] != '\0'; i++) {
        if(str[i] != '"' && str[i] != ',' && str[i] != '\n') {
            tmp[j++] = str[i];
        }
    }

    tmp[j] = '\0';
    strcpy(str, tmp);
} // fim removeCharEspeciais()

/**
* Detecta quando tem vírguals consecutivas e coloca ",nao informado," no lugar
* @param char* original, char* nova
*/
void trataVirgulaDupla(char* original, char* nova) {
    char* i = original;
    char* j = nova;

    while (*i != '\0') { // passa caractere por caractere
        *j = *i;

        if (*i == ',' && *(i + 1) == ',') { // se encontrar ,, substitui
            j++;
            *j++ = 'n';
            *j++ = 'a';
            *j++ = 'o';
            *j++ = ' ';
            *j++ = 'i';
            *j++ = 'n';
            *j++ = 'f';
            *j++ = 'o';
            *j++ = 'r';
            *j++ = 'm';
            *j++ = 'a';
            *j++ = 'd';
            *j++ = 'o';
            *j = ',';
            i++;
        }
        i++;
        j++;
    }
    *j = '\0';
}// trataVirgulaDupla

/**
* Preenche o jogador parametrizado com os valores do csv
* @param char* linha, Jogador *j
*/
void setJogador(char *linha, Jogador *j) {
    char *token = strtok(linha, ","); // Troca a primeira vírgula por null (\0)
    int count = 0;

    while (token != NULL) { // Cada iteração preenche um atributo, aumentando o counter e passando pro próximo case
        removeCharsEspeciais(token);

        if(strcmp(token,"") == 0){
            token = strtok(NULL,",");
            count++;

        } else {
            switch (count) 
            {
            case 0:
                j->id = atoi(token);
                break;
            case 1:
                strcpy(j->nome, token);
                break;
            case 2:
                j->altura = atoi(token);
                break;
            case 3:
                j->peso = atoi(token);
                break;
            case 4:
                strcpy(j->universidade, token);
                break;
            case 5:
                j->anoNascimento = atoi(token);
                break;
            case 6:
                strcpy(j->cidadeNascimento, token);
                break;
            case 7:
                strcpy(j->estadoNascimento, token);
                break;
            }

            token = strtok(NULL, ","); // atualiza o token
            count++; 
        }
    }
}// fim setJogador()

/**
* Procura o ID parametrizado no arquivo e chama a função para preencher o jogador
* @param Jogador *pJogador, int idChave
*/
void salvaPorId(Jogador *pJog, int idChave) {

    char path[] = "players.csv";
    //char path[] = "/tmp/players.csv";
    FILE *file = fopen(path, "r"); // Abre o csv
    if (file == NULL) {
        perror("Error opening file");
    }
   
    char linha[200] = {0};
    char linhaTratada[200] = {0};
    char tmp[200] = {0};
    
    char dif[2] = ","; //caractere a ser removido por strtok
    bool encontrou = false;
    bool ehHeader = true;

    while (fgets(linha, sizeof(linha), file ) && !encontrou) {
        if(ehHeader) ehHeader = false; // Pula a primeira linha do csv(header) na primeira iteração
        else{
            strcpy(tmp, linha); // copia a linha para uma variável temporária
            char *idAtual = strtok(tmp, dif); // isola o Id
            
            if(atoi(idAtual) == idChave) {
                trataVirgulaDupla(linha, linhaTratada);
                setJogador(linhaTratada, pJog);
                encontrou = true;
            }
        }
    }
    fclose(file);
}//fim salvaPorId();

/**
* Adiciona todos os jogadores do csv em um array de Jogadores
* @param Jogador jogadores[]
*/
void addJogadores(Jogador fila[]){
    char strId[5] = {0};
    fgets(strId,sizeof(strId),stdin); // lê o id

    bool isFim = false;
    if(strcmp(strId, "FIM\n") == 0) isFim = true;

    while(!isFim){
        int id = atoi(strId);
        if(id > 0 && id <=3921){ // Verifica se o Id é válido
            Jogador j = startJogador(); // Cria o jogador j
            salvaPorId(&j, id);
            inserir(fila, j);
        }

        fflush(stdin);
        fgets(strId,sizeof(strId),stdin);
        if(strcmp(strId, "FIM\n") == 0) isFim = true;
    }
}//fim addJogadores();

/***********************************************************************/
/********************** - Fila Circular - ************************/

/**
* Insere um elemento na primeira posicao da fila e move os demais
* elementos para o fim da fila.
* @param Jogador fila[]
* @param Jogador j - elemento a ser inserido.
*/
void inserir(Jogador fila[], Jogador j) {
    if (((ultimo + 1) % MAXTAM) == primeiro) printf("AAAAAAA\n");

    fila[ultimo] = clone(j);
    ultimo = (ultimo + 1) % MAXTAM;

} // fim inserirInicio()

/**
* Remove um elemento da ultima posicao da fila.
* @param Jogador fila[]
* @return resp Jogador elemento a ser removido.
*/
char* remover(Jogador fila[]) {
    if (primeiro == ultimo) exit(1);
    
    strcpy(resp, fila[primeiro].nome);
    primeiro = (primeiro + 1) % MAXTAM;

    return resp;
}// fim removerFim()

/* *************** Outros *************** */

/**
* Escreve um jogador com formatação correta
* @param Jogador j 
* @param int i - index
*/
void mostraJogador(Jogador j, int i) {
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", 
            i, j.nome, j.altura, j.peso,j.anoNascimento, 
            j.universidade, j.cidadeNascimento, j.estadoNascimento);
}// fim mostraJogador()

/**
* Escreve os jogadores que o usuário requisitar
* @param Jogador fila[]
*/
void mostraFila(Jogador fila[]) { 
    int i = primeiro;
    
    while (i != ultimo) {
        mostraJogador(fila[i], i);
        i = ((i + 1) % MAXTAM);
    }

}// fim mostraFila()

/**
* Procura um elemento pelo id e retorna se ele existe.
* @param Jogador fila[]
* @param int idChave - id do elemento a ser pesquisado.
* @return true se o array existir, false em caso contrario.
*/
bool idExiste(Jogador fila[], int idChave) {
    bool resp = false;
    for (int i = 0; i < MAXTAM && resp == false; i++) {
        resp = (fila[i].id == idChave);
    }
    return resp;
}// fim idExiste()

/**
* Realiza diferentes ações à fila baseado no parametro
* @param String comando[] - String com a acao desejada
* @param int primNum - id ou, se segNum != null, posicao
* @param int segNum - se != null, posicao
*/
void decodifica(Jogador fila[], char* comando) {
    Jogador j;
    char* removido[50] = {0};

    int len = strlen(comando);
    if ((len > 0) && (comando[len-1] == '\n')) comando[len-1] = '\0';
    char *token = strtok(comando," "); // token = comando

    if(token!=NULL){
        if (strcmp(token,"I") == 0){
            token = strtok(NULL, " "); // token = id
            salvaPorId(&j, atoi(token));
            inserir(fila,j);
            mostraFila(fila);
            printf("\np: %i / u: %i\n\n", primeiro, ultimo);
        }
        else if(strcmp(token,"R") == 0){
            printf("(R) %s\n", remover(fila));
            mostraFila(fila);
            printf("\n ------ \n p: %i / u: %i\n", primeiro, ultimo);
        }
        else printf("Erro ao decodificar!\n");
    }

}// fim decodifica

/**
* Recebe quantos comandos e organiza a suas execuções
* @param Jogador fila[]
*/
void manipulaFila(Jogador fila[]) {
    int count;
    scanf("%d", &count);
    limpaBuffer();

    char comando[15] = {0};

    for(count; count > 0; count--){
        fgets(comando, sizeof(comando), stdin);
        decodifica(fila, comando);
    }

}// fim manipulaFila()

/**
 * Calcula a media de altura dos elementos da fila
 * @param Jogador fila[]
 * @return int media arredondada
 * 
*/
int mediaAlturaFila(Jogador fila[]){
    int soma = 0;
    int i = primeiro;
    while (i != ultimo) {
        soma += fila[i].altura;
        i = ((i + 1) % MAXTAM);
    }

    return soma / ultimo;
}


/********************************************************************/
/*************************** - Main - ******************************/

int main() {
    Jogador fila[MAXTAM+1];

    addJogadores(fila);
    manipulaFila(fila);
    mostraFila(fila);

    return 0;
}// fim main()