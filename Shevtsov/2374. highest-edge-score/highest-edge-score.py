class Solution:
    def edgeScore(self, edges: List[int]) -> int:
        n = len(edges)
        scores = [0] * n
        for i in range(n):
            scores[edges[i]] += i
        curMax = 0
        curMaxInd = 0
        for i in range(n):
            if(scores[i] > curMax):
                curMax = scores[i]
                curMaxInd = i
        return curMaxInd
