#include "../header/printArray.h"
#include "../header/swapInArray.h"
#include "../header/findMinInArray.h"

void selectionSort(int arr[], int size) {
    int minIndex;
    int current = 0;
    while (current < size) {
        minIndex = findMinInArray(arr, current, size);
        swapInArray(current, minIndex, arr);
        current++;
    }
}

int main() {
    int A[] = {2, 7, 1, 4, 0, 5, 8, 9, 3, 9};
    int n = 10; // How can we find the size of array at run-time?
    selectionSort(A, n);
    printArray(A, n);
    return 0;
}