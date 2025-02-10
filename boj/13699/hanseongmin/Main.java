import java.io.*;

/*
11592KB, 68ms

점화식 == DP
사실상 문제 자체에서 DP임을 가르쳐주고 있다.
주어진 점화식대로 구현하면 끝.

숫자가 int형 범위를 넘어설 수 있는데 이 또한 예제에서 알려준다. 친절한 문제.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i - 1; j++) {
                dp[i] += (dp[j] * dp[i - j - 1]);
            }
        }

        bw.write(String.valueOf(dp[n]));
        bw.flush();
    }
}