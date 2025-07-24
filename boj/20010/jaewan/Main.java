
/*
 * 모든 마을과 마을을 연결하는 최소 비용, 마을과 마을을 이동하는 최악의 비용 구하기
 * kruskal 로 mst 구하고, 모든 정점에서 DFS 돌면서 최단 경로 최대 비용 구하기
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static int max;
    static int[] parent;
    static boolean[] visit;
    static Node[] mst;

    public static void main(String[] args) throws IOException {
        int N = readInt(), K = readInt();
        mst = new Node[N];
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            mst[i] = new Node();
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < K; i++)
            pq.offer(new Edge(readInt(), readInt(), readInt()));

        int res1 = 0, cnt = 0;
        while (!pq.isEmpty() && cnt < N - 1) {
            Edge e = pq.poll();
            if (find(e.u) != find(e.v)) {
                union(e.u, e.v);
                res1 += e.w;
                cnt++;
                mst[e.u].link.add(new Edge(e.u, e.v, e.w));
                mst[e.v].link.add(new Edge(e.v, e.u, e.w));
            }
        }

        for (int i = 0; i < N; i++) {
            visit = new boolean[N];
            visit[i] = true;

            DFS(i, 0);
        }

        System.out.println(res1 + '\n' + max);
    }

    static void DFS(int v, int cost) {
        max = Math.max(max, cost);
        for (Edge e : mst[v].link) {
            if (visit[e.v])
                continue;

            visit[e.v] = true;
            DFS(e.v, cost + e.w);
        }
    }

    static void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        parent[rootU] = rootV;
    }

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
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
            return w - o.w;
        }
    }

    static class Node {
        ArrayList<Edge> link = new ArrayList<>();
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