package org.dusad.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) {
        int val[] = {22, 20, 15, 30, 24, 54, 21, 32, 18, 25};
        int wt[] = {4, 2, 3, 5, 5, 6, 9, 7, 8, 10};
        int sackWeight = 30;
        KnapSackUtil.bottomUpDP(val, wt, sackWeight);
        KnapSackUtil.topDownRecursive(val, wt, sackWeight);

    }
}

class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        ArrayList<List<String>> lists = new ArrayList<>(Arrays.stream(strs).sorted()
                .collect(Collectors.groupingBy(this::sortStringChars)).
                        values());
        return lists;
    }

    public String sortStringChars(String str) {
        char[] charArray = str.toCharArray();
        Arrays.sort(charArray);
        return String.valueOf(charArray);
    }
}
