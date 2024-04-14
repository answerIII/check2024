class Solution {
    static public int[] findRedundantConnection(int[][] edges) {
        Set<Integer> vertSet = new HashSet<>();
        for(int i = 0; i < edges.length; ++i){
            vertSet.add(edges[i][0]);
            vertSet.add(edges[i][1]);
        }
        Map<Para, Integer> input = new HashMap<>(edges.length);
        Graph graph = new Graph(vertSet);
        for(int i = 0; i < edges.length; ++i){
            graph.addEdge(edges[i][0], edges[i][1]);
            graph.addEdge(edges[i][1], edges[i][0]);
            input.put(new Para(edges[i][0], edges[i][1]), i);
        }

        Map<Integer, Integer> parentMap;
        List<Para> cycleList;
        for(int currVert: vertSet){
            parentMap = new HashMap<>();
            cycleList = new ArrayList<>();
            Integer lastVert = dfs(currVert, graph, null, parentMap);
            if(lastVert != null){
                int currVertBack = lastVert;
                while (parentMap.containsKey(currVertBack)){
                    cycleList.add(new Para(parentMap.get(currVertBack), currVertBack));
                    currVertBack = parentMap.get(currVertBack);
                    if(currVertBack == lastVert)
                        break;
                }
                int inMax = 0;
                Para currMaxEdge = null;
                for(Para edge: cycleList){
                    if(input.get(edge) == null){
                        Para reversePara = new Para(edge.second, edge.first);
                        if(inMax < input.get(reversePara)){
                            currMaxEdge = reversePara;
                            inMax = input.get(reversePara);
                        }
                    }
                    else if(inMax < input.get(edge)){
                        currMaxEdge = edge;
                        inMax = input.get(edge);
                    }
                }
                return new int[]{currMaxEdge.first, currMaxEdge.second};
            }
        }
        return new int[]{};
    }

    static Integer dfs(int startVertices, Graph graph, Integer parentVert, Map<Integer, Integer> parentMap){
        graph.setVerticesColor(startVertices,1);
        for(int vertices: graph.getAdjacencyList(startVertices)){
            if(parentVert != null && vertices == parentVert)
                continue;
            if(graph.verticesColor(vertices) == 0){
                parentMap.put(vertices,startVertices);
                Integer result = dfs(vertices, graph, startVertices, parentMap);
                if(result != null)
                    return result;
            }
            else if(graph.verticesColor(vertices) == 1){
                parentMap.put(vertices,startVertices);
                return vertices;
            }
        }
        graph.setVerticesColor(startVertices,2);
        return null;
    }
    static class Graph {

        private List<Integer> verticesList;
        private List<Integer> adjacencyList[];

        public Graph(Set<Integer> verts) {
            verticesList = new ArrayList<>(verts.size()+1);
            for (int i = 0; i < verts.size()+1; ++i)
                verticesList.add(i,0);
            adjacencyList = new ArrayList[verts.size()+1];
            for (int i = 0; i < verts.size()+1; ++i)
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