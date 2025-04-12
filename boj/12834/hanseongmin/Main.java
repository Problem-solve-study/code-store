import java.io.*;

/*
17868KB, 1416ms

N이 작아서 무지성 플로이드-워셜 돌렸는데 다익스트라 2번으로 풀리는 문제였음...
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt(); int V = nextInt(); int E = nextInt();
        int KIST = nextInt(); int food = nextInt();
        int[] team = new int[N];
        for (int i = 0; i < N; i++) {
            team[i] = nextInt();
        }

        int[][] dp = new int[V + 1][V + 1];
        for (int i = 0; i <= V; i++) {
            for (int j = 0; j <= V; j++) {
                if (i == j) dp[i][j] = 0;
                else dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < E; i++) {
            int a = nextInt();
            int b = nextInt();
            int l = nextInt();
            dp[a][b] = dp[b][a] = Math.min(dp[a][b], l);
        }

        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (dp[i][k] == Integer.MAX_VALUE || dp[k][j] == Integer.MAX_VALUE) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        int res = 0;
        for (int t : team) {
            int houseToKIST = dp[t][KIST] == Integer.MAX_VALUE ? -1 : dp[t][KIST];
            int houseToFood = dp[t][food] == Integer.MAX_VALUE ? -1 : dp[t][food];
            res += (houseToKIST + houseToFood);
        }
        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
