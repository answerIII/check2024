package main

import "fmt"

func maxInt(a, b int) int {
	if a > b {
		return a
	}

	return b
}

func maximalNetworkRank(n int, roads [][]int) int {
	roadsSet := make(map[int]map[int]struct{})
	networkDegree := make([]int, n)

	for _, road := range roads {
		a := road[0]
		b := road[1]
		networkDegree[a]++
		networkDegree[b]++

		if roadsSet[a] == nil {
			roadsSet[a] = make(map[int]struct{})
		}
		roadsSet[a][b] = struct{}{}

		if roadsSet[b] == nil {
			roadsSet[b] = make(map[int]struct{})
		}

		roadsSet[b][a] = struct{}{}
	}

	maxNetworkRank := 0
	for i := 0; i < n; i++ {
		for j := i+1; j < n; j++ {
			rank := networkDegree[i]+networkDegree[j]

			_, ok1 := roadsSet[i][j]
			_, ok2 := roadsSet[j][i]

			if ok1 || ok2 {
				rank--
			}

			maxNetworkRank = maxInt(maxNetworkRank, rank)
		}
	}

	return maxNetworkRank
}

func main() {
	fmt.Println("test1: ", maximalNetworkRank(4, [][]int{{0, 1}, {0, 3}, {1, 2}, {1, 3}}))
	fmt.Println("test2: ", maximalNetworkRank(5, [][]int{{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}}))
	fmt.Println("test3: ", maximalNetworkRank(8, [][]int{{0, 1}, {1, 2}, {2, 3}, {2, 4}, {5, 6}, {5, 7}}))
}
