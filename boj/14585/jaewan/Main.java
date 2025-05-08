/*
 * M개의 사탕이 들어있는 N개의 사탕 바구니가 있다.
 * 수빈이가 먹을 수 있는 사탕의 최대 개수
 * 
 * 수빈이는 x, y 좌표가 늘어나는 방향으로만 움직일 수 있다.
 * 
 * dp[i][j] 에서 얻을 수 있는 사탕의 최대 개수
 * (0,0)에서 (i,j)까지 오려면 i+j초가 걸린다.
 * 해당 위치에 사탕 바구니가 있으면, M - i - j 개의 사탕을 얻을 수 있음.
 * 
 * (0,0)에서 증가하는 방향대로 dp 계산하면 되겠다.
 */

import java.io.IOException;

public class Main {
    static final int SIZE = 300;

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        boolean[][] candy = new boolean[SIZE + 1][SIZE + 1];
        int[][] dp = new int[SIZE + 1][SIZE + 1];
        for (int i = 0; i < N; i++)
            candy[readInt()][readInt()] = true;

        for (int i = 0; i < SIZE + 1; i++) {
            for (int j = 0; j < SIZE + 1; j++) {
                // 사탕이 녹아서 없음
                int remain = Math.max(M - i - j, 0);

                if (j > 0)
                    dp[i][j] = dp[i][j - 1] + (candy[i][j] ? remain : 0);
                if (i > 0)
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j] + (candy[i][j] ? remain : 0));
            }
        }

        System.out.println(dp[SIZE][SIZE]);
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