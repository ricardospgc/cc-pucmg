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
static int sizeLista = 0;
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
void inserirInicio(Jogador lista[], Jogador j);
void inserirFim(Jogador lista[], Jogador j);
void inserirPos(Jogador lista[], Jogador j, int pos);
char* removerInicio(Jogador lista[]);
char* removerFim(Jogador lista[]);
char* removerPos(Jogador lista[], int pos);
void mostraJogador(Jogador j, int i);
void mostraLista(Jogador lista[]);
bool idExiste(Jogador lista[], int idChave);
void decodifica(Jogador lista[], char* comando);
void manipulaLista(Jogador* lista);

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

    //char path[] = "players.csv";
    char path[] = "/tmp/players.csv";
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
void addJogadores(Jogador jogadores[]){
    char strId[5] = {0};
    fgets(strId,sizeof(strId),stdin); // lê o id

    bool isFim = false;
    if(strcmp(strId, "FIM\n") == 0) isFim = true;

    while(!isFim){
        int id = atoi(strId);
        if(id > 0 && id <=3921){ // Verifica se o Id é válido
            Jogador j = startJogador(); // Cria o jogador j
            salvaPorId(&j, id);
            inserirFim(jogadores, j);
        }

        fflush(stdin);
        fgets(strId,sizeof(strId),stdin);
        if(strcmp(strId, "FIM\n") == 0) isFim = true;
    }
}//fim addJogadores();

/***********************************************************************/
/********************** - Lista Estática - ************************/

/**
* Insere um elemento na primeira posicao da lista e move os demais
* elementos para o fim da lista.
* @param Jogador lista[]
* @param Jogador j - elemento a ser inserido.
*/
void inserirInicio(Jogador lista[], Jogador j) {

    // validar insercao
    if (sizeLista >= maxJogadores) {
        perror("Erro ao inserir inicio!");
    }

    // levar elementos para o fim do array
    for (int i = sizeLista; i > 0; i--) {
        lista[i] = clone(lista[i - 1]);
    }

    lista[0] = clone(j);
    sizeLista++;
} // fim inserirInicio()

/**
* Insere um elemento na ultima posicao da lista.
* @param Jogador lista[]
* @param Jogador j - elemento a ser inserido.
*/
void inserirFim(Jogador lista[], Jogador j) {
    // validar insercao
    if (sizeLista > maxJogadores) {
        perror("Erro ao inserir fim!");
    }
    lista[sizeLista] = clone(j);
    sizeLista++;
} // fim inserirFim()

/**
* Insere um elemento em uma posicao especifica e move os demais
* elementos para o fim da lista.
* @param Jogador lista[]
* @param Jogador j - elemento a ser inserido.
* @param int     pos - Posicao de insercao.
*/
void inserirPos(Jogador lista[], Jogador j, int pos) {
    // validar insercao
    if (sizeLista >= maxJogadores || pos < 0 || pos > sizeLista) {
        perror("Erro ao inserir pos!");
    }

    // levar elementos para o fim do array
    for (int i = sizeLista; i > pos; i--) {
        lista[i] = clone(lista[i - 1]);
    }

    lista[pos] = clone(j);
    sizeLista++;
}// fim inserirPos()

/* *************** Remocoes *************** */

/**
* Remove um elemento da primeira posicao da lista e movimenta
* os demais elementos para o inicio da mesma.
* @param Jogador lista[]
* @return resp Jogador elemento a ser removido.
*/
char* removerInicio(Jogador lista[]) {

    // validar remocao
    if (sizeLista == 0) {
        perror("Erro ao remover inicio!");
    }

    strcpy(resp, lista[0].nome);
    sizeLista--;

    for (int i = 0; i < sizeLista; i++) {
        lista[i] = clone(lista[i + 1]);
    }

    return resp;
} // fim removerInicio()

