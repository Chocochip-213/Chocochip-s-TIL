import sys
sys.stdin = open("input.txt", "r")


T = 4 # 입력 4줄( 사각형 4개 )
squre_list = []
squre_buffer = []
squre_set = set()

for _ in range(T):
    squre_list.append(list(map(int,input().split())))

for item in squre_list:
    for i in range(item[0], item[2]):
        for j in range(item[1], item[3]):
           # squre_buffer.append([i,j, i+1, j+1])
            squre_set.add((i,j, i+1, j+1))

# squre_cnt = list(squre_set)
# print(squre_set)
# print(squre_buffer)

print(len(squre_set))