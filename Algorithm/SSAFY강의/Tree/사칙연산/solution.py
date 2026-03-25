import sys
sys.stdin = open('input.txt', 'r')

T = 10

for tc in range(1, T + 1):
    N = int(input())
    # 정점의 갯수
    tree_value = [0] * (N+1)
    init_list = []

    for _ in range(N):
        init_list.append(list(input().split()))

    init_list.reverse()

    for item in init_list:
        if len(item) == 2:
            tree_value[int(item[0])] = int(item[1])

        else:
            current_node = int(item[0])
            left_node = int(item[2])
            right_node = int(item[3])
            cal = item[1]
            if cal == '-':
                tree_value[current_node] += tree_value[left_node] - tree_value[right_node]
            elif cal == '*':
                tree_value[current_node] += tree_value[left_node] * tree_value[right_node]
            elif cal == '/':
                tree_value[current_node] += tree_value[left_node] / tree_value[right_node]
            else:
                tree_value[current_node] += tree_value[left_node] + tree_value[right_node]

    print(f'#{tc} {int(tree_value[1])}')
