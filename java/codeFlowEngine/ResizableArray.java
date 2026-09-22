package codeFlowEngine;

public class ResizableArray {

    int capacity = 8;
    int size = 0;
    int[] data = new int[capacity];

    // A dyamic array class without using java.util.ArrayList
    void add(int element) { //— Appends element, doubling capacity when size == capacity.
        if (size == capacity) {
            capacity *= 2;
            int[] temp = new int[capacity];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
        data[size] = element;
        size++;
    }

    int get(int index) { //— Returns element at index in $O(1)$ time; throws IndexOutOfBoundsException for invalid indices.
        if (index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for the current array");
        }
        return data[index];
    }

    void removeAt(int index) {//— Removes item at index in-place, shifting
        if (index >= size) {
            throw new IndexOutOfBoundsException("Invalid index for the current array");
        }
        for (int i = index; i + 1 < size; i++) {
            data[i] = data[i+1];
        }
    }
}