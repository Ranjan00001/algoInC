package codeFlowEngine;

public class SlidingWindowMax extends SyntaxValidator {
    
    Object[] getMaxInWindowK(Object[] array, int K) {
        assert K >= 1;
        Object[] result = new Object[array.length - K];
        int i = 0;
        int currentPassMax = Integer.MIN_VALUE;
        while (i < array.length) {
            currentPassMax = Math.max((int) array[i], currentPassMax);
            if (i >= K - 1) result[i - K + 1] = currentPassMax;
        }
        return result;
    }// Have to practice more questions where we use these data-structures... How do I get the hint...
}
