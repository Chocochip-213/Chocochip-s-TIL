# T = int(input())
#
# for test_case in range(T):
#     word = input()
#     N = len(word)
#
#     for i in range(N):
#         if word[i] == word[N-1-i]:
#             continue
#         else:
#             print(f"#{test_case} 0")
#             break
#     else:
#         print(f"#{test_case} 1")


def nineturn(NN):
    array_len = len(NN)
    buffer_list = []
    final_list = []
    for i in range(array_len):
        for j in range(array_len):
            buffer_list.append(NN[array_len-1-j][i])
        final_list.append(buffer_list)
        buffer_list = []

    return final_list


T = int(input())
# 테케 입력 횟수
for test_case in range(1, T+1):

    N = int(input())
    NNarray = []
    nine = []
    hundeight = []
    twoseven = []

    for _ in range(N):
        NNarray.append(list(input().split())) # 기존 배열 문자열로 입력받기

    nine.extend(nineturn(NNarray)) # 90도 회전 반환받기
    hundeight.extend(nineturn(nine)) # 180도 회전 반환
    twoseven.extend(nineturn(hundeight)) # 270도 회전 반환


    print(f"#{test_case}")
    for i in range(0, N): # 출력
        print(f"{''.join(nine[i])} {''.join(hundeight[i])} {''.join(twoseven[i])}")




































