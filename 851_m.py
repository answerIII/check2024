class Solution:
    def loudAndRich(self, richer: List[List[int]], quiet: List[int]) -> List[int]:
        num_people = len(quiet)
        graph = [[] for _ in range(num_people)]
        
        for rich, poor in richer:
            graph[poor].append(rich)

        visited = [-1] * num_people

        def dfs(vertex):
            if visited[vertex] != -1:
                return visited[vertex]

            min_quiet_person = vertex 
            for neighbor in graph[vertex]:
                quiet_neighbor = dfs(neighbor)
                if quiet[quiet_neighbor] < quiet[min_quiet_person]:
                    min_quiet_person = quiet_neighbor

            visited[vertex] = min_quiet_person
            return min_quiet_person

        for person in range(num_people):
            dfs(person)

        return visited

# Глобально: идея в том, чтобы развернуть ребра графа из richer массива и по этому правилу сложить уже в связный список. 
# Далее сам алгоритм:
# Тут много экспериментировал над скоростью. Идея похоже на идею Кормана, но не нужны сами пре-/пост- значения. 
# Предполагаем, что самый громкий человек - сам рассматриваемый человек, 
# далее проходимся вглубь и возвращаем максимальное значение из дочерних значений "шумности". 
