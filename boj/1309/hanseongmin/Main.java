import java.io.*;

/*
15028KB, 96ms

대놓고 DP 문제인데 점화식이 바로 띡 나오지는 않았음 그림판으로 끄적끄적거리다가 점화식을 찾음

현재 칸에 사자를 두지 않는 경우를 x, 두는 경우를 .이라고 할 때
맨 처음 칸에 나올 수 있는 경우의 수는
x x, . x, x .으로 총 3가지

두 번째 칸에 x x를 배치한다고 하면 그 이전 칸에서는 어떤 형태로 나오든 상관이 없음(사자를 두질 않으니)
. x를 배치한다고 하면 그 이전 칸에는 x x거나 x .을 배치하는 경우여야 함. (이전 칸이 . x라면 세로로 붙어있게 되니)
마찬가지로 x .을 배치한다고 하면 그 이전 칸에는 x x거나 . x를 배치해야 함

이를 그대로 dp 식으로 옮겨주면 됨
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        final int MOD = 9901;
        int N = nextInt();
        //현재 칸, 모양
        //x x, . x, x .
        int[][] dp = new int[N + 1][3];
        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2];
            dp[i][1] = dp[i - 1][0] + dp[i - 1][2];
            dp[i][2] = dp[i - 1][0] + dp[i - 1][1];

            dp[i][0] %= MOD;
            dp[i][1] %= MOD;
            dp[i][2] %= MOD;
        }
        System.out.print((dp[N][0] + dp[N][1] + dp[N][2]) % MOD);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
