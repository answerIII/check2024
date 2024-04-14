class Solution {
    static public int[] minEdgeReversals(int n, int[][] edges) {
        Graph graph = new Graph(n);
        Set<Para> edgeSet = new HashSet<>();
        for (int[] edge : edges) {
            edgeSet.add(new Para(edge[0], edge[1]));
            graph.addEdge(edge[0], edge[1]);
            graph.addEdge(edge[1], edge[0]);
        }
        int[] result = new int[n];
        AtomicInteger currQuanReverse = new AtomicInteger(0);
        dfs(0, null, graph, currQuanReverse, edgeSet);
        result[0] = currQuanReverse.intValue();
        filling(0, null, graph, edgeSet, result, result[0]);
        return result;
    }

    static void dfs(int startVertices, Integer parent, Graph graph, AtomicInteger quanReverse, Set<Para> edgeSet){
        for(int vertices: graph.getAdjacencyList(startVertices)){
            if(parent != null && vertices == parent)
                continue;
            if(!edgeSet.contains(new Para(startVertices, vertices))){
                quanReverse.incrementAndGet();
            }
            dfs(vertices, startVertices, graph, quanReverse, edgeSet);
        }
    }

    static void filling(int startVertices, Integer parent, Graph graph, Set<Para> edgeSet, int[] result, int pastVal){
        for(int vertices: graph.getAdjacencyList(startVertices)){
            if(parent != null && vertices == parent)
                continue;
            if(!edgeSet.contains(new Para(startVertices, vertices))){
                result[vertices] = pastVal - 1;
                filling(vertices, startVertices, graph, edgeSet, result, pastVal - 1);
            }
            else{
                result[vertices] = pastVal + 1;
                filling(vertices, startVertices, graph, edgeSet, result, pastVal + 1);
            }
        }
    }
    static class Graph {
        List<Integer>[] adjacencyList;

        Set<Integer>[] passedVert;

        int[] qurrQuanReverse;
        public Graph(int n) {
            qurrQuanReverse = new int[n];
            passedVert = new HashSet[n];
            for (int i = 0; i < n; ++i)
                passedVert[i] = new HashSet<>();
            adjacencyList = new ArrayList[n];
            for (int i = 0; i < n; ++i)
                adjacencyList[i] = new ArrayList<>();
        }

        public void addEdge(int v, int w) {
            adjacencyList[v].add(w);
        }

        public List<Integer> getAdjacencyList(int n){
            return adjacencyList[n];
        }
    }

    static class Para{
        int first;
        int second;

        public Para(int first, int second){
            this.first=first;
            this.second=second;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Para other = (Para) obj;
            return (first == other.first && second == other.second);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, second);
        }
    }
}