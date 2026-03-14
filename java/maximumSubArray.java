/*
Given an integer array nums, find the subarray with the largest sum, and return its sum.
 */

// class Solution {

//     class tuple {
//         int maxSubArraySum;
//         int start;
//         int last;

//         public tuple(int maxSubArraySum, int start, int last) {
//             this.maxSubArraySum = maxSubArraySum;
//             this.start = start;
//             this.last = last;
//         }
//     }

//     public int maxSubArray(int[] nums) {
//         // int largetSumSoFar = 0;
//         tuple current = new tuple(0, 0, 0);
//         tuple result = new tuple(0, 0, 0);

//         for (int i = 0; i < nums.length; i++) {
//             current.maxSubArraySum += nums[i];
//             if (current.maxSubArraySum > result.maxSubArraySum && result.last == i) {
//                 result.maxSubArraySum = current.maxSubArraySum;
//                 result.last = i + 1;
//             } else {
//                 current = new tuple(nums[i], i, i + 1);
//             }
//         }
//         return result.maxSubArraySum;
//     }
// }

class Solution {
    public int maxSubArray(int[] nums) {
        int largetSumSoFar = -Integer.MAX_VALUE;
        int currentSum = 0;

        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (currentSum > largetSumSoFar) {
                largetSumSoFar = currentSum;
            }if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return largetSumSoFar;
    }
}