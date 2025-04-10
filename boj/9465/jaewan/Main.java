// 25296 KB, 232 ms
/*
 * dp, dp[i][j]에 점수 최댓값 저장
 * 해당 위치에 스티커는 dp[1-i][j-1]에서 떼거나 , dp[i][j-2], dp[1-i][j-2]에서 떼거나.
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int TC = readInt();
        StringBuilder sb = new StringBuilder();
        for (int tc = 0; tc < TC; tc++) {
            int n = readInt();
            int[] arr1 = new int[n + 1], arr2 = new int[n + 1];
            for (int i = 0; i < n; i++)
                arr1[i] = readInt();
            for (int i = 0; i < n; i++)
                arr2[i] = readInt();

            int[][] dp = new int[2][n + 1];
            dp[0][0] = arr1[0];
            dp[1][0] = arr2[0];
            dp[0][1] = dp[1][0] + arr1[1];
            dp[1][1] = dp[0][0] + arr2[1];

            for (int j = 2; j < n; j++) {
                dp[0][j] = Math.max(dp[0][j - 2] + arr1[j], dp[1][j - 2] + arr1[j]);
                dp[0][j] = Math.max(dp[1][j - 1] + arr1[j], dp[0][j]);

                dp[1][j] = Math.max(dp[0][j - 2] + arr2[j], dp[1][j - 2] + arr2[j]);
                dp[1][j] = Math.max(dp[0][j - 1] + arr2[j], dp[1][j]);
            }

            sb.append(Math.max(dp[0][n - 1], dp[1][n - 1])).append('\n');
        }
        System.out.println(sb.toString());
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