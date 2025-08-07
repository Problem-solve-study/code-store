
/*
 * visit[i][j] : i번째 지점에 j 속도로 방문 체크 BFS
 * 속도 한계점이 어디일까.
 * 141 까지 가속만 한다면 총 이동 거리는 10,011로 도달 못함.
 * 140 도 안됨.
 * 줄일 수 있을 것 같은데 일단 140 까지만 하자.
 */
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static int N, M;
    static boolean[] isSmall;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        isSmall = new boolean[N + 1];

        for (int i = 0; i < M; i++)
            isSmall[readInt()] = true;

        visit = new boolean[N + 1][141];
        ArrayDeque<Data> queue = new ArrayDeque<>();
        if (!isSmall[2]) {
            queue.offer(new Data(2, 1, 1));
            visit[2][1] = true;
        }
        boolean flag = false;
        while (!queue.isEmpty()) {
            Data cur = queue.poll();

            int nextPos, nextVelocity;

            // 가속 점프
            nextVelocity = cur.velocity + 1;
            nextPos = cur.pos + nextVelocity;
            if (nextPos == N) {
                System.out.println(cur.cnt + 1);
                flag = true;
                break;
            }
            if (nextVelocity > 0 && nextPos <= N && !isSmall[nextPos] && !visit[nextPos][nextVelocity]) {
                visit[nextPos][nextVelocity] = true;
                queue.offer(new Data(nextPos, nextVelocity, cur.cnt + 1));
            }
            // 그대로 점프
            nextVelocity = cur.velocity;
            nextPos = cur.pos + nextVelocity;
            if (nextPos == N) {
                System.out.println(cur.cnt + 1);
                flag = true;
                break;
            }
            if (nextVelocity > 0 && nextPos <= N && !isSmall[nextPos] && !visit[nextPos][nextVelocity]) {
                visit[nextPos][nextVelocity] = true;
                queue.offer(new Data(nextPos, nextVelocity, cur.cnt + 1));
            }
            // 감속 점프
            nextVelocity = cur.velocity - 1;
            nextPos = cur.pos + nextVelocity;
            if (nextPos == N) {
                System.out.println(cur.cnt + 1);
                flag = true;
                break;
            }
            if (nextVelocity > 0 && nextPos <= N && !isSmall[nextPos] && !visit[nextPos][nextVelocity]) {
                visit[nextPos][nextVelocity] = true;
                queue.offer(new Data(nextPos, nextVelocity, cur.cnt + 1));
            }
        }
        if (!flag)
            System.out.println(-1);
    }

    static class Data {
        int pos, velocity, cnt;

        public Data(int pos, int velocity, int cnt) {
            this.pos = pos;
            this.velocity = velocity;
            this.cnt = cnt;
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