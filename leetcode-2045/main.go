package main

import (
	"container/list"
	"fmt"
)

const inf int = 10e6

type Pair struct {
	node, cost int
}

func deijkstra(graph map[int][]int) []int {
	l := list.New()

	d1 := make([]int, len(graph)+1)
	for i := range d1 {
		d1[i] = inf
	}

	d2 := make([]int, len(graph)+1)
	for i := range d2 {
		d2[i] = inf
	}

	d1[1] = 0
	l.PushBack(&Pair{1, 0})

	for l.Len() > 0 {
		front := l.Front()
		p := front.Value.(*Pair)
		l.Remove(front)

		if d2[p.node] < p.cost {
			continue
		}

		for _, neighbor := range graph[p.node] {
			if d1[neighbor] > p.cost+1 {
				d2[neighbor] = d1[neighbor]
				d1[neighbor] = p.cost + 1
				l.PushBack(&Pair{neighbor, p.cost + 1})
			} else if d1[neighbor] < p.cost+1 && p.cost+1 < d2[neighbor] {
				d2[neighbor] = p.cost + 1
				l.PushBack(&Pair{neighbor, p.cost + 1})
			}
		}
	}

	return d2
}

func secondMinimum(n int, edges [][]int, time int, change int) int {
	graph := make(map[int][]int, n)
	for _, edge := range edges {
		arr := graph[edge[0]]
		arr = append(arr, edge[1])
		graph[edge[0]] = arr

		arr = graph[edge[1]]
		arr = append(arr, edge[0])
		graph[edge[1]] = arr
	}

	secondMin := deijkstra(graph)[n]

	allTime := 0
	for i := 0; i < secondMin; i++ {
		allTime += time
		if (allTime/change)%2 == 1 && i != secondMin-1 {
			allTime = ((allTime / change) + 1) * change
		}
	}

	return allTime
}

func main() {
	fmt.Println("test1: ", secondMinimum(5, [][]int{
		{1, 2},
		{1, 3},
		{1, 4},
		{3, 4},
		{4, 5},
	}, 3, 5))
	fmt.Println("test2: ", secondMinimum(2, [][]int{{1, 2}}, 3, 2))
	fmt.Println("test: ", secondMinimum(12, [][]int{
		{1, 2},
		{1, 3},
		{3, 4},
		{2, 5},
		{4, 6},
		{2, 7},
		{1, 8},
		{5, 9},
		{3, 10},
		{8, 11},
		{6, 12},
	}, 60, 600))
}
