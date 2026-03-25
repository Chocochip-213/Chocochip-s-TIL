import sys
sys.stdin = open("input.txt", "r")

T = int(input())


for test_case in range(1, T + 1):
    sum_buffer = 0
    sum_list = []
    N, M = map(int, input().split())
    ballon_list = []
    for _ in range(N):
        ballon_list.append(list(map(int, input().split())))

    dx = [0, 0, 1, -1] # 우 좌 하 상
    dy = [1, -1, 0, 0] # 우 좌 하 상

    for i in range(N):
        for j in range(M):
            for k in range(4):
                for ballon in range(1, ballon_list[i][j]+1):
                    chk_x = i + dx[k] * ballon
                    chk_y = j + dy[k] * ballon
                    if 0 <= chk_x < N and 0 <= chk_y < M:
                        sum_buffer += ballon_list[chk_x][chk_y]

            sum_buffer += ballon_list[i][j]
            sum_list.append(sum_buffer)
            sum_buffer = 0



    print(f"#{test_case} {max(sum_list)}")