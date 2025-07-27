#include <stdio.h>
#include <math.h>

float newtonianSquareRoot(float n, float guess, float epsilon) {
    if (fabs(guess * guess - n) < epsilon) {
        return guess;
    } else {
        float new_guess = (guess + n / guess) / 2;
        return newtonianSquareRoot(n, new_guess, epsilon);
    }
}

float binarySquareRoot(float n, float epsilon) {
    float low = 0, high = n, mid;

    // Handle numbers less than 1
    if (n < 1.0 && n > 0.0) {
        high = 1.0;
    }

    while ((high - low) > epsilon) {
        mid = (low + high) / 2;
        if (mid * mid > n) {
            high = mid;
        } else {
            low = mid;
        }
    }

    return (low + high) / 2;
}

void printSquareRoot(int n) {
    float epsilon = 0.00001;
    if (n < 0) {
        float result = newtonianSquareRoot(-n, 1.0, epsilon);
        printf("%.9fi\n", result);  // Imaginary number
    } else {
        float result = newtonianSquareRoot(n, 1.0, epsilon);
        printf("%.9f\n", result);
    }
}

int main() {
    printSquareRoot(25);   // Example
    printSquareRoot(-9);   // Imaginary
    printSquareRoot(2);    // Approximate sqrt
    return 0;
}
