//14512KB, 108ms

import java.io.*;

public class Main {
    static int[] dp;
    static final int MOD = 10007;

    public static void main(String[] args) throws Exception {
        var br = new BufferedReader(new InputStreamReader(System.in));
        var bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        dp = new int[1001];
        dp[1] = 1; dp[2] = 3;

        bw.write(String.valueOf(getAns(n)));
        bw.flush();
    }

    public static int getAns(int n) {
        if (dp[n] != 0) return dp[n];

        dp[n] = (getAns(n - 1) + 2 * getAns(n - 2)) % MOD;
        return dp[n];
    }
}
