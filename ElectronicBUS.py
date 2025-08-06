T = int(input())
# 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):

    K, N, M = map(int, input().split())
    M_info = list(map(int, input().split()))  # 각 충전기 번호
    moving_list = [0] * (N + 1)
    max_cnt = M
    M_info.extend([0] * 10)
    charge_cnt = 0
    K_buffer = K

    for idx in M_info:  # moving_list에 충전기 위치 + 1 실시 / 충전기 없는 곳은 0
        moving_list[idx] += 1

    for current_location, move in enumerate(moving_list):
        if K_buffer == 0:
            print(f"#{test_case} 0")
            break
        if move == 1 and max_cnt != 0:  # 충전기가 현재 위치에 있다면.
            K_buffer -= 1
            max_cnt -= 1  # 충전기 남은 갯수 -1 실시

            if M_info[M_info.index(current_location) + 1] - current_location > K_buffer or (
                    max_cnt == 0 and N - current_location > K_buffer):
                charge_cnt += 1
                K_buffer = K


        elif current_location != 0:  # 충전기가 현재 위치에 없다면
            K_buffer -= 1
        else:
            pass
    else:
        print(f"#{test_case} {charge_cnt}")

        