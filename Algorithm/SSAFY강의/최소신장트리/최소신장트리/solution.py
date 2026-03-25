import sys
sys.stdin = open('input.txt', 'r')

import heapq

def union_set(a, b):
    px = find_set(a)
    py = find_set(b)

    if px != py:
        if rank_list[px] > rank_list[py]:
            default_list[py] = px
        elif rank_list[px] < rank_list[py]:
            default_list[px] = py
        else:
            default_list[py] = px
            rank_list[px] += 1

def find_set(x):
    if x != default_list[x]:
        default_list[x] = find_set(default_list[x])
    return default_list[x]


T = int(input())

for tc in range(1, T + 1):
    V, E = map(int, input().split())
    # V: 마지막 노드번호, E: 간선의 개수
    origin_input = []
    heap = []
    default_list = list(range(0, V + 1))
    rank_list = [0] * (V + 1)
    sum_value = 0

    for _ in range(E):
         origin_input.append(list(map(int, input().split())))

    for n1, n2, w in origin_input:
        heapq.heappush(heap, (w, n1, n2))

    while heap:
        chk = heapq.heappop(heap)
        if find_set(chk[1]) != find_set(chk[2]):
            union_set(chk[1], chk[2])
            sum_value += chk[0]

    print(f"#{tc} {sum_value}")