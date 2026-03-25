import sys
sys.stdin = open('input.txt', 'r')

from collections import defaultdict

def dfs(start, end, graph, visited):
    global result
    visited[start] = True

    if start == end:
        result = 1
        return 0

    if result:
        return 0

    for chk in graph[start]:
        if visited[chk]: continue
        else:
            dfs(chk, end, graph, visited)



T = int(input())

for tc in range(1, T + 1):
    V, E = map(int, input().split())
    # V: 노드 갯수, E: 간선 갯수
    graph = defaultdict(list)
    result = 0

    for _ in range(E):
        start_v, end_v = map(int, input().split())
        graph[start_v].append(end_v)

    start_chk_v, end_chk_v = map(int, input().split())
    # 출발 노드, 도착 노드

    visited = [False] * (V+1)

    dfs(start_chk_v, end_chk_v, graph, visited)

    print(f"#{tc} {result}")
