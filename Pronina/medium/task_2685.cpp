#include <iostream>
#include <vector>
#include <string>
#include <stack>

using namespace std;

class Solution {
public:
    int countCompleteComponents(int n, vector<vector<int>>& edges) {
        //init
        vector<vector<short>> graph(n, vector<short>(n, 0));
        vector<bool> visitedV(n, false);
        for (const auto& e : edges) {
            int idx0 = e[0];
            int idx1 = e[1];
            graph[idx0][idx1] = 1; //exists
            graph[idx1][idx0] = 1;
        }

        int count = 0;
        for (int i = 0; i < n; ++i) {
            if (!visitedV[i]) {
                if (dfs(i, graph, visitedV)) {
                    ++count;
                }
            }
        }
        return count;

    }

private:
    bool dfs(int start, vector<vector<short>>& graph, vector<bool>& visitedV) {
        stack<int> nodesToVisit;
        vector<int> vertex; //in component

        nodesToVisit.push(start);
        vertex.push_back(start);
        visitedV[start] = true;


        while (!nodesToVisit.empty()) {
            int node = nodesToVisit.top();
            nodesToVisit.pop();
            for (int i = 0; i < graph.size(); ++i) {
                if (graph[node][i] == 1 && !visitedV[i]) {
                    nodesToVisit.push(i);
                    visitedV[i] = true;
                    vertex.push_back(i);
                }
            }
        }
        

        // check if complete
        for (int i = 0; i < vertex.size(); ++i) {
            for (int j = i + 1; j < vertex.size(); ++j) {
                if (graph[vertex[i]][vertex[j]] != 1 || graph[vertex[j]][vertex[i]] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
};


int main() {
    Solution solution;
    int n = 6;
    //vector<vector<int>> edges = { {0, 1}, {0, 2}, {1, 2}, {3, 4} };
    //vector<vector<int>> edges = { {0, 1}, {0, 2}, {1, 2}, {3, 4}, {3, 5} };
    vector<vector<int>> edges = { {0, 1}, {3, 4} };

    cout << solution.countCompleteComponents(n, edges) << endl;
    return 0;
}