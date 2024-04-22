class Solution:
    def maximalNetworkRank(self, n: int, roads: List[List[int]]) -> int:
        if(len(roads) == 0):
            return 0
        if(len(roads) == n*(n-1)/2):
            return 2*n - 3
        
        deg = [0] * (n + 1)
        graph = [[False for column in range(n + 1)]
                      for row in range(n + 1)]
        for road in roads:
            deg[road[0]] += 1
            deg[road[1]] += 1
            graph[road[0]][road[1]] = True
            graph[road[1]][road[0]] = True
        
        curMaxRank = 0
        for i in range(n):
            for j in range(n):
                if(i == j):
                    continue
                cm_cpm = graph[i][j]
                if(deg[i] + deg[j] >= curMaxRank and not cm_cpm):
                    curMaxRank = deg[i] + deg[j]
                elif(deg[i] + deg[j] - 1 >= curMaxRank and cm_cpm):
                    curMaxRank = deg[i] + deg[j] - 1
        return curMaxRank
