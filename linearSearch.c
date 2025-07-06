// This is the program for doing linear search in C

#include <stdio.h>
#include <stdlib.h>

int search(int target, int* array); // This is the declaration for function

int main() {
    int target = 5;
    int array[] = {2, 3, 4, 90, 7};
    int result = search(target, array);
    if (result == -1) {
        printf("Couldn't find the target in the given array");
        return 1;
    }
    printf("result is: %d", result);
    return result;
}

int search(int target, int* array) {
    int length = 5;
    for (int i = 0; i < length; i ++) {
        if (array[i] == target) return i;
    }
    return -1;
}
