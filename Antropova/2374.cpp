class Solution {
public:
    int edgeScore(vector<int>& edges) {
        vector<unsigned int> edge_score_of_node (edges.size());
        //рассчитываем оценки 
        for (int i = 0; i < edge_score_of_node.size(); ++i){
            edge_score_of_node[edges[i]] += i;
        }

        int answer = 0;
        int max_score = edge_score_of_node[0];

        for (int i = 0; i < edge_score_of_node.size(); ++i){
            if (edge_score_of_node[i] > max_score){
                max_score = edge_score_of_node[i];
                answer = i;
            }
        }

        return answer;
    }
};
