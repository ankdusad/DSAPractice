package org.dusad.dp;

/**
 * Given two strings how many minimum edits(update, delete or add) is needed to convert one string to another
 */
public class MinEditApp {
    public static void main(String[] args) {
        String str1 = "azced";
        String str2 = "abcdef";
        System.out.println("str 1: " + str1);
        System.out.println("str 2: " + str2);
        MinEditAppUtil.calculateMinEdit(str1.toCharArray(), str2.toCharArray());

    }
}

class MinEditAppUtil {

    static void calculateMinEdit(char[] a, char[] b) {
        int[][] T = new int[a.length + 1][b.length + 1];
        for (int i = 0; i <= a.length; i++) {
            for (int j = 0; j <= b.length; j++) {
                if (i == 0) T[i][j] = j;
                else if (j == 0) T[i][j] = i;
                else if (a[i - 1] == b[j - 1]) T[i][j] = T[i - 1][j - 1];
                else T[i][j] = 1 + Math.min(T[i - 1][j - 1], Math.min(T[i - 1][j], T[i][j - 1]));
            }
        }
        deriveMinEditFromMatrix(T, a, b);
    }

    private static void deriveMinEditFromMatrix(int[][] T, char[] a, char[] b) {
        printMatrix(T);
        System.out.println("Min Edits : " + T[a.length][b.length]);
        printActualEdits(T, a, b);

    }


    private static void printMatrix(int[][] matrix) {
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                System.out.printf("%4d", anAMatrix);
            }
            System.out.println();
        }
    }

    private static void printActualEdits(int T[][], char[] str1, char[] str2) {
        int i = T.length - 1;
        int j = T[0].length - 1;
        while (i != 0 && j != 0) {
            if (str1[i - 1] == str2[j - 1]) {
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j - 1] + 1) {
                System.out.println("Edit " + str2[j - 1] + " in string2 to " + str1[i - 1] + " in string1");
                i = i - 1;
                j = j - 1;
            } else if (T[i][j] == T[i - 1][j] + 1) {
                System.out.println("Delete in string1 " + str1[i - 1]);
                i = i - 1;
            } else if (T[i][j] == T[i][j - 1] + 1) {
                System.out.println("Delete in string2 " + str2[j - 1]);
                j = j - 1;
            } else {
                throw new IllegalArgumentException("Some wrong with given data");
            }

        }
    }
}
