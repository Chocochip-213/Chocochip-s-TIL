import sys
sys.stdin = open("input.txt", "r")
import itertools

def SynagySum(food_list, food_synagy):
    sum_buffer = 0
    for j in food_list:
        for i in food_list:
            if i == j:
                continue
            else:
                sum_buffer += food_synagy[i-1][j-1]
    return sum_buffer


T = int(input())

for test_case in range(1, T + 1):
    # N개의 식재료에서 nCr -> n = N // r = N // 2
    # 그 조합들을 그러니까 앞쪽껄 열인덱스로 설정하고 그 뒤에것들을 행인덱스로 설정하면서 모든값 sum
    # 예를 들어 a = [(1, 2, 3), (1, 2, 4)] 일때
    # 외부 j = (1,2,3) 순회 / 내부 i = 본인제외한 값 | j = 1 -> i = 2,3 | j = 2 -> i = 1,3 | j = 3 -> i = 1,2 |
    # N개 range 생성 후 풀기
    N = int(input())
    # N : 식재료의 갯수
    synagy_min = float("inf")
    food_synagy = [list(i for i in map(int, input().split())) for j in range(N)]
    food_comb = list(itertools.combinations(range(1, N+1), N // 2)) # 식재료 고르기


    for food_select in food_comb:
        food_cnt = list(range(1, N+1))
        food_namogy = []
        # food_select = 고른 조합
        # food_namogy = 나머지 조합.
        for _ in food_cnt:
            if _ not in food_select:
                food_namogy.append(_)
        # 이 위는 수정하지마세요.
        fir_food = SynagySum(food_select, food_synagy)
        sec_food = SynagySum(food_namogy, food_synagy)
        diff = abs(fir_food - sec_food)
        if diff < synagy_min:
            synagy_min = diff

    print(f"#{test_case} {synagy_min}")




    # print(f"식재료 조합 : {food_comb}")
    # print(f"나머지 조합 : {food_namogy}")
    # food_idx = list(itertools.permutations(food_comb, len(food_comb)))

