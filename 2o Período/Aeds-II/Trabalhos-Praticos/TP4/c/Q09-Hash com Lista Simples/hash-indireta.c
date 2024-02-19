#include <stdio.h>
#include <stdlib.h>
#include <err.h>

// TIPO CELULA ===================================================================
typedef struct Celula {
	int elemento;        // Elemento inserido na celula.
	struct Celula* prox; // Aponta a celula prox.
} Celula;

Celula* novaCelula(int elemento) {
   Celula* nova = (Celula*) malloc(sizeof(Celula));
   nova->elemento = elemento;
   nova->prox = NULL;
   return nova;
}

// LISTA PROPRIAMENTE DITA =======================================================
Celula* primeiro;
Celula* ultimo;


/**
 * Cria uma lista sem elementos (somente no cabeca).
 */
void startLista () {
   primeiro = novaCelula(-1);
   ultimo = primeiro;
}

/**
 * Insere um elemento na ultima posicao da lista.
 * @param x int elemento a ser inserido.
 */
void inserirLista(int x) {
   ultimo->prox = novaCelula(x);
   ultimo = ultimo->prox;
}

/**
 * Mostra os elementos da lista separados por espacos.
 */
void mostrarLista() {
   printf("[ ");
   Celula* i;
   for (i = primeiro->prox; i != NULL; i = i->prox) {
      printf("%d ", i->elemento);
   }
   printf("] \n");
}

// HASH INDIRETA ================================================================

typedef struct HashIndireta {
    Celula tabela[25];
    

} HashIndireta;


int main(int argc, char** argv) {

    startLista();

    inserirLista(3);
    inserirLista(5);
    inserirLista(9);
    inserirLista(4);
    inserirLista(8);
    inserirLista(7);

    mostrarLista();

   return 0;
}