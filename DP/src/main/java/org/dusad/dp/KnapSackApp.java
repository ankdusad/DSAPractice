package org.dusad.dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.StrictMath.max;

public class KnapSackApp {
    public static void main(String[] args) {
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        int sackWeight = 30;
        KnapSackUtil.bottomUpDP(val, wt, sackWeight);

    }
}

class KnapSackUtil {

    static void bottomUpDP(int val[], int wt[], int W) {
        int T[][] = new int[val.length + 1][W + 1];
        for (int i = 0; i <= val.length; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    T[i][j] = 0;
                } else if (j >= wt[i - 1]) {
                    T[i][j] = max(T[i - 1][j], val[i - 1] + T[i - 1][j - wt[i - 1]]);
                } else
                    T[i][j] = T[i - 1][j];
            }
        }
        deriveItemFromMatrix(val, wt, W, T);
    }

    private static void deriveItemFromMatrix(int[] val, int[] wt, int W, int[][] t) {
        printMatrix(t, val, wt);
        List<Integer> weightList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();

        int i = val.length, j = W;
        while (i != 0 && j != 0) {
            if (t[i][j] == t[i - 1][j]) {
                i--;
            } else {
                j = j - wt[i - 1];
                weightList.add(wt[i - 1]);
                valueList.add(val[i - 1]);
                i--;
            }
        }
        Collections.reverse(weightList);
        Collections.reverse(valueList);
        System.out.println(" weight List :" + weightList + "\n value List  :" + valueList + "\n Total Value :" + t[val.length][W]);
    }

    private static void printMatrix(int[][] matrix, int[] val, int[] wt) {
        int i = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (col == 0) {
                    if (row > 0) System.out.printf("%4d | %4d", wt[i], val[i++]);
                    else System.out.printf("%4d | %4d", 0, 0);
                }
                System.out.printf("%4d", matrix[row][col]);
            }
            System.out.println();
        }
    }
}
