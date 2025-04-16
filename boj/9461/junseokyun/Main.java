
//11552KB 68ms
import java.io.*;

public class Main {
    static int N;
    static long[] dp;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            if (N == 1 || N == 2) {
                sb.append(1).append('\n');
                continue;
            }
            dp = new long[N + 1];
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 1;
            for (int i = 3; i <= N; i++) {
                dp[i] = dp[i - 2] + dp[i - 3];
            }
            sb.append(dp[N]).append('\n');
        }
        System.out.println(sb.toString());
    }
}