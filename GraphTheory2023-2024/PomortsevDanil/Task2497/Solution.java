class Solution {
    static public int maxStarSum(int[] vals, int[][] edges, int k) {
        List<Integer> adjacencyList[] = new ArrayList[vals.length];
        for (int i = 0; i < adjacencyList.length; ++i)
            adjacencyList[i] = new ArrayList<>();
        for(int i = 0; i < edges.length; ++i){
            adjacencyList[edges[i][1]].add(vals[edges[i][0]]);
            adjacencyList[edges[i][0]].add(vals[edges[i][1]]);
        }
        int result = Integer.MIN_VALUE;
        for(int currVert = 0; currVert < vals.length; ++currVert){
            int currSum = vals[currVert];
            adjacencyList[currVert].sort((a, b) -> b - a);
            for(int i = 0; i < k; ++i){
                if(i >= adjacencyList[currVert].size()){
                    break;
                }
                if(adjacencyList[currVert].get(i) < 0){
                    break;
                }
                currSum += adjacencyList[currVert].get(i);
            }
            result = Math.max(result, currSum);
        }
        return result;
    }
}