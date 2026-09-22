package codeFlowEngine;

public class WindowMetrics {

    /*
    Given a stream of integer latency logs in an array, find:
        The maximum sum of any contiguous subarray of fixed window size $K$.
        The length of the smallest contiguous subarray with sum >= Target.
    */
    int maxSubArraySum(int[] array, int k) {
        int maxSum = Integer.MIN_VALUE;
        int runningSum = 0;
        for (int i = 0; i < k; i++) {
            runningSum += array[i];
        }
        maxSum = Math.max(maxSum, runningSum);

        for (int i = k; i < array.length; i++) {
            runningSum += array[i] - array[i - k];
            if (runningSum > 0) {
                maxSum = Math.max(maxSum, runningSum);
            } else {
                runningSum = 0;
            }
        }

        return maxSum;
    }

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
        while (end < array.length) {
            if (!reachedSum) {
                runningSum += array[end];
                if (runningSum >= target) {
                    result = Math.min(end - start + 1, result);
                    reachedSum = true;
                } else {
                    end++;
                }
            } else {
                runningSum -= array[start];
                if (runningSum < target) {
                    result = Math.min(end - start + 1, result);
                    reachedSum = false;
                }
                start++;
            }
        }
        return result;
    }
}