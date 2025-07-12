#include <stdio.h>
#include <string.h>

int main() {
    char str[100];  // Declare a character array to hold the string input
    printf("Enter a string: ");
    // scanf("%s", str);  // Using %s to read a string input from the user
    // Note: scanf with %s stops reading at the first whitespace, so it won't read strings with spaces.
    // For strings with spaces, you can use fgets instead.
    fgets(str, sizeof(str), stdin);
    // Or chnage the formatter to "%[^\n]" to read until a newline character
    // scanf("%[^\n]", str);  // This will read the entire line until a newline character

    printf("You entered: %s\n", str);  // Print the string entered by the user
}