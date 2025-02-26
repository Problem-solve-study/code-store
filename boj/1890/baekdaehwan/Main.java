/**
 * 50496KB	304ms
 *
 * [사고 흐름]
 * 대놓고 메모이제이션 문제, 친절하게도 경로 개수는 long으로 세어야 한다는 것을 알려줌.
 * 근데 DP[r][c]가 0이면 다시 해를 구하도록 만드는 바람에 3트함... 나는 바보가 맞음
 *
 * [알고리즘 설명]
 * 일반적인 탐다운 메모이제이션 방식, 메모이제이션을 사용하면 바텀업과 시간, 메모리 사용량 측면에서 거의 차이나지 않는다.
 * 그리고 메모이제이션된 값이 0일수 있으므로, 꼭 -1로 초기화하기.
 */

 import java.util.*;
 import java.io.*;
 
 public class Main {
     static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
     static int N;
     static int[][] map;
     static long[][] DP;
     public static void main(String[] args) throws Exception {
         N = nextInt();
         map = new int[N][N];
         DP = new long[N][N];
 
         for (int r=0; r<N; ++r) {
             for (int c=0; c<N; ++c) {
                 map[r][c] = nextInt();
                 DP[r][c] = -1;
             }
         }
         DP[N-1][N-1] = 1;
         bt(N>>1, N>>1);
         System.out.println(bt(0, 0));
     }
 
     public static long bt(int r, int c) {
         if (DP[r][c] == -1) {
             DP[r][c] = 0;
             if (r+map[r][c] < N) DP[r][c] += bt(r+map[r][c], c);
             if (c+map[r][c] < N) DP[r][c] += bt(r, c+map[r][c]);
         }
         return DP[r][c];
     }
 
     public static int nextInt() throws Exception {
         st.nextToken();
         return (int) st.nval;
     }
 }