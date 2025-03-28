/*
 * 매 곡 시작 전 볼륨을 변경
 * 현재 볼륨이 P면, 다음 곡은 P-V[i], P+V[i] 둘 중 하나로 연주해야 함.
 * dp[i][j] = dp[i-1][j+V[i]]+dp[i-1][j-V[i]]
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt(), S = readInt(), M = readInt();
        boolean[][] dp = new boolean[N + 1][M + 1];
        int[] V = new int[N + 1];
        for (int i = 1; i <= N; i++)
            V[i] = readInt();

        dp[0][S] = true;
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                int idx = j + V[i];
                if (idx <= M)
                    dp[i][j] |= dp[i - 1][idx];
                idx = j - V[i];
                if (idx >= 0)
                    dp[i][j] |= dp[i - 1][idx];
            }
        }

        if (!printRes(dp[N], M))
            System.out.println(-1);
    }

    static boolean printRes(boolean[] res, int M) {
        for (int i = M; i >= 0; i--) {
            if (res[i]) {
                System.out.println(i);
                return true;
            }
        }
        return false;
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}