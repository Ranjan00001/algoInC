/*
Given an array nums with n objects colored red, white, or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white, and blue.

We will use the integers 0, 1, and 2 to represent the color red, white, and blue, respectively.

You must solve this problem without using the library's sort function.
 */

class Solution {
    public void sortColors(int[] nums) {
        int redCount, whiteCount, blueCount = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                redCount++;
            } else if (nums[i] == 1) {
                whiteCount++;
            } else {
                blueCount++;
            }
        }

        for (int i = 0; i < redCount; i++) {
            nums[i] = 0;
        }
        for (int i = redCount; i < redCount + whiteCount; i++) {
            nums[i] = 1;
        }
        for (int i = redCount + whiteCount; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}