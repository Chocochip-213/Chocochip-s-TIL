import sys
sys.stdin = open('input.txt', 'r')

import heapq

# 각 노드의 상하좌우로 접근하면, 인접 노드와 가중치를 가져올 수 있다.
# 상하좌우의 인덱스 번호와 가중치 번호를 힙에 넣으면 될것같다.
# 마지막에 도착지까지의 최단 경로를 구하면 될 것 같다.
def dijkstra(maps, start):
    delt = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    heap = []
    heapq.heappush(heap, (0, start_idx))
    distances = [[float('inf')] * N for _ in range(N)]
    # 거리 가중치 INF 초기화.
    distances[start_idx[0]][start_idx[1]] = 0
    # 시작 가중치 0 초기화.

    while heap:
        curr_dist, curr_xy = heapq.heappop(heap)
        curr_x, curr_y = curr_xy

        for i in range(4):
            dx, dy = delt[i]
            nx, ny = curr_x + dx, curr_y + dy
            if 0 <= nx < N and 0 <= ny < N:
                new_dist = curr_dist + maps[nx][ny]
                if distances[nx][ny] > new_dist:
                    distances[nx][ny] = new_dist
                    heapq.heappush(heap, (new_dist, (nx, ny)))

    return distances




T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    # N: 지도의 크기 N x N
    # 시작: 0,0 / 끝: N-1, N-1
    map_list = []
    start_idx = (0, 0)
    for _ in range(N):
        map_list.append(list(map(int, input())))

    # print(map_list)
    result = dijkstra(map_list, start_idx)


    print(f'#{tc} {result[N-1][N-1]}')