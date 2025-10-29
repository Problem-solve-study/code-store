/* 
 * (1 + x)(1 + x + x^2) x ... x (1 + x + ... + x^k)
 * 
 * 1, 2, ... , k
 * 
 * 각 항에서 숫자 이하인 것 골라서 N 만들기 문제
 * 
 * k <= 20 인데 단계별로 k 가짓수 가능
 * 즉, 전부 다 하면 20^20 ...
 * 
 * dp로 계수 계산.
 * 
 */

import java.io.IOException;

public class Main {
    static int N, K, cnt;

    public static void main(String[] args) throws IOException {
        int T = readInt();
        long[][] dp;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            K = readInt();
            N = readInt();
            if (N == 0) {
                sb.append(1).append('\n');
                continue;
            }
            dp = new long[K + 1][N + 1];
            dp[1][0] = dp[1][1] = 1;

            for (int i = 2; i <= K; i++) {
                for (int j = 0; j <= N; j++) {
                    for (int mul = 0; mul <= i; mul++) {
                        if (j - mul < 0)
                            break;
                        dp[i][j] += dp[i - 1][j - mul];
                    }
                }
            }
            sb.append(dp[K][N]).append('\n');
        }
        System.out.print(sb);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}