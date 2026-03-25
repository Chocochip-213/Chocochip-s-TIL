import sys
sys.stdin = open('input.txt', 'r')

import heapq
from collections import defaultdict

def dijkstra(graph, start_node):
    heap = []
    heapq.heappush(heap, (0, start_node))
    distances = {v: float('inf') for v in range(1, N + 1)}
    distances[start_node] = 0

    while heap:
        curr_dist, curr_node = heapq.heappop(heap)

        if distances[curr_node] < curr_dist: continue

        for next_node, next_dist in graph[curr_node].items():
            new_dist = curr_dist + next_dist
            if distances[next_node] > new_dist:
                distances[next_node] = new_dist
                heapq.heappush(heap, (new_dist, next_node))


    return distances



T = int(input())

for tc in range(1, T + 1):
    N, M, X = map(int, input().split())
    # N: 집의 갯수, M: 도로 갯수, X: 목표 집
    forward_graph = defaultdict(dict)
    reverse_graph = defaultdict(dict)
    start_node = X
    chk_value = None
    max_value = float('-inf')

    for _ in range(M):
        x, y, c = map(int, input().split())
        forward_graph[x][y] = c
        reverse_graph[y][x] = c

    # result = dijkstra(forward_graph, start_node) + dijkstra(reverse_graph, start_node)
    forward_result = dijkstra(forward_graph, start_node)
    reverse_result = dijkstra(reverse_graph, start_node)


    for i in forward_result:
        chk_value = forward_result[i] + reverse_result[i]
        if max_value < chk_value:
            max_value = chk_value

    print(f"#{tc} {max_value}")