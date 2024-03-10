package main

import "fmt"

// O(n)
func findChampion(n int, edges [][]int) int {
	winners := make(map[int]struct{}, n)
	losers := make(map[int]struct{}, n)
	foundTeams := make(map[int]struct{}, n)

	for _, edge := range edges {
		winner := edge[0]
		loser := edge[1]

		if _, ok := losers[winner]; !ok {
			winners[winner] = struct{}{}
		}

		losers[loser] = struct{}{}
		delete(winners, loser)
		foundTeams[loser] = struct{}{}
		foundTeams[winner] = struct{}{}
	}

	if len(winners)+(n-len(foundTeams)) != 1 {
		return -1
	}

	var result int
	for winner := range winners {
		result = winner
	}

	return result
}

func main() {
	fmt.Println("test1: ", findChampion(3, [][]int{{0, 1}, {1, 2}}))
	fmt.Println("test2: ", findChampion(4, [][]int{{0, 2}, {1, 3}, {1, 2}}))
}
