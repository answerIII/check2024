class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        map<int, vector<pair<int,int>>> graph;
        //в ответ положим максимально возможное по условию расстояние
        int res_min_dist=10000; 

        for (vector<int>& road : roads){
            //считаем вершины с 0й
            int src=road[0]-1;
            int dst=road[1]-1;
            int dist=road[2];
            graph[src].emplace_back(dst, dist);
            graph[dst].emplace_back(src,dist);
        }

        set<int> visit; 
        dfs(0, visit, graph, res_min_dist);
        return res_min_dist; 
    }

    void dfs(int i, set<int>& visit, map<int, vector<pair<int,int>>>& graph, int& res_min_dist){
        if(visit.contains(i)){
            return;
        }
        visit.insert(i);
        for (auto [n, dist] : graph[i]){
            res_min_dist=min(res_min_dist,dist);
            dfs(n, visit, graph, res_min_dist);
        }
    }
};
