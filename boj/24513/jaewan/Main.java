
// 52172 KB, 356 ms
/*
 * BFS, 1번 2번이 같은 시간에 도착하면 3번 바이러스.
 */
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), M = readInt();
        int[] res = new int[4];
        int[][] map = new int[N][M], time = new int[N][M];

        ArrayDeque<Data> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++) {
                map[i][j] = readInt();
                if (map[i][j] == 1 || map[i][j] == 2) {
                    // 종류, 시간, y, x
                    queue.offer(new Data(map[i][j], 1, i, j));
                }
            }

        int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };
        while (!queue.isEmpty()) {
            Data cur = queue.poll();
            if (map[cur.y][cur.x] == 3)
                continue;
            for (int i = 0; i < 4; i++) {
                int ny = cur.y + dy[i];
                int nx = cur.x + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M || map[ny][nx] == -1 || map[ny][nx] == cur.type)
                    continue;
                if (map[ny][nx] == 0) {
                    map[ny][nx] = cur.type;
                    time[ny][nx] = cur.time + 1;
                    queue.offer(new Data(cur.type, time[ny][nx], ny, nx));
                } else if (map[ny][nx] != 3 && time[ny][nx] == cur.time + 1) {
                    map[ny][nx] = 3;
                }
            }
        }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if (map[i][j] > 0)
                    res[map[i][j]]++;
        System.out.printf("%d %d %d", res[1], res[2], res[3]);
    }

    static class Data {
        int type, time, y, x;

        public Data(int type, int time, int y, int x) {
            this.type = type;
            this.time = time;
            this.y = y;
            this.x = x;
        }

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
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}