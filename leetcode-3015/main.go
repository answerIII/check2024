package main

import (
	"fmt"
)

// O(n^3) - Floyd Warhall
func countOfPairs(n int, x int, y int) []int {
	matrix := make([][]int, n)
	for i := range matrix {
		matrix[i] = make([]int, n)
	}

	for i := 0; i < n-1; i++ {
		matrix[i][i+1] = 1
		matrix[i+1][i] = 1
	}

	if x != y {
		matrix[x-1][y-1] = 1
		matrix[y-1][x-1] = 1
	}

	for k := 0; k < n; k++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				if i == j {
					continue
				}

				if matrix[i][j] != 0 && matrix[i][k] != 0 && matrix[k][j] != 0 {
					matrix[i][j] = min(matrix[i][j], matrix[i][k]+matrix[k][j])
				} else if matrix[i][j] == 0 && matrix[i][k] != 0 && matrix[k][j] != 0 {
					matrix[i][j] = matrix[i][k] + matrix[k][j]
				}
			}
		}
	}

	result := make([]int, n)
	for i := 0; i < n; i++ {
		for j := 0; j < n; j++ {
			if matrix[i][j] > 0 {
				result[matrix[i][j]-1]++
			}
		}
	}

	return result
}

func main() {
	fmt.Println("test1: ", countOfPairs(3, 1, 3))
	fmt.Println("test2: ", countOfPairs(5, 2, 4))
	fmt.Println("test3: ", countOfPairs(4, 1, 1))
}
