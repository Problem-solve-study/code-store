import sys
import math
from collections import *

input = sys.stdin.readline
N = int(input())
honey = list(map(int, input().split()))
sums = [0]*N
sums[0] = honey[0]
for i in range(1, N): sums[i]=sums[i-1]+honey[i]

totalSum = 0
# 벌통이 맨 오른쪽에 있을 경우
for i in range(1, N-1):
    totalSum = max(totalSum, 2*sums[N-1] - honey[i] - honey[0] - sums[i], sums[N-2] - honey[i]+sums[i-1], sums[N-2]-honey[0] + honey[i])

print(totalSum)