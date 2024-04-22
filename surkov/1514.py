from typing import List
import heapq as hq


class Solution:

    def maxProbability(self, n: int, edges: List[List[int]], succProb: List[float], start: int, end: int) -> float:
        g = {i: [] for i in range(n)}
        for prob, (from_node, to_node) in zip(succProb, edges):
            g[from_node].append((prob, to_node))
            g[to_node].append((prob, from_node))

        distance = {start: -1}
        heap = [(-1, start)]
        while len(heap) > 0:
            prob, curr = hq.heappop(heap)
            for n_prob, neighbor in g[curr]:
                if neighbor in distance:
                    if abs(n_prob * prob) > abs(distance[neighbor]):
                        hq.heappush(heap, (-abs(n_prob * prob), neighbor))
                        distance[neighbor] = -abs(n_prob * prob)
                else:
                    hq.heappush(heap, (-abs(n_prob * prob), neighbor))
                    distance[neighbor] = -abs(n_prob * prob)

        res = distance.get(end)
        if res is None:
            return 0
        return -res


if __name__ == "__main__":
    print(Solution().maxProbability(3, [[0,1]], [0.5], 0, 2))
