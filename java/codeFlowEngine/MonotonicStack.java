package codeFlowEngine;

public class MonotonicStack extends SyntaxValidator {
    
    int[] nextGreaterElement(int[] array) { // Not complete
        int[] result = new int[array.length];
        Stack s = new Stack();
        for (int i = 0; i < array.length; i++) {
            if (array[i] > array[(int) s.top()]) {
                result[i] += 1;
            } else {
                s.add(i);
            }
            
            s.add(array[i]);

        }

        return result;
    }
}
