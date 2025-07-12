#include <stdio.h>
#include <stdbool.h>
#include <math.h>

bool isPrime(int n) {
    if (n < 2) return false;
    int squareRoot = (int) sqrt(n);
    for (int i = 2; i < squareRoot; i ++) {
        if (n % i == 0) return false;
    }
    return true;
}

int main() {
    int n = 3;
    bool a = isPrime(n);
    printf("%d is a prime: %s\n", n, a ? "true" : "false");
    return 0;
}

// Note that here we need to link the math library by saying it in command during compilation
// We need to run it with  `-lm` 