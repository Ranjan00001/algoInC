
#include <stdio.h>

int main() {
    // Size of pointer types
    printf("Size of int pointer: %zu bytes\n", sizeof(int*));          // Size of pointer to int
    printf("Size of char pointer: %zu bytes\n", sizeof(char*));        // Size of pointer to char
    printf("Size of float pointer: %zu bytes\n", sizeof(float*));      // Size of pointer to float
    printf("Size of double pointer: %zu bytes\n", sizeof(double*));    // Size of pointer to double
    printf("Size of void pointer: %zu bytes\n", sizeof(void*));        // Size of pointer to void

    // Pointer declaration and initialization
    int value = 42;                // An integer variable
    int *pointer = &value;        // Pointer to the integer variable
    int **pointerToPointer = &pointer; // Pointer to pointer
    int num = 12;
    int *ptr = (pointer);

    // Pointer dereferencing
    printf("Value: %d\n", value);                // Print the value of the variable
    printf("Pointer points to value: %d\n", *pointer); // Dereference pointer

    //Pointer to array
    int arr[5] = {1, 2, 3, 4, 5};   // Technically, arr is an array but it decays into pointer to its first element when passed to functions or assigned to pointers.
    // printf("Type of arr: %s\n", typeof(arr)); // This is wrong since typeof can only be used to infer types at compile time and that too in GCC only.
    
    printf("Address of arr: %zu\n", (void*)&arr);       // Address of the entire array
    printf("Address of arr[0]: %zu\n", (void*)&arr[0]); // Address of the first element
    
    // Pointer arithmetic
    printf("Address of arr + 1: %zu\n", (void*)(&arr + 1));       // Points to the memory after the entire array
    printf("Address of arr[0] + 1: %zu\n", (void*)(&arr[0] + 1)); // Points to the second element of the array
    
    int (*arrPtr)[5] = &arr; // &arr gives address of the first element of the array, which is of type int*[5].
    for (int i = 0; i < 5; i++) {
        printf("Array element %d: %d\n", i, *(arrPtr + i)); // Access array elements using pointer arithmetic
    }
    
}