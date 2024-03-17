package main

import "fmt"

func dfs(graph map[int][]int, node int) int {
	if _, ok := graph[node]; !ok {
		return 1
	}

	neighbors := graph[node]
	delete(graph, node)

	var count int
	for _, neighbor := range neighbors {
		if _, ok := graph[neighbor]; ok {
			count += dfs(graph, neighbor) + 1
		}
	}

	return count
}

func makeConnected(n int, connections [][]int) int {
	graph := make(map[int][]int, len(connections))
	for _, connection := range connections {
		graph[connection[0]] = append(graph[connection[0]], connection[1])
		graph[connection[1]] = append(graph[connection[1]], connection[0])
	}

	connectionNodes := len(graph)

	spansSize := 0
	components := make([]int, 0)
	for node := range graph {
		componentSize := dfs(graph, node)
		components = append(components, componentSize)
		spansSize += componentSize
	}

	res := n - connectionNodes + len(components)-1

	if res > len(connections)-spansSize {
		return -1
	}

	return res
}

func main() {
	fmt.Println("test1: ", makeConnected(4, [][]int{
		{0, 1},
		{0, 2},
		{1, 2},
	}))
	fmt.Println("test2: ", makeConnected(6, [][]int{
		{0, 1},
		{0, 2},
		{0, 3},
		{1, 2},
		{1, 3},
	}))
	fmt.Println("test3: ", makeConnected(6, [][]int{
		{0, 1},
		{0, 2},
		{0, 3},
		{1, 2},
	}))
}
