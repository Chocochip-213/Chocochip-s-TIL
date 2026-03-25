import sys
sys.stdin = open('input.txt', 'r')

import heapq
from collections import defaultdict

T = int(input())

for tc in range(1, T + 1):
    N, M, X = map(int, input().split())
    # N: N개의 집, M: M개의 도로, X: 인수의 집 번호
    graph = [[float('inf')] * (N + 1) for _ in range(N + 1)]
    max_dist = float('-inf')

    for i in range(N + 1):
        graph[i][i] = 0


    for _ in range(M):
        x, y, c = map(int, input().split())
        graph[x][y] = c

    for k in range(1, N + 1):
        for i in range(1, N + 1):
            for j in range(1, N + 1):
                graph[i][j] = min(graph[i][j], graph[i][k] + graph[k][j])




    for i in range(1, N + 1):
        chk_dist = graph[X][i] + graph[i][X]
        if chk_dist > max_dist:
            max_dist = chk_dist

    print(f"#{tc} {max_dist}")
