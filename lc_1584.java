package leetcode;

class Solution1584 {
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        int[][] graph = new int[n][n];

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                int md = Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
                graph[i][j] = md;
                graph[j][i] = md;
            }
        }

        return findSpanningTree(graph, n);
    }

    private int findSpanningTree(int[][] graph, int n) {
        int[] verts = new int[n];
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            verts[i] = Integer.MAX_VALUE;
        }
        verts[0] = 0;

        for (int i = 0; i < n; i++) {
            int u = minVert(visited, verts);
            visited[u] = true;

            for (int v = 0; v < n; v++) {
                if (!visited[v] && graph[u][v] < verts[v]) {
                    verts[v] = graph[u][v];
                }
            }
        }

        int res = 0;
        for (int i : verts) {
            res += i;
        }
        return res;
    }

    private int minVert(boolean[] visited, int[] verts) {
        int minIndex = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < verts.length; i++) {
            if (!visited[i] && verts[i] < min) {
                minIndex = i;
                min = verts[i];
            }
        }
        return minIndex;
    }
}
