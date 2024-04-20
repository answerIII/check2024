package leetcode2368m;

import java.util.*;

public class Solution {
    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        boolean[] visited = new boolean[n];
        for (int r : restricted) {
            visited[r] = true;
        }

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            graph[parent].add(child);
            graph[child].add(parent);
        }

        int count = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited[current]) {
                visited[current] = true;
                count++;
                for (int v : graph[current]) {
                    if (!visited[v]) {
                        stack.push(v);
                    }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {{0, 1}, {0, 2}, {0, 5}, {0, 4}, {3, 2}, {6, 5}};
        int[] restricted = {4, 2, 1};

        Solution solution = new Solution();
        int maxReachableNodes = solution.reachableNodes(n, edges, restricted);
        System.out.println(maxReachableNodes);
    }
}
