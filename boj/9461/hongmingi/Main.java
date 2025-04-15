// 11472KB	72ms
import java.io.*;
import java.util.*;
/*
 * n이 6 이상일 때부터 나선의 길이는 n-1과 n-5의 나선 길이의 합으로 나타내어진다.
 * 이를 dp로 구현하면 된다.
 */
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    long[] dp = new long[101];
    dp[1] = dp[2] = dp[3] = 1;
    dp[4] = dp[5] = 2;
    for(int i=6; i<=100; i++){
      dp[i] = dp[i-1]+dp[i-5];
    }
    for(int i=0; i<n; i++){
      int k = Integer.parseInt(br.readLine());
      System.out.println(dp[k]);
    }
  }
}
