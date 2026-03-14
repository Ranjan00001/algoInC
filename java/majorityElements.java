/*
Given an array nums of size n, return the majority element.

The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.
*/

class Solution {
    public int majorityElement(int[] nums) {
        int majorityCount = nums.length / 2;
        HashMap<Integer, Integer> counter = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentCount = counter.getOrDefault(nums[i], 0);
            if (currentCount == majorityCount) {
                return nums[i];
            } else {
                counter.put(nums[i], currentCount + 1);
            }
        }
        return -1;
    }
}
