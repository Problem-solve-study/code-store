/*
 * 1번 본사 컴퓨터는 모든 컴퓨터와 연결되어 있음.
 * 1번 제외하고, MST를 구성하도록 하는 최소 비용 X와, 연결할 컴퓨터들의 쌍의 개수 K 를 구하기.
 */

import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    static int cnt;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int n = readInt(), m = readInt();
        cnt = n - 1;
        parent = new int[n + 1];

        for (int i = 1; i <= n; i++)
            parent[i] = i;

        for (int i = 0; i < m; i++) {
            int u = readInt(), v = readInt();
            if (u == 1 || v == 1)
                continue;

            union(u, v);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();

        for (int i = 1; i <= n; i++) {
            // 혹시 여기서 이미 연결된 간선들의 입력 안받으면 더 빨라질까?

            for (int j = 1; j < i; j++)
                pq.offer(new Edge(i, j, readInt()));
            // 중복, 버리기
            for (int j = i; j <= n; j++)
                readInt();
        }

        int X = 0, K = 0;
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty() && cnt > 0) {
            Edge cur = pq.poll();

            if (cur.u == 1 || cur.v == 1 || !union(cur.u, cur.v))
                continue;

            sb.append(cur.u).append(' ').append(cur.v).append('\n');
            X += cur.w;
            K++;
        }
        System.out.printf("%d %d\n", X, K);
        System.out.print(sb);
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return this.w - o.w;
        }

    }

    static int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int u, int v) {
        int rootU = find(u), rootV = find(v);

        if (rootU != rootV) {
            cnt--;
            parent[rootV] = rootU;
            return true;
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