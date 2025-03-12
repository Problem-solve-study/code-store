// 11492 KB, 68 ms
/*
 * 체력을 L 잃고, 기쁨을 J 얻는다.
 * 체력은 0이하가 되면 죽어서 안됨.
 * 얻을 수 있는 최대 기쁨을 출력.
 * 
 * dp[i][j] : i 번째 요소까지 고려했을 때, 체력 j 잃고 얻을 수 있는 최대 기쁨.
 * dp[0][0] = 0;
 * dp[i][j] = dp[i-1][j], dp[i-1][j-L]+J
 * ======
 * 1차원 dp 배열로 최적화
 */

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] L = new int[N + 1], J = new int[N + 1];
        int[] dp = new int[100];
        for (int i = 1; i <= N; i++)
            L[i] = readInt();
        for (int i = 1; i <= N; i++)
            J[i] = readInt();

        for (int i = 1; i <= N; i++)
            for (int j = 99; j >= L[i]; j--)
                dp[j] = Math.max(dp[j], dp[j - L[i]] + J[i]);

        int max = 0;
        for (int i = 0; i < 100; i++)
            max = Math.max(max, dp[i]);
        System.out.println(max);
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}