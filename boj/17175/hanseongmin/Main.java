import java.io.*;

/*
11564KB, 68ms

f(0~1): 1
f(2): 3 (f(2) -> f(0) + f(1))
f(3): 5 (f(3) -> f(2)(f(1) + f(0)) + f(1))

이전의 결과가 계속하여 재활용될 수 있는 구조이므로 DP로 풀이
dp[i]: i를 입력했을 때 함수가 호출되는 횟수
dp[i] = dp[i - 1] + dp[i - 2] + 1
(i번째 함수도 호출되므로 +1을 해줘야 함)
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int MOD = 1_000_000_007;

        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[51];
        dp[0] = 1; dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = (1 + dp[i - 1] % MOD + dp[i - 2] % MOD) % MOD;
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
    }
}