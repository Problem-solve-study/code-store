import java.io.*;

/*
55356KB, 692ms

음 늘 보던 DP군 하고 DP로 풀었다.
행이 1인 경우, 열이 1인 경우, 그 외의 경우로 나눠서 조건에 맞게 최소 비용을 갱신해주면 된다.
풀고나서 다익스트라 태그가 달려있는걸 보고 당황. 다익스트라로도 풀 수 있는듯 하다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int[][] map = new int[n + 1][n + 1];
        int[][] dp = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                map[i][j] = nextInt();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) continue;

                if (i == 1) { //행이 1인 경우에는 옆에서만 올 수 있음
                    dp[i][j] = dp[i][j - 1] + (map[i][j] >= map[i][j - 1] ? map[i][j] - map[i][j - 1] + 1 : 0);
                } else if (j == 1) { //열이 1인 경우에는 위에서만 올 수 있음
                    dp[i][j] = dp[i - 1][j] + (map[i][j] >= map[i - 1][j] ? map[i][j] - map[i - 1][j] + 1 : 0);
                } else { //그 외의 경우에는 옆에서 오거나 위에서 올 수 있는데, 둘 중 최솟값 택함
                    dp[i][j] = Math.min(
                            dp[i - 1][j] + (map[i][j] >= map[i - 1][j] ? map[i][j] - map[i - 1][j] + 1 : 0),
                            dp[i][j - 1] + (map[i][j] >= map[i][j - 1] ? map[i][j] - map[i][j - 1] + 1 : 0)
                    );
                }
            }
        }
        System.out.println(dp[n][n]);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
