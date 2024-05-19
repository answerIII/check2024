class Solution:
    def minMalwareSpread(self, graph: List[List[int]], initial: List[int]) -> int:
        def dfs(node: int, color: int):
            colors[node] = color
            for neighbor, adj in enumerate(graph[node]):
                if adj and neighbor not in colors:
                    dfs(neighbor, color)

        N = len(graph)
        colors: Dict[int, int] = {}

        c = 0
        for node in range(N):
            if node not in colors:
                dfs(node, c)
                c += 1

        size = collections.Counter(colors.values())
        
        color_count = collections.Counter()
        for node in initial:
            color_count[colors[node]] += 1

        ans = float('inf')
        for node in initial:
            color = colors[node]
            if color_count[color] == 1:
                if ans == float('inf'):
                    ans = node
                elif size[color] > size[colors[ans]]:
                    ans = node
                elif size[color] == size[colors[ans]] and node < ans:
                    ans = node

        return ans if ans < float('inf') else min(initial)

# Данный вариант обратный предыдущему. Тут скорость высокая, а количество памяти большое. 
# Такой эффект достигается за счет рекурсии. Однако сложность тут квадратичная. 
# Идея в том, чтобы DFS'ом раскрасить каждую компоненту связности в свой цветов. 
# Далее посчитать количество вершин каждого цвета в initial. Таким образом, во время DFS узлы раскрашиваются для всего графа, 
# а затем на следующем этапе мы анализируем только исходные зараженные узлы для принятия решения о том, какой узел можно удалить 
# для минимизации распространения. Ну и дальше само решение, основанное на повторном рассмотрении всех узлов из initial
