#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

class Solution {
public:
    int networkBecomesIdle(vector<vector<int>>& edges, vector<int>& patience) {
        int n = patience.size();

        // Init
        vector<vector<int>> graph(n);
        for (const auto& edge : edges) {
            graph[edge[0]].push_back(edge[1]);
            graph[edge[1]].push_back(edge[0]);
        }
        
        /*for (int i = 0; i < n; ++i) {
            cout << i << "   : ";
            for (auto e : graph[i]) {
                cout << e << " ";
            }
            cout << endl;
        }*/

        // BFS 
        vector<int> distance(n, 0);
        queue<int> q;
        q.push(0);
        vector<bool> visited(n, false);
        visited[0] = true;
        while (!q.empty()) {
            int curr = q.front(); 
            q.pop();
            for (int next : graph[curr]) { 
                if (!visited[next]) { 
                    visited[next] = true; 
                    distance[next] = distance[curr] + 1; 
                    q.push(next); 
                }
            }
        }

        
        int res = 0;
        for (int i = 1; i < n; ++i) { // 0 doesn't count
            int time = distance[i] * 2; // forward and back
            int k = (time - 1) / patience[i]; 
            int lastM = (k * patience[i]) + time; // last message
            res = max(res, lastM);
        }

        return ++res; // because of 0sec
    }
};

int main() {
    Solution solution;
    vector<vector<int>> edges1 = { {0,1}, {1,2} };
    vector<int> patience1 = { 0,2,1 };

    vector<vector<int>> edges2 = { {0,1}, {0,2}, {1,2} };
    vector<int> patience2 = { 0,10,10 };
    int result1 = solution.networkBecomesIdle(edges1, patience1);
    int result2 = solution.networkBecomesIdle(edges2, patience2);

    cout << result1 << endl;
    cout << result2 << endl;

    return 0;
}
