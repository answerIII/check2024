package main

import "fmt"

// O(n)
func countPairs(n int, edges [][]int) int64 {
	adjEdges := make([][]int, n)
	found := make(map[int]struct{}, n)

	for _, edge := range edges {
		adjEdges[edge[0]] = append(adjEdges[edge[0]], edge[1])
		adjEdges[edge[1]] = append(adjEdges[edge[1]], edge[0])

		found[edge[0]] = struct{}{}
		found[edge[1]] = struct{}{}
	}

	seen := make(map[int]struct{})

	var dfs func(int) int
	dfs = func(node int) int {
		neighbors := adjEdges[node]
		seen[node] = struct{}{}

		var count int = 1
		for _, neighbor := range neighbors {
			if _, ok := seen[neighbor]; !ok {
				count += dfs(neighbor)
			}
		}

		return count
	}

	componentsSize := make([]int, 0, n)
	for node := range adjEdges {
		if _, ok := seen[node]; !ok {
			componentsSize = append(componentsSize, dfs(node))
		}
	}

	var numPairs int64
	for _, size := range componentsSize {
		if size != n {
			numPairs += int64(size * (n - size))
			n -= size
		}
	}

	return numPairs
}

func main() {
	fmt.Println("test1: ", countPairs(7, [][]int{{0, 2}, {0, 5}, {2, 4}, {1, 6}, {5, 4}}))
	fmt.Println("test2: ", countPairs(3, [][]int{{0, 1}, {0, 2}, {1, 2}}))
}
