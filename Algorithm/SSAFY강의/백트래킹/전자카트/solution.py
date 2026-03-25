import sys
sys.stdin = open('input.txt', 'r')


def dfs(st, ed, remain_node, sum_battery):
    global battery_list, min_battery, not_remain, original_start
    current_battery = 0
    if sum_battery > min_battery:
        not_remain = True
        return

    if not remain_node:
        # 남은 갈 구역이 없으면
        not_remain = True
        current_battery = battery_list[st][ed]
        sum_battery += current_battery
        if sum_battery < min_battery:
            min_battery = sum_battery
        return


    for i in range(len(remain_node)):
        not_remain = False
        temp = remain_node[i]
        buffer_node = remain_node[:i] + remain_node[i+1:]
        current_battery = battery_list[st][temp]
        dfs(temp, ed, buffer_node, sum_battery + current_battery)





T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    # 2 ~ N: 관리구역 수
    # 인덱스 번호로 사용해보자.
    # a[출발구역][도착구역]으로 접근
    battery_list = []
    number_list = []
    min_battery = float('inf')
    start, end = 0, 0
    not_remain = False

    for _ in range(1, N):
        number_list.append(_)
    original_len = len(number_list)


    for k in range(N):
        battery_list.append(list(map(int, input().split())))

    dfs(start, end, number_list, 0)

    print(f"#{tc} {min_battery}")
