
/*
 * 경비행기 중간 급유 k이하일 때, 연료통의 최소용량을 구하기.
 * 
 * 매개변수 탐색으로, 거리가 d이하인 경로만 k개 이하 경유지 들러서 탐색.
 * 처음에, 상호 거리를 먼저 다 구해 놓고 시작.
 * 
 * BFS 탐색 해서 출발지에서 도착지까지 도달이 가능한지 탐색.
 */
import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static int N, K;
    static int[][] 정거장;

    public static void main(String[] args) throws IOException {
        N = readInt();
        K = readInt();
        정거장 = new int[2][N + 2];
        정거장[0][0] = 0;
        정거장[1][0] = 0;
        정거장[0][N + 1] = 10000;
        정거장[1][N + 1] = 10000;
        for (int i = 1; i <= N; i++) {
            정거장[0][i] = readInt();
            정거장[1][i] = readInt();
        }

        System.out.println(parametricSearch());
    }

    static int parametricSearch() {
        int left = 0, right = 1415;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid))
                right = mid - 1;
            else
                left = mid + 1;
        }
        return left;
    }

    // 거리 계산 함수
    static int getDist(int a, int b) {
        double dx = 정거장[0][a] - 정거장[0][b];
        double dy = 정거장[1][a] - 정거장[1][b];
        return (int) Math.ceil(Math.sqrt(dx * dx + dy * dy) / 10);
    }

    // 판정함수, 거리 d 이하, K번 이하 들러서 BFS가 가능한지 여부 반환
    static boolean check(int d) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[] visit = new boolean[N + 2];
        q.offer(new int[] { 0, 0 });
        visit[0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();

            for (int i = 1; i < N + 2; i++) {
                if (visit[i] || getDist(cur[0], i) > d)
                    continue;
                if (i == N + 1)
                    return true;
                if (cur[1] < K) {
                    q.offer(new int[] { i, cur[1] + 1 });
                    visit[i] = true;
                }
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
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}