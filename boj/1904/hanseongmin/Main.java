import java.io.*;

/*
15480KB, 84ms

문제를 읽다보니 DP의 느낌이 솔솔 난다. 뭔가 문제에서 점화식을 세우라는 느낌이다.
dp[i]: n이 i일 때 만들 수 있는 가짓수라고 하자.
dp[1] = 1이고 dp[2] = 2, dp[4] = 5라고 문제에서 친절히 알려주고 있다.

1 - 1
2 - 00, 11
3 - 001, 100, 111
4 - 0000, 0011, 1001, 1100, 1111
5 - 00001, 00100, 00111, 10000, 10011, 11001, 11100, 11111

1 2 3 5 8? 피보나치인거같은데?
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        final int MOD = 15746;

        int n = nextInt();
        int[] dp = new int[1_000_001];
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] += (dp[i - 2] + dp[i - 1]);
            dp[i] %= MOD;
        }
        System.out.println(dp[n]);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
