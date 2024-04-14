import java.util.*;
class Solution {
    static public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Set<String> setVertices = new HashSet<>();
        equations.forEach((pair) -> {
            setVertices.add(pair.get(0));
            setVertices.add(pair.get(1));
        });
        Graph graph = new Graph(setVertices);
        for(int i = 0; i < values.length; ++i){
            graph.addEdge(equations.get(i).get(0), equations.get(i).get(1), values[i]);
            graph.addEdge(equations.get(i).get(1), equations.get(i).get(0), 1.0/values[i]);
        }

        double[] result = new double[queries.size()];
        int itr = 0;

        for(List<String> quarie: queries){
            result[itr] = dfs(quarie.get(0), quarie.get(1), new HashSet<>(), graph);
            ++itr;
        }
        return result;
    }

    static public double dfs(String startVertices, String endVertices, Set<String> passedVertices, Graph graph){
        if(!graph.verInGraph(startVertices) || !graph.verInGraph(endVertices)) return -1.0;
        if(startVertices.equals(endVertices)) return 1.0;
        passedVertices.add(startVertices);
        for(Edge currVertices: graph.getAdjacencyList(startVertices)){
            if(!passedVertices.contains(currVertices.vertices)){
                double result = dfs(currVertices.vertices, endVertices, passedVertices, graph);
                if (result != -1.0) return result * currVertices.value;
            }
        }
        return -1.0;
    }
    static class Graph {

        private Set<String> verticesSet = new HashSet<>();
        private HashMap<String,List<Edge>> adjacencyList;

        public Graph(Collection<String> vertices) {
            verticesSet.addAll(vertices);
            adjacencyList = new HashMap<>();
            verticesSet.forEach((currVertices) -> adjacencyList.put(currVertices, new ArrayList<>()));
        }

        public void addEdge(String v, String w, Double value) {
            adjacencyList.get(v).add(new Edge(w,value));
        }

        public List<Edge> getAdjacencyList(String v){
            return adjacencyList.get(v);
        }

        public boolean verInGraph(String vertices){
            return  verticesSet.contains(vertices);
        }
    }
    static class Edge{
        String vertices;
        double value;
        public Edge(String vertices, double value){
            this.vertices=vertices;
            this.value=value;
        }
    }
}