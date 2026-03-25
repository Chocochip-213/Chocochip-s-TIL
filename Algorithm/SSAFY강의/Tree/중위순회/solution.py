import sys
sys.stdin = open('input.txt', 'r')

class Treenode:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None


def inorder(root):
    if root:
        inorder(root.left)
        print(index_list[root.key], end='')
        inorder(root.right)

T = 10

for tc in range(1, T + 1):

    N = int(input())
    # N: 총 정점의 갯수
    init_list = []
    for _ in range(N):
        init_list.append(list(input().split()))

    nodes = {i: Treenode(i) for i in range(1, N+1)}
    global index_list
    index_list = [None] * (N+1)

    for item in init_list:
        parents = int(item[0])
        # 각 노드 번호 가져오기
        parents_value = item[1]
        # 각 노드의 해당되는 알파벳 가져오기
        index_list[parents] = parents_value
        # 각 노드의 알파벳값 인덱싱
        nodes_parents = nodes[parents]
        # 부모 노드 객체 가져오기
        for i in item[2:]:
            if nodes_parents.left is None:
                nodes_parents.left = nodes[int(i)]
            else:
                nodes_parents.right = nodes[int(i)]

    root_num = nodes[1]
    print(f'#{tc}', end=' ')
    inorder(root_num)
    print("")