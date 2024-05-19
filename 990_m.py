class Solution:
    def equationsPossible(self, equations: List[str]) -> bool:
        def find(parent, x):
            if parent[x] != x:
                parent[x] = find(parent, parent[x])
            return parent[x]

        def union(parent, x, y):
            parent[find(parent, x)] = find(parent, y)

        parent = {}
        
        for eq in equations:
            x, op, _, y = eq
            if x not in parent:
                parent[x] = x
            if y not in parent:
                parent[y] = y
            if op == '=':
                parent[find(parent, x)] = find(parent, y)
            
        for eq in equations:
            x, op, _, y = eq
            if op == '!':
                if find(parent, x) == find(parent, y):
                    return False
        
        return True

# Идея в следующем:
# 1. "Парсим" строку X в одно множество, Y в другое.
# 2. Если знак "==", то переносим X и Y в одно множество
# Когда все переносы в одно множество завершены, начинаем проверять случай с "!=". 
# Если такой знак, а переменные из одного множества(то есть равны), то это нарушение => False

