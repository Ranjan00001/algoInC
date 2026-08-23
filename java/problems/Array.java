package problems;

import java.util.Arrays;
import java.util.HashSet;
import java.lang.Integer;
import java.lang.Math;
import problems.Sorting;

public class Array {

    /**
     * Problem 1A: Check Duplicates (Memory-Optimized Approach)
     * Description: Given an array of integers, check if it contains any duplicate values.
     * 
     * Time Complexity: O(N^2)
     * Auxiliary Space: O(1)
     */
    public static boolean checkDuplicateMemoryOptimized(int[] input) {
        if (input == null || input.length < 2) return false;
        
        for (int i = 0; i < input.length; i++) {
            for (int j = i + 1; j < input.length; j++) {
                if (input[i] == input[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Problem 1B: Check Duplicates (Time-Optimized Approach)
     * Description: Given an array of integers, check if it contains any duplicate values using extra memory.
     * 
     * Time Complexity: O(N)
     * Auxiliary Space: O(N)
     */
    public static boolean checkDuplicateTimeOptimized(int[] input) {
        if (input == null || input.length < 2) return false;
        
        HashSet<Integer> seen = new HashSet<>();
        for (int num : input) {
            if (!seen.add(num)) {
                return true; // Duplicate found (add returns false if element already exists)
            }
        }
        return false;
    }

    /**
     * Problem 2: In-Place Positive & Negative Segregation
     * Description: Rearrange elements in an array such that all negative numbers appear first, 
     * followed by positive numbers. Relative order of elements does not need to be preserved.
     * 
     * Time Complexity: O(N)
     * Auxiliary Space: O(1)
     */
    public static void segregatePosNeg(int[] input) {
        if (input == null || input.length < 2) return;
        
        int i = 0;
        int boundary = input.length - 1;

        while (i < boundary) {
            if (input[i] < 0) {
                i++;
            } else {
                int temp = input[boundary];
                input[boundary] = input[i];
                input[i] = temp;
                boundary--;
            }
        }
    }

    public static int[] sortedSquares(int[] array) {

        for (int i = 0; i < array.length; i++) {
            array[i] *= array[i];
        }
        Sorting s = new Sorting();
        s.mergeSort(array, 0, array.length - 1);
        return array;
    }

    static int[] sortedSquares(int[] array, int secondApproach) {
        int[] result;
        if (secondApproach == 0) {
            return sortedSquares(array);
        } else {
            int left = 0; int right = array.length - 1;
            result = new int[array.length];
            int pos = array.length - 1;
            while (left <= right) {
                if (Math.abs(array[left]) < Math.abs(array[right])) {
                    result[pos--] = array[right] * array[right];
                    right--;
                } else {
                    result[pos--] = array[left] * array[left];
                    left++;
                }
            }
        }
        return result;
    }

    // Maximum sub-array sum
    public static int maxSubArraySum(int[] array) {
        int maxSum = Integer.MIN_VALUE;
        int curSum = 0;
        for (int i = 0; i < array.length; i++) {
            curSum += array[i];
            maxSum = Math.max(maxSum, curSum);
            if (curSum < 0) {
                curSum = 0;
            }
            
        }
        return maxSum;
    }

    // TODO: rotate an array with given k

    public static void main(String[] args) {
        
        // Test Task 2: Segregation
        int[] arrToSegregate = {-1, 4, -5, 0, 8, -178};
        System.out.println("\n--- Task 2: Segregation ---");
        System.out.println("Before Segregation: " + Arrays.toString(arrToSegregate));
        segregatePosNeg(arrToSegregate);
        System.out.println("After Segregation:  " + Arrays.toString(arrToSegregate));

        // Test Task 3: Check Duplicates
        int[] testArray = {1, 2, 3, 4, 2};
        System.out.println("--- Task 3: Check Duplicates ---");
        System.out.println("Array: " + Arrays.toString(testArray));
        System.out.println("Has Duplicates [O(1) Space]: " + checkDuplicateMemoryOptimized(testArray));
        System.out.println("Has Duplicates [O(N) Time]:  " + checkDuplicateTimeOptimized(testArray));

        int[] input = {-4, -3, 0, 3, 10};
        System.out.println(Arrays.toString(sortedSquares(input, 1)));

        // Test Task: Maximum Subarray Sum (Kadane's Algorithm)
        int[] kadaneArr1 = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] kadaneArr2 = {-5, -2, -8, -1};
        System.out.println("\n--- Task: Maximum Subarray Sum ---");
        System.out.println("Array 1: " + Arrays.toString(kadaneArr1) + " -> Max Sum: " + maxSubArraySum(kadaneArr1));
        System.out.println("Array 2: " + Arrays.toString(kadaneArr2) + " -> Max Sum: " + maxSubArraySum(kadaneArr2));
    }
}