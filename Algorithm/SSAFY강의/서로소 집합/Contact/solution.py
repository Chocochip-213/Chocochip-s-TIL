import sys
sys.stdin = open('input.txt', 'r')

from collections import deque, defaultdict


T = 10
for tc in range(1, T + 1):
    N, M = map(int, input().split())
    # N: 한줄의 길이, M: 연락 시작점
    q = deque()
    origin_list = list(map(int, input().split()))
    visited = [False] * 101
    nodes_dict = defaultdict(list)
    recent_call = []
    buffer_list = []

    for i in range(0, len(origin_list)-1, 2):
        nodes_dict[origin_list[i]].append(origin_list[i+1])

    q.extend(nodes_dict[M])
    # 시작 연락 돌리기
    visited[M] = True

    while q: # q = [1], [2,3,4], [6,7]
        buffer_list.clear()
        buffer_list.extend(q)
        q.clear()

        for calls in buffer_list:
            if not visited[calls]:
                visited[calls] = True
                for i in nodes_dict[calls]:
                    if not visited[i]:
                        q.append(i)

    print(f"#{tc} {max(buffer_list)}")
