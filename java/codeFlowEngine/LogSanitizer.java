package codeFlowEngine;

public class LogSanitizer {

    boolean isValidPalindrome(String str) {
        str = str.toLowerCase();
        int start = 0, end = str.length() - 1;
        while (start < end) {
            if (!Character.isAlphabetic(str.charAt(start))) {start++; continue;}
            if (!Character.isAlphabetic(str.charAt(end))) {end--; continue;}

            if (str.charAt(start) != str.charAt(end)) {
                return false;
            }
            start++;end--;
        }
        return true;
    }

}
