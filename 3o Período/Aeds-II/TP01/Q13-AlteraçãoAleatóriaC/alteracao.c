#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAXTAM 5000

bool ehFim(char []);
char* alteracao(char []);
char* alteracaoRec(char [] ,int );

/**
 *  Funcao que verifica se a entrada 'e igual a "FIM"
*/
bool ehFim(char input[]){
    bool resp = false;
    if(input[0] == 'F' && input[1] == 'I' && input[2] == 'M') 
        resp = true;
    return resp;
}//fim ehFim()

/**
 * Chama a funcao recursiva que analisa se e' palindromo
*/
char* alteracao(char input[MAXTAM]) {
    return alteracaoRec(input, 0);
}// fim alteracao

/**
 * Retorna se a string de entrada e' um palindromo
*/
char* alteracaoRec(char input[MAXTAM], int index){
    char original = ('a' + rand() % 26);
    char nova = ('a' + rand() % 26);

    if(index < strlen(input)){
        if(input[index] == original){
            input[index] = nova;
        }
        alteracaoRec(input, index+1);
    }
    return input;
}//fim alteracaoRec()

int main(){
    char input[MAXTAM] = {0};
    srand(4);

    fgets(input, sizeof(input), stdin);

    while(!ehFim(input)){
        input[strlen(input) - 1] = '\0'; // retira o '\n' do fim

        printf("%s\n", alteracao(input)); 

        fgets(input, sizeof(input), stdin);
    }
    return 0;
}//fim main