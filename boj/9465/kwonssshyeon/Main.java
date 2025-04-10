
// 	27276KB	336ms
import java.io.*;
import java.util.*;

/**
 * dp 문제
 */
public class Main {
    static int n, map[][];

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int tc = nextInt();
        for (int t = 0; t < tc; t++) {
            n = nextInt();
            map = new int[2][n];
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < n; j++) {
                    map[i][j] = nextInt();
                }
            }
            int[][] dp = new int[2][n];
            dp[0][0] = map[0][0];
            dp[1][0] = map[1][0];
            for (int j = 1; j < n; j++) {
                for (int i = 0; i < 2; i++) {
                    // 직전 값과, 대각선 방향에서 현재 값 더한 것 중 최대를 저장
                    dp[i][j] = Math.max(dp[i][j - 1], dp[(i + 1) % 2][j - 1] + map[i][j]);
                }
            }

            sb.append(Math.max(dp[0][n - 1], dp[1][n - 1])).append("\n");
        }
        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
