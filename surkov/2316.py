from typing import List
from itertools import combinations


class Solution:
    def countPairs(self, n: int, edges: List[List[int]]) -> int:
        visited = [False] * n
        graph = {i: list() for i in range(n)}
        for node1, node2 in edges:
            graph[node1].append(node2)
            graph[node2].append(node1)

        ans = 0
        for node in range(n):
            if visited[node]:
                continue
            size = 0
            to_visit = [node]
            while len(to_visit) > 0:
                curr = to_visit.pop()
                if visited[curr]:
                    continue
                visited[curr] = True
                size += 1
                for sibling in graph[curr]:
                    if not visited[sibling]:
                        to_visit.append(sibling)

            ans += size * (n - size)

        return ans // 2


if __name__ == "__main__":
    sol = Solution()
    sol.countPairs(7, [[0, 2], [0, 5], [2, 4], [1, 6], [5, 4]])
