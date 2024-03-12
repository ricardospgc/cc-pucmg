#include <stdlib.h>
#include <stdio.h>

void printaReverso(FILE *arq, int i){
    char buf[1000];
    
    int linha = 0;
    fseek(arq, 0, SEEK_SET);  //Garante que a leitura seja no inicio do arquivo
    while(fgets(buf, sizeof(buf), arq) != NULL) { // enquanto nao for o fim do arquivo
	    linha++;
	    if (i == linha) printf("%s", buf);		
	}

}//fim printaReverso

int main (){
    FILE *arq = fopen("valores.txt","w");
    if (arq == NULL) {
        perror("Erro ao abrir o arquivo");
        exit(EXIT_FAILURE);
    }

    int n;
    scanf("%i",&n);

    float valor;
    for(int i = 0; i < n; i++){
        scanf("%f", &valor);
        fprintf(arq, "%g\n", valor);
    }

    fclose(arq);
    // fim da impressao de valores no arquivo

    arq = fopen("valores.txt", "r");
    if (arq == NULL) {
        perror("Erro ao abrir o arquivo");
        exit(EXIT_FAILURE);
    }   

    for(int i = n; i > 0; i--){
        printaReverso(arq, i);
    }
    fclose(arq);


}// fim main