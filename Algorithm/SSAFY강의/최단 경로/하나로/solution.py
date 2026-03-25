import sys
sys.stdin = open('input.txt', 'r')

import heapq

def prim(island, visited, start_island, E):
    heap = []
    heapq.heappush(heap, (0, start_island))
    total_dist = 0

    while heap:
        curr_dist, curr_idx = heapq.heappop(heap)
        if visited[curr_idx]: continue
        curr_island = island[curr_idx]
        curr_x, curr_y = curr_island
        visited[curr_idx] = True
        total_dist += curr_dist


        for idx, is_visit in enumerate(visited):
            if is_visit: continue
            chk_x, chk_y = island[idx]
            # 아직 방문안한 섬들의 위치좌표
            if curr_x == chk_x:
                dist = E * ((curr_y - chk_y) ** 2)
            elif curr_y == chk_y:
                dist = E * ((curr_x - chk_x) ** 2)
            else:
                dist = E * ((curr_x - chk_x) ** 2 + (curr_y - chk_y) ** 2)
            heapq.heappush(heap, (dist, idx))


    return total_dist

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    # N: 섬의 개수
    X_list = tuple(map(int, input().split()))
    Y_list = tuple(map(int, input().split()))
    E = float(input())
    # 가중치 = E * L^2
    island = []
    visited = []
    start_island = 0
    for i in range(N):
        island.append((X_list[i], Y_list[i]))
        visited.append(False)

    result = prim(island, visited, 0, E)

    print(f"#{tc} {round(result)}")



