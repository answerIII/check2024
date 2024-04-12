#include <iostream>
#include <vector>
#include <string>
#include <utility>
#include <stack>

using namespace std;

class Solution {
public:
    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values, vector<vector<string>>& queries) {
        vector<vector<double>> graph;
        vector<string> vertex;

        //fill vertexes
        for (int i = 0; i < equations.size(); ++i) {
            for (const string& idx : equations[i]) {
                if (find(vertex.begin(), vertex.end(), idx) == vertex.end()) { // unique one
                    vertex.push_back(idx);
                }
            }
        }

        /*for (auto i : vertex) {
            std::cout << i << " ";
        }*/

        // init graph
        int n = vertex.size();
        graph.resize(n, vector<double>(n, -1.0));

        int idx0 = 0;
        int idx1 = 0;
        for (int i = 0; i < equations.size(); ++i) {
            const string& eq0 = equations[i][0];
            const string& eq1 = equations[i][1];
            idx0 = getIndex(eq0, vertex);
            idx1 = getIndex(eq1, vertex);
            graph[idx0][idx1] = values[i];
            graph[idx1][idx0] = 1.0 / values[i];
        }


        vector<double> results;
        idx0 = 0; idx1 = 0;
        for (const auto& q : queries) { //check query 
            const string& var0 = q[0];
            const string& var1 = q[1];
            idx0 = getIndex(var0, vertex);
            idx1 = getIndex(var1, vertex);
            if (idx0 == -1 || idx1 == -1) {
                results.push_back(-1.0);
                continue;
            }
            double result = dfs(idx0, idx1, graph);
            results.push_back(result);
        }

        return results;
    }

private:
    int getIndex(const string& v, const vector<string>& vertex) {
        auto i = find(vertex.begin(), vertex.end(), v);
        if (i != vertex.end()) {
            return distance(vertex.begin(), i);
        }
        return -1;
    }

    double dfs(int start, int end, vector<vector<double>>& graph) {
        int n = graph.size();
        vector<bool> visited(n, false);
        stack<int> nodesToVisit;
        stack<double> values;

        nodesToVisit.push(start);
        values.push(1.0);

        while (!nodesToVisit.empty()) {
            int node = nodesToVisit.top();
            double val = values.top();
            nodesToVisit.pop();
            values.pop();

            if (node == end) {
                return val;
            }

            visited[node] = true;
            for (int i = 0; i < n; ++i) {
                if (graph[node][i] != -1.0 && !visited[i]) {
                    nodesToVisit.push(i);
                    values.push(val * graph[node][i]);
                }
            }
        }

        return -1.0;
    }

    template<typename Iterator>
    Iterator find(Iterator first, Iterator last, const string& value) {
        while (first != last) {
            if (*first == value) {
                return first;
            }
            ++first;
        }
        return last;
    }



};


int main() {
    Solution solution;
    vector<vector<string>> equations = { {"a", "b"} };
    vector<double> values = { 0.5 };
    //vector<vector<string>> queries = { {"a", "c"}, {"b", "a"}, {"a", "e"}, {"a", "a"}, {"x", "x"}};
    //vector<vector<string>> queries = { {"a", "c"}, {"c", "b"}, {"bc", "cd"}, {"cd", "bc"} };
    vector<vector<string>> queries = { {"a", "b"}, {"b", "a"}, {"a", "c"}, {"x", "y"} };

    vector<double> results = solution.calcEquation(equations, values, queries);
    for (auto result : results) {
        cout << result << " ";
    }
    cout << endl;

    return 0;
}