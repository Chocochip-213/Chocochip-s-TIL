import sys
sys.stdin = open('input.txt', 'r')

T = int(input())

for tc in range(1, T + 1):
    N, M, L = map(int, input().split())
    # N: 노드의 개수, M: 리프 노드의 개수
    # L: 값을 출력할 노드 번호.
    tree_value = [0] * (N + 1)
    init_list = []
    for _ in range(M):
        leaf, leaf_value = map(int, input().split())
        init_list.append(leaf)
        tree_value[leaf] = leaf_value


    for i in range(N, 0, -1):
        i = int(i)
        tree_value[i//2] += tree_value[i]

    print(f"#{tc} {tree_value[L]}")