import java.io.*;

/*
11544KB 68ms

N이 작아서 브루트포스로도 가능하게끔 한 거 같음
근데 문제가 너무 DP라서 그냥 DP로 했음
DP로 풀었다면 Large도 그대로 제출해서 맞을 수 있다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        final int 좌하 = 0;
        final int 하 = 1;
        final int 우하 = 2;
        int N = nextInt(); int M = nextInt();
        int[][] map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                map[i][j] = nextInt();
            }
        }

        //DP 테이블 채우기
        //우주선은 전에 움직인 방향으로 움직일 수 없으므로 이를 적절히 고려하며 채운다.
        int[][][] dp = new int[N + 1][M + 1][3];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (j == 1) {
                    dp[i][j][하] = dp[i - 1][j + 1][좌하] + map[i][j];
                    dp[i][j][우하] = Math.min(dp[i - 1][j][하], dp[i - 1][j + 1][좌하]) + map[i][j];
                } else if (j == M) {
                    dp[i][j][하] = dp[i - 1][j - 1][우하] + map[i][j];
                    dp[i][j][좌하] = Math.min(dp[i - 1][j][하], dp[i - 1][j - 1][우하]) + map[i][j];
                } else {
                    dp[i][j][좌하] = Math.min(dp[i - 1][j][하], dp[i - 1][j - 1][우하]) + map[i][j];
                    dp[i][j][하] = Math.min(dp[i - 1][j - 1][우하], dp[i - 1][j + 1][좌하]) + map[i][j];
                    dp[i][j][우하] = Math.min(dp[i - 1][j][하], dp[i - 1][j + 1][좌하]) + map[i][j];
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= M; i++) {
            for (int j = 0; j < 3; j++) {
                res = Math.min(res, dp[N][i][j] == 0 ? Integer.MAX_VALUE : dp[N][i][j]);
            }
        }

        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
