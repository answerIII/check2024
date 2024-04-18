from typing import List 
from collections import defaultdict, deque

class Graph:
    def __init__(self):
        self.adjList = defaultdict(list)

    def addEdge(self, u, v):
        self.adjList[u].append(v)

    def bfs(self, startNode: int, n: int):
        childs = 0  
        queue = deque()
        visited = [False] * n
        visited[startNode] = True
        queue.append(startNode)
        while queue:
            currentNode = queue.popleft()
            for neighbor in self.adjList[currentNode]:
                if not visited[neighbor]:
                    visited[neighbor] = True
                    childs += 1
                    queue.append(neighbor)
        return childs

class Solution:
    def maximumDetonation(self, bombs: List[List[int]]) -> int:
        n = len(bombs)
        graph = Graph()
        for i in range(n):
            for j in range(n):
                if(i == j): continue
                if((bombs[i][0] - bombs[j][0])*(bombs[i][0] - bombs[j][0]) + (bombs[i][1] - bombs[j][1])*(bombs[i][1] - bombs[j][1]) <= bombs[i][2] * bombs[i][2]):
                    graph.addEdge(i, j)              
        bombCount = [0] * n
        for i in range(n):
            bombCount[i] = graph.bfs(i, n)
        
        return max(bombCount) + 1
