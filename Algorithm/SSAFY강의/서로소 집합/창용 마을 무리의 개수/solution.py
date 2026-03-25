import sys
sys.stdin = open('input.txt', 'r')

def union_set(a, b):
    x = find_set(a)
    y = find_set(b)
    if x != y:
        if rank[x] > rank[y]:
            people[y] = x
        elif rank[x] < rank[y]:
            people[x] = y
        else:
            people[y] = x
            rank[x] += 1

def find_set(x):
    if x != people[x]:
        people[x] = find_set(people[x])
    return people[x]

T = int(input())

for tc in range(1, T + 1):
    N, M = map(int, input().split())
    # N: N명의 사람, M: 관계 간선 입력 수
    origin_input = []
    people = list(range(N + 1))
    rank = [0] * (N + 1)
    max_rank = 0
    cnt = 0

    for _ in range(M):
        origin_input.append(list(map(int, input().split())))

    for i in origin_input:
        union_set(i[0], i[1])

    for i in range(1, N + 1):
        find_set(i)

    result = len(set(people)) - 1

    print(f"#{tc} {result}")
