
#include <stdio.h>

int main() {
    // Integer constants
    int decimal = 42;          // Decimal integer
    unsigned int u_decimal = 42U; // Unsigned decimal integer
    long int l_decimal = 42L; // Long integer
    long long int ll_decimal = 42LL; // Long long integer

    // Floating point constants
    float floatDecimal = 3.14; // Float
    float f_decimal = 3.14f;   // Float
    double d_decimal = 3.14;   // Double
    long double ld_decimal = 3.14L; // Long double
    float exponentialDecimal = 2.5e3f; // Float in exponential notation
    double d_exponentialDecimal = 2.5e3; // Double in exponential notation
    long double ld_exponentialDecimal = 2.5e3L; // Long double

    // Octal and hexadecimal constants
    int octal = 052;           // Octal (equivalent to decimal 42)
    int hexadecimal = 0x2A;    // Hexadecimal (equivalent to decimal 42)

    printf("Decimal: %d\n", decimal);
    printf("Unsigned Decimal: %u\n", u_decimal);
    printf("Long Decimal: %ld\n", l_decimal);
    printf("Long Long Decimal: %lld\n", ll_decimal);
    printf("Float (default): %f\n", floatDecimal);
    printf("Float: %.2f\n", f_decimal);
    printf("Double: %.2f\n", d_decimal);
    printf("Long Double: %.2Lf\n", ld_decimal);
    printf("Exponential Float: %.2e\n", exponentialDecimal);
    printf("Exponential Double: %.2e\n", d_exponentialDecimal);
    printf("Exponential Long Double: %.2Le\n", ld_exponentialDecimal);
    printf("Octal: %o\n", octal);
    printf("Hexadecimal: %X\n", hexadecimal);

    return 0;
}