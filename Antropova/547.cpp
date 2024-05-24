class Solution {
public:
    int findCircleNum(vector<vector<int>>& isConnected) {
        int province_counter = 0;
        int count_city = isConnected.size();
        
        //array of visited cities
        bool is_visited[count_city];
        for (int i=0; i<count_city; ++i) {
            is_visited[i]=false;
        }

        //recursive dfs lambda-function
        std::function<void(int)> dfs=[&](int index_city){
            is_visited[index_city]=true;
            for (int i=0; i<count_city; ++i){
                //is city unvisited and isConnected[][]==1
                if(is_visited[i]==false && isConnected[index_city][i]){
                    dfs(i);
                }
            }
        };

        //see all the cities and count the provinces  
        for (int i=0; i<count_city;++i){
            if(is_visited[i]==false){
                dfs(i);
                ++province_counter;
            }
        }
        return province_counter;
    }
};
