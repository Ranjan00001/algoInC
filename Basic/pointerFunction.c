#include <stdio.h>  

// Function definition
float func(int a, int b) {
    return (float)(a + b) / 2;  // Example: returns the average of two integers as a float.
}

int main()  
{  
    float (*fp) (int , int);    // Declaration of a function pointer.  
    fp = &func;                  // Assigning the function's address to the function pointer.
    // Here &func and func is the same, both refer to the address of the function `func`.
    // Using the function pointer to call the function
    float result = fp(10, 20);  // Calls `func(10, 20)`
    printf("Result: %.2f\n", result);  // Output: Result: 15.00

    return 0;  
}