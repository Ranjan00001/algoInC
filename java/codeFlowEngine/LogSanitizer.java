package codeFlowEngine;

public class LogSanitizer {

    boolean isValidPalindrome(String str) {
        assert str != null;
        int start = 0, end = str.length() - 1; // this will be the first line to crash for null, but not after assert
        while (start < end) {
            if (!Character.isLetterOrDigit(str.charAt(start))) {start++; continue;}
            if (!Character.isLetterOrDigit(str.charAt(end))) {end--; continue;}

            if (Character.toLowerCase(str.charAt(start)) != Character.toLowerCase(str.charAt(end))) {
                return false;
            }
            start++;end--;
        }
        return true;
    }

}
