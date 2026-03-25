import math
import sys
sys.stdin = open('input.txt', 'r')

import heapq

def dijkstra(graph, start):
    distances = {v: math.inf for v in graph}
    distances[start] = 0
    min_heap = []
    heapq.heappush(min_heap, [0, start])

    while min_heap:
        curr_dist, curr_vert = heapq.heappop(min_heap)

        if distances[curr_vert] < curr_dist: continue

        for adjacent, weight in graph[curr_vert].items():
            distance = curr_dist + weight
            if distance < distances[adjacent]:
                distances[adjacent] = distance
                heapq.heappush(min_heap, [distance, adjacent])

    return distances

T = int(input())

for tc in range(1, T + 1):
    N, E = map(int, input().split())
    # N: 마지막 번호, E: 도로의 개수
    start = 0
    # 시작번호는 0번
    graph = {v: {} for v in range(N + 1)}

    for _ in range(E):
        s, e, w = map(int, input().split())
        graph[s][e] = w

    result = dijkstra(graph, start)
    print(f"#{tc} {list(result.values()).pop()}")



