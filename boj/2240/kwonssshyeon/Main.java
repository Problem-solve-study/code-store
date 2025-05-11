// 	12856KB	68ms
import java.io.*;
import java.util.*;

/**
 * 처음에는 t = 1000 이어서, 제자리에 있거나 움직이거나 2중에 1개로 움직이는 모든 경우의 수는 1000^2 = 10^6 으로 충분할거라 생각했는데
 * 시간복잡도를 잘못구했다.
 * 가만히 있으면서 자두를 먹을 수 있는 경우, 가만히 있으면서 자두를 못 먹는 경우, 움직여서 자두를 먹는 경우
 * 위 3가지의 경우가 생겨서 백트래킹을 하면 1000^3으로 시간초과가 발생할 수 있다.
 * => 메모이제이션 적용
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[] map;
    static int t, w, answer;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        t = nextInt();
        w = nextInt();
        map = new int[t];
        for (int i=0; i<t; i++) {
            map[i] = nextInt();
        }
        dp = new int[t+1][3][w+1];
        for (int i=0; i<=t; i++) {
            for (int j=0; j<=2; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        System.out.println(dfs(0, 1, 0));
    }

    static int dfs(int idx, int now, int wCount) {
        if (wCount > w) return -1;
        if (idx == t) return 0;

        if (dp[idx][now][wCount] != -1) return dp[idx][now][wCount];

        int result = 0;
        if (now == map[idx]) {
            result = dfs(idx+1, now, wCount) + 1;
        }
        else {
            int stay = dfs(idx+1, now, wCount);
            int move = -1;
            if (wCount + 1 <= w) {
                move = dfs(idx+1, now == 1 ? 2 : 1, wCount+1) + 1;
            }
            result = Math.max(stay, move);
        }
        dp[idx][now][wCount] = result;
        return dp[idx][now][wCount];
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
