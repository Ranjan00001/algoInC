package codeFlowEngine;

public class WindowMetrics {

    /*
    Given a stream of integer latency logs in an array, find:
        The maximum sum of any contiguous subarray of fixed window size $K$.
        The length of the smallest contiguous subarray with sum >= Target.
    */
    int maxSubArraySum(int[] array, int k) {// Do not confuse this with kadane's algo
        int maxSum = Integer.MIN_VALUE;
        int runningSum = 0;
        if (k <= 0 || k >= array.length) {
            for (int i : array) {
                runningSum += i;
            }
            return runningSum;
        }
        for (int i = 0; i < k; i++) {
            runningSum += array[i]; // First window as negative sum is covered here
        }
        maxSum = Math.max(maxSum, runningSum);

        for (int i = k; i < array.length; i++) {
            runningSum += array[i] - array[i - k];
            maxSum = Math.max(maxSum, runningSum);
            // if (runningSum > 0) {
            //     maxSum = Math.max(maxSum, runningSum);
            // } else {
            //     maxSum = Math.max(maxSum, runningSum); // Needed this when every window has negative sum
            // }
        }

        return maxSum;
    }
    @deprecated
    int smallestSubArraySum(int[] array, int target) {
        // Not able to get the solution...
        // How can we manage the smallest array range so that their sum can get >= target

        // Sol -> skiding window -> first find the range that gets upto target
        // Now keep sliding = 0 the range window...

        // TO-DO
        int start = 0, end = 0;
        int runningSum = 0;
        // for (end = 0; end < array.length; end++) {
        //     runningSum += array[end];
        //     if (runningSum >= target) {
        //         break;
        //     }
        // }

        int result = array.length;
        boolean reachedSum = false;
        while (end < array.length || reachedSum) { // adding this reachedSum condition to handle when end = array.length and reachedSum = true but we can still shrink the window to get smallest-sub-array
            if (!reachedSum) {
                runningSum += array[end];
                if (runningSum >= target) {
                    result = Math.min(end - start + 1, result);
                    reachedSum = true;
                    // end++; -- Not doing because we need this value for next window also... It'll never go infinite because I'm toggling reachedSum
                } else {
                    end++;
                }
            } else {
                runningSum -= array[start];
                if (runningSum < target) {
                    result = Math.min(end - start + 1, result); // looks redundant?
                    reachedSum = false;
                } else {
                    start++;
                }
            }
        }
        return result;
    }

    int smallestSubArraySum(int[] array, int target, int yes) {
        int start = 0;
        int runningSum = 0;
        int minLen = Integer.MAX_VALUE;

        // 1. Expand the window using 'end'
        for (int end = 0; end < array.length; end++) {
            runningSum += array[end];
            
            // 2. Shrink the window from 'start' as long as the condition is satisfied
            while (runningSum >= target) {
                minLen = Math.min(minLen, end - start + 1);
                runningSum -= array[start];
                start++;
            }
        }
        return minLen == Integer.MAX_VALUE ? 0 : minLen;
    }

}