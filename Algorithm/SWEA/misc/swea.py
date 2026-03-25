#
# # # T = int(input())
# # # # 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
# # # for test_case in range(1, T + 1):
# # #
# # #     N = input()
# # #     Ai = list(map(int, str(input())))
# # #     cnt_list = [0, ] * 10
# # #
# # #     for i in Ai:
# # #         cnt_list[i] += 1
# # #
# # #     max_cnt = max(cnt_list)
# # #     max_idx = 0
# # #
# # #     for idx, j in enumerate(cnt_list):
# # #         if j == max_cnt:
# # #             max_idx = idx
# # #
# # #     print(f"#{test_case} {max_idx} {max_cnt}")
# #
# # T = int(input())
# # # # 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
# # for test_case in range(1, T + 1):
# # #
# #
# #     i_list_buffer = []
# #     N, Q = map(int ,input().split()) # N, Q 입력
# #     defalut_list = ['0'] * N #
# #
#     for j in range(Q):
#         I_list = list(map(int, input().split()))
#         i_list_buffer.append(I_list)
#
#     i_cnt = 1
#
#     for k in i_list_buffer:
#         for l in range(k[0],k[1] + 1): # 1 , 3
#             defalut_list[l-1] = str(i_cnt)
#         i_cnt += 1
# #
# #     A = " ".join(defalut_list)
# #
# #     print(f"#{test_case} {A}")
#
# #import sys
#
# #sys.stdin = open("input.txt", "r")
#
#
# T = int(input())
# # # 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
# for test_case in range(1, T + 1):
#     N = int(input())
#     Ai = list(map(int, input().split()))
#
#     max = Ai[0]
#     min = Ai[0]
#     for score in Ai[1:]:
#         if score > max:
#             max = score
#         if score < min:
#             min = score
#     print(f"#{test_case} {max - min}")
#


#
# T = int(input())
# # # 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
# for test_case in range(1, T + 1):
#
#     N = int(input())
#     sooyeol = input()
#     cnt = 0
#     max_list = []
#     for i in sooyeol:
#         if i == '1':
#             cnt += 1
#         else:
#             max_list.append(cnt)
#             cnt = 0
#     else:
#         max_list.append(cnt)
#     print(f"#{test_case} {max(max_list)}")


T = int(input())
# # 여러개의 테스트 케이스가 주어지므로, 각각을 처리합니다.
for test_case in range(1, T + 1):

    N, M = map(int, input().split())
    Ai = list(map(int, input().split()))
    max_value = sum(Ai[:M])
    min_value = sum(Ai[:M])
    cnt = 1
    while cnt + M <= len(Ai):
        recent_value = sum(Ai[cnt:cnt+M])
        #print(recent_value)
        if recent_value > max_value:
            max_value = recent_value
        if recent_value < min_value:
            min_value = recent_value
        cnt += 1
    #print(max_value)
    #print(min_value)
    print(f"#{test_case} {max_value - min_value}")






