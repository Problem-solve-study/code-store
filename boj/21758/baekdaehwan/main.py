# 124380KB	120ms
# 누적합 문제입니다. 함정에 빠지지 않게 조심하세요

n = int(input())
a = list(map(int, input().split()))

ca = a.copy()
for i in range(1, n):
    ca[i] = ca[i - 1] + ca[i]

total = 0
maxM = 0
maxL = 0
maxR = 0
for i in range(1, n - 1):
    maxL = max(maxL, -a[i] + ca[-1] - ca[i])
    maxR = max(maxR, -a[i] + ca[i - 1])
    maxM = max(maxM, a[i])
    total += a[i]

res = max(
    total + maxM,
    ca[-1] - a[0] + maxL,
    ca[-1] - a[-1] + maxR
)
print(res)