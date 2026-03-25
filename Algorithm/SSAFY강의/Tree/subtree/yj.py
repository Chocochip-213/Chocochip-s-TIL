import sys
sys.stdin = open('input.txt', 'r')


T  = int(input())
for tc in range(1, T+1):
    E, N = map(int, input().split())
    lst = list(map(int, input().split()))
    dic = {}
    for i in range(len(lst)):
        if i % 2 == 0:
            if dic.get(lst[i]) == None:
                dic[lst[i]] = [lst[i+1]]
            else:
                dic[lst[i]].append(lst[i+1])
    print(dic)
    visited = [False] * (max(lst)+1)

    def dfs(dic, key):
        global result
        if dic.get(key) == None:
            visited[key] = True
            return
        for i in dic[key]:
            visited[i] = True
            dfs(dic, i)
    visited[N] = True
    dfs(dic, N)
    print(f'#{tc} {sum(visited)}')