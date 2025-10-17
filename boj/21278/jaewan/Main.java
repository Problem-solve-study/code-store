/*
 * 호석이 두마리 치킨
 * 
 * 도시 안에 2개의 매장, N개의 건물과 M개의 도로
 * 도로 양방향. 접근성의 합이 최소화 되는 건물 2개를 골라 치킨집 열기.
 * 
 * 건물 X의 접근성은 X 에서 가장 가까운 호석이 두마리 치킨집까지 왕복하는 최단 시간
 * 
 * N <= 100
 * 
 * 플로이드 워셜 돌려서 각 정점간의 거리를 구한다.
 * 그리고 최소값과 그때의 두 정점을 구한다.
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int N, M;
    static int[][] dist;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();

        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(dist[i], 999_999);
            dist[i][i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int u = readInt(), v = readInt();
            dist[u][v] = dist[v][u] = 2;
        }

        // Floyd-Warshall
        for (int k = 1; k <= N; k++)
            for (int i = 1; i <= N; i++)
                for (int j = 1; j <= N; j++)
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);

        int A = 0, B = 0, min = Integer.MAX_VALUE;
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int t = 0;
                for (int k = 1; k <= N; k++)
                    t += Math.min(dist[k][i], dist[k][j]);
                if (t < min) {
                    min = t;
                    A = i;
                    B = j;
                }
            }
        }
        System.out.printf("%d %d %d", A, B, min);
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