package problems;

import java.util.Arrays;
import problems.Searching;
/**
 * Two-Pointer Practice Suite
 */
public class PointerPattern {

    /**
     * Problem 1: Two Sum II - Input Array Is Sorted
     * ---------------------------------------------
     * Description: Given a 1-indexed array of integers 'numbers' that is ALREADY 
     * SORTED in non-decreasing order, find two numbers such that they add up to a 
     * specific 'target' number. 
     * Return their 1-based indices as an array [index1, index2].
     * 
     * Example:
     *   Input: numbers = [2, 7, 11, 15], target = 9
     *   Output: [1, 2] (since 2 + 7 = 9)
     * 
     * Target Time Complexity: O(N)
     * Target Auxiliary Space: O(1)
     */
    public static int[] twoSumSorted(int[] numbers, int target) {
        /**
         * This has O(NlogN) time and not using two-pointer.
        Searching s = new Searching();
        for (int i = 0; i < numbers.length; i++) {
            int num = numbers[i];
            int required = target - num;
            int result = 0;
            if (num < required) {
                // binary search in right part
                result = s.binarySearch(numbers, i + 1, numbers.length - 1, required);
            } else {
                // binary search in left part
                result = s.binarySearch(numbers, 0, i - 1, required);
            }
            if (result != -1) {
                return new int[] {i, result + 1};
            }
        }
        */
        int left = 0; int right = numbers.length - 1;
        int sum;
        while (left < right) {
            sum = numbers[left] + numbers[right];
            if ( sum == target) {
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{0, 0};
    }

    /**
     * Problem 2: Remove Duplicates from Sorted Array
     * ----------------------------------------------
     * Description: Given a sorted array 'nums', remove duplicates IN-PLACE such 
     * that each unique element appears only once. Return the number of unique 
     * elements 'k'. The first 'k' elements of 'nums' should hold the unique values.
     * 
     * Example:
     *   Input: nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]
     *   Output: 5 (modified array first 5 elements: [0, 1, 2, 3, 4])
     * 
     * Target Time Complexity: O(N)
     * Target Auxiliary Space: O(1)
     */
    public static int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int unique = 1;
        int current = nums[0];
        for (int num: nums) {
            if (current != num) {
                nums[unique] = num;
                unique++;
            }
            current = num;
        }
        System.out.println(Arrays.toString(nums));
        return unique;
    }

    /**
     * Problem 3: Sort Colors (Dutch National Flag)
     * ---------------------------------------------
     * Description: Given an array 'nums' containing N objects colored red (0), 
     * white (1), or blue (2), sort them IN-PLACE so that objects of the same 
     * color are adjacent, in order 0, 1, 2.
     * 
     * Example:
     *   Input: nums = [2, 0, 2, 1, 1, 0]
     *   Output: [0, 0, 1, 1, 2, 2]
     * 
     * Target Time Complexity: O(N) [Single Pass]
     * Target Auxiliary Space: O(1)
     */
    public static void sortColors(int[] nums) {
        /*
        // redix-sort
        int red = 0;
        int white = 0;
        for (int num: nums) {
            if (num == 0) {
                red++;
            } else if (num == 1) {
                white++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (red > 0) {
                nums[i] = 0;
                red--;
            } else if (white > 0) {
                nums[i] = 1;
                white--;
            } else {
                nums[i] = 2;
            }
        }
        */
        // Two-pointer approach and in one pass
        // We have to be careful with these pointers... we're considering them for purpose here
        // 1. As yhey're keeping boundary of these numbers
        // In the previous approach these pointers were for keeping count.
        int left = 0;
        int right = nums.length - 1;
        int current = 0;
        while (left <= current && current <= right) {
            if (nums[current] == 0) {
                Helper.swap(nums, left, current);
                left++;
                current++;
            } else if (nums[current] == 2){
                Helper.swap(nums, right, current);
                right--;
            } else {
                current++;
            }
        }
    }

    /**
     * Problem 4: Container With Most Water
     * ------------------------------------
     * Description: Given an integer array 'height' of length N, where each element 
     * represents a vertical line height at index i. Find two lines that together 
     * with the x-axis form a container, such that the container holds the maximum 
     * amount of water.
     * 
     * Note: Area between index i and j = min(height[i], height[j]) * |j - i|
     * 
     * Example:
     *   Input: height = [1, 8, 6, 2, 5, 4, 8, 3, 7]
     *   Output: 49
     * 
     * Target Time Complexity: O(N)
     * Target Auxiliary Space: O(1)
     */
    public static int maxArea(int[] height) {
        int maxArea = 0; int area;
        int left = 0; int right = height.length - 1;
        while (left < right) {
            if (height[left] < height[right]) {
                area = height[left] * (right - left);
                maxArea = Math.max(area, maxArea);
                left++;
            } else {
                area = height[right] * (right - left);
                maxArea = Math.max(area, maxArea);
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("=== Two-Pointer Practice Suite ===");
        
        // Test 1: Two Sum Sorted
        int[] numbers = {2, 7, 11, 15};
        System.out.println("1. Two Sum Sorted: " + Arrays.toString(twoSumSorted(numbers, 13)));

        // Test 2: Remove Duplicates
        int[] dupNums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int k = removeDuplicates(dupNums);
        System.out.println("2. Remove Duplicates count: " + k);

        // Test 3: Sort Colors
        int[] colors = {2, 0, 2, 1, 1, 0};
        sortColors(colors);
        System.out.println("3. Sort Colors: " + Arrays.toString(colors));

        // Test 4: Container With Most Water
        int[] heights = {1, 8, 6, 2, 5, 4, 8, 3, 7, 1};
        System.out.println("4. Max Water Area: " + maxArea(heights));
    }
}
