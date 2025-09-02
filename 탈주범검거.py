import sys
sys.stdin = open("input.txt", "r")

T = int(input())
for tc in range(1, T + 1):
    N, M, Si, Sj, Time = map(int, input().split())
    # N: 세로, M: 가로, Si Sj: 시작 인덱스, Time: 탈출 후 시간
    map_list = []
    # map_list: 지하 지도
    for _ in range(N):
        map_list.append(list(map(int, input().split())))
    search_place = [(Si, Sj)]
    current_time = 1
    jawoon_dict = {
        1 : [(-1, 0), (1, 0), (0, -1), (0, 1)],
        # 1: 상,하,좌,우
        2 : [(-1, 0), (1, 0)],
        # 2: 상,하
        3 : [(0, -1), (0, 1)],
        # 3: 좌, 우
        4 : [(-1, 0), (0, 1)],
        # 4: 상, 우
        5 : [(1, 0), (0, 1)],
        # 5: 하, 우
        6 : [(1, 0), (0, -1)],
        # 6: 하, 좌
        7 : [(-1, 0), (0, -1)]
        # 7: 상, 좌
    }

    # 시작지점을 기반으로 탐색 시작
    # 탐색 시작 값 for문을 잘 만들어야 할 것 같음.
    # 도착 지점의 값을 dict에서 델타값 찾아서 탐색
    # 탐색한 지역에 터널이 없다면 제외
    # 탐색한 지점도 탐색 시작 지역으로 append
    # 시간 다될때까지 진행

    while current_time < Time:
        search_buffer = []
        for place in search_place:
            # 탐색 지역 순회
            # place = (i, j)
            tunner = map_list[place[0]][place[1]]
            # 현재 탐색 지점의 터널 종류
            if tunner != 0:
                # 터널이 있다면
                search_s = jawoon_dict[tunner]
                # 터널 종류에 따른 이동가능 델타 좌표 넣기
                for search in search_s:
                    # 델타 좌표들 순회
                    i = place[0] + search[0]
                    j = place[1] + search[1]
                    # 현재 탐색 지점에 델타좌표 반영
                    if 0 <= i < N and 0 <= j < M:
                        if map_list[i][j]:
                            # 반영 후 터널이 있다면
                            for k in jawoon_dict[map_list[i][j]]:
                                if k == (-search[0], -search[1]):
                                    # 터널이 현재 터널과 연결되어 있다면.
                                    search_buffer.append((i, j))
                                    # 해당 좌표도 탐색 지역에 추가
        # 시간 1시간 추가
        current_time += 1
        search_place.extend(search_buffer)
        search_place = list(set(search_place))

    print(f"#{tc} {len(search_place)}")