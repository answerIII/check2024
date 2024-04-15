package main

import (
	"fmt"
	"math"
)

const inf int = 10e6

func deijkstra(graph map[[2]int]map[[2]int]int, start [2]int) map[[2]int]int {
	d := make(map[[2]int]int, len(graph))
	for node := range graph {
		d[node] = inf
	}

	d[start] = 0

	used := make(map[[2]int]struct{}, len(graph))

	for range graph {
		minDist := inf
		var minNode [2]int

		for nodeJ := range graph {
			_, ok := used[nodeJ]
			if !ok && d[nodeJ] < minDist {
				minDist = d[nodeJ]
				minNode = nodeJ
			}
		}

		if d[minNode] == inf {
			break
		}

		used[minNode] = struct{}{}

		for nodeJ, dist := range graph[minNode] {
			if d[minNode]+dist < d[nodeJ] {
				d[nodeJ] = d[minNode] + dist
			}
		}
	}

	return d
}

// O(specialRoads.len^2)
func minimumCost(start []int, target []int, specialRoads [][]int) int {
	graph := make(map[[2]int]map[[2]int]int, len(specialRoads)/2+2)

	specialNodes := make(map[[2]int]struct{}, len(specialRoads)/2+2)
	for _, roads := range specialRoads {
		firstPoint := [2]int{roads[0], roads[1]}
		secondPoint := [2]int{roads[2], roads[3]}
		if firstPoint != [2]int(start) {
			specialNodes[firstPoint] = struct{}{}
		}

		if secondPoint != [2]int(start) {
			specialNodes[secondPoint] = struct{}{}
		}

		if graph[firstPoint] == nil {
			graph[firstPoint] = make(map[[2]int]int, len(specialRoads)/2+2)
		}

		distance := int(math.Abs(float64(secondPoint[0]-firstPoint[0])) + math.Abs(float64(secondPoint[1]-firstPoint[1])))

		oldVal, ok := graph[firstPoint][secondPoint]
		if ok {
			graph[firstPoint][secondPoint] = min(roads[4], distance, oldVal)
		} else {
			graph[firstPoint][secondPoint] = min(roads[4], distance)
		}
	}

	specialNodes[[2]int(target)] = struct{}{}

	for specialNode := range specialNodes {
		distance := math.Abs(float64(specialNode[0]-start[0])) + math.Abs(float64(specialNode[1]-start[1]))

		if graph[[2]int(start)] == nil {
			graph[[2]int(start)] = make(map[[2]int]int, len(specialRoads)/2+2)
		}

		if graph[[2]int{specialNode[0], specialNode[1]}] == nil {
			graph[[2]int{specialNode[0], specialNode[1]}] = make(map[[2]int]int, len(specialRoads)/2+2)
		}

		if _, ok := graph[[2]int(start)][[2]int{specialNode[0], specialNode[1]}]; !ok {
			graph[[2]int(start)][[2]int{specialNode[0], specialNode[1]}] = int(distance)
		}

		if _, ok := graph[[2]int{specialNode[0], specialNode[1]}][[2]int(start)]; !ok {
			graph[[2]int{specialNode[0], specialNode[1]}][[2]int(start)] = int(distance)
		}
	}

	for specialNodeI := range specialNodes {
		for specialNodeJ := range specialNodes {
			if specialNodeI != specialNodeJ && specialNodeI != [2]int(target) {
				distance := math.Abs(float64(specialNodeI[0]-specialNodeJ[0])) + math.Abs(float64(specialNodeI[1]-specialNodeJ[1]))

				if graph[[2]int{specialNodeJ[0], specialNodeJ[1]}] == nil {
					graph[[2]int{specialNodeJ[0], specialNodeJ[1]}] = make(map[[2]int]int, len(specialRoads)/2+2)
				}

				if graph[[2]int{specialNodeI[0], specialNodeI[1]}] == nil {
					graph[[2]int{specialNodeI[0], specialNodeI[1]}] = make(map[[2]int]int, len(specialRoads)/2+2)
				}

				if _, ok := graph[[2]int{specialNodeJ[0], specialNodeJ[1]}][[2]int{specialNodeI[0], specialNodeI[1]}]; !ok {
					graph[[2]int{specialNodeJ[0], specialNodeJ[1]}][[2]int{specialNodeI[0], specialNodeI[1]}] = int(distance)
				}

				if _, ok := graph[[2]int{specialNodeI[0], specialNodeI[1]}][[2]int{specialNodeJ[0], specialNodeJ[1]}]; !ok {
					graph[[2]int{specialNodeI[0], specialNodeI[1]}][[2]int{specialNodeJ[0], specialNodeJ[1]}] = int(distance)
				}
			}
		}
	}

	return deijkstra(graph, [2]int(start))[[2]int(target)]
}

func main() {
	fmt.Println("test1: ", minimumCost([]int{1, 1}, []int{4, 5}, [][]int{
		{1, 2, 3, 3, 2},
		{3, 4, 4, 5, 1},
	}))
	fmt.Println("test2: ", minimumCost([]int{3, 2}, []int{5, 7}, [][]int{
		{3, 2, 3, 4, 4},
		{3, 3, 5, 5, 5},
		{3, 4, 5, 6, 6},
	}))
}
