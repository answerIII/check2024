class Solution {
    static public boolean possibleBipartition(int n, int[][] dislikes) {
        Graph graph = new Graph(n);
        for(int i = 0; i < dislikes.length; ++i){
            graph.addEdge(dislikes[i][0], dislikes[i][1]);
            graph.addEdge(dislikes[i][1], dislikes[i][0]);
        }

        Set<Integer> passedVertices = new HashSet<>();
        for(int currVert = 1; currVert <= n; ++currVert){
            if(!passedVertices.contains(currVert)){
                if(graph.getAdjacencyList(currVert).size() == 0){
                    continue;
                }
                if(!dfs(currVert, graph, passedVertices, true)){
                    return false;
                }
            }
            if(passedVertices.size() == n){
                break;
            }
        }
        return true;
    }

    static boolean dfs(int startVertices, Graph graph, Set<Integer> passedVertices, boolean lastColor){
        graph.setVerticesColor(startVertices,!lastColor);
        passedVertices.add(startVertices);
        for(int vertices: graph.getAdjacencyList(startVertices)){
            if(!passedVertices.contains(vertices)){
                boolean result = dfs(vertices, graph, passedVertices, !lastColor);
                if(!result)
                    return false;
            }
            else if(graph.verticesColor(vertices)== !lastColor){
                return false;
            }
        }
        return true;
    }
    static class Graph {

        private List<> verticesList;
        private List<Integer> adjacencyList[];

        public Graph(int quanVerts) {
            verticesList = new ArrayList<>(quanVerts+1);
            for (int i = 0; i < quanVerts + 1; ++i)
                verticesList.add(i,Boolean.TRUE);
            adjacencyList = new LinkedList[verticesList.size()];
            for (int i = 0; i < verticesList.size(); ++i)
                adjacencyList[i] = new LinkedList();
        }

        public void addEdge(int v, int w) {
            adjacencyList[v].add(w);
        }

        public boolean verticesColor(int number){
            return verticesList.get(number);
        }

        public void setVerticesColor(int number, boolean color){
            verticesList.set(number, color);
        }

        public List<Integer> getAdjacencyList(int n){
            return adjacencyList[n];
        }
    }
}