
package problems;

import java.util.Arrays;

public class Sorting {

    public void mergeSort(int[] array, int left, int right) {
        int middle = left + (right - left) / 2;
        if (left >= right) {
            return;
        }
        mergeSort(array, left, middle);
        mergeSort(array, middle + 1, right);
        merge(array, left, middle, right);
    }

    public static void merge(int[] array, int left, int middle, int right) {
        int i = left;
        int j = middle + 1;
        int[] temp = new int[right - left + 1];
        int current = 0;
        while (i <= middle && j <= right) {
            if (array[i] < array[j]) {
                temp[current] = array[i];
                current++;
                i++;
            } else {
                temp[current] = array[j];
                current++;
                j++;
            }
        }
        while (i <= middle) {
            temp[current++] = array[i++];
        }
        while (j <= right) {
            temp[current++] = array[j++];
        }

        int k = left;
        for (int n: temp) {
            array[k] = n;
            k++;
        }
    }
    
    public static void main(String[] args) {
        int[] num ={-4, 0, 9, -3, 0, 8};
        int left = 0; int right = num.length - 1;
        Sorting s = new Sorting();
        s.mergeSort(num, left, right);
        System.out.println(Arrays.toString(num));
    }
}