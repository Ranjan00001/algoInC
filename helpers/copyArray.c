
void copyArray(int fromArray[], int fromIndex, int toIndex, int resultArray[], int current) {
    for (int i = fromIndex; i < toIndex; i++) {
        resultArray[current] = fromArray[i];
        current++;
    }
}