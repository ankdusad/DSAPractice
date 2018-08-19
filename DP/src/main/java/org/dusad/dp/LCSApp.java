package org.dusad.dp;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.StrictMath.max;

public class LCSApp {
    public static void main(String[] args) {
        String str1 = "ABCDGHLQR";
        String str2 = "AEDPHR";
        LCSUtil.longestCommonSubSequence(str1.toCharArray(), str2.toCharArray());
        LCSUtil.lcsRecursive(str1.toCharArray(), str2.toCharArray());
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

        deriveSubsequentFromMatrix(T, a, b);
    }

    private static void deriveSubsequentFromMatrix(int[][] T, char[] a, char[] b) {
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


    public static void lcsRecursive(char[] a, char[] b) {
        List<Character> lcs = new ArrayList<>();
        System.out.println("LCS rec:" + Lists.charactersOf(lcsRecursiveUtil(a, b, 0, 0)));
    }

    private static String lcsRecursiveUtil(char[] a, char[] b, int i, int j) {
        if (i >= a.length || j >= b.length) return "";
        if (a[i] == b[j]) return a[i] + lcsRecursiveUtil(a, b, i + 1, j + 1);
        return (lcsRecursiveUtil(a, b, i, j + 1).length() > lcsRecursiveUtil(a, b, i + 1, j).length()) ? lcsRecursiveUtil(a, b, i, j + 1) : lcsRecursiveUtil(a, b, i + 1, j);
    }
}
