class Solution:
    def findOrder(self, numCourses: int, prerequisites: List[List[int]]) -> List[int]:
        graph = [[] for _ in range(numCourses)]
        for course, prereq in prerequisites:
            graph[prereq].append(course)

        visited = [0] * numCourses
        order = []

        def visit(vertex):
            visited[vertex] = -1
            for v in graph[vertex]:
                if visited[v] == 0:
                    if not visit(v):
                        return False
                if visited[v] == -1:
                    return False
            visited[vertex] = 1
            order.append(vertex)
            return True

        for vertex in range(numCourses):
            if visited[vertex] == 0:
                if not visit(vertex):
                    return []

        return order[::-1]

# Прежде всего делаем удобную структуру данных: список смежности. 
# Далее сам алгоритм: 
# Идея в том, что мы отказываемся от классического псевдокода из книги Кормана. 
# Нам нужны только пост- значения, которые мы можем фиксировать сразу в массиве order. 
