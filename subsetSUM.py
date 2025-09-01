import sys

sys.stdin = open("input.txt", "r")



T = int(input())

for test_case in range(1, T + 1):

    N, K = map(int, input().split())

    input_list = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12]
    # input_list = [1, 2, 3, 4]
    bit = 2 ** len(input_list) # 집합 원소의 갯수만큼 비트 생성을 위한 줄
    bit_end = (bit << 1) - 1 # 모든 경우 끝
    total_cnt = 0

    while bit < bit_end:
        bit_cnt = 0
        input_sum = 0
        bit_list = list(bin(bit)[3:])
        for idx, buffer in enumerate(bit_list):
            if buffer == '1':
                bit_cnt += 1
                input_sum += input_list[idx]
        if bit_cnt == N:
            if input_sum == K:
                total_cnt += 1
        bit += 1

    print(f"#{test_case} {total_cnt}")
