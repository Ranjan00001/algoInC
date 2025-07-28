#include <stdio.h>
#include "../header/printArray.h"

void swap(int ind1, int ind2, int *array) {
    int temp = array[ind1];
    array[ind1] = array[ind2];
    array[ind2] = temp;
}

void insertionSort(int n, int array[]) {
    int current = 1;    // Can take the waiver of that fact that one element in itself will always be sorted, so starting from 1
    // We'll take the (current)th element and insert it into the sorted section
    int key;
    while (current < n) {
        int sortedIndex = current - 1;
        while (sortedIndex > 0 && array[current] < array[sortedIndex]) {
            sortedIndex--;
        }
        swap(sortedIndex, current, array);
        current++;
    }
}

int main() {
    int A[] = {2, 7, 1, 4, 0, 5, 8, 9, 3, 9};
    int n = 10; // How can we find the size of array at run-time?
    insertionSort(n, A);
    printArray(A, n);
}