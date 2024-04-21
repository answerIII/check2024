package leetcode310m;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> result = new ArrayList<>();
        if (n == 1){
            result.add(0);
            return result;
        }
        List<Set<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        List<Integer> leaves = new ArrayList<>();
        for (int i = 0; i < n; ++i){
            if (graph.get(i).size() == 1){
                leaves.add(i);
            }
        }

        while (n > 2){
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<>();
            for (int leaf : leaves){
                int parent = graph.get(leaf).iterator().next();
                graph.get(parent).remove(leaf);
                if (graph.get(parent).size()==1){
                    newLeaves.add(parent);
                }
            }
            leaves = newLeaves;
        }
        result.addAll(leaves);
        return result;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};

        Solution solution = new Solution();
        List<Integer> roots = solution.findMinHeightTrees(n, edges);

        System.out.println(roots);
    }
}
