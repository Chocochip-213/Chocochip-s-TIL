import sys

sys.stdin = open("input.txt", "r")

T = int(input())

for tc in range(1, T + 1):
    N = int(input())
    matrix = [list(map(int, input().split())) for _ in range(N)]
    max_idx = []
    max_value = 0
    delta = [[-1, 0], [0, 1], [1, 0], [0, -1]]
    answer = []
    ok_flag = 0
    # 가장 큰 값 찾기
    for i in range(N):
        for j in range(N):
            if matrix[i][j] > max_value:
                max_value = matrix[i][j]
                max_idx.clear()
                max_idx.append((i, j))
            elif max_value == matrix[i][j]:
                max_idx.append((i, j))


    for idx in max_idx:
        i, j = idx # 맥스 인덱스 추출이넹
        cnt = 1
        min_value = max_value
        while True:
            value_idx = (0, 0)
            ok_flag = 0
            value = matrix[i][j]
            for di, dj in delta:
                # 여기 안에서는 돈 애들 중에 제일 작은 값이랑 그 인덱스를 기록
                ni, nj = i + di, j + dj
                if 0 <= ni < N and 0 <= nj < N:
                    if matrix[i][j] > matrix[ni][nj]:
                        if value > matrix[ni][nj]:
                            value = matrix[ni][nj]
                            value_idx = (ni, nj)
                        ok_flag = 1
            # 이제 델타 탐색 끝나구 value에는 탐색에서 구한 제일 작은 값이랑 value_idx에는 그 인덱스가 담겨잇슴
            # 여기서 전체 최솟값이랑 지금 내가 들고나온 값을 비교
            # if min_value > value:
            #     min_value = value
            #     i, j = value_idx
            #     cnt += 1
            # else:
            #     break
            if ok_flag == 0:
                answer.append(cnt)
                break
            else:
                cnt += 1
                i, j = value_idx


    print(f"#{tc} {max(answer)}")