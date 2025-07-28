// src/printArray.c
#include <stdio.h>
#include "../header/printArray.h"

void printArray(int arr[], int size) {
    for (int i = 0; i < size; i++)
        printf("%d ", arr[i]);
    printf("\n");
}
