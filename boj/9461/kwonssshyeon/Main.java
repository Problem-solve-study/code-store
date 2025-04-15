
// 11524KB	68ms
import java.io.*;
import java.util.*;

/**
 * 그림을 통해 dp[i] = dp[i-1] + dp[i-5] 라는 점화식을 유추함.
 * int 오버플로 때문에 1틀
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int T = nextInt();
        long[] dp = new long[101];
        dp[1] = 1L;
        dp[2] = 1L;
        dp[3] = 1L;
        dp[4] = 2L;
        dp[5] = 2L;
        for (int i = 6; i <= 100; i++) {
            dp[i] = dp[i - 1] + dp[i - 5];
        }
        for (int t = 0; t < T; t++) {
            n = nextInt();
            sb.append(dp[n]).append("\n");
        }
        System.out.print(sb);
    }

    // fast i/o
    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
