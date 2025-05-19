// 20908 KB, 188 ms
/*
 * 오른쪽, 오른쪽 아래, 오른쪽 위로만 움직일 수 있다.
 * 쪽문으로 탈출하면서 최대한 많은 당근을 먹어야 한다.
 * 오른쪽으로만 이동하므로 dp를 사용하여 해결할 수 있다.
 * 
 * dp[i][j] = i행 j열에서 먹을 수 있는 당근의 최대 개수
 * dp[i][j] = max(dp[i - 1][j - 1], dp[i][j - 1], dp[i + 1][j - 1]) + 당근[i][j]
 * 가면서 쪽문인 경우, res 값을 갱신한다.
 * 
 * 1차원 dp 배열 두개사용해서 최적화 될거같은데 귀찮아서 안할래
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int[][] dp = new int[N][M], map = new int[N][M];

        int res = -1, startX = 0, startY = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int t = System.in.read();
                if (t == 'C') {
                    map[i][j] = 1;
                } else if (t == '#') {
                    map[i][j] = -1;
                } else if (t == 'R') {
                    startY = i;
                    startX = j;
                } else if (t == 'O') {
                    map[i][j] = 2;
                }
            }
            System.in.read();
        }

        for (int i = 0; i < N; i++)
            Arrays.fill(dp[i], -1);
        dp[startY][startX] = 0;

        for (int j = startX + 1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                for (int k = -1; k <= 1; k++) {
                    if (i + k < 0 || i + k >= N)
                        continue;
                    if (dp[i + k][j - 1] == -1)
                        continue;
                    if (map[i][j] == -1)
                        continue;
                    dp[i][j] = Math.max(dp[i][j], dp[i + k][j - 1] + (map[i][j] == 1 ? 1 : 0));
                    if (map[i][j] == 2) {
                        res = Math.max(res, dp[i][j]);
                    }
                }
            }
        }
        System.out.println(res);
    }

    static int readInt() throws IOException {
        int c = System.in.read(), n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
