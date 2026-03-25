import sys
# sys.stdin = open("input.txt", "r")

T = int(input())

for tc in range(1, T + 1):
    N, M = map(int, input().split())
    # N : N x N 보드판, M : 돌을 놓는 횟수
    board_list = []
    board_xy = [] # 돌을 놓을 좌표
    stone_color = [] # 좌표에 놓을 돌 색깔
    board = [] # N x N 보드판
    temp_xy = [] # 뒤집을 돌 좌표 임시저장
    gaggai = 1 # 전진 할 수
    dx = [0, 0, 1, -1, 1, -1, -1, 1] # 우 좌 하 상 우하, 좌상, 우상, 좌하
    dy = [1, -1, 0, 0, 1, -1, 1, -1]
    Black_cnt = 0
    White_cnt = 0



    for _ in range(N): # 보드판 생성 N x N
        board.append([0]*N)
    board[N // 2][N // 2] = 2
    board[N // 2][N // 2 - 1] = 1
    board[N // 2 - 1][N // 2 - 1] = 2
    board[N // 2 - 1][N // 2] = 1
    for _ in range(M): # 좌표 및 돌 색깔 입력받기
        board_list.append(list(map(int, input().split())))
        # 3 2 1 -> (3, 2) 1: 흑돌 / 2: 백돌
    for xy in board_list: # 좌표 및 돌 전처리(분리)
        board_xy.append((xy[0]-1, xy[1]-1)) # -1 처리는 인덱스 번호로 변환
        stone_color.append(xy[2]) # 돌 색깔 따로 저장
    stone_color.reverse() # pop연산을 위한 reverse
    board_xy.reverse() # pop연산을 위한 reverse

    while board_xy and stone_color:
        temp = board_xy.pop()
        board[temp[0]][temp[1]] = stone_color.pop()
        for i in range(8):
            gaggai = 1
            temp_xy.clear()
            while True:
                ni = temp[0] + (dx[i] * gaggai)
                nj = temp[1] + (dy[i] * gaggai)
                if 0 <= ni < N and 0 <= nj < N: # 벗어나면
                    if board[temp[0]][temp[1]] == board[ni][nj]: # 탐색한게 원래값과 같다면
                        for k in temp_xy:
                            board[k[0]][k[1]] = board[temp[0]][temp[1]] # 뒤집기 로직
                        break
                    elif board[ni][nj] == 0:
                        break
                    else:
                        temp_xy.append((ni, nj))
                        gaggai += 1
                else:
                    break


    for _ in board:
        for l in _:
            if l == 1: Black_cnt += 1
            elif l == 2: White_cnt += 1

    print(f"#{tc} {Black_cnt} {White_cnt}")

