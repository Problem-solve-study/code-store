import java.io.*;
import java.util.*;

/*
25780KB, 276ms

오늘 강의장에서 -1로 초기화를 해야하는 DP 문제라는 것을 들어버림
그대로 풀이했음
 */

public class Main {
    static int N, M, r, c;
    static int[][] dp;
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        map = new char[N][M]; dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                dp[i][j] = -1;
                //현재 위치를 저장하고 DP의 값을 0으로 초기화(가능한 경우이므로)
                if (map[i][j] == 'R') {
                    r = i; c = j;
                    dp[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < M; j++) {
            for (int i = 0; i < N; i++) {
                //현재 위치가 벽이라면 반드시 못가는 경우
                if (map[i][j] == '#') continue;
                //이후 가능한 이전 위치로부터 dp 테이블 갱신 시도
                if (bc(i, j - 1)) dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                if (bc(i - 1, j - 1)) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1]);
                if (bc(i + 1, j - 1)) dp[i][j] = Math.max(dp[i][j], dp[i + 1][j - 1]);
                //현재 위치가 도달 가능하며(!= -1) 당근이 존재할 경우 테이블의 값 하나 증가
                if (dp[i][j] != -1 && map[i][j] == 'C') dp[i][j]++;
            }
        }

        //배열을 순회하며 가장 많은 당근을 가지고 나갈 수 있는 쪽문을 찾음
        int max = -1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'O') {
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        System.out.print(max);
    }

    static boolean bc(int r, int c) {
        return (0 <= r && r < N) && (0 <= c && c < M);
    }
}
