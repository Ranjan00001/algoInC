#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    // 1. Declare using array of characters with explicit initialization
    char str[100] = {'H', 'e', 'l', 'l', 'o', ',', ' ', 'W', 'o', 'r', 'l', 'd', '!', '\0'};
    // Now try to modify the string
    str[0] = 'h';  // Change 'H' to 'h'
    str[7] = 'w';  // Change 'W' to 'w'
    printf("Modified string: %s\n", str);  // Print the modified string
    // Now reassign the string
    // str = "Hello, World!";  // This will not work as expected, since str is an array, not a pointer

    // 2. Declare using a string literal directly in the declaration
    char str2[100] = "Hello, World!";
    // Now try to modify the string
    str2[0] = 'h';  // Change 'H' to 'h'
    str2[7] = 'w';  // Change 'W' to 'w'
    printf("Modified string 2: %s\n", str2);  // Print the modified string
    // Now reassign the string
    // str2 = "Hello, World!";  // This will not work as expected

    // So we can use strcpy to reassign the string
    strcpy(str2, "Hello, World!");  // This will work as expected
    printf("Reassigned string 2: %s\n", str2);

    // Also, we can pointer to reassign the string
    char *str3 = "Hello, World!";
    str3 = "Goodbye, World!";  // This will work as expected
    printf("Pointer reassigned string: %s\n", str3);
    // But with pointer, we cannot modify the string directly
    // str3[0] = 'h';  // This will cause a segmentation fault
}