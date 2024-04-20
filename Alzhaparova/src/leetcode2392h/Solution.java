package leetcode2392h;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[][] resultMatrix = new int[k][k];
        List<Integer> sortedRows = topologicalSort(k, rowConditions);
        List<Integer> sortedCols = topologicalSort(k, colConditions);
        if (sortedRows.size() != k || sortedCols.size() != k) {
            return new int[0][0];
        }

        int[] indices = new int[k+1];
        for (int i = 0; i < k; ++i){
            indices[sortedCols.get(i)] = i;
        }

        for (int i = 0; i < k; ++i){
            resultMatrix[i][indices[sortedRows.get(i)]] = sortedRows.get(i);
        }

        return resultMatrix;
    }

    private List<Integer> topologicalSort(int k, int[][] conditions) {
        List<Integer> result = new ArrayList<>();
        List<Integer>[] graph = new ArrayList[k + 1];
        for (int i = 1; i <= k; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] inDegree = new int[k + 1];

        for (int[] cond : conditions){
            int parent = cond[0];
            int child = cond[1];
            graph[parent].add(child);
            inDegree[child]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= k; ++i){
            if (inDegree[i] == 0){
                queue.add(i);
            }
        }

        while (!queue.isEmpty()){
            int curr = queue.poll();
            result.add(curr);
            for (int neighbor : graph[curr]){
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0){
                    queue.add(neighbor);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int k = 3;
        int[][] rowConditions = {{1,2},{3,2}};
        int[][] colConditions = {{2,1},{3,2}};


        Solution solution = new Solution();
        int[][] resultMatrix = solution.buildMatrix(k, rowConditions, colConditions);

        if (resultMatrix.length == 0) {
            System.out.println("[]");
        } else {
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    System.out.print(resultMatrix[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}
