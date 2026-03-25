import sys
sys.stdin = open('input.txt', 'r')

import heapq
from collections import defaultdict

def dijkstra(graph, start_node):
    heap = []
    heapq.heappush(heap, (0, start_node))
    distances = {v: float('inf') for v in range(0, N + 1)}
    distances[start_node] = 0

    while heap:
        curr_dist, curr_node = heapq.heappop(heap)
        if distances[curr_node] < curr_dist: continue

        for next_node, next_dist in graph[curr_node].items():
            new_dist = curr_dist + next_dist
            if new_dist < distances[next_node]:
                distances[next_node] = new_dist
                heapq.heappush(heap, (new_dist, next_node))

    return distances[N-1]


TC = int(input())

for tc in range(1, TC + 1):
    N, T = map(int, input().split())
    # N: 정점의 개수, T: 간선의 수
    graph = defaultdict(dict)
    start_node = 0

    for _ in range(T):
        a, b, w = map(int, input().split())
        graph[a][b] = w

    result = dijkstra(graph, start_node)
    if result == float('inf'):
        result = 'impossible'

    print(f"#{tc} {result}")