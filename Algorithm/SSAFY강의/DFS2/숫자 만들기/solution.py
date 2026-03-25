import sys
sys.stdin = open('input.txt', 'r')


def dfs(calc_code, current_idx, current_value):
    global total_max, total_min, num_list, calc_dict
    if current_idx == len(num_list):
        # 숫자 연산이 끝나면
        if total_max < current_value:
            total_max = current_value
        if total_min > current_value:
            total_min = current_value
        return

    for i in range(4):

        if calc_code[i] > 0:
            calc_code[i] -= 1
            what_code = calc_dict[i]

            if what_code == '+':
                buffer = current_value + num_list[current_idx]
                dfs(calc_code, current_idx + 1, buffer)
            elif what_code == '-':
                buffer = current_value - num_list[current_idx]
                dfs(calc_code, current_idx + 1, buffer)
            elif what_code == '*':
                buffer = current_value * num_list[current_idx]
                dfs(calc_code, current_idx + 1, buffer)
            elif what_code == '/':
                buffer = int(current_value / num_list[current_idx])
                dfs(calc_code, current_idx + 1, buffer)
            calc_code[i] += 1



T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    # N: 숫자의 개수
    calc_list = list(map(int, input().split()))
    # 각 연산자의 개수: +, -, *, / 순서.
    num_list = list(map(int, input().split()))
    # 수식에 사용되는 숫자.
    total_max = float("-inf")
    total_min = float("inf")
    calc_dict = {
        0: '+',
        1: '-',
        2: '*',
        3: '/'
    }

    dfs(calc_list, 1, num_list[0])

    print(f"#{tc} {abs(total_max-total_min)}")