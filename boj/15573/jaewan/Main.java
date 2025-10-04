/*
 * 매개변수 탐색 + BFS
 */

import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static int N, M, K;
    static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
    static int[][] map;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        K = readInt();
        map = new int[N][M];

        int max = 0;
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                map[i][j] = readInt();
                max = Math.max(max, map[i][j]);
            }

        System.out.println(parametricSearch(1, max));
    }

    // K 개 이상의 광물을 채굴할 수 있는 최소의 D 리턴
    static int parametricSearch(int left, int right) {
        int mid;

        while (left <= right) {
            mid = (left + right) / 2;

            if (check(mid))
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    // 채굴기 강도 D 일때 K 개 이상 채굴 가능한지 여부 리턴하는 판정함수
    static boolean check(int D) {
        int cnt = 0;
        boolean[][] visit = new boolean[N][M];

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            if (map[i][0] <= D) {
                visit[i][0] = true;
                cnt++;
                queue.offer(new int[] { i, 0 });
            }
            if (map[i][M - 1] <= D) {
                visit[i][M - 1] = true;
                cnt++;
                queue.offer(new int[] { i, M - 1 });
            }
        }
        for (int j = 1; j < M - 1; j++) {
            if (map[0][j] <= D) {
                visit[0][j] = true;
                cnt++;
                queue.offer(new int[] { 0, j });
            }
        }

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int ny = cur[0] + dy[dir], nx = cur[1] + dx[dir];

                if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx] || map[ny][nx] > D)
                    continue;

                visit[ny][nx] = true;
                cnt++;
                if (cnt >= K)
                    return true;
                queue.offer(new int[] { ny, nx });
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