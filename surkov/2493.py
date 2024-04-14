from typing import List
from collections import deque, defaultdict


class Solution:
    def magnificentSets(self, n: int, edges: List[List[int]]) -> int:
        graph = defaultdict(list)
        for a, b in edges:
            graph[a].append(b)
            graph[b].append(a)

        components = []
        seen = set()
        for i in range(1, n + 1):
            if i in seen:
                continue

            q = deque([i])
            visited = {i}
            while len(q) > 0:
                node = q.popleft()
                for neighbor in graph[node]:
                    if neighbor in visited:
                        continue
                    visited.add(neighbor)
                    q.append(neighbor)
            components.append(visited)
            seen |= visited

        longest = [-1] * len(components)
        for k, component in enumerate(components):
            for i in component:
                possible_longest = self.bfs(graph, i)
                if possible_longest < 0:
                    return -1
                longest[k] = max(longest[k], possible_longest)
        return sum(longest)

    def bfs(self, graph, i):
        q = deque([i])
        seen = {i}
        seen_level = set()
        ans = 0
        while len(q) > 0:
            ans += 1
            next_level = set()
            for _ in range(len(q)):
                node = q.popleft()
                for neighbor in graph[node]:
                    if neighbor in seen_level:
                        return -1
                    if neighbor in seen:
                        continue
                    seen.add(neighbor)
                    next_level.add(neighbor)
                    q.append(neighbor)
            seen_level = next_level
        return ans


if __name__ == "__main__":
    print(Solution().magnificentSets(92,
                                     [[67, 29], [13, 29], [77, 29], [36, 29], [82, 29], [54, 29], [57, 29], [53, 29],
                                      [68, 29], [26, 29], [21, 29], [46, 29], [41, 29], [45, 29], [56, 29], [88, 29],
                                      [2, 29], [7, 29], [5, 29], [16, 29], [37, 29], [50, 29], [79, 29], [91, 29],
                                      [48, 29], [87, 29], [25, 29], [80, 29], [71, 29], [9, 29], [78, 29], [33, 29],
                                      [4, 29], [44, 29], [72, 29], [65, 29], [61, 29]]))
