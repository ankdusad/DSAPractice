package org.dusad.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.StrictMath.max;

public class LCSApp {
    public static void main(String[] args) {
        String str1 = "ABCDGHLQR";
        String str2 = "AEDPHR";
        LCSUtil.longestCommonSubSequence(str1.toCharArray(), str2.toCharArray());
    }
}

class LCSUtil {

    static void longestCommonSubSequence(char[] a, char[] b) {

        int[][] T = new int[a.length + 1][b.length + 1];

        for (int i = 0; i <= a.length; i++) {
            for (int j = 0; j <= b.length; j++) {
                if (i == 0 || j == 0) {
                    T[i][j] = 0;
                } else if (a[i - 1] == b[j - 1]) {
                    T[i][j] = 1 + T[i - 1][j - 1];
                } else {
                    T[i][j] = max(T[i - 1][j], T[i][j - 1]);
                }
            }
        }

        deriveSubsequenceFromMatrix(T, a, b);
    }

    private static void deriveSubsequenceFromMatrix(int[][] T, char[] a, char[] b) {
        printMatrix(T, a, b);
        List<Character> subSequence = new ArrayList<>();
        int i = a.length, j = b.length;
        while (i != 0 && j != 0) {
            if (T[i][j] == T[i - 1][j]) i--;
            else if (T[i][j] == T[i][j - 1]) j--;
            else {
                subSequence.add(a[i - 1]);
                i--;
                j--;
            }
        }
        Collections.reverse(subSequence);
        System.out.println("LCS :" + subSequence);
    }

    private static void printMatrix(int[][] matrix, char[] a, char[] b) {
        int i = 0, j = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (row == 0 && col > 0)
                    System.out.printf("%4c", b[j++]);
                else if (col == 0 && row > 0)
                    System.out.printf("%4c", a[i++]);
                else
                    System.out.printf("%4d", matrix[row][col]);
            }
            System.out.println();
        }
    }


}
