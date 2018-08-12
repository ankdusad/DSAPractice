package org.dusad.dp;

import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubSetApp {
    public static void main(String[] args) {
        int a[] = {1, 3, 5, 5, 2, 1, 1, 6};
        int sum = 11;
        SubSetUtil.findSubset(a, sum);

    }
}

class SubSetUtil {
    static void findSubset(int[] a, int sum) {
        boolean T[][] = new boolean[a.length + 1][sum + 1];
        for (int i = 0; i <= a.length; i++) {
            for (int j = 0; j <= sum; j++) {
                if (j == 0) T[i][j] = true;
                else if (i == 0) T[i][j] = false;
                else if (j < a[i - 1]) T[i][j] = T[i - 1][j];
                else T[i][j] = T[i - 1][j] || T[i - 1][j - a[i - 1]];
            }
        }
        printMatrix(T, a, sum);
        deriveSubsetFromMatrix(T, a, sum);
    }

    private static void deriveSubsetFromMatrix(boolean[][] T, int[] a, int sum) {
        int i = a.length, j = sum;
        List<Integer> subSet = new ArrayList<>();
        while (i != 0 && j != 0) {
            if (T[i][j] == T[i - 1][j]) i--;
            else {
                subSet.add(a[i - 1]);
                j = j - a[i - 1];
                i--;
            }
        }
        Collections.reverse(subSet);
        System.out.println("Set is:" + Ints.asList(a) + " Sum is : " + sum);
        System.out.print("SubSet is" + subSet);
    }

    private static void printMatrix(boolean[][] matrix, int[] a, int sum) {
        int i = 0;
        System.out.print("    ");
        for (int j = 0; j <= sum; j++)
            System.out.print("  " + j);
        System.out.print("\n    ");
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (col == 0) {
                    if (row > 0) System.out.printf("%4d", a[i++]);
                }
                char b = matrix[row][col] ? 'T' : 'F';
                System.out.print("  " + b);
            }
            System.out.println();
        }
    }
}


