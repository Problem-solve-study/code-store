//14352KB, 108ms

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] dp = new long[n + 1][n + 1][3];
        dp[1][1][0] = 1;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;
                if (!boundaryCheck(n, i, j) || map[i][j] == 1) continue;

                if (boundaryCheck(n, i, j + 1) && map[i][j + 1] != 1) {
                    dp[i][j][0] += dp[i][j - 1][0];
                    dp[i][j][0] += dp[i - 1][j - 1][2];
                }

                if (boundaryCheck(n, i + 1, j) && map[i + 1][j] != 1) {
                    dp[i][j][1] += dp[i - 1][j][1];
                    dp[i][j][1] += dp[i - 1][j - 1][2];
                }

                if (boundaryCheck(n, i + 1, j + 1) && 
                map[i + 1][j] != 1 && map[i][j + 1] != 1 && map[i + 1][j + 1] != 1) {
                    dp[i][j][2] += dp[i][j - 1][0];
                    dp[i][j][2] += dp[i - 1][j][1];
                    dp[i][j][2] += dp[i - 1][j - 1][2];
                }
            }
        }

        bw.write(String.valueOf(dp[n][n - 1][0] + dp[n - 1][n][1] + dp[n - 1][n - 1][2]));
        bw.flush();
    }

    public static boolean boundaryCheck(int n, int r, int c) {
        return (0 <= r && r <= n) && (0 <= c && c <= n);
    }
}