#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAXTAM 100

bool ehFim(char []);
bool ehPalindromo(char []);
bool ehPalindromoRec(char [], int );

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
bool ehPalindromo(char input[MAXTAM]) {
    return ehPalindromoRec(input, 0);
}// fim ehPalindromo

/**
 * Retorna se a string de entrada e' um palindromo
*/
bool ehPalindromoRec(char input[MAXTAM], int i){
    bool resp = true;

        if((i < strlen(input)/2) && (resp == true)){
            
            if(input[i] != input[strlen(input) - i - 1])
                resp = false;
            else 
                ehPalindromoRec(input, i+1);
        }
        
        return resp;
}//fim ehPalindromoRec()

int main(){
    char input[MAXTAM] = {0};

    fgets(input, sizeof(input), stdin);

    while(!ehFim(input)){

        input[strlen(input) - 1] = '\0'; // retira o '\n' do fim

        bool resp = ehPalindromo(input); 

        if(resp) printf("SIM\n");
        else printf("NAO\n");

        fgets(input, sizeof(input), stdin);
    }


    return 0;
}//fim main