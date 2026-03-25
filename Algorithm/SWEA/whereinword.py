T = int(input())

for test_case in range(1, T + 1):

    word_puzzle_list = [] # 단어 퍼즐 모양 / 벽 = 0 공간 = 1
    sero_puzzle_list = [] # 기존 퍼즐 모양 회전 -> 세로 확인
    buffer_list = []
    buffer_cnt = 0
    total_cnt = 0

    N, K = map(int, input().split())
    for _ in range(N):
        word_puzzle_list.append(list(map(int, input().split())))

    for j in range(N): # 원본 퍼즐 회전 로직
        for i in range(N):
            buffer_list.append(word_puzzle_list[i][j])
        sero_puzzle_list.append(buffer_list)
        buffer_list = []

    buffer_list.append(word_puzzle_list) # 버퍼 리스트에 원본 퍼즐 넣기
    buffer_list.append(sero_puzzle_list) # 버퍼 리스트에 90도 회전 퍼즐 넣기

    for list_puzzle in buffer_list: # list_puzzle에 원본퍼즐, 90도 퍼즐 순회
        for word in list_puzzle: # 퍼즐 한 행씩 순회
            if sum(word) >= K: # 한 행을 다 더한게 K값과 크거나 같으면
                for garo in range(N): #
                    if word[garo] == 1: #  행 순회중 1 만나면 버퍼cnt 1 증가
                        buffer_cnt += 1
                    else: # 순회중 0 만나면
                        if buffer_cnt == K: # 버퍼cnt가 K와 같으면
                            total_cnt += 1 # 단어 자리 1 증가 (total)
                            buffer_cnt = 0 # 버퍼 초기화
                        else: # 이외에는
                            buffer_cnt = 0 # 그냥 버퍼 초기화
                if buffer_cnt == K: # 0 안만나고 끝났을 때 버퍼cnt가 K와 같으면
                    total_cnt += 1  # 단어 자리 1 증가 (total)
                    buffer_cnt = 0 # 버퍼 초기화
                else: # 0 안만나고 끝났을 때 해당사항 없으면
                    buffer_cnt = 0 # 그냥 버퍼 초기화

    print(f"#{test_case} {total_cnt}")