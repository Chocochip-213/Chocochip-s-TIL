import sys


'''
      아래의 구문은 input.txt 를 read only 형식으로 연 후,
      앞으로 표준 입력(키보드) 대신 input.txt 파일로부터 읽어오겠다는 의미의 코드입니다.
      여러분이 작성한 코드를 테스트 할 때, 편의를 위해서 input.txt에 입력을 저장한 후,
      아래 구문을 이용하면 이후 입력을 수행할 때 표준 입력 대신 파일로부터 입력을 받아올 수 있습니다.

      따라서 테스트를 수행할 때에는 아래 주석을 지우고 이 구문을 사용하셔도 좋습니다.
      아래 구문을 사용하기 위해서는 import sys가 필요합니다.

      단, 채점을 위해 코드를 제출하실 때에는 반드시 아래 구문을 지우거나 주석 처리 하셔야 합니다.
'''
sys.stdin = open("input.txt", "r")


T = int(input())

for test_case in range(1, T+1):
    N, M = map(int, input().split())
    # N : 정수의 개수
    # M : 구간의 개수
    sum_buffer_list = []
    sum_buffer_int = 0

    Ai = list(map(int, input().split()))# N개의 정수 ai 입력받기

    for base_idx in range(N-M+1):
        for idx in range(M):
            check_idx = base_idx + idx
            sum_buffer_int += Ai[check_idx]


        sum_buffer_list.append(sum_buffer_int)
        sum_buffer_int = 0

    print(f"#{test_case} {max(sum_buffer_list)-min(sum_buffer_list)}")

