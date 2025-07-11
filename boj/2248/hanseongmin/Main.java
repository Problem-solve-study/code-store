import java.io.*;

/*
11544KB, 68ms

dp[i][j]: i의 길이를 가지고 j개의 1비트를 가지고 있을 때 경우의 수라고 생각하면
dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]로 점화식이 나온다.

점화식 도출은 어렵지 않은데 I번째 수를 찾기가 조금 까다로운듯
*/

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static long N, L, I;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        N = nextLong();
        L = nextLong();
        I = nextLong();
        dp = new int[(int)N + 1][(int)L + 1];
        dp[1][0] = 1; dp[1][1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i][0] = 1;
            for (int j = 1; j <= L; j++) {
                dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
            }
        }

        StringBuilder sb1 = new StringBuilder(Integer.toBinaryString(findAns(N, L, I)));
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < N - sb1.length(); i++) {
            sb2.append('0');
        }
        System.out.print("" + sb2 + sb1);
    }

    //n번째 비트이고 1비트의 수가 l개 이하이면서, i번째 수
    static int findAns(long n, long l, long i) {
        //LSB까지 도달했다면 1번째 수일 때 0, 2번쨰 수일 때 1
        if (n == 1) {
            return i == 1 ? 0 : 1;
        }

        //n번째 비트가 0일지 1일지를 판별
        //n번째 비트가 0이라고 가정하고 경우의 수 카운팅
        long sum = 0;
        for (int j = 0; j <= l; j++) {
            sum += dp[(int)n - 1][j];
        }

        if (sum < i) {
            //sum이 i보다 작다면 n번째 비트가 0일 때 이후의 경우이니
            //n번째 비트가 1이라는 소리.
            return (int) Math.pow(2, n - 1) + findAns(n - 1, l - 1, i - sum);
        } else {
            //반대의 경우는 n번쨰 비트가 0임
            return findAns(n - 1, l, i);
        }
    }

    static long nextLong() throws Exception {
        st.nextToken();
        return (long) st.nval;
    }
}