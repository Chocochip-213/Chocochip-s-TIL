import sys

sys.stdin = open("input.txt", "r")

T = int(input())

for tc in range(1, T + 1):
    N, M = map(int,input().split())
    # N: 화물 수 / M: 트럭 수

    hwamool_list = sorted(list(map(int, input().split())))
    juckjae_list = sorted(list(map(int, input().split())))
    juckjae_sum = 0

    while hwamool_list and juckjae_list:
        if hwamool_list[-1] > juckjae_list[-1]:
            hwamool_list.pop()
        else:
            juckjae_sum += hwamool_list.pop()
            juckjae_list.pop()

    print(f"#{tc} {juckjae_sum}")