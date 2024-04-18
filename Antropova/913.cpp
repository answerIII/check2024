class Solution {
public:
    int catMouseGame(vector<vector<int>>& graph) {
        int count_nodes=graph.size();

        //memorization [mouse_pos][cat_pos][turn 0/1]
        vector<vector<vector<int>>> memorization (count_nodes, vector<vector<int>>(count_nodes, vector<int>(2)));
        vector<vector<vector<int>>> ver_out_degree (count_nodes, vector<vector<int>>(count_nodes, vector<int>(2)));
        //создадим очередь отслеживания состояний - ход(чет/нечет), положение мыши, положение кота, статус игры
        //0 - ничья, 1 - победа мыши, 2 - победа кота
        queue<tuple<int,int,int,int>> q;

        //заполним известные положения в случае победы
        //четные ходы %2 == 0 - мышь, нечетные %2==1 - кот
        for (int i = 1; i < count_nodes; ++i){
            for (int turn = 0; turn <= 1; ++turn){
                memorization[i][i][turn] = 2;
                q.emplace(turn, i, i, 2);

                memorization[0][i][turn] = 1;
                q.emplace(turn, 0, i, 1);
            }
        }

        //посчитаем степени вершин
        //четные ходы %2 == 0 - мышь, нечетные %2==1 - кот
        for (int m = 0; m < count_nodes; ++m){
            for (int c = 0; c < count_nodes; ++c){
                ver_out_degree[m][c][0] = graph[m].size();
                ver_out_degree[m][c][1] = graph[c].size();
                for (int i = 0; i < graph[c].size(); ++i){
                    if (graph[c][i] == 0){
                    --ver_out_degree[m][c][1];
                    }
                }             
            }
        }

        //рассмотрим ход игры
        while (!q.empty()){
            tuple <int, int, int, int> tmp = q.front();
            int turn = get<0>(tmp);
            int mouse_pos = get<1>(tmp);
            int cat_pos = get<2>(tmp);
            int game_status = get<3>(tmp);
            q.pop();

            if (mouse_pos == 1 && cat_pos == 2 && turn == 0){
                return game_status;
            }

            int last_turn = (turn == 0) ? 1 : 0;

            for (int last_pos : graph[last_turn ? cat_pos : mouse_pos]){
                int last_mouse_pos = last_turn ? mouse_pos : last_pos;
                int last_cat_pos = last_turn ? last_pos : cat_pos;
                if (last_cat_pos == 0) {
                    continue;
                }
                //если исход игр уже посчитали
                if (memorization[last_mouse_pos][last_cat_pos][last_turn] != 0){
                    continue;
                }

                if (--ver_out_degree[last_mouse_pos][last_cat_pos][last_turn] == 0 || last_turn == 0 && game_status == 1 || last_turn == 1 && game_status == 2) {
                    memorization[last_mouse_pos][last_cat_pos][last_turn] = game_status;
                    q.emplace(last_turn, last_mouse_pos, last_cat_pos, game_status);
                }
            }

        }
        return memorization [1][2][0];
    }
};
