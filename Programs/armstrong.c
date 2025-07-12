#include <stdio.h>
#include <math.h>
#include <stdbool.h>

int getNumDigits(int n) {
    int copy = n;
    int digits = 0;
    while (copy) {
        digits += 1;
        copy /= 10;
    }
    return digits;
}

bool isArmstrong(int n) {
    // get number of digits in n
    int copy = n;
    int digits = getNumDigits(n);

    // access each digits and then do the operation and check sum
    int sum = 0;
    copy = n;
    while (copy) {
        sum += (int) pow(copy % 10, digits);
        copy /= 10;
    }
    return n == sum;    
}

int main() {
    int n = 153;
    bool a = isArmstrong(n);
    printf("%d is a armstrong number: %s\n", n, a ? "true": "false");
    return 0;
}