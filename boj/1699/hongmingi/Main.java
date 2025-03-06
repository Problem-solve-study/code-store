// 12144KB	112ms
import java.util.*;
import java.io.*;
/*
 * n이 주어졌을 때 n보다 작은 제곱수들을 체크하고 그 제곱수들만큼 전에 있던 cnt 값들 중 제일 작은 값에 1을 더해서
 * dp 배열에 추가하는 형태로 구현.
 */
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N+1];
        Arrays.fill(dp, 100000);
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i<=N; i++){
            for(int j=0; j<=Math.sqrt(i); j++){
                dp[i] = Math.min(dp[i], dp[i-j*j]+1);
            }
        }
        System.out.println(dp[N]);
    }
}