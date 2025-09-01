import sys
sys.stdin = open("input.txt", "r")

import itertools

T = int(input())

for test_case in range(1, T + 1):
    N, K = map(int, input().split())
    # N : 자연수의 개수, K : 원하는 합
    K_cnt = 0
    sequence_list = list(map(int, input().split()))
    for i in range(1, N+1):
        # i = 몇개를 뽑을지 정하는 수
        shuffle_list = itertools.combinations(sequence_list, i)
        for shuffle in shuffle_list: # 해당 개를 뽑았을때의 조합 순회
            if sum(shuffle) == K:
                K_cnt += 1

    print(f"#{test_case} {K_cnt}")
