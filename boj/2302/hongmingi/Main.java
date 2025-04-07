import java.io.*;
import java.util.*;
/*
 * 피보나치를 활용한 경우의 수 탐색.
 * VIP 자리들은 고정석인데 이 사이에 n개의 좌석이 있을 경우 fib[n]개의 경우의 수를 곱해줌.
 * n개의 좌석 중 a, b, c 3자리가 vip일 경우 fib[a-1]*fib[b-a-1]*fib[c-b-1]*fib[n-c].
 */
public class Main {
  static int[] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for(int i=2; i<=n; i++){
          dp[i] = dp[i-1]+dp[i-2];
        }
        int cnt = 1;
        int first = 0; // 두 vip 좌석 중 오른쪽
        int second; // 왼쪽
        for(int i=0; i<m; i++){
          second = Integer.parseInt(br.readLine());
          cnt*=dp[second-first-1];
          first = second;
        } 
        second = n;
        cnt*=dp[second-first];
        System.out.println(cnt);        
    }

}
