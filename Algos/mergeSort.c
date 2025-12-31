#include <stdio.h>
#include <stdlib.h>
#include "../header/printArray.h"
#include "../header/copyArray.h"

void merge(int array[], int p, int q, int r) {
    int * resultArray = (int *) malloc(sizeof(int) * (r - p + 1));
    if (resultArray == NULL) {
        fprintf(stderr, "Error: Memory allocation failed in merge().\n");
        exit(-1);  // Stop the program safely
    }

    int current = 0;
    int left = p; int right = q + 1;
    while (left <= q && right < r) {
        printf("current: %d, left: %d, right: %d\n", current, left, right);
        if (array[left] < array[right]) {
            resultArray[current] = array[left];
            left++;
        } else {
            resultArray[current] = array[right];
            right++;
        }
        current++;
    }
    if (left > q) {
        copyArray(array, right, r, resultArray, current); // Copy array[right:r] into resultArray
    } else {
        copyArray(array, left, q + 1, resultArray, current);
    }
    // By this time, we'll have a new resultArray combined to output a sorted array
    // We need copy that back into origial array
    copyArray(resultArray, 0, r - p, array, p);
    free(resultArray);
}

void mergeSortProcedure(int array[], int start, int end) {
    int p, q, r;
    p = start; r = end;
    q = p + (r - p) / 2;
    if (p != q) {
        mergeSortProcedure(array, p, q);
        mergeSortProcedure(array, q + 1, r);
        merge(array, p, q, r);
    }
}

void mergeSort(int array[], int size) {
    mergeSortProcedure(array, 0, size - 1);    
}

int main() {
    int A[] = {2, 7, 1, 4, 0, 5, 8, 9, 3, 9};
    int n = 10;
    mergeSort(A, n);
    printArray(A, n);
}