N = int(input())
# N: 사람 수

time_list = sorted(list(map(int, input().split())))
result = 0

for i in range(len(time_list)):
    result += sum(time_list[:i+1])

print(result)