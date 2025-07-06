
// This is the program for swapping two variable in C

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    int a, b, temp;
    if (argc < 3) {
        printf("Usage: ./programName num1 num2 /n");
        return 1;
    }
    a = atoi(argv[1]);
    b = atoi(argv[2]);
    temp = a;
    a = b;
    b = temp;
    printf("a is %d, b is %d /n", a, b);
    return 0;
}
