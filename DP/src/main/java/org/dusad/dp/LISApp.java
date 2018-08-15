package org.dusad.dp;

import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Find a subsequence in given array in which the subsequence's elements are
 * in sorted order, lowest to highest, and in which the subsequence is as long as possible
 */
public class LISApp {
    public static void main(String[] args) {
        int arr[] = {23, 10, 22, 5, 33, 8, 9, 21, 50, 41, 60, 80, 99, 22, 23, 24, 25, 26, 27};
        System.out.println("Array : \n" + Ints.asList(arr));
        LISUtil.longestSubsequence(arr);
    }
}

class LISUtil {

    static void longestSubsequence(int[] arr) {
        int T[] = new int[arr.length];
        int actualSolution[] = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            T[i] = 1;
            actualSolution[i] = i;
        }

        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j]) {
                    if (T[j] + 1 > T[i]) {
                        T[i] = T[j] + 1;
                        //set the actualSolution to point to guy before me
                        actualSolution[i] = j;
                    }
                }
            }
        }

        //find the index of max number in T
        int maxIndex = 0;
        for (int i = 0; i < T.length; i++) {
            if (T[i] > T[maxIndex]) {
                maxIndex = i;
            }
        }

        List<Integer> sequence = new ArrayList<>();
        //lets print the actual solution
        int t = maxIndex;
        int newT = maxIndex;
        do {
            t = newT;
            sequence.add(arr[t]);
            newT = actualSolution[t];
        } while (t != newT);
        System.out.println();
        Collections.reverse(sequence);

        System.out.println("SubSequence temp Array : \n" + Ints.asList(T));
        System.out.println("LIS : " + T[maxIndex]);
        System.out.println("Sequence : " + sequence);

        printSubSequence(T, arr);

    }

    private static void printSubSequence(int[] T, int[] arr) {

        int lcs = T[T.length - 1];
        List<Integer> sequence = new ArrayList<>();
        int j = T.length - 1;
        while (lcs != 1) {
            sequence.add(j);


        }
    }
}