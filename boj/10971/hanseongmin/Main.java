import java.io.*;
import java.util.*;

/*
12176KB, 88ms

예전에 수업 시간 중에 나온적 있는 문제인것 같음.
10밖에 안되는 TSP라 완전 탐색
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N;
    static int res = Integer.MAX_VALUE;
    static int[][] W;
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        N = nextInt();
        W = new int[N + 1][N + 1];
        v = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                W[i][j] = nextInt();
            }
        }

        dfs(1, 1, 0);
        System.out.println(res);
    }

    static void dfs(int cnt, int cur, int d) {
        if (cnt == N) {
            if (W[cur][1] != 0) {
                res = Math.min(res, d + W[cur][1]);
            }
            return;
        }

        for (int i = 2; i <= N; i++) {
            if (!v[i] && W[cur][i] != 0) {
                v[i] = true;
                dfs(cnt + 1, i, d + W[cur][i]);
                v[i] = false;
            }
        }
    }
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}