package main

import "fmt"

// O(n)
func findChampion(n int, edges [][]int) int {
	losers := make([]int, n)
	for _, edge := range edges {
		losers[edge[1]]++
	}

	champs := 0
	champ := losers[0]
	for i, loserCount := range losers {
		if loserCount == 0 {
			champs++
			champ = i
		}
	}

	if champs > 1 {
		return -1
	}

	return champ
}

func main() {
	fmt.Println("test1: ", findChampion(3, [][]int{{0, 1}, {1, 2}}))
	fmt.Println("test2: ", findChampion(4, [][]int{{0, 2}, {1, 3}, {1, 2}}))
}
