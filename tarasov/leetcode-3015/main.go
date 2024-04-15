package main

import (
	"fmt"
)

const inf = 10e6

// O(n^2) - n * BFS
func countOfPairs(n int, x int, y int) []int {
	result := make([]int, n)

	housesAdj := make([][]int, n)
	for i := 0; i < n-1; i++ {
		housesAdj[i] = append(housesAdj[i], i+1)
		housesAdj[i+1] = append(housesAdj[i+1], i)
	}

	housesAdj[x-1] = append(housesAdj[x-1], y-1)
	housesAdj[y-1] = append(housesAdj[y-1], x-1)

	var bfs func(house int) = func(house int) {
		distance := make([]int, n)
		for i := range distance {
			if i != house {
				distance[i] = inf
			}
		}

		queue := []int{house}
		for len(queue) > 0 {
			elem := queue[0]
			queue = queue[1:]

			neighbors := housesAdj[elem]

			for _, neighbor := range neighbors {
				if distance[elem]+1 < distance[neighbor] {
					result[distance[elem]]++
					distance[neighbor] = distance[elem] + 1
					queue = append(queue, neighbor)
				}
			}
		}
	}

	for i := 0; i < n; i++ {
		bfs(i)
	}

	return result
}

func main() {
	fmt.Println("test1: ", countOfPairs(3, 1, 3))
	fmt.Println("test2: ", countOfPairs(5, 2, 4))
	fmt.Println("test3: ", countOfPairs(4, 1, 1))
}
