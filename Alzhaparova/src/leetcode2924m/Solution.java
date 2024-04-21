package leetcode2924m;

public class Solution {
    public int findChampion(int n, int[][] edges) {
        int[] candidates = new int[n];

        for (int[] edge : edges){
            int child = edge[1];
            candidates[child]++;
        }

        int count = 0;
        int champion = -1;
        for (int i = 0; i < n; ++i){
            if (candidates[i] == 0){
                count++;
                champion = i;
            }
        }

        if (count > 1) {
            return -1;
        }

        return champion;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] edges = {{0, 1}, {1, 2}};
        Solution solution = new Solution();
        int champion = solution.findChampion(n, edges);
        System.out.println(champion);
    }
}
