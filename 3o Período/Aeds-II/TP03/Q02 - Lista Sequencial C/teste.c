#include <stdio.h>

int main(){
    int c = 30;
    char input[100];
    scanf(" %[^\n]", input);
    while(c > 0){
        printf(" %s", input);
        scanf(" %[^\n]", input);
        c--;
    }
}