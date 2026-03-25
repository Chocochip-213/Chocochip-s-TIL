import sys
sys.stdin = open('input.txt', 'r')


T = int(input())

def union_set(a, b):
    px = find_set(a)
    py = find_set(b)

    if px != py:
        if rank_list[px] > rank_list[py]:
            default_list[py] = px
        elif rank_list[px] < rank_list[py]:
            default_list[px] = py
        else:
            default_list[py] = px
            rank_list[px] += 1


def find_set(a):
    if a != default_list[a]:
        default_list[a] = find_set(default_list[a])
    return default_list[a]


for tc in range(1, T + 1):
    N, M = map(int, input().split())
    # N: N개의 각각의 집합, M: 연산의 개수
    default_list = list(range(0, N + 1))
    # 기본 집합 초기화
    rank_list = [0] * (N + 1)
    # 부모 집합의 대장 저장할 곳.
    calc_list = []
    # 연산 입력 값 저장


    for _ in range(M):
        calc_list.append(list(map(int, input().split())))

    print(f"#{tc}", end=" ")
    for item in calc_list:
        if item[0] == 0:
            union_set(item[1], item[2])
        else:
            x = find_set(item[1])
            y = find_set(item[2])
            if x == y:
                print("1", end='')
            else:
                print("0", end='')
    print("")


