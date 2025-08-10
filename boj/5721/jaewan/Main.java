
/*
 * 1. 각 줄마다 집을 수 있는 최대 사탕 개수를 구하고
 * 2. 줄을 선택 
 * dp로
 * 
 * 연속된 칸 선택 안되고, 연속된 줄 선택 안됨.
 * dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i])
 */
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N, M;

        StringBuilder sb = new StringBuilder();
        while ((N = readInt()) != 0 && (M = readInt()) != 0) {
            int[][] arr = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= M; j++)
                    arr[i][j] = readInt();

            int[][] dp = new int[N + 1][M + 1];
            for (int i = 1; i <= N; i++)
                dp[i][1] = arr[i][1];

            for (int i = 1; i <= N; i++) {
                for (int j = 2; j <= M; j++) {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i][j - 2] + arr[i][j]);
                }
            }

            int[] res = new int[N + 1];
            res[1] = dp[1][M];
            for (int i = 2; i <= N; i++) {
                res[i] = Math.max(res[i - 1], res[i - 2] + dp[i][M]);
            }

            sb.append(res[N]).append('\n');
        }
        System.out.print(sb);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }
}