def input_inital():
    global unsort_color_list
    N = int(input()) # 입력 받을 색깔의 종류 갯수
    for _ in range(N): # 색칠 인덱스 입력
        unsort_color_list.append(list(map(int, input().split())))
    return N

def init_main_list(): # 10 x 10 생성
    global main_list
    global buffer_list
    for _ in range(10):
        for _ in range(10):
            buffer_list.append(0)
        main_list.append(buffer_list)
        buffer_list = []

def color_split(): # 색깔 나누기 함수
    global red_color_list
    global blue_color_list
    global unsort_color_list
    for b in unsort_color_list:
        if b[4] == 1:
            red_color_list.append(b[:4])
        else:
            blue_color_list.append(b[:4])





T = int(input())

for test_case in range(1, T + 1):
    main_list = [] # 기본 10x10 도화지
    unsort_color_list = [] # 색칠 입력 인덱스
    blue_color_list = [] # 파란색 색칠 인덱스
    red_color_list = [] # 빨간색 색칠 인덱스
    buffer_list = []
    total_cnt = 0

    N = input_inital() # 입력 함수
    init_main_list() # 10 x 10 도화지 생성 함수
    color_split() # 색깔 나누기 함수

    for red in red_color_list:
        for i in range(red[0], red[2]+1):
            for j in range(red[1], red[3]+1):
                main_list[i][j] = 1

    for blue in blue_color_list:
        for i in range(blue[0], blue[2]+1):
            for j in range(blue[1], blue[3]+1):
                if main_list[i][j] == 1:
                    total_cnt += 1


    print(f"#{test_case} {total_cnt}")


