//#include <iostream>
//#include <vector>
//#include <string>
//#include <queue>
//
//using namespace std;
//
//class Solution {
//public:
//    vector<int> loudAndRich(vector<vector<int>>& richer, vector<int>& quiet) {
//        //init
//        int n = quiet.size();
//        vector<vector<int>> graph(n);
//        for (auto& person : richer) {
//            int more = person[0];
//            int less = person[1];
//            graph[less].push_back(more);
//        }
//
//        /* for (int i = 0; i < n; ++i) {
//             cout << i << ": ";
//             for (int j : graph[i]) {
//                 cout << j << " ";
//             }
//             cout << endl;
//         }*/
//
//
//        vector<int> res(n, -10);
//
//        for (int i = 0; i < n; ++i) {
//            if (res[i] == -10) { // if don't know
//                dfs(i, graph, quiet, res);
//            }
//        }
//
//        return res;
//    }
//
//private:
//    void dfs(int start, vector<vector<int>>& graph, vector<int>& quiet, vector<int>& res) {
//        stack<int> nodesToVisit;
//        vector<bool> visitedV(graph.size(), false);
//
//        nodesToVisit.push(start);
//        visitedV[start] = true;
//
//        int min = start;
//        while (!nodesToVisit.empty()) {
//            int node = nodesToVisit.top();
//            nodesToVisit.pop();
//
//            // if there is more quiet
//            if (quiet[min] > quiet[node]) {
//                min = node;
//            }
//
//            for (int i : graph[node]) {
//                if (!visitedV[i]) {
//                    nodesToVisit.push(i);
//                    visitedV[i] = true;
//                }
//            }
//        }
//
//        res[start] = min;
//    }
//};
//
//
//int main() {
//    Solution solution;
//    vector<vector<int>> richer = { {1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3} };
//    //vector<vector<int>> richer = { };
//    vector<int> quiet = { 3,2,5,4,6,1,7,0 };
//    //vector<int> quiet = { 0 };
//    vector<int> result = solution.loudAndRich(richer, quiet);
//
//    for (int i : result) {
//        cout << i << " ";
//    }
//    cout << endl;
//
//
//    return 0;
//}




//better speed (prev was correct but slow)


#include <iostream>
#include <vector>
#include <stack>
#include <algorithm>

using namespace std;

class Solution {
public:
    vector<int> loudAndRich(vector<vector<int>>& richer, vector<int>& quiet) {
        int n = quiet.size();
        vector<vector<int>> graph(n);
        vector<int> degree(n, 0);
        vector<int> res(n);

        for (auto& person : richer) {
            int more = person[0];
            int less = person[1];
            graph[less].push_back(more);
            ++degree[more];
        }

        // Topolog sort
        vector<int> topoOrder;
        stack<int> st;
        for (int i = 0; i < n; ++i) {
            if (degree[i] == 0) { // those who poor 
                st.push(i);
            }
        }
        while (!st.empty()) {
            int node = st.top();
            st.pop();
            topoOrder.push_back(node);
            for (int neighbor : graph[node]) {
                if (--degree[neighbor] == 0) {
                    st.push(neighbor);
                }
            }
        }
        reverse(topoOrder.begin(), topoOrder.end());

       
        for (int node : topoOrder) {
            res[node] = node; 
            for (int neighbor : graph[node]) {
                if (quiet[res[node]] > quiet[res[neighbor]]) {
                    res[node] = res[neighbor]; 
                }
            }
        }

        return res;
    }
};

int main() {
    Solution solution;
    //vector<vector<int>> richer = { {1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3} };
    vector<vector<int>> richer = { };
    //    vector<int> quiet = { 3,2,5,4,6,1,7,0 };
    vector<int> quiet = { 0 };
    vector<int> result = solution.loudAndRich(richer, quiet);

    for (int i : result) {
        cout << i << " ";
    }
    cout << endl;

    return 0;
}
