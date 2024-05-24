class Solution {
public:
    int maximumDetonation(vector<vector<int>>& bombs) {
        int bombs_count=bombs.size();
        int res_max_bombs=0;
        
        map<int, vector<int>> graph;
        unsigned long int dist=0;

        for (int i=0; i < bombs_count; ++i){
            for (int j=i+1; j < bombs_count; ++j){
                long int x1=bombs[i][0];
                long int y1=bombs[i][1];
                unsigned long int r1=bombs[i][2];
                long int x2=bombs[j][0];
                long int y2=bombs[j][1];
                unsigned long int r2=bombs[j][2];

                dist=pow((x1-x2),2) + pow((y1-y2),2);

                if (r1*r1 >= dist){
                    graph[i].push_back(j);
                }
                if (r2*r2 >= dist){
                    graph[j].push_back(i);
                }
            }
        }
        
        for (int i=0;i < bombs_count;++i){
            set<int> visit;        
            res_max_bombs=std::max(res_max_bombs,dfs(i,visit, graph));
        }
        return res_max_bombs;
    }

    int dfs(int i, set<int>& visit, map<int, vector<int>>& graph){
        if (visit.contains(i)){
            return 0;
        }
        visit.insert(i);
        //check graph[i] is exist
        if(graph.count(i)){
            for (int n : graph[i]){
                dfs(n, visit, graph);
            }
        }
        return visit.size();
    }
};
