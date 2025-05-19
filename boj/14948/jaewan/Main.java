// 15172 KB, 156 ms
/*
 * 블록의 숫자만큼 레벨이 되어야 지나갈 수 있다.
 * (0, 0)에서 상,하,좌,우 이동해 (n-1, m-1)에 도착해야 탈출
 * !! 특수장비 !!
 * 한 칸 건너뛰기 가능.
 * 도중에 방향을 바꿀 수 없고 격자 밖으로 나갈 수 없다.
 * 병영을 탈출하기 위해 달성해야 하는 최소한의 레벨을 출력
 * 레벨 제한 k (0 <= k <= 10^9)
 * 
 * 음. 매개변수 탐색 같은데.
 * 판정 함수의 시간복잡도는
 * 3차원 dfs로 N^2
 * 
 * O(log K x N^2)
 */

import java.io.IOException;

public class Main {
    static int N, M, lv;
    static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
    static int[][] map;
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        map = new int[N][M];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                map[i][j] = readInt();
        System.out.println(parametricSearch(map[0][0], 1_000_000_000));
    }

    static int parametricSearch(int left, int right) {
        while (left <= right) {
            lv = (left + right) / 2;
            visit = new boolean[2][N][M];
            visit[0][0][0] = true;
            if (dfs(0, 0, false)) {
                right = lv - 1;
            } else
                left = lv + 1;
        }
        return left;
    }

    static boolean dfs(int y, int x, boolean isUsed) {
        if (y == N - 1 && x == M - 1)
            return true;
        for (int i = 0; i < 4; i++) {
            int ty = y + dy[i], tx = x + dx[i];
            if (ty < 0 || tx < 0 || ty >= N || tx >= M)
                continue;
            if (map[ty][tx] <= lv) {
                if (!visit[isUsed ? 1 : 0][ty][tx]) {
                    visit[isUsed ? 1 : 0][ty][tx] = true;
                    if (dfs(ty, tx, isUsed))
                        return true;
                }
            }
            // 건너뜀
            else if (!isUsed) {
                ty += dy[i];
                tx += dx[i];

                if (ty < 0 || tx < 0 || ty >= N || tx >= M || visit[1][ty][tx] || map[ty][tx] > lv)
                    continue;
                visit[1][ty][tx] = true;
                if (dfs(ty, tx, true))
                    return true;
            }
        }
        return false;
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