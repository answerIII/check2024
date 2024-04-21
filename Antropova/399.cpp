class Solution {
public:
    vector<double> calcEquation(vector<vector<string>>& equations, vector<double>& values, vector<vector<string>>& queries) {
        //строим ориентированный граф
        map <string, vector<pair<string, double>>> graph;
        for (int i = 0; i < equations.size(); ++i){
            graph[equations[i][0]].push_back(make_pair(equations[i][1], values[i]));
            graph[equations[i][1]].push_back(make_pair(equations[i][0], 1.0 / values [i]));             
        }

        vector<double> res;
        for (int i = 0; i < queries.size(); ++i){
            res.push_back(bfs(queries[i][0], queries[i][1], graph));
        }

        return res;
    }

    double bfs (string x, string y, map <string, vector<pair<string, double>>>& graph){
        //случай 1 : в графе такая вершина отсутствует
        if (!graph.contains(x)){
            return -1.0;
        }
        //случай 2 : переменная делится на себя
        if (x == y) {
            return 1.0;
        }
        //случай 3 : ищем ответ в графе
        set <string> visited;
        deque <pair<string, double>> q;

        q.push_back(make_pair(x, 1.0));
        visited.insert(x);

        while (!q.empty()){
            pair<string, double> tmp = q.front();
            string node = tmp.first;
            double value = tmp.second;
            q.pop_front();

            if (y == node){
                return value;
            }

            for (pair<string, double> tmp_pair : graph[node]){
                if (!visited.contains(tmp_pair.first)){
                    q.push_back(make_pair(tmp_pair.first, tmp_pair.second * value));
                    visited.insert(tmp_pair.first);
                }
            }
        }

        return -1.0;
    }
};
