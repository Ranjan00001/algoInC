#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {    // Here are 3 wats to declare a string in C:
    // 1. Using an array of characters with explicit initialization
    // char str[100] = {'H', 'e', 'l', 'l', 'o', ',', ' ', 'W', 'o', 'r', 'l', 'd', '!', '\0'};
    
    // 2. Using the strcpy function to copy a string literal into a character array
    char str[100];  // String in C is an array of characters with a null terminator    
    strcpy(str, "Hello, World!");
    
    // // 3. Using a string literal directly in the declaration
    // char str[100] = "Hello, World!";
    
    // Print the first character of the string
    printf("The first character of the string is: %c\n", str[0]);
    
    // Print the entire string
    printf("The entire string is: %s\n", str);  // When we use %s, it prints the string until it hits the null terminator
    printf("The length of the string is: %zu\n", strlen(str));  // Using strlen to get the length of the string, keeps adding up into length until it hits the null terminator
    // Thus getting the null terminator is inportant point when we have to traverse the string or print it.
    return 0;
}