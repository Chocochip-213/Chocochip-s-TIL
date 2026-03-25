import sys
sys.stdin = open('input.txt', 'r')

def dfs(x, y, map, buffer):
    global global_set
    buffer.append(map[x][y])

    if len(buffer) == 7:
        global_set.add(tuple(buffer))
        return

    for dx, dy in delt:
        nx = x + dx
        ny = y + dy
        if 0 <= nx < input_range and 0 <= ny < input_range:
            dfs(nx, ny, map, buffer)
            buffer.pop()


T = int(input())

for tc in range(1, T + 1):
    input_range = 4
    grid = []
    delt = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    # 상하좌우 델타
    global_set = set()


    for _ in range(input_range):
        grid.append(list(map(int, input().split())))

    for i in range(input_range):
        for j in range(input_range):
            buffer_list = []
            dfs(i, j, grid, buffer_list)


    print(f"#{tc} {len(global_set)}")