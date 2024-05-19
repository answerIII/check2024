class Solution:
    def maximumDetonation(self, bombs: List[List[int]]) -> int:
        def kaboom(b1, b2):
            return (b1[0] - b2[0]) ** 2 + (b1[1] - b2[1]) ** 2 <= b1[2] ** 2

        graph = {}
        count = 0
        for i in range(len(bombs)):
            graph[i] = set()
            for j in range(len(bombs)):
                if i != j and kaboom(bombs[i], bombs[j]):
                    graph[i].add(j)
                    count += 1
                    #print(f"{i} -> {j}")
        #print("amount of edges: ", count) 

        max_detonated = 0
        visited = set()
        for i in range(len(bombs)):
            if i not in visited:
                count = 1 
                queue = deque([i])
                visited = set()
                visited.add(i)
                while queue:
                    node = queue.popleft()
                    for neighbor in graph[node]:
                        if neighbor not in visited:
                            queue.append(neighbor)
                            visited.add(neighbor)
                            count += 1
                max_detonated = max(max_detonated, count)

        return max_detonated

# 1 этап. Подготовка. Загнать всё в граф(список смежности)
# На этом этапе требуется формула, которая смотрит на то, что бомбы активированы. 
# Эту формулу мы делаем с помощью теоремы Пифагора

# 2 этап. Оценка взрыва. Тут использую BFS, так как мы выяснили из прошлых задач, что он быстрее DFS. 
# В BFS мы просто считаем максимальное количество элементов, которые мы можем "захватить". 
# Важным этапом было осознание того, что каждую итерацию множество(set) надо было обнулять. 
# В этом всё отличие от стандартного BFS. Максимальный взрыв - максимальное количество достижимых бомб из источника
