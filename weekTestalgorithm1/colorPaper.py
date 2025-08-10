import sys
sys.stdin = open("input.txt", "r")

T = int(input())

i_j_list = []
paper_set = set()

for _ in range(T):
    i_j_list.append(list(map(int, input().split())))

for item in i_j_list:
    for i in range(item[0], item[0]+10):
        for j in range(item[1], item[1]+10):
            paper_set.add((i,j,i+1,j+1))

print(len(paper_set))