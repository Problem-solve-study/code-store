/*
 * 팩토리얼의 자리수.
 * 자리수는 밑이 10인 로그를 취하면 자릿수를 알 수 있다.
 * 로그의 성질에 따라, log(1 x 2 x 3 x ... x n) = log(1) + log(2) + log(3) + ... + log(n) 으로 표현되므로,
 * dp 배열에 저장하며 값을 구한다.
 */

import java.io.IOException;

public class Main {
    static final int MAX = 10_000_000;
    static double[] dp = new double[MAX + 1];

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= MAX; i++)
            dp[i] = dp[i - 1] + Math.log10(i);

        int T = readInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++)
            sb.append((int) dp[readInt()] + 1).append('\n');

        System.out.println(sb.toString());
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}