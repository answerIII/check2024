package leetcode3015m;

import java.util.*;

//todo: подумать над оптимизацией
public class Solution {
    public int[] countOfPairs(int n, int x, int y) {
        int[] result = new int[n+1];
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i - j == 1 || j - i == 1) {
                    graph[i][j] = 1;
                } else {
                    graph[i][j] = 10001; // вместо бесконечности
                }
            }
        }
        graph[x - 1][y - 1] = 1;
        graph[y - 1][x - 1] = 1;

        for (int u = 0; u < n; ++u){
            for (int v = 0; v < n; ++v){
                System.out.print(graph[u][v] + " ");
            }
            System.out.print('\n');
        }

        // флойд-уоршалл
        for (int i = 0; i < n; ++i){
            for (int u = 0; u < n; ++u){
                for (int v = 0; v < n; ++v){
                    graph[u][v] = Math.min(graph[u][v], graph[u][i] + graph[i][v]);
                }
            }
        }

        for (int u = 0; u < n; ++u){
            for (int v = 0; v < n; ++v){
                System.out.print(graph[u][v] + " ");
            }
            System.out.print('\n');
        }

        for (int i = 0; i < n; ++i){
            for (int j = 0; j < n; ++j){
                if (i != j && graph[i][j] != 10001){
                    result[graph[i][j]]++;
                }
            }
        }

        return Arrays.copyOfRange(result, 1, result.length); // для индексации с 1
    }


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.countOfPairs(4, 1, 1);
        System.out.println(Arrays.toString(result));
    }
}
