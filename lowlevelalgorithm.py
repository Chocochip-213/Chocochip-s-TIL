gababo = list(map(int, input().split()))

if (gababo[0] + 1 == gababo[1]):
    print("B")

elif (gababo[0] - 1 == gababo[1]):
    print("A")
else:
    print("B")