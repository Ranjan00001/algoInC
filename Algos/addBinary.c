#include <stdio.h>
#include <stdlib.h>
#include "../header/printArray.h"

int* binaryAdder(const int a[], const int b[], const int size) {
    int* c = (int*) malloc(sizeof(int) * (size + 1));
    int i = size - 1;
    int carry = 0;
    while (i >= 0) {
        c[i+1] = (a[i] ^ b[i]) ^ carry;
        carry = (a[i] & b[i]) | (a[i] & carry) | (b[i] & carry);
        i--;
    }
    c[0] = carry;
    return c;
}

int main() {
    const int a[] = {1, 0, 1, 0, 1};
    const int b[] = {0, 1, 0, 0, 1};
    printArray(binaryAdder(a, b, 5), 6);
}