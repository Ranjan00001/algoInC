package codeFlowEngine;

public class SyntaxValidator {

    class Stack {
        int capacity = 8;
        Object[] data;
        int size = 0;

        void add(Object element) {
            if (size >= capacity) {
                capacity *= 2;
                Object[] temp = new Object[capacity];
                for (int i = 0; i < data.length; i++) {
                    temp[i] = data[i];
                }
                data = temp;
            }
            data[size - 1] = element;
            size++;
        }

        Object pop() {
            Object element = data[size - 1];
            size--;
            return element;
        }

        Object top() {
            if (size <= 0) {
                return new Object();
            }
            return data[size - 1];
        }
    }

    char getPairBracket(char c) {
        switch (c) {
            case '{':
                return '}';

            case '}':
                return '{';

            case '[':
                return ']';

            case ']':
                return '[';

            case '(':
                return ')';

        }
        return '(';
    }

    boolean isValidBracket(String brackets) {
        Stack s = new Stack();
        // All the elements of brackets should be among openBracks or closedBracks.
        String openBracks = "[{(";
        String closeBracks = ")}]";
        for (int i = 0; i < brackets.length(); i++) {
            if (openBracks.contains(brackets.substring(i))) {
                s.add(brackets.charAt(i));
            } else if (closeBracks.contains(brackets.substring(i)) && !s.pop().equals(brackets.charAt(i)))  {
                return false;
            }
        }
        return s.size == 0;
    }
}
