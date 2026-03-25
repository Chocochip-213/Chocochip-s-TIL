import sys
sys.stdin = open('input.txt', 'r')

class Treenode:
    def __init__(self, root):
        self.root = root
        self.left = None
        self.right = None

def preorder(node):
    if node:
        print(node.root, end=' ')
        preorder(node.left)
        preorder(node.right)

def inorder(node):
    if node:
        inorder(node.left)
        print(node.root, end=' ')
        inorder(node.right)

def postorder(node):
    if node:
        postorder(node.left)
        postorder(node.right)
        print(node.root, end=' ')


V = int(input())
init_list = list(map(int, input().split()))

Nodes = {i: Treenode(i) for i in range(1, V+1)}

for i in range(0, len(init_list), 2):
    parents = Nodes[init_list[i]]
    child = Nodes[init_list[i+1]]
    if parents.left is None:
        parents.left = child
    else:
        parents.right = child

root_node = Nodes[1]

preorder(root_node)
print("")
inorder(root_node)
print("")
postorder(root_node)

