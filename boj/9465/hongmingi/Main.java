// 121628KB	616ms
import java.io.*;
import java.util.*;
/*
 * 2차원 dp를 통해 구현. 1부터 n까지의 스티커들의 합 중 [0][n]이 선택되었을 때의 최댓값과 
 * [1][n]이 선택되었을 때의 최댓값을 구하고 이 둘 중 더 큰 값을 반환하면 됨.
 */
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int T = Integer.parseInt(br.readLine());
    for(int t=0; t<T; t++){
      int n = Integer.parseInt(br.readLine());
      int[][] scores = new int[2][n+1];
      for(int i=0; i<2; i++){
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int j=1; j<=n; j++){
          scores[i][j] = Integer.parseInt(st.nextToken());
        }
      }

      int[][] dp = new int[2][n+1];
      dp[0][1] = scores[0][1];
      dp[1][1] = scores[1][1];

      for(int i=2; i<=n; i++){
        dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + scores[0][i];
        dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + scores[1][i];
      }
      System.out.println(Math.max(dp[0][n], dp[1][n]));
    }
  }
}