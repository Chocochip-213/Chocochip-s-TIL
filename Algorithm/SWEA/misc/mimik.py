import sys

sys.stdin = open("input.txt", "r")

T = int(input())

for TC in range(1, T + 1):
    N, K = map(int, input().split())
    # N: 숫자의 개수,  K: 크기 순서
    # 변 개수 : 4개
    turn_total = []
    turn_list = [] # 회전후 뽑아낸 숫자 조합.
    code = list(input())
    # 나누기 몫 - 1 하면 총 회전 수가 나올것 같다.(모든조합)
    turn_cnt = N // 4 - 2 # 원본, 0(1회전) ~ turn_cnt(마지막회전) 까지임.

    for j in range(0, N + 1 - (N // 4), N // 4):  # 첫번쨰 회전 전 숫자조합 뽑기
        buffer = []
        for k in range(N // 4):  # 한 변의 값을 뽑아내는 for문
            idx = j + k
            buffer.append(code[idx])
        turn_list.append(buffer)

    for i in range(turn_cnt+1): # 회전 후 숫자조합 뽑기
        code.insert(0, code.pop()) # 회전 로직
        for j in range(0, N+1-(N // 4), N // 4): # 회전후 각 변들을 순회하는 로직
            buffer = []
            for k in range(N // 4): # 한 변의 값을 뽑아내는 for문
                idx = j+k
                buffer.append(code[idx])
            turn_list.append(buffer)

    result = set([''.join(i) for i in turn_list])
    result_ten = sorted([int(i, 16) for i in result])
    print(f"#{TC} {result_ten[-K]}")
