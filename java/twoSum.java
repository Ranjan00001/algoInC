/*
Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

You can return the answer in any order.
*/
 import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = {};
        // for (int i = 0; i < nums.length; i++) {
        //     for (int j = i + 1; j < nums.length; j++) {
        //         if (nums[i] + nums[j] == target) {
        //             result = new int[] { i, j };
        //         }
        //     }
        // }
        // return result;
        int complement;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            complement = target - nums[i];
            if (map.containsKey(complement)) {
                result = new int[] { map.get(complement), i };
            }
            map.put(nums[i], i);
        }
        return result;
    }
}