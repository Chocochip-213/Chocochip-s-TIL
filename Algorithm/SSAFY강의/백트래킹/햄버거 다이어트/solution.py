import sys
sys.stdin = open('input.txt', 'r')



T = int(input())

def dfs(start_idx, end_idx, limit_cal, sum_cal, sum_taste, food):
    global max_taste
    if sum_cal > limit_cal:
        return
    if sum_taste > max_taste:
        max_taste = sum_taste
    if start_idx > end_idx:
        return
    curr_taste = food[start_idx][0]
    curr_cal = food[start_idx][1]

    dfs(start_idx+1, end_idx, limit_cal, sum_cal+curr_cal, sum_taste+curr_taste, food)
    dfs(start_idx+1, end_idx, limit_cal, sum_cal, sum_taste, food)


for tc in range(1, T + 1):
    N, L = map(int, input().split())
    # N: 재료의 수, L: 제한 칼로리
    food_info = []
    # (맛점수, 칼로리)
    max_taste = float('-inf')


    for _ in range(N):
        food_info.append(list(map(int, input().split())))

    dfs(0, N-1, L, 0, 0, food_info)


    print(f"#{tc} {max_taste}")