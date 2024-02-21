#include <stdlib.h>
#include <stdio.h>
#include <stdbool.h>
#include <string.h>

#define MAXTAM 100

bool ehFim(char input[]){
    bool resp = false;
    if(input[0] == 'F' && input[1] == 'I' && input[2] == 'M') 
        resp = true;
    return resp;
}//fim ehFim()

bool ehPalindromo(char input[]) {
    int tam = strlen(input) - 1;
    bool resp = true;

    for (int i = 0; i < tam / 2; i++) {
        if (input[i] != input[tam - 1 - i])
            resp = false;
    }

    return resp;
}

int main(){
    char input[MAXTAM] = {0};

    fgets(input, sizeof(input), stdin);

    while(!ehFim(input)){
        bool resp = ehPalindromo(input); 
        if(resp) printf("SIM\n");
        else printf("NAO\n");

        fgets(input, sizeof(input), stdin);
    }
    return 0;
}//fim main