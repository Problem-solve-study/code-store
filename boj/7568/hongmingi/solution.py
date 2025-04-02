import sys

input = sys.stdin.readline

def main():
  n = int(input());
  inbody = [list(map(int, input().split())) for _ in range(n)]
  rank = [0 for _ in range(n)]
  for i in range(n):
    for j in range(n):
      if i == j: continue
      if inbody[i][0]<inbody[j][0] and inbody[i][1]<inbody[j][1]:
        rank[i] += 1
    
    print(rank[i]+1, end=' ')
    
main()