package main

import "fmt"

func allPathsSourceTarget(graph [][]int) [][]int {
	result := make([][]int, 0)
	cur := make([]int, 1)

	var backtrack func(node int)
	backtrack = func(node int) {
		if node == len(graph)-1 {
			temp := make([]int, len(cur))
			copy(temp, cur)
			result = append(result, temp)
			return
		}

		for i := 0; i < len(graph[node]); i++ {
			cur = append(cur, graph[node][i])
			backtrack(graph[node][i])
			cur = cur[:len(cur)-1]
		}
	}

	backtrack(0)

	return result
}

func main() {
	fmt.Println("test1: ", allPathsSourceTarget([][]int{
		{1, 2},
		{3},
		{3},
		{},
	}))
	fmt.Println("test2: ", allPathsSourceTarget([][]int{
		{4, 3, 1},
		{3, 2, 4},
		{3},
		{4},
		{},
	}))
}
