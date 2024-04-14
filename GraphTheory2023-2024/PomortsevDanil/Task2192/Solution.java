class Solution {
    static public List<List<Integer>> getAncestors(int n, int[][] edges) {
        Graph graph = new Graph(n);
        for(int i = 0; i < edges.length; ++i){
            graph.addEdge(edges[i][1], edges[i][0]);
        }
        List<List<Integer>> result = new ArrayList<>(n);
        for(int currVert = 0; currVert < n; ++currVert){
            List<Integer> currParent = new ArrayList<>();
            Set<Integer> passedVert = new HashSet<>();
            dfs(currVert, graph, currParent, passedVert);
            Collections.sort(currParent);
            result.add(currParent);
        }
        return result;
    }

    static void dfs(int startVertices, Graph graph, List<Integer> currParent, Set<Integer> passedVert){
        passedVert.add(startVertices);
        for(int vertices: graph.getAdjacencyList(startVertices)){
            if(!passedVert.contains(vertices)){
                currParent.add(vertices);
                dfs(vertices, graph, currParent, passedVert);
            }
        }
    }

    static class Graph {
        private List<Integer> adjacencyList[];

        public Graph(int quanVerts) {
            adjacencyList = new LinkedList[quanVerts];
            for (int i = 0; i < adjacencyList.length; ++i)
                adjacencyList[i] = new LinkedList();
        }

        public void addEdge(int v, int w) {
            adjacencyList[v].add(w);
        }

        public List<Integer> getAdjacencyList(int n){
            return adjacencyList[n];
        }
    }
}