// 11520KB 64ms

import java.util.*;
import java.io.*;

/**
 * DP
 * 내려가는 경우도 세어야 해서, 카운팅으로 인덱싱하는 것보다 상수 변수가 편할 것 같아서 사용했습니다.
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = next();

        int[][][] dp = new int[5][11][n + 1];
        final int MOD = 1_000_000_007;
        final int ZERO = 0;
        final int UP_1 = 1;
        final int UP_2 = 2;
        final int DOWN_1 = 3;
        final int DOWN_2 = 4;

        for (int i = 0; i < 10; i++) {
            dp[ZERO][i][1] = 1;
        }

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i < 10; i++) {
                if (i != 0) {
                    dp[UP_1][i][len] += dp[ZERO][i - 1][len - 1];
                    dp[UP_1][i][len] %= MOD;
                    dp[UP_1][i][len] += dp[DOWN_1][i - 1][len - 1];
                    dp[UP_1][i][len] %= MOD;
                    dp[UP_1][i][len] += dp[DOWN_2][i - 1][len - 1];
                    dp[UP_1][i][len] %= MOD;

                    dp[UP_2][i][len] += dp[UP_1][i - 1][len - 1];
                    dp[UP_2][i][len] %= MOD;
                }

                if (i != 9) {
                    dp[DOWN_1][i][len] += dp[UP_1][i + 1][len - 1];
                    dp[DOWN_1][i][len] %= MOD;
                    dp[DOWN_1][i][len] += dp[UP_2][i + 1][len - 1];
                    dp[DOWN_1][i][len] %= MOD;
                    dp[DOWN_1][i][len] += dp[ZERO][i + 1][len - 1];
                    dp[DOWN_1][i][len] %= MOD;

                    dp[DOWN_2][i][len] += dp[DOWN_1][i + 1][len - 1];
                    dp[DOWN_2][i][len] %= MOD;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < 10; i++) {
            for (int type = 0; type < 5; type++) {
                count += dp[type][i][n];
                count %= MOD;
            }
        }

        System.out.print(count);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
