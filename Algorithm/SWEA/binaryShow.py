import sys

sys.stdin = open("input.txt", "r")

T = int(input())

for TC in range(1, T + 1):
    N, M = map(int, input().split())
    result = ''

    bit = bin(M)[2:]
    bit_chk = bit[-1:-N-1:-1]
    if sum(map(int, bit_chk)) == N:
        result = 'ON'
        pass
    else:
        result = 'OFF'

    print(f"#{TC} {result}")