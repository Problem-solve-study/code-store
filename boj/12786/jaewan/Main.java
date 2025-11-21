/*
 * 가능한 기능
 * 1. O: 가만히 있기
 * 2. A: 위로 1m 이동
 * 3. B: 현재 높이만큼 위로 이동
 * 4. C: 아래로 1m 이동
 * 5. T: 어느 위치로든 순간이동
 * 
 * 나무의 개수 N <= 100
 * T 기능 제한 횟수 K <= 50
 * N개의 줄에 각 나무의 구멍 개수 M, M 개의 구멍 높이
 * 
 * 1에서 출발
 * 마지막 나무까지 통과할 때의 최소 T기능 사용횟수 출력
 * 
 * 상태를 생각해 보면,
 * (위치, 높이, T 사용횟수)
 * 
 * 같은 위치와 높이면, 사용횟수가 더 적은 것만.
 * 
 * dp로도 해결이 가능하겠다.
 * 나무의 index 별로, 모든 경우는 이전 index 의 나무에서 오는 경우니까.
 * dp[i][j] : i 번째 나무의 j 높이까지 도달하기 위한 최소 T 기능 사용 횟수
 */

import java.util.Arrays;

public class Main {
    static int N, K;
    static final int MAX = 999;

    public static void main(String[] args) throws Exception {
        N = readInt();
        K = readInt();
        boolean[][] isBlanked = new boolean[N + 1][21];
        for (int i = 1; i <= N; i++) {
            int t = readInt();
            while (t-- > 0)
                isBlanked[i][readInt()] = true;
        }

        int[][] dp = new int[N + 1][21];
        for (int i = 0; i <= N; i++)
            Arrays.fill(dp[i], MAX);

        dp[0][1] = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < 21; j++) {
                if (!isBlanked[i][j])
                    continue;

                // 가만히 있기
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j]);

                // 위로 1m 이동
                if (j > 1)
                    dp[i][j] = Math.min(dp[i - 1][j - 1], dp[i][j]);

                // 현재 높이만큼 위로 이동, 20m 인 경우는 10m 부터 전부 다됨
                if ((j & 1) == 0) {
                    if (j == 20)
                        for (int k = 10; k <= 20; k++)
                            dp[i][j] = Math.min(dp[i - 1][k], dp[i][j]);
                    else
                        dp[i][j] = Math.min(dp[i - 1][j / 2], dp[i][j]);
                }

                // 아래로 1m 이동
                if (j < 20)
                    dp[i][j] = Math.min(dp[i - 1][j + 1], dp[i][j]);

                // 순간이동
                for (int k = 1; k <= 20; k++)
                    dp[i][j] = Math.min(dp[i - 1][k] + 1, dp[i][j]);
            }
        }

        int res = MAX;
        for (int i = 1; i < 21; i++)
            res = Math.min(res, dp[N][i]);
        if (res != MAX && res <= K)
            System.out.println(res);
        else
            System.out.println(-1);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static int readInt() throws Exception {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;

        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static byte read() throws Exception {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }
}