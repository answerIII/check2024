class Solution {
public:
    vector<string> watchedVideosByFriends(vector<vector<string>>& watchedVideos, vector<vector<int>>& friends, int id, int level) {
        set <int> visited;
        deque <int> q;
        q.push_back(id);
        visited.insert(id);

        //BFS
        //в очередь собираем друзей заданного уровня
        while (level > 0){
            --level;
            int size_queue = q.size();
            for (int i = 0; i < size_queue; ++i){
                int node = q.front();
                q.pop_front();

                for (int friend_node : friends[node]){
                    if (!visited.contains(friend_node)){
                        q.push_back(friend_node);
                        visited.insert(friend_node);
                    }
                }
            }
        }

        //собираем фильмы, просмотренные найденными друзьями
        vector <string> res;
        map <string, int> watching_counter;

        while (!q.empty()){
            int watcher = q.front();
            q.pop_front();
            for (string film : watchedVideos[watcher]){
                ++watching_counter[film];
                if (find(res.begin(), res.end(), film) == res.end()){
                    res.push_back(film);
                } 
            }
        }

        //сортируем фильмы по частоте просмотра
        sort(res.begin(), res.end(), [&watching_counter](string i, string j){
            //если частота просмотра одинаковая - возвращаем в алфовитном порядке
            if (watching_counter[i] == watching_counter[j]){
                return (i < j);
            }
            return (watching_counter[i] < watching_counter[j]);
            });
        return res;
    }
};
