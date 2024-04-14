class Solution {
    static public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Graph graph = new Graph(n);
        for(int i = 0; i < connections.size(); ++i){
            graph.addEdge(connections.get(i).get(0), connections.get(i).get(1));
            graph.addEdge(connections.get(i).get(1), connections.get(i).get(0));
        }

        Set<Integer> passedVertices = new HashSet<>();
        int[] enter = new int[n];
        int[] ret = new int[n];
        List<List<Integer>> result = new LinkedList<>();
        for(int currVert = 1; currVert <= n; ++currVert){
            if(!passedVertices.contains(currVert)){
                dfs(currVert, -1, graph, passedVertices, 0, enter, ret, result);
            }
            if(passedVertices.size() == n){
                break;
            }
        }
        return result;
    }

    static void dfs(int startVertices, int parent, Graph graph, Set<Integer> passedVertices, int time, int[] enter, int[] ret, List<List<Integer>> result){
        ++time;
        enter[startVertices] = time;
        ret[startVertices] = time;
        passedVertices.add(startVertices);
        for(int vertices: graph.getAdjacencyList(startVertices)){
            if(!passedVertices.contains(vertices)){
                dfs(vertices, startVertices, graph, passedVertices, time, enter, ret, result);
                ret[startVertices] = Math.min(ret[startVertices], ret[vertices]);
                if(ret[vertices] > enter[startVertices]){
                    ArrayList<Integer> addition = new ArrayList<>();
                    addition.add(vertices);
                    addition.add(startVertices);
                    result.add(addition);
                }
            }
            else if(vertices != parent){
                ret[startVertices] = Math.min(ret[startVertices], enter[vertices]);
            }
        }
    }
    static class Graph {

        private List<Boolean> verticesList;
        private List<Integer> adjacencyList[];

        public Graph(int quanVerts) {
            verticesList = new ArrayList<>(quanVerts+1);
            for (int i = 0; i < quanVerts; ++i)
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