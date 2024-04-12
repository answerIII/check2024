#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Solution{
public:
    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values,vector<vector<string>>& queries) {
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

        for (auto string& i : vertex.size()) {
            std::cout << i << " ";
       }
        return {}
    }

private:
    string find(string first, string last; const string& value) {
        while (first != last) {
            if (*first == value) {
                return first;
            }
            ++first;
        }
        return last;
    }
}


int main() {
    Solution solution;
    vector<vector<string>> equations = { {"a", "b"}, {"b", "c"}, {"c", "d"} };
    vector<double> values = { 2.0, 3.0, 4.0 };
    vector<vector<string>> queries = { {"a", "d"}, {"b", "c"}, {"a", "e"} };

    solution.calcEquation(equations, values, queries);

    return 0;
}