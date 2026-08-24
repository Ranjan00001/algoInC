package problems;

/**
 * Utility helper functions for array manipulations and algorithms.
 */
public class Helper {

    /**
     * Swaps two elements in an array at indices i and j.
     */
    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Reverses elements in an array in-place from start index to end index (inclusive).
     */
    public static void reverse(int[] array, int start, int end) {
        int i = start;
        int j = end;
        while (i < j) {
            swap(array, i, j);
            i++;
            j--;
        }
    }

    /**
     * Shifts elements in an array one position to the right in the range [start, end].
     */
    public static void shift(int[] array, int start, int end) {
        int current = end;
        while (start < current) {
            array[current] = array[current - 1];
            current--;
        }
    }
}
