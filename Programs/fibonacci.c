#include <stdio.h>
#include <stdlib.h>

// This doesn't uses recursion
int* fibonacci(int fib[], int n) {
    fib[0] = 0;
    if (n <= 1) return fib;
    fib[1] = 1;
    if (n == 2) return fib;
    
    for (int i = 2; i < n; i++) {
        fib[i] = fib[i - 1] + fib[i - 2];
    }
    return fib;
    
}
// In this version, the fib array is forced to come from outside since creating it inside will not let it be accessible for caller functions.
// Becasue when we create a varible inside of some funcitons, it is created under the local space.
// We need it to be in global space or heap.
// So we have malloc function that will create space under heap.

int * fibonacci2(int n) {
    int* fib = (int *) malloc(n * sizeof(int));
    fib[0] = 0;
    if (n <= 1) return fib;
    fib[1] = 1;
    if (n == 2) return fib;
    
    for (int i = 2; i < n; i++) {
        fib[i] = fib[i - 1] + fib[i - 2];
    }
    return fib;
}

// Now let's use recursive function call to make that happen
int fibonacci3(int n) {
    if (n <= 1) return 0;
    else if (n == 2) return 1;
    return fibonacci3(n - 1) + fibonacci3(n - 2);
}

int main(){
    int n = 11;
    // int fib[n];
    // int * result = fibonacci(fib, n);
    int * result = fibonacci2(n);
    int *original_ptr = result;  // Save original pointer for later
    printf("Fibonacci for n = %d: ", n);
    for (int i = 0; i < n; i++) {
        printf("%d, ", *result++);
    }
    // free(result);    // We can't free this result as it may be out of scope since we have been incrementing this pointer to access next elements
    free(original_ptr);
    return 0;
}