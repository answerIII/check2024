package main

import "fmt"

func insertInMap(m map[int][]int, ind int, elem int) {
	arr := m[ind]
	arr = append(arr, elem)
	m[ind] = arr
}

func dfs(adjEdges map[int][]int, node int) int {
	if _, ok := adjEdges[node]; !ok {
		return 1
	}

	neighbors := adjEdges[node]
	delete(adjEdges, node)

	var count int
	for _, neighbor := range neighbors {
		if _, ok := adjEdges[neighbor]; ok {
			count += dfs(adjEdges, neighbor) + 1
		}
	}

	return count
}

// O(n)
func countPairs(n int, edges [][]int) int64 {
	adjEdges := make(map[int][]int, n)
	found := make(map[int]struct{}, n)

	for _, edge := range edges {
		insertInMap(adjEdges, edge[0], edge[1])
		insertInMap(adjEdges, edge[1], edge[0])
		found[edge[0]] = struct{}{}
		found[edge[1]] = struct{}{}
	}

	componentsSize := make([]int, 0)
	for node := range adjEdges {
		componentsSize = append(componentsSize, dfs(adjEdges, node)+1)
	}

	for i := 0; i < n-len(found); i++ {
		componentsSize = append(componentsSize, 1)
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
