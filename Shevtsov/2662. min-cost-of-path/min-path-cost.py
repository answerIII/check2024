def dist(v1: List[int], v2: List[int]) -> int:
    return abs(v1[0]-v2[0])+abs(v1[1]-v2[1])

class Solution:
    def minimumCost(self, start: List[int], target: List[int],
                          specialRoads: List[List[int]]) -> int:

        specialRoads = tuple(((x1,y1),(x2,y2),cost) for x1,y1,x2,y2,cost
                               in specialRoads if dist((x1,y1),(x2,y2)) > cost)
        
        min_cost = dist(start, target)
        seen_node = set()
        heap = [(0,tuple(start))]
        
        while heap:
            cost, pos = heappop(heap)

            if(pos in seen_node or cost > min_cost): 
                continue

            min_cost = min(min_cost, cost + dist(pos, target))
            seen_node.add(pos)

            for s, e, roadCost in specialRoads:
                heappush(heap, (cost + roadCost + dist(pos,s), e))
                
        return min_cost
