package leetcode2285m;

import java.util.Arrays;

public class Solution {
    public static long maximumImportance(int n, int[][] roads) {
        long[] countOfRoads = new long[n];
        for (int[] road : roads) {
            ++countOfRoads[road[0]];
            ++countOfRoads[road[1]];
        }
        Arrays.sort(countOfRoads);
        long totalImportance = 0;
        for (int i = 0; i < n; ++i){
            totalImportance += (i + 1) * countOfRoads[i];
        }
        return totalImportance;
    }

    public static void main(String[] args) {
        int[][] roads = {{0, 1}, {1, 2}, {2, 3}, {0, 2}, {1, 3}, {2, 4}};
        int n = 5;

        long maxImportance = maximumImportance(n, roads);
        System.out.println(maxImportance);
    }
}
