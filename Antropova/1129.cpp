class Solution {
public:
    vector<int> shortestAlternatingPaths(int n, vector<vector<int>>& redEdges, vector<vector<int>>& blueEdges) {
        vector<int> answer_array;
        for (int i = 0; i<n; ++i){
            answer_array.push_back(-1);
        }

        map<int, vector<int>> red_edges;
        for (int i = 0; i < redEdges.size(); ++i){
            red_edges[redEdges[i][0]].push_back(redEdges[i][1]);
        }

        map<int, vector<int>> blue_edges;
        for (int i = 0; i < blueEdges.size(); ++i){
            blue_edges[blueEdges[i][0]].push_back(blueEdges[i][1]);
        }

        //создадим множество посещённых вершин, сохраняя цвет ребра 
        //N - неопределен, В - синий, R - красный
        set<pair<int,char>> visit;
        visit.insert(make_pair(0,'N'));

        //создадим двустороннюю очередь из ребер: храним вершину, предыдущий цвет ребра и длину пути
        deque <tuple<int,char,int>> deq;
        deq.push_back(make_tuple(0, 'N', 0));

        while (!deq.empty()){
            //забираем первый элемент из очереди
            tuple<int,char,int> first_elem = deq.front(); 
            deq.pop_front();
            int vertex=get<0>(first_elem);
            char color=get<1>(first_elem);
            int len=get<2>(first_elem);

            //обновляем ответ
            if (answer_array[vertex] == -1){
                answer_array[vertex] = len;
            }

            //выполняем действия в зависимости от цвета рассматриваемого ребра
            if(color=='B' or color=='N'){
                for (int neig_vertex : red_edges[vertex]){
                    if (!visit.contains(make_pair(neig_vertex,'R'))){
                        visit.insert(make_pair(neig_vertex,'R'));
                        deq.push_back(make_tuple(neig_vertex, 'R', len+1));
                    }
                }               
            }

            if(color=='R' or color=='N'){
                for (int neig_vertex : blue_edges[vertex]){
                    if (!visit.contains(make_pair(neig_vertex,'B'))){
                        visit.insert(make_pair(neig_vertex,'B'));
                        deq.push_back(make_tuple(neig_vertex, 'B', len+1));
                    }
                }               
            }
        }
        
        return answer_array;
    }
};
