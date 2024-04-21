package leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Solution2603 {
    private boolean isUselessLeaf(int index, List<Set<Integer>> list, int[] coins) {
        return list.get(index).size()==1 && coins[index]==0;
    }

    private boolean isLeaf(int index, List<Set<Integer>> list) {
        return list.get(index).size()==1;
    }

    private Integer deleteLeafAdjEdge(LinkedList<Integer> leafQueue, List<Set<Integer>> list) {
        int curVertex = leafQueue.poll();

        if (list.get(curVertex).size()==0) {
            return -1;
        }

        int adjVertex = list.get(curVertex).iterator().next();

        list.get(curVertex).remove(adjVertex);
        list.get(adjVertex).remove(curVertex);

        return adjVertex;
    }

    public int collectTheCoins(int[] coins, int[][] edges) {

        int n = edges.length;
        ArrayList<Set<Integer>> list = new ArrayList<>();
        LinkedList<Integer> leafQueue = new LinkedList<>();
        int totalEdges = n * 2;
        int deletedEdges = 0;

        for(int i=0;i<=n;i++){
            list.add(new HashSet<>());
        }

        for(int i=0;i<n;i++){
            int startVert = edges[i][0];
            int endVert = edges[i][1];
            list.get(startVert).add(endVert);
            list.get(endVert).add(startVert);
        }

        for(int i=0;i<=n;i++){
            if(isUselessLeaf(i,list,coins)){
                leafQueue.add(i);
            }
        }
        while(!leafQueue.isEmpty()){
            int adjVertex = deleteLeafAdjEdge(leafQueue,list);
            if (adjVertex == -1) {
                continue;
            }

            if(isUselessLeaf(adjVertex, list, coins)){
                leafQueue.add(adjVertex);
            }
            deletedEdges += 2;
        }

        for(int i=0;i<=n;i++){
            if(isLeaf(i,list)){
                leafQueue.add(i);
            }
        }

        int size=2;
        while(size>0){
            size--;
            int numOfLeaves = leafQueue.size();
            while(numOfLeaves>0){
                numOfLeaves--;
                int adjVertex = deleteLeafAdjEdge(leafQueue, list);
                if (adjVertex == -1) {
                    continue;
                }

                if(isLeaf(adjVertex, list)){
                    leafQueue.add(adjVertex);
                }
                deletedEdges += 2;
            }
        }

        return totalEdges - deletedEdges;
    }
}
