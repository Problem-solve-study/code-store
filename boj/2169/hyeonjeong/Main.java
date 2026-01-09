// 32616KB 324ms
import java.io.*;
import java.util.*;

/**
 * DP
 * 
 * dp[i][j] = (i, j)에 도달할 수 있는 경로의 가치의 합 최대값
 * 이때 다시 방문하지 않는 조건으로 인해, 왼쪽에서 오는 경로와 오른쪽에서 오는 경로를 분리해 탐색한다.
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = next();
        int m = next();
        int[][] map = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = next();
            }
        }

        int[][] dp = new int[n][m];
        dp[0][0] = map[0][0];
        for (int j = 1; j < m; j++) {
            dp[0][j] = dp[0][j - 1] + map[0][j];
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                dp[i][j] = dp[i - 1][j] + map[i][j];
            }

            int[] leftDp = Arrays.copyOf(dp[i], m);
            for (int j = 1; j < m; j++) {
                leftDp[j] = Math.max(leftDp[j - 1] + map[i][j], leftDp[j]);
            }
            
            int[] rightDp = Arrays.copyOf(dp[i], m);
            for (int j = m - 2; j >= 0; j--) {
                rightDp[j] = Math.max(rightDp[j + 1] + map[i][j], rightDp[j]);
            }

            for (int j = 0; j < m; j++) {
                dp[i][j] = Math.max(leftDp[j], rightDp[j]);
            }
        }

        System.out.println(dp[n - 1][m - 1]);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
