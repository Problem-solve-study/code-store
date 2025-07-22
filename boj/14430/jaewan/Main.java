
/*
 * 왼쪽 위부터 오른쪽 아래(N, M) 까지 자원을 탐색
 * 오른쪽 또는 아래로 한 칸 이동 가능
 * 탐색할 수 있는 자원의 최대 숫자 구하기
 * 
 * N <= 300, M <= 300
 * 
 * dp[i][j] = i, j 위치까지 얻을 수 있는 최대 자원의 개수
 */
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int[][] dp = new int[N][M];

        dp[0][0] = readInt();
        for (int j = 1; j < M; j++)
            dp[0][j] = dp[0][j - 1] + readInt();

        for (int i = 1; i < N; i++) {
            dp[i][0] = dp[i - 1][0] + readInt();
            for (int j = 1; j < M; j++)
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + readInt();
        }

        System.out.println(dp[N - 1][M - 1]);
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