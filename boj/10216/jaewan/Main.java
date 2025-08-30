
/*
 * 각 정점들 입력받으면서, 이전 정점들간의 거리를 계산해,
 * dist[i][j] <= R[i] + R[j] 면, Union-Find 통해서 그룹 개수를 구하기.
 * 
 * O(N^2)
 */
import java.io.IOException;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int T = readInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int N = readInt();
            int[][] enemies = new int[N][2];
            int[] R = new int[N];
            parent = new int[N];
            for (int i = 0; i < N; i++)
                parent[i] = i;

            for (int i = 0; i < N; i++) {
                enemies[i][0] = readInt();
                enemies[i][1] = readInt();
                R[i] = readInt();

                for (int j = 0; j < i; j++) {
                    if (dist(enemies[i], enemies[j]) <= (R[i] + R[j]) * (R[i] + R[j]))
                        union(i, j);
                }
            }

            int cnt = 0;
            for (int i = 0; i < N; i++)
                if (parent[i] == i)
                    cnt++;
            sb.append(cnt).append('\n');
        }
        System.out.print(sb);
    }

    public static int dist(int[] pos1, int[] pos2) {
        return (pos1[0] - pos2[0]) * (pos1[0] - pos2[0]) + (pos1[1] - pos2[1]) * (pos1[1] - pos2[1]);
    }

    static int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        parent[rootV] = rootU;
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