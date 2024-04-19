from collections import defaultdict

class Graph:
    def __init__(self):

        self.graph = defaultdict(list)

    def addEdge(self, u, v):
        self.graph[u].append(v)

    def DFSUtil(self, v, visited, prob, t, target, dist):
        visited.add(v)
        
        for neighbour in self.graph[v]:
            if neighbour not in visited:
                dist[neighbour] = dist[v] + 1
                if(v == 1 and len(self.graph[v]) == 1): continue
                prob[neighbour] = prob[v]/(len(self.graph[v]) - 1)

                self.DFSUtil(neighbour, visited, prob, t, target, dist)

    def DFS(self, v, n, t, target):
        prob = [0.0] * (n + 1)
        prob[1] = 1.0
        dist = [0] * (n + 1)
        visited = set()
        self.graph[1].append(0)
        self.DFSUtil(v, visited, prob, t, target, dist)
        return [prob, dist]

class Solution:
    def frogPosition(self, n: int, edges: List[List[int]], t: int, target: int) -> float:
        graph = Graph()
        for edge in edges:
            graph.addEdge(edge[0], edge[1])
            graph.addEdge(edge[1], edge[0])
        res = graph.DFS(1, n, t, target)
        print(res[1], 2)
        if(res[1][target] == t or (res[1][target] < t and len(graph.graph[target]) == 1)):
            return res[0][target]
        return 0.0
