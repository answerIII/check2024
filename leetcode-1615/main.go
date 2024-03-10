package main

import "fmt"

func insertInMap(m map[int]map[int]struct{}, ind int, elem int) {
	set := m[ind]
	if set == nil {
		set = make(map[int]struct{})
	}
	set[elem] = struct{}{}
	m[ind] = set
}

func getMaxAdj(m map[int]map[int]struct{}) (maxNode int, max int) {
	for node, neighbors := range m {
		if max < len(neighbors) {
			max = len(neighbors)
			maxNode = node
		}
	}

	return
}

func maximalNetworkRank(n int, roads [][]int) int {
	if len(roads) < 2 {
		return len(roads)
	}

	adjEdges := make(map[int]map[int]struct{}, n)
	for _, road := range roads {
		insertInMap(adjEdges, road[0], road[1])
		insertInMap(adjEdges, road[1], road[0])
	}

	firstMaxNode, firstMax := getMaxAdj(adjEdges)
	delete(adjEdges, firstMaxNode)

	var res, prevSecondMax int
	for len(adjEdges) != 0 {
		secondMaxNode, secondMax := getMaxAdj(adjEdges)
		if secondMax < prevSecondMax {
			break
		}

		secondNeighbors := adjEdges[secondMaxNode]
		if _, ok := secondNeighbors[firstMaxNode]; ok {
			res = max(res, firstMax+secondMax-1)
		} else {
			res = max(res, firstMax+secondMax)
		}

		delete(adjEdges, secondMaxNode)
		prevSecondMax = secondMax
	}

	return res
}

func main() {
	fmt.Println("test1: ", maximalNetworkRank(3, [][]int{{0, 1}, {0, 3}, {1, 2}, {1, 3}}))
	fmt.Println("test2: ", maximalNetworkRank(5, [][]int{{0, 1}, {0, 3}, {1, 2}, {1, 3}, {2, 3}, {2, 4}}))
	fmt.Println("test1: ", maximalNetworkRank(8, [][]int{{0, 1}, {1, 2}, {2, 3}, {2, 4}, {5, 6}, {5, 7}}))
}
