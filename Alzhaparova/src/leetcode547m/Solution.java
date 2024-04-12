package leetcode547m;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] visited = new boolean[n];
        int provinces = 0;

        for (int i = 0; i < n; ++i){
            if (!visited[i]){
                dfs(isConnected, visited, i);
                ++provinces;
            }
        }

        return provinces;

    }

    public void dfs(int[][] isConnected, boolean[] visited, int i){
        visited[i] = true;
        for (int j = 0; j < isConnected.length; ++j){
            if (isConnected[i][j] == 1 && !visited[j]){
                dfs(isConnected, visited, j);
            }
        }
    }

    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        Solution solution = new Solution();
        int provinces = solution.findCircleNum(isConnected);
        System.out.println(provinces);
    }
}