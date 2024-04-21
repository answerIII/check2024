package leetcode;

/*
Алгоритм Кана:
Добавьте в очередь все узлы со степенью 0 .
Пока очередь не пуста:
Удалить узел из очереди.
Для каждого исходящего ребра удаленного узла уменьшите степень входа узла назначения на 1 .
Если степень входа узла назначения становится 0 , добавьте его в очередь.
Если очередь пуста и в графе еще есть узлы, граф содержит цикл и не может быть топологически отсортирован.
Узлы в очереди представляют собой топологическое упорядочение графа.
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class Solution207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = new List[numCourses];
        int[] incomingDegrees = new int[numCourses];
        int result = 0;

        for (var prerequisite : prerequisites) {
            int afterCourse = prerequisite[0];
            int beforeCourse = prerequisite[1];
            if (graph[beforeCourse] == null) {
                graph[beforeCourse] = new ArrayList<>();
            }
            graph[beforeCourse].add(afterCourse);
            incomingDegrees[afterCourse]++;

        }
        LinkedList<Integer> queue = new LinkedList<>();

        for (int i = 0; i < numCourses; i++) {
            if (incomingDegrees[i] == 0) {
                queue.offer(i);
                result++;
            }
        }

        while (!queue.isEmpty()) {
            int course = queue.poll();
            if (graph[course] != null) {
                for (var edge : graph[course]) {
                    incomingDegrees[edge]--;
                    if (incomingDegrees[edge] == 0) {
                        queue.offer(edge);
                        result++;
                    }
                }
            }
        }

        return result == numCourses;
    }
}
