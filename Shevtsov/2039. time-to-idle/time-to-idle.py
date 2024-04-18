from collections import defaultdict, deque

class Graph:
    def __init__(self):
        self.adjList = defaultdict(list)

    def addEdge(self, u, v):
        self.adjList[u].append(v)

    def bfs(self, startNode: int, n: int):
        dist = [0] * n   
        queue = deque()
        visited = [False] * n
        visited[startNode] = True
        queue.append(startNode)
        while queue:
            currentNode = queue.popleft()
            for neighbor in self.adjList[currentNode]:
                if not visited[neighbor]:
                    visited[neighbor] = True
                    dist[neighbor] = dist[currentNode] + 1                    
                    queue.append(neighbor)
        return dist

class Solution:
    def networkBecomesIdle(self, edges: List[List[int]], patience: List[int]) -> int:
        n = len(patience)
        graph = Graph()
        for edge in edges:
            graph.addEdge(edge[0], edge[1])
            graph.addEdge(edge[1], edge[0])
        
        dist = graph.bfs(0, n)
        lastMesTime = [0] * n
        for i in range(1, n):
            path = 2*dist[i]
            if(path <= patience[i]):
                lastMesTime[i] = path
            elif(path % patience[i] == 0):
                lastMesTime[i] = 2*path - patience[i]
            else:
                lastMesTime[i] = 2*path - (path % patience[i])
        res = max(lastMesTime) + 1
        return res
