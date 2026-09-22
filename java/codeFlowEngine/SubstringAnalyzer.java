package codeFlowEngine;

public class SubstringAnalyzer {

    int longestSubstringWithoutRepeatingChars(String str) {
        int start = 0, end = str.length();
        int result = 0;
        StringBuilder sb = new StringBuilder(str);
        while(start <= end) {
            if (sb.indexOf(str.substring(end)) == -1) {
                sb.append(str.substring(end));
                end++;
                result = Math.max(sb.length(), result);
            } else {
                sb.replace(start, start + 1, "");
                start++;
            }
        }
        return result;
    }
    
}