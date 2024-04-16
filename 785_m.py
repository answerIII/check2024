class Solution:
    def isBipartite(self, graph: List[List[int]]) -> bool:
        colors = [-1] * len(graph)
        
        for i in range(len(graph)):
            if colors[i] == -1:
                colors[i] = 0  
                
                queue = deque([i])
                
                while queue:
                    current = queue.popleft()
                    current_color = colors[current]
                    
                    for neighbor in graph[current]:
                        if colors[neighbor] == -1:
                            colors[neighbor] = 1 - current_color 
                            queue.append(neighbor)
                        elif colors[neighbor] == current_color:
                            return False 
        
        return True

# Как я и предположил, BFS повел себя более удачно. 
# Объясняется тем, что я ушел от рекурсии, тем самым укоротил себе время до обнаружения потенциального соседа того же цвета. 
# То есть в DFS я сначала ушел в глубину и потом только нашел ответ, когда из нее вышел, а в BFS я никуда не уходил, всё в одном действии делается. 
