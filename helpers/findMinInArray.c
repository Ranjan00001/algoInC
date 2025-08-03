
int findMinInArray(int array[], int start, int end) {
    int min = start;
    int current = start + 1;
    while (current < end) {
        if (array[min] > array[current]) {
            min = current;
        }
        current++;
    }
    return min;    
}