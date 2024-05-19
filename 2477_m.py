class Solution:
    def minimumFuelCost(self, roads: List[List[int]], seats: int) -> int:
        graph = defaultdict(list)
        for road in roads:
            a, b = road
            graph[a].append(b)
            graph[b].append(a)
        
        def dfs(vertex, parent):
            total_liters = 0
            total_representatives = 1 
            
            for neighbor in graph[vertex]:
                if neighbor != parent:
                    reps, fuel = dfs(neighbor, vertex)
                    total_representatives += reps
                    total_liters += fuel + (reps + seats - 1) // seats
            return total_representatives, total_liters
        
        total_reps, total_liters = dfs(0, -1)
        return total_liters

# Создаем словарь списков, делаем список смежности
# ДФС
# Считаем количество литров на себя и всех потомков и количество представителей в каждом городе
# Количество литров, в таком случае, будет считаться как количество литров у потомка + сумма свободных мест в машине - 1(т.к оно занято) 
# и представителей поделенная на количество мест в одной машине. 
# Можно открыть комменты и понять лучше
