//문제: 12911번 좋아하는 배열
//메모리: 16156 KB
//시간: 136 ms

/*
 * dp로 접근
 * dp[i][j]는 i번째 자리가 j가 되는 경우의 수다.
 * j는 i-1번째 자리의 수가 j가 아닌 j의 배수라면 불가능한 경우이므로
 * 길이가 i-1인 배열의 경우의 수에서 j가 아닌 j의 배수로 끝나는 경우를 빼서 dp[i][j]를 구할 수 있다.
 * 위 방법을 구현하였다.
 */

public class Main {
    static final int p = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        int n = nextInt(), k = nextInt();
        int[][] dp = new int[n][k + 1];

        dp[0][0] = k;
        for (int i = 1; i <= k; i++)
            dp[0][i] = 1;

        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i][1] = 1;
            for (int j = 2; j <= k; j++) {
                dp[i][j] = dp[i - 1][0];
                for (int l = (j << 1); l <= k; l += j)
                    dp[i][j] = (dp[i][j] - dp[i - 1][l] + p) % p;
                dp[i][0] = (dp[i][0] + dp[i][j]) % p;
            }
        }
        System.out.println(dp[n - 1][0]);
    }

    static int nextInt() throws Exception {
        int n, c;
        while ((c = System.in.read()) < 48) ;
        n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