/**
* Remove um elemento da ultima posicao da lista.
* @param Jogador lista[]
* @return resp Jogador elemento a ser removido.
*/
char* removerFim(Jogador lista[]) {

    // validar remocao
    if (sizeLista == 0) {
        perror("Erro ao remover fim!");
    }

    return lista[--sizeLista].nome;
}// fim removerFim()

/**
* Remove um elemento de uma posicao especifica da lista e
* movimenta os demais elementos para o inicio da mesma.
* @param lista[] 
* @param pos Posicao de remocao.
* @return resp Jogador elemento a ser removido.
*/
char* removerPos(Jogador lista[], int pos) {

    // validar remocao
    if (sizeLista == 0 || pos < 0 || pos >= sizeLista) {
        perror("Erro ao remover pos!");
    }

    strcpy(resp, lista[pos].nome);
    sizeLista--;

    for (int i = pos; i < sizeLista; i++) {
        lista[i] = clone(lista[i + 1]);
    }

    return resp;
}// fim removerPos()

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
* @param Jogador lista[]
*/
void mostraLista(Jogador lista[]) { 
    for(int i = 0; i < sizeLista; i++){
        mostraJogador(lista[i], i);
    }
}// fim mostraLista()


/**
* Procura um elemento pelo id e retorna se ele existe.
* @param Jogador lista[]
* @param int idChave - id do elemento a ser pesquisado.
* @return true se o array existir, false em caso contrario.
*/
bool idExiste(Jogador lista[], int idChave) {
    bool resp = false;
    for (int i = 0; i < sizeLista && resp == false; i++) {
        resp = (lista[i].id == idChave);
    }
    return resp;
}// fim idExiste()

/**
* Realiza diferentes ações à lista baseado no parametro
* @param String comando[] - String com a acao desejada
* @param int primNum - id ou, se segNum != null, posicao
* @param int segNum - se != null, posicao
*/
void decodifica(Jogador lista[], char* comando) {
    Jogador j;
    char* removido[50] = {0};

    int len = strlen(comando);
    if ((len > 0) && (comando[len-1] == '\n')) comando[len-1] = '\0';
    char *token = strtok(comando," "); // token = comando

    if(token!=NULL){
        if (strcmp(token,"II") == 0){
            token = strtok(NULL, " "); // token = id
            salvaPorId(&j, atoi(token));
            inserirInicio(lista,j);
        }
        else if(strcmp(token, "IF") == 0){
            token = strtok(NULL, " "); // token = id
            salvaPorId(&j, atoi(token));
            inserirFim(lista,j);
        }
        else if(strcmp(token,"I*") == 0){
            token = strtok(NULL, " "); // token = posicao
            int posicao = atoi(token);
            token = strtok(NULL, " "); // token = id
            salvaPorId(&j, atoi(token));
            inserirPos(lista, j, posicao);
        }
        else if(strcmp(token,"RI") == 0){
            printf("(R) %s\n", removerInicio(lista));
        }
        else if(strcmp(comando, "RF") == 0){
            printf("(R) %s\n", removerFim(lista));
        } 
        else if(strcmp(comando,"R*") == 0){
            token = strtok(NULL, " "); // token = pos
            int posicao = atoi(token);
            printf("(R) %s\n", removerPos(lista,posicao));
        }

    }

}// fim decodifica

/**
* Recebe quantos comandos e organiza a suas execuções
* @param Jogador lista[]
*/
void manipulaLista(Jogador lista[]) {
    int count;
    scanf("%d", &count);
    limpaBuffer();

    char comando[15] = {0};

    for(count; count > 0; count--){
        fgets(comando, sizeof(comando), stdin);
        decodifica(lista, comando);
    }

}// fim manipulaLista()


/********************************************************************/
/*************************** - Main - ******************************/

int main() {
    Jogador lista[maxJogadores];


    addJogadores(lista);
    manipulaLista(lista);
    mostraLista(lista);

    return 0;
}// fim main()