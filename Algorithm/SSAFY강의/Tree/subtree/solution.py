import sys
sys.stdin = open('input.txt', 'r')

class Treenode:
    def __init__(self, key):
        self.key = key
        self.left = None
        self.right = None

def postorder(root):
    if root:
        left_cnt = postorder(root.left)
        right_cnt = postorder(root.right)
    else:
        return 0
    return left_cnt + right_cnt + 1


T = int(input())

for tc in range(1, T + 1):
    E, N = map(int, input().split())
    # E: 간선의 개수, N: 루트 노드의 번호
    init_list = list(map(int, input().split()))
    nodes = {i: Treenode(i) for i in range(1, max(init_list)+1)}
    cnt = 0


    for i in range(0, len(init_list), 2):
        parents = nodes[init_list[i]]
        child = nodes[init_list[i+1]]
        if parents.left is None:
            parents.left = child
        else:
            parents.right = child


    root_node = nodes[N]
    total_cnt = postorder(root_node)
    print(f"#{tc} {total_cnt}")
