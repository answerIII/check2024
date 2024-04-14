class Solution {
    static public boolean canFinish(int numCourses, int[][] prerequisites) {
        Graph graph = new Graph(numCourses);
        for(int i = 0; i < prerequisites.length; ++i){
            graph.addEdge(prerequisites[i][0], prerequisites[i][1]);
        }
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < numCourses; ++i){
            if(!dfs(i, graph)){
                return false;
            }
        }
        return true;
    }

    static boolean dfs(int startVertices, Graph graph){
        graph.setVerticesColor(startVertices,1);
        for(int vertices: graph.getAdjacencyList(startVertices)){
            if(graph.verticesColor(vertices) == 0){
                if(!dfs(vertices, graph))
                    return false;
            }
            else if(graph.verticesColor(vertices) == 1)
                return false;
        }
        graph.setVerticesColor(startVertices,2);
        return true;
    }
    static class Graph {
        private List<Integer> verticesList;
        private List<Integer> adjacencyList[];

        public Graph(int n) {
            verticesList = new ArrayList<>(n);
            for(int i = 0; i < n; ++i){
                verticesList.add(0);
            }
            adjacencyList = new LinkedList[n];
            for (int i = 0; i < n; ++i)
                adjacencyList[i] = new LinkedList();
        }

        public void addEdge(int v, int w) {
            adjacencyList[v].add(w);
        }

        public int verticesColor(int number){
            return verticesList.get(number);
        }

        public void setVerticesColor(int number, int color){
            verticesList.set(number, color);
        }

        public List<Integer> getAdjacencyList(int n){
            return adjacencyList[n];
        }
    }
}