
// 17260 KB, 156 ms
/*
 * N x M 맵에서 A x B 유닛 이동
 * S 에서 E 까지 최소 이동 횟수
 * N, M, A, B, K 장애물의 수
 * BFS 하면서 이동 여부 판단할때 유닛 크기 고려
 */
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static int N, M, A, B, K, endY, endX;
    static boolean[][] isWall;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        A = readInt();
        B = readInt();
        K = readInt();

        isWall = new boolean[N + 1][M + 1];
        for (int i = 0; i < K; i++)
            isWall[readInt()][readInt()] = true;

        System.out.println(BFS(readInt(), readInt()));
    }

    static int BFS(int startY, int startX) throws IOException {
        int endY = readInt(), endX = readInt();
        int[] dy = { 0, 0, -1, 1 }, dx = { 1, -1, 0, 0 };
        boolean[][] visit = new boolean[N + 1][M + 1];

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[] { startY, startX, 0 });
        visit[startY][startX] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == endY && cur[1] == endX)
                return cur[2];

            for (int dir = 0; dir < 4; dir++) {
                int ny = cur[0] + dy[dir], nx = cur[1] + dx[dir];

                if (ny <= 0 || nx <= 0 || ny + A > N + 1 || nx + B > M + 1 || visit[ny][nx])
                    continue;
                if (!check(ny, nx))
                    continue;
                visit[ny][nx] = true;
                queue.offer(new int[] { ny, nx, cur[2] + 1 });
            }
        }
        return -1;
    }

    static boolean check(int y, int x) {
        for (int i = 0; i < A; i++)
            for (int j = 0; j < B; j++)
                if (isWall[y + i][x + j])
                    return false;
        return true;
    }

    static byte[] buf = new byte[8192];
    static int pos, len;

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