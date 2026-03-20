import java.util.ArrayList;
import java.util.List;

public class Pattern {

    /**
     * Prints a right-angled triangle pattern of stars.
     * Example for n = 5:
     * *
     * **
     * ***
     * ****
     * *****
     * 
     * @param n the number of rows
     */
    public static void printRightAngledTriangle(int n) {
        
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < (row + 1); col++) {
                System.out.print("*");
            }
            System.out.print("/n");
        }
    }

    /**
     * Prints an inverted right-angled triangle pattern of stars.
     * Example for n = 5:
     * *****
     * ****
     * ***
     * **
     * *
     * 
     * @param n the number of rows
     */
    public static void printInvertedRightAngledTriangle(int n) {
        for (int row = 0; row < n; row++) {
            for (int col = (n - row); col > 0; col--) {
                System.out.print("*");
            }
            System.out.print("/n");
        }
    }

    /**
     * Prints a pyramid pattern of stars.
     * Example for n = 5:
     *     *
     *    ***
     *   *****
     *  *******
     * *********
     * 
     * @param n the number of rows
     */
    public static void printPyramid(int n) {
        // int maxCountStar = 2*n - 1;
        for (int row = 1; row <= n; row++) {
            for (int col = 0; col < (n + row - 1); col++) {
                if (col < (n - row)) {
                    System.out.print(" ");
                } else {
                    System.out.print("*");
                }
            }
            for (int extraStar = 1; extraStar < row; extraStar++) {
                System.out.print("*");
            }
            System.out.print("/n");
        }
    }

    /**
     * Prints a hollow square pattern of stars.
     * Example for n = 5:
     * *****
     * *   *
     * *   *
     * *   *
     * *****
     * 
     * @param n the number of rows and columns
     */
    public static void printHollowSquare(int n) {
        String star = "*".repeat(n);
        System.out.println(star);
        for (int row = 1; row < (n - 1); row++) {
            System.out.print("*");
            int col = 1;
            while (col < (n - 1)) {
                System.out.print(" ");
            }
            System.out.println("*");
        }
        System.out.println(star);
    }

    /**
     * LeetCode 118: Pascal's Triangle
     * Given an integer numRows, return the first numRows of Pascal's triangle.
     * Example for numRows = 5:
     * [[1], [1,1], [1,2,1], [1,3,3,1], [1,4,6,4,1]]
     * 
     * In Pascal's triangle, each number is the sum of the two numbers directly above it.
     * You can choose to print it directly or return a List<List<Integer>>.
     * 
     * @param numRows the number of rows
     */
    public static List<List<Integer>> printPascalsTriangle(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows < 1) {
            return result;
        }
        List<Integer> firstRow = new ArrayList<>() {{add(1);}};
        result.add(firstRow);
        for (int row = 1; row < numRows; row++) {
            result.add(populateNextRow(row, result.get(row - 1)));
        }

        return result;
    }

    static List<Integer> populateNextRow(int currentRow, List<Integer> previousList) {
        List<Integer> result = new ArrayList<>();
        int col = 0; int sum;
        if (currentRow < 2) {
            while (col <= currentRow) {
                result.add(1);
                col++;
            }
        } else {
            while (col <= currentRow) {
                if (col == 0 || col == currentRow) {
                    result.add(1);
                } else {
                    sum = previousList.get(col - 1) + previousList.get(col);
                    result.add(sum);
                }
                col++;
            }

        }

        return result;
    }
    /**
     * LeetCode 6: Zigzag Conversion
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * 
     * And then read line by line: "PAHNAPLSIIGYIR"
     * 
     * @param s the string to convert
     * @param numRows the number of zigzag rows
     * @return the converted string read line by line
     */
    public static String convertZigzag(String s, int numRows) {
        // String firstLine = getNthChar(s, 0, 4); // 0 -> 4 -> 8 (so on if available) (also insert space after every char)
        // String secondLine = getNthChar(s, 1, 2);   // 1 -> 3 -> 5 (so on if available) (no space)
        // String thirdLine = getNthChar(s, 2, 4); // 2 -> 6 -> 10(so on if available) (with space)
        String result = "";
        String nthRow;
        int currentCycle = 0;
        // int slantLineCharCount = numRows - 2;
        int cycleLen = numRows - 2; // slantLineCharCount + numRows;
        // One decision point is remained to align (0, n - 1), (1, n - 2)
        // After that alignment, the jump for each group will become the same
        // jump for (0th, (n-1)th) -> numRows + slantLineCharCount
        // jump for (1, (n - 2)th) -> if (numRows <= 2) then 2 else ((numRows * 2) - 3)
        // jump for (2, (n - 3)rd) -> (numRows * 2) - 6
        for (int row = 0; row < numRows; row++) {
            nthRow = "";
            int index = (currentCycle * cycleLen) + row;
            while (index < s.length()) {
                nthRow += s.substring(index, index + 1);
                index = (currentCycle * cycleLen) + row;
            }
            currentCycle ++;
            result += nthRow;
        }
        return result;
    }

    static String getNthChar(String str, int start, int n) {
        String result = "";
        for (int i = start; i < str.length(); i += n) {
            result = result.concat(str.substring(i, i + 1));
        }
        return result;
    }
}
