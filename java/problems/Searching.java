package problems;

public class Searching {
    public int binarySearch(int[] num, int left, int right, int target) {
        int middle = left + (right - left) / 2;
        if (target == num[middle]) {
            return middle;
        } else if (target < num[middle]) {
            binarySearch(num, left, middle, target);
        } else {
            binarySearch(num, middle + 1, right, target);
        }

        return -1;
    }
    
}