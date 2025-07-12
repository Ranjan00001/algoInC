#include <stdio.h>
#include <stdbool.h>

bool isPalindrome(int n) {
    int copy = n;
    // reverse the number
    int reverse = 0;
    while (copy) {
        reverse *= 10;
        reverse += copy % 10;
        copy = copy / 10;
    }
    // printf("%d", 343 % 10);
    return n == reverse;
}

int main() {
    int n = 93439;
    bool a = isPalindrome(n);
    printf("%d is a palindrome: %s\n", n, a ? "true" : "false");
    return 0;
}