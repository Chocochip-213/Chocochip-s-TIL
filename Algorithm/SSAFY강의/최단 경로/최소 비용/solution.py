import sys
sys.stdin = open('input.txt', 'r')

import heapq

def dijkstra(grid, start):
    heap = []
    heapq.heappush(heap, (0, start))
    delt = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    distances = [[float('inf')] * N for _ in range(N)]
    distances[start[0]][start[1]] = 0

    while heap:
        curr_dist, curr_node = heapq.heappop(heap)
        curr_x, curr_y = curr_node
        if distances[curr_x][curr_y] < curr_dist: continue

        for i in range(4):
            nx, ny = delt[i]
            next_x = curr_x + nx
            next_y = curr_y + ny
            if 0 <= next_x < N and 0 <= next_y < N:
                if grid[curr_x][curr_y] < grid[next_x][next_y]:
                    next_dist = curr_dist + grid[next_x][next_y] - grid[curr_x][curr_y] + 1
                else:
                    next_dist = curr_dist + 1

                if distances[next_x][next_y] > next_dist:
                    distances[next_x][next_y] = next_dist
                    heapq.heappush(heap, (next_dist, (next_x, next_y)))
    return distances[N-1][N-1]

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    # N: N x N 칸 수
    # 시작: (0, 0), 끝: (N - 1, N - 1)
    grid_origin = []
    start_idx = (0, 0)
    for _ in range(N):
        grid_origin.append(list(map(int, input().split())))

    result = dijkstra(grid_origin, start_idx)

    print(f"#{tc} {result}")