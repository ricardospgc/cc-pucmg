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

int contaMaiuscula(char str[]){
    int resp = 0;

    for(int i = 0; i < strlen(str); i++){
        if(str[i] >= 'A' && str[i] <= 'Z') resp++;
    }

    return resp;
}//fim contaMaiuscula

int main(){
    char input[MAXTAM] = {0};

    fgets(input, sizeof(input), stdin);

    while(!ehFim(input)){
        printf("%i\n",contaMaiuscula(input));

        fgets(input, sizeof(input), stdin);
    }
    return 0;
}//fim main