from typing import Optional


# Definition for a Node.
class Node:
    def __init__(self, val=0, neighbors=None):
        self.val = val
        self.neighbors = neighbors if neighbors is not None else []

    def __repr__(self):
        return f"Node({self.val!r}, {[n.val for n in self.neighbors]!r})"


class Solution:
    def cloneGraph(self, node: Optional['Node']) -> Optional['Node']:
        if node is None:
            return None

        copies = {}
        wired = set()

        def copy_or_get(val):
            res = copies.get(val, None)
            if res is not None:
                return res
            res = Node(val)
            copies[val] = res
            return res

        to_wire = [node]
        while len(to_wire) > 0:
            curr = to_wire[-1]

            curr_copy = copy_or_get(curr.val)

            if curr_copy.val in wired:
                to_wire.pop()
            else:
                for neighbor in curr.neighbors:
                    n_copy = copy_or_get(neighbor.val)
                    curr_copy.neighbors.append(n_copy)
                    if n_copy.val not in wired:
                        to_wire.append(neighbor)
                wired.add(curr_copy.val)

        return copies[node.val]


if __name__ == "__main__":
    nodes = [Node(i) for i in range(1, 5)]
    adj = [[2, 4], [1, 3], [2, 4], [1, 3]]
    for i, conns in enumerate(adj):
        nodes[i].neighbors = [nodes[j - 1] for j in conns]

    sol = Solution()
    sol.cloneGraph(nodes[0])
