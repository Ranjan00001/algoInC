package codeFlowEngine;

public class MonotonicStack extends SyntaxValidator {
    
    int[] nextGreaterElement(int[] array) { // Not complete
        int length = array.length;
        int[] result = new int[length];
        Stack s = new Stack();
        s.add(length - 1);
        int top;
        for (int i = length; i > 0; i--) {
            top = (char) s.top();
            if (top < array[i]) {
                s.pop();
            }
            s.add(i);
        }

        for (int i = 0; i < length; i++) {
            if (s.top().equals(i)) {
                s.pop();
            }
            top = (char) s.pop();
            result[i] = top - i;
        }

        return result;
    }
}
