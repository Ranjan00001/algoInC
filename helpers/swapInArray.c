
void swapInArray(int ind1, int ind2, int *array) {
    int temp = array[ind1];
    array[ind1] = array[ind2];
    array[ind2] = temp;
}