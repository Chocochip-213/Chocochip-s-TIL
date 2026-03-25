

T = int(input())

for test_case in range(1, T+1):

    dx = [0, 0, 1, -1] # 하, 상, 우, 좌
    dy = [1, -1, 0, 0]
    # N x M 격자판
    N, M = map(int, input().split())
    main_list = []
    sum_list = []
    sum_value = 0

    for _ in range(N):
        main_list.append(list(map(int, input().split())))


    for i in range(N):
        for j in range(M):
            sum_value += main_list[i][j]
            for _ in range(4):
                di = i + dx[_]
                dj = j + dy[_]

                if di < 0 or di >= N or dj < 0 or dj >= M:
                    continue
                sum_value += main_list[di][dj]
            sum_list.append(sum_value)
            sum_value = 0

    print(f"#{test_case} {max(sum_list)}")