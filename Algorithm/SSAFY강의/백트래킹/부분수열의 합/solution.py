import sys
sys.stdin = open('input.txt', 'r')


# dfs에서 고르는 경우 안고르는 경우 넘기고
# 인덱스 번호를 하나 씩 넘겨서 선택하게
# 넘기는 값에 현재값을 +해서 넘기고
# 하나는 안 +해서 넘기고 해서
# 만약에 합이 K를 넘어버리면
# 바로 return해서 백트래킹하기

def dfs(curr_idx, curr_sum, N_list, end_idx, expect_sum):
    global total_cnt
    if curr_sum == expect_sum:
        total_cnt += 1
        return
    if curr_sum > expect_sum:
        return
    if curr_idx > end_idx:
        return
    dfs(curr_idx+1, curr_sum+N_list[curr_idx], N_list, end_idx, expect_sum)
    dfs(curr_idx+1, curr_sum, N_list, end_idx, expect_sum)

T = int(input())

for tc in range(1, T + 1):
    N, K = map(int, input().split())
    # N: N개의 자연수, K: 합이 K가 되는 경우
    N_list = list(map(int, input().split()))
    # 수열 A
    total_cnt = 0


    dfs(0, 0, N_list, N-1, K)

    print(f"#{tc} {total_cnt}")