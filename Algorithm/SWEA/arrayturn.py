def turn_array(original, N):
    turn_list = []
    global buffer_list
    for j in range(N-1, -1, -1):  # 원본 퍼즐 회전 로직
        for i in range(N-1, -1, -1):
            buffer_list.append(original[i][j])
        turn_list.append(buffer_list)
        buffer_list = []
    return turn_list

T = int(input())


for test_case in range(1, T+1):

    N = int(input())
    original_list = []
    buffer_list = []
    nine_list = []
    baekpal_list = []
    ibaekchil_list = []

    for _ in range(N):
        original_list.append(list(map(int, input().split())))

    nine_list = turn_array(original_list, N)
    baekpal_list = turn_array(nine_list, N)
    ibaekpal_list = turn_array(baekpal_list, N)

    print(nine_list)
    print(baekpal_list)
    print(ibaekpal_list)
 # 테스트