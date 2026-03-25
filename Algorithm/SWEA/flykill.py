T = int(input())

for test_case in range(1, T + 1):

    N, M = map(int, input().split())
    fly_list = []
    stacked_sum = 0
    stacked_list = []
    for _ in range(N):
        fly_list.append(list(map(int, (input().split()))))
        # 파리 N x N 배열 입력 받기.

    for i in range(0, N - M + 1): # 행 반복
        for j in range(0, N - M + 1): # 열 반복
            for Mi in range(M):
                for Mj in range(M):
                    Find_i = i + Mi
                    Find_j = j + Mj
                    stacked_sum += fly_list[Find_i][Find_j]
            stacked_list.append(stacked_sum)
            stacked_sum = 0

    print(f"#{test_case} {max(stacked_list)}")