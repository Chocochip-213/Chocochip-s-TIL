import sys
sys.stdin = open("input.txt", "r")


T = int(input())

for test_case in range(1, T + 1):
    N, K = map(int, input().split())
    # N: 총 주머니의 개수, K: 나눠줄 주머니의 개수
    min_candy = float('inf')
    poket_in_list = sorted(list(map(int, input().split())))
    # 각 주머니에 든 사탕 리스트.
    for base_i in range(N-K+1):
        buffer = poket_in_list[base_i + K - 1] - poket_in_list[base_i]
        if buffer < min_candy:
            min_candy = buffer
    print(f"#{test_case} {min_candy}")

