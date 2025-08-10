import sys
sys.stdin = open("input.txt", "r")

T = int(input())

for test_case in range(1, T+1):

    # N = 배열
    # M = 파리채 크기
    N, M = map(int, input().split())
    N_list = []
    M_sum = 0
    M_sum_list = []

    for _ in range(N): # 파리배열 입력
        N_list.append(list(map(int, input().split())))

    for base_se in range(N-M+1):
        for base in range(N-M+1):
            for i in range(M):
                for j in range(M):
                    base_i = base_se + i
                    base_j = base + j
                    M_sum += N_list[base_i][base_j]
            M_sum_list.append(M_sum)
            M_sum = 0


    print(f"#{test_case} {max(M_sum_list)}")

