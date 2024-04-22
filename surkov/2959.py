from typing import List
from copy import deepcopy

INF = 10 ** 10


class Solution:
    def numberOfSets(self, n: int, maxDistance: int, roads: List[List[int]]) -> int:

        d = [[INF] * n for _ in range(n)]
        for i in range(n):
            d[i][i] = 0

        for u, v, w in roads:
            d[u][v] = min(d[u][v], w)
            d[v][u] = min(d[v][u], w)

        ans = 0
        for mask in range(1 << n):
            new_d = deepcopy(d)

            left_nodes = []
            for i in range(n):
                if (mask >> i) & 1 == 0:
                    for j in range(n):
                        new_d[i][j] = INF
                        new_d[j][i] = INF
                else:
                    left_nodes.append(i)

            if self.floyd(new_d, left_nodes, maxDistance):
                ans += 1

        return ans

    @staticmethod
    def floyd(d, left_nodes, max_distance):
        n = len(d)
        for i in range(n):
            for u in range(n):
                for v in range(n):
                    d[u][v] = min(d[u][v], d[u][i] + d[i][v])

        for x in left_nodes:
            for y in left_nodes:
                if x == y:
                    continue
                if d[x][y] > max_distance:
                    return False

        return True

