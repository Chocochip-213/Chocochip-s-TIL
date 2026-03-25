import sys
sys.stdin = open("input.txt", "r")


def find_start_end(maze):
    global maze_size
    start_idx = None
    end_idx = None
    for i in range(maze_size):
        for j in range(maze_size):
            if start_idx and end_idx:
                return start_idx, end_idx
            if maze[i][j] == 2:
                start_idx = (i, j)
            elif maze[i][j] == 3:
                end_idx = (i, j)

def is_connect_dfs(maze, st_idx, end_idx):
    global delt, maze_size, visited, result, maze_diretion
    visited[st_idx[0]][st_idx[1]] = True
    # print(st_idx)
    if st_idx == end_idx:
        # 도착
        result = 1
        return 0
    if result:
        return 0
    for i in range(4):
        chk_idx_i = st_idx[0] + delt[i][0]
        chk_idx_j = st_idx[1] + delt[i][1]
        if not maze[chk_idx_i][chk_idx_j] == 1 and not visited[chk_idx_i][chk_idx_j]:
            # 길이 있다면
            is_connect_dfs(maze, (chk_idx_i, chk_idx_j), end_idx)



T = 10

for test_case in range(1, T + 1):

    delt = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    # 상하좌우 델타
    st_idx = None
    end_idx = None
    maze_size = 16
    visited = [[False] * maze_size for _ in range(maze_size)]
    result = 0

    tc = int(input())
    maze = []
    for _ in range(maze_size):
        maze.append(list(map(int, input())))


    st_idx, end_idx = find_start_end(maze)

    is_connect_dfs(maze, st_idx, end_idx)

    print(f"#{tc} {result}")