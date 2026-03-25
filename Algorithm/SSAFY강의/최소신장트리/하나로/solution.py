import sys
sys.stdin = open('input.txt', 'r')

from collections import defaultdict
import heapq

def find_dist(islandA, islandB):
    global E, islands, islands_dist
    A = islands[islandA]
    B = islands[islandB]

    if A[0] == B[0]:
        dist = E * ((A[1] - B[1]) ** 2)
    elif A[1] == B[1]:
        dist = E * ((A[0] - B[0]) ** 2)
    else:
        dist = E * ((A[0] - B[0]) ** 2 + (A[1] - B[1]) ** 2)

    heapq.heappush(islands_dist, (dist, islandA, islandB))
    heapq.heappush(islands_dist, (dist, islandB, islandA))


def union_set(a, b):
    pa = find_set(a)
    pb = find_set(b)
    if pa != pb:
        if rank[pa] > rank[pb]:
            island_list[pb] = pa
        elif rank[pa] < rank[pb]:
            island_list[pa] = pb
        else:
            island_list[pb] = pa
            rank[pa] += 1

def find_set(x):
    if x != island_list[x]:
        island_list[x] = find_set(island_list[x])
    return island_list[x]

# 가중치: 제곱의 곱(E * L^2)
# 가중치를 다 구해봐야겠군

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    # N: 섬의 개수
    X_list = list(map(int, input().split()))
    # N개의 섬의 X좌표값 리스트
    Y_list = list(map(int, input().split()))
    # N개의 섬의 Y좌표값 리스트
    E = float(input())
    # E: 환경 부담 세율
    island_list = list(range(N))
    islands_dist = []
    rank = [0] * N
    islands = defaultdict()
    sum = 0

    for k in range(N):
        islands[k] = (X_list[k], Y_list[k])

    for i in range(N):
        for j in range(i + 1, N):
            find_dist(i, j)

    while islands_dist:
        dist, a, b = heapq.heappop(islands_dist)

        if find_set(a) != find_set(b):
            union_set(a, b)
            sum += dist

    print(f"#{tc} {round(sum)}")