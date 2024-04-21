package leetcode;

import java.util.ArrayList;
import java.util.List;

class Edge {
    private int u;
    private int v;
    private int w;

    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public int getU() {
        return u;
    }

    public int getV() {
        return v;
    }

    public int getW() {
        return w;
    }
}

class Solution2492 {
    private List<Edge>[] graph;
    private Integer score = Integer.MAX_VALUE;
    private boolean[] visited;

    public int minScore(int n, int[][] roads) {
        graph = new List[n + 1];
        visited = new boolean[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] road : roads) {
            graph[road[0]].add(new Edge(road[0], road[1], road[2]));
            graph[road[1]].add(new Edge(road[1], road[0], road[2]));
        }

        visited = new boolean[n + 1];
        visited[1] = true;

        dfs(1);
        return score;
    }
    private void dfs(int currVert) {
        for (Edge edge : graph[currVert]) {
            int nextVert = edge.getV();
            int dist = edge.getW();
            score = Math.min(score, dist);
            if (!visited[nextVert]) {
                visited[nextVert] = true;
                dfs(nextVert);
            }
        }
    }
}
