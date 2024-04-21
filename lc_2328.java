package leetcode;

class Solution2328 {
    int mod = (int) 1.0e9+7;
    public int countPaths(int[][] grid) {
        int n = grid.length;
        int m  = grid[0].length;
        int res = (n * m) % mod;

        Integer[][] dp = new Integer[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0; j<m; j++) {
                res = res + dfs(i,j,grid,dp);
                res = res % mod;
            }
        }
        return res;
    }


    public int dfs(int i, int j, int[][] grid, Integer[][] dp)
    {
        int n = grid.length;
        int m = grid[0].length;
        int count = 0;

        if(dp[i][j]!=null) return dp[i][j];
        if(i<0 || j<0 || i>=n || j>=m) return 0;

        if(i > 0 && grid[i][j] < grid[i-1][j]) count  = count + dfs(i-1, j, grid, dp) +1;
        if(j > 0 && grid[i][j] < grid[i][j-1]) count  = count + dfs(i, j-1, grid, dp) +1;
        if(i < n-1 && grid[i][j] < grid[i+1][j]) count  = count + dfs(i+1, j, grid, dp) +1;
        if(j < m-1 && grid[i][j] < grid[i][j+1]) count  = count + dfs(i, j+1, grid, dp) +1;

        count = count%mod;
        dp[i][j] = count;
        return count;
    }
}
