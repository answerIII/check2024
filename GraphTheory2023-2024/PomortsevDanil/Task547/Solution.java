class Solution {
    public int findCircleNum(int[][] isConnected) {
        Set<Integer> passedVertices = new HashSet<>();
        int result = 0;
        for(int i = 0; i < isConnected.length; ++i){
            if(!passedVertices.contains(i)){
                dfs(isConnected, passedVertices, i);
                ++result;
            }
        }
        return result;
    }

    public static void dfs(int[][] graph, Set<Integer> passedVertices, int currVertices){
        passedVertices.add(currVertices);
        for(int i = 0; i < graph.length; ++i){
            if(graph[currVertices][i] == 1 && !passedVertices.contains(i)){
                dfs(graph, passedVertices, i);
            }
        }
    }
}