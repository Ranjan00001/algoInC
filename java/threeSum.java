
/*
Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// class Solution {

//     private static List<Integer> twoSum(int[] nums, int sourceIndex) {
//         int target = nums[sourceIndex];
//         List<Integer> result = new ArrayList<>(3);
//         result.add(target);

//         HashMap<Integer, Integer> hm = new HashMap<>();
//         for (int i = 0; i < nums.length; i++) {
//             int remaining = -(target + nums[i]);
//             if (hm.containsKey(remaining)) {
//                 int j = hm.get(remaining);
//                 if (j != sourceIndex) {
//                     result.add(nums[i]);
//                     result.add(nums[hm.get(remaining)]);
//                     return result;
//                 }
//             }
//             hm.put(remaining, i);
            
//         }

//         return result;
//     }

//     public List<List<Integer>> threeSum(int[] nums) {
//         List<List<Integer>> result = new ArrayList<>();

//         for (int i = 0; i < nums.length; i++) {
//             List<Integer> twoSumResult = Solution.twoSum(nums, i);
//             if (!result.contains(twoSumResult)) {
//                 result.add(twoSumResult);
//             }
//         }

//         return result;
//     }
// }

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);

        for (int current = 0; current < nums.length - 2; current++) {
            if (current > 0 && nums[current] == nums[current - 1]) {
                continue;
            }
            int left = current + 1;
            int right = nums.length - 1;
            while (left < right) {
                int currentSum = nums[current] + nums[left] + nums[right];
                if (currentSum == 0) {
                    List<Integer> triplet = new ArrayList<>(Arrays.asList(nums[current], nums[left], nums[right]));
                    // List<Integer> triplet = new ArrayList<>() {{
                    //     add(nums[currentSum]);
                    //     add(nums[left]);
                    //     add(nums[right]);
                    // }};
                    result.add(triplet);
                    left++;right--;
                    while (left < right && nums[left] == nums[left - 1]){
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (currentSum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }
}