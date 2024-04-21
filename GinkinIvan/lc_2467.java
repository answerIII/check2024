package leetcode;

import java.util.ArrayList;
import java.util.List;

class Solution2467 {


    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        if (amount.length == 2) {
            return amount[0];
        }

        List<Integer>[] graph = new List[amount.length];
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        Node root = new Node(0);
        Node bobNode = dfsBob(root, bob, graph);
        int bobNodeLength = bobNode.dist + 1;
        amount[bobNode.index] = 0;
        int cnt = 0;
        while (cnt < bobNodeLength / 2) {
            amount[bobNode.index] = 0;
            bobNode = bobNode.parent;
            cnt++;
        }

        if (bobNodeLength % 2 == 1) {
            amount[bobNode.index] = amount[bobNode.index] / 2;
        }

        MaximumProfit maxProfit = new MaximumProfit(Integer.MIN_VALUE);
        dfsAlice(0, -1, graph, amount, amount[0], maxProfit);

        return maxProfit.getMaxProfit();
    }

    private Node dfsBob(Node node, int bobIndex, List<Integer>[] graph) {
        if (node.index == bobIndex) {
            return node;
        }
        for (int nodeIndex : graph[node.index]) {
            if (node.parent == null || node.parent.index != nodeIndex) {
                Node bobNode = dfsBob(new Node(nodeIndex, node), bobIndex, graph);
                if (bobNode != null) {
                    return bobNode;
                }
            }
        }
        return null;
    }

    private void dfsAlice(int index, int parentIndex, List<Integer>[] graph, int[] amount, int income, MaximumProfit max) {
        boolean isLeaf = true;
        for (int nodeIndex : graph[index]) {
            if (nodeIndex != parentIndex) {
                isLeaf = false;
                dfsAlice(nodeIndex, index, graph, amount, income + amount[nodeIndex], max);
            }
        }
        if (isLeaf) {
            if (income > max.getMaxProfit()) {
                max.setMaxProfit(income);
            }
        }
    }
}

class Node {
    int dist;
    int index;
    Node parent;

    public Node(int index) {
        this.index = index;
        this.dist = 0;
    }

    public Node(int index, Node parent) {
        this.index = index;
        this.parent = parent;
        this.dist = parent.dist + 1;
    }
}

class MaximumProfit {
    private int maxProfit;

    public MaximumProfit(int maxProfit) {
        this.maxProfit = maxProfit;
    }

    public int getMaxProfit() {
        return maxProfit;
    }

    public void setMaxProfit(int maxProfit) {
        this.maxProfit = maxProfit;
    }
}
