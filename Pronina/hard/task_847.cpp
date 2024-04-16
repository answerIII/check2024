#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

class Solution {
public:
    int shortestPathLength(vector<vector<int>>& graph) {
        int n = graph.size();
        vector<vector<int>> dp(pow(2, n), vector<int>(n, INT_MAX)); // all of them are max for future (size for test1 16x4)

        //init
        queue<pair<int, int>> q; 
        for (int i = 0; i < n; ++i) {
            dp[pow(2, i)][i] = 0;   // i=0: dp = [0, intMax...] (len to vertex is 0 at the beginning)
            q.push({ 1 << i, i });
        }

        //bfs
        while (!q.empty()) {
            int mask = q.front().first; // Mask for current state (visited vertex)
            int cv = q.front().second;    // Current vertex
            q.pop();                    

            for (int v : graph[cv]) {
                int next_mask = mask | (1 << v); // Mask for the next state after visiting v
                if (dp[next_mask][v] == INT_MAX) {
                    dp[next_mask][v] = dp[mask][cv] + 1; 
                    q.push({ next_mask, v });           
                }
            }
        }

        
        int min_len = INT_MAX;
        for (int i = 0; i < n; ++i) {
            min_len = min(min_len, dp[(1 << n) - 1][i]);
        }

        return min_len;
    }
};


int main() {
    Solution solution;
    vector<vector<int>> graph1 = { {1,2,3},{0},{0},{0} };
    vector<vector<int>> graph2 = { {1},{0,2,4},{1,3,4},{2},{1,2} };

    cout << "Test1: " << solution.shortestPathLength(graph1) << endl;  
    cout << "Test2: " << solution.shortestPathLength(graph2) << endl; 

    return 0;
}