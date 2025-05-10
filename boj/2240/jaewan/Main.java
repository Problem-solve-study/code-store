// 11456 KB, 64 ms
/*
 * 자두 T초 동안, 최대 W번만 움직이고 싶어 함.
 * 받을 수 있는 자두의 최대 개수.
 * 
 * 각 초마다 W 번 움직였을 때 받을 수 있는 최대 개수
 * dp[T][W]. 1번에서 시작. 짝수 번 이동하면 위치는 1번, 홀수 번 이동했을 때 위치는 2번.
 * 
 * dp[..][0] = 자두가 1번 나무에서 떨어지는지
 * dp[1][1] = 자두가 2번 나무에서 떨어지는지 + max(dp[0][0], dp[0][1])
 * dp[1][2] = 자두가 1번 나무에서 떨어지는지 + max(dp[0][1], dp[0][2])
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int T = readInt(), W = readInt();
        int[][] dp = new int[T][W + 1];
        int cur = readInt();
        dp[0][0] = cur == 1 ? 1 : 0;
        dp[0][1] = cur == 2 ? 1 : 0;
        for (int i = 1; i < T; i++) {
            cur = readInt();
            dp[i][0] = dp[i - 1][0] + (cur == 1 ? 1 : 0);
            for (int j = 1; j <= W; j++) {
                // 짝수 번 움직인 경우 위치는 1번.
                if ((j & 1) == 0)
                    dp[i][j] = (cur == 1 ? 1 : 0) + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
                // 홀수 번 움직인 경우 위치 2번
                else
                    dp[i][j] = (cur == 2 ? 1 : 0) + Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
        }
        System.out.println(Math.max(dp[T - 1][W], dp[T - 1][W - 1]));
    }

    static int readInt() throws IOException {
        int c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}