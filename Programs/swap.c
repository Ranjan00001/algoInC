#include <stdio.h>
#define entry main  // renames main method to entry

int swap(int * a, int * b) {
    *b = *b + *a;
    *a = *b - *a;
    *b = *b - *a;
    return 0;
}

int entry() {
    int a, b;
    a = 12; b = 14;
    printf("Before swapping a and b are %d, %d\n", a, b);
    swap(&a, &b);
    printf("After swapping a and b are %d, %d\n", a, b);
    return 0;
}