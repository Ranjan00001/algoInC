/*
You are given an array arr[] of integers. Your task is to find the maximum sum of the smallest and second smallest elements across all subarrays (of size >= 2) of the given array.
*/
//////////////////////// IMPORTANT ///////////////////////////////////////////////;

class Solution {
    public int maxSum(int arr[]) {
        // Crate all possible sub arrays (length >= 2) out of the given array 
        // Get smallest and second smallest elelment out of these subarray
        // Sum them
        // Now find the largest among them
        // Time -> nc2 => n^2
        // int smalllest = 0;
        // int secondSmallest = 0;
        // if (arr[0] < arr[1]) {
        //     smalllest = arr[0];
        //     secondSmallest = arr[1];
        // } else {
        //     smalllest = arr[1];
        //     secondSmallest = arr[0];
        // }
        int result = Integer.MIN_VALUE;
        /*Thinking like to find short-circuiting solution
        1. What if we chose smalllest and 2nd smallest across array then returned it as result -> then surely there will be a next element of any of these elements forming a subarray to return larger sum than them
        2. Keep track of smallest and 2nd smallest
            Concept: adding elements to a subarray never increase mimimum
            So only 2 element size subarray is enough for use to produce the result ( not need to create every possible size subarray)

        */
        for (int i = 0; i < arr.length - 1; i++) {
            // if (arr[i] < secondSmallest) {
            //     if (arr[i] < smalllest) {
            //         secondSmallest = smalllest;
            //         smalllest = arr[i];
            //     } else {
            //         secondSmallest = arr[i];
            //     }
            // } else {
            //     smalllest = secondSmallest;
            //     secondSmallest = arr[i];
            // }
            int currentSum = arr[i] + arr[i + 1];
            if (currentSum > result) {
                result = currentSum;
            }
        }

        /*
        Dry run: arr =  [4, 3, 5, 1]
        smallest = 1
        seconsSmallest = 3-
        result = 7-
        i = 2-3
        */

        return result;
    }
}