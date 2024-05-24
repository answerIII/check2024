#include <iostream>
#include <vector>
#include <stack>


using namespace std;

class Solution {
public:
    vector<bool> checkIfPrerequisite(int numCourses, vector<vector<int>>& prerequisites, vector<vector<int>>& queries) {
        int n = numCourses;
        vector<vector<int>> graph(n);
        for (const auto& pre : prerequisites) {
            graph[pre[0]].push_back(pre[1]);
        }

        vector<bool> result;
        for (const auto& query : queries) {
            result.push_back(dfs(query[0], query[1], graph));
        }

        return result;
    }

    bool dfs(int start, int target, const vector<vector<int>>& graph) {
        vector<bool> visited(graph.size(), false);
        stack<int> nodesToVisit;
        nodesToVisit.push(start);

        while (!nodesToVisit.empty()) {
            int current = nodesToVisit.top();
            nodesToVisit.pop();
            visited[current] = true;
            for (int neighbor : graph[current]) {
                if (neighbor == target) {
                    return true;
                }
                if (!visited[neighbor]) {
                    nodesToVisit.push(neighbor);
                }
            }
        }

        return false;
    }
};
 
int main() {
    Solution solution;
    vector<vector<int>> prerequisites = { {1,0} };
    vector<vector<int>> queries = { {0,1},{1,0} };
    int numCourses = 2;
    vector<bool> result = solution.checkIfPrerequisite(numCourses, prerequisites, queries);

    cout << "1: ";
    for (bool val : result) {
        cout << val << " ";
    }
    cout << endl;

    prerequisites = {};
    queries = { {1,0},{0,1} };
    numCourses = 2;
    result = solution.checkIfPrerequisite(numCourses, prerequisites, queries);

    cout << "2: ";
    for (bool val : result) {
        cout << val << " ";
    }
    cout << endl;

    prerequisites = { {1,2},{1,0},{2,0} };
    queries = { {1,0},{1,2} };
    numCourses = 3;
    result = solution.checkIfPrerequisite(numCourses, prerequisites, queries);

    cout << "3: ";
    for (bool val : result) {
        cout << val << " ";
    }
    cout << endl;

    prerequisites = { {6,3},{6,8},{6,5},{6,10},{6,0},{6,7},{6,4},{6,9},{6,1},{3,8},{3,10},{3,0},{3,7},{3,4},{3,2},{3,9},{3,1},{8,5},{8,10},{8,4},{8,2},{8,9},{5,10},{5,7},{5,4},{5,9},{5,1},{10,0},{10,7},{10,4},{10,2},{10,9},{0,7},{0,4},{0,2},{7,2},{7,9},{7,1},{4,2},{4,9},{4,1},{2,9},{2,1} };
    queries = { {2,1},{8,9},{6,7},{3,8},{4,10},{9,6},{4,2},{5,10},{3,5},{5,9},{10,7},{7,6},{7,10},{0,5},{2,8},{6,2},{9,7},{9,4},{5,0},{9,5},{0,9},{6,10},{8,9},{5,8},{8,9},{4,5},{1,10},{6,5},{5,9},{0,9},{2,6},{4,5},{9,1},{8,1},{9,10},{4,6},{6,4},{5,9},{7,1},{10,1},{9,6},{1,3},{2,0},{9,10},{5,9},{7,5},{9,6},{1,4},{3,1},{10,4},{5,6},{1,4},{4,3},{9,5},{4,5},{5,8},{5,6},{9,10},{9,10},{7,8},{5,6},{4,6},{3,5},{7,10},{8,10},{7,8},{0,4},{7,0},{8,3},{8,10},{2,4},{6,10},{0,1},{10,6},{7,2},{4,3},{2,3},{3,1},{1,4},{5,7},{4,10},{7,2},{6,8},{0,8},{4,3},{8,7},{0,3},{10,9},{5,7},{6,8},{8,5},{3,5},{9,5},{7,9},{7,9},{3,4},{7,6},{3,9},{2,0},{10,6},{7,6},{10,6},{4,3},{9,10},{3,7},{7,10},{6,1} };
    numCourses = 11;
    result = solution.checkIfPrerequisite(numCourses, prerequisites, queries);

    cout << "25: ";
    for (bool val : result) {
        cout << val << " ";
    }
    cout << endl;

    return 0;
}