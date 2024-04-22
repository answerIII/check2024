from typing import List


class Solution:
    def canVisitAllRooms(self, rooms: List[List[int]]) -> bool:
        visited = [False] * len(rooms)
        to_visit = [0]

        while len(to_visit) > 0:
            curr = to_visit.pop()
            visited[curr] = True
            for key in rooms[curr]:
                if not visited[key]:
                    to_visit.append(key)

        return all(visited)
