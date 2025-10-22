// 37420 KB, 316 ms, 99631021
/* 
 * 각 입력을 간선으로 보고, MST 알고리즘을 통해 해결 가능
 * 
 * 원래 트리 형태임.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        N = readInt();
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++)
            parent[i] = i;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++)
            for (int j = i + 1; j <= N; j++)
                pq.add(new Edge(i, j, readInt()));

        int cnt = 0;

        ArrayList<ArrayList<Integer>> link = new ArrayList<>();
        for (int i = 0; i <= N; i++)
            link.add(new ArrayList<>());

        while (cnt < N - 1 && !pq.isEmpty()) {
            Edge cur = pq.poll();

            if (union(cur.u, cur.v)) {
                cnt++;
                link.get(cur.u).add(cur.v);
                link.get(cur.v).add(cur.u);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(link.get(i).size()).append(' ');
            Collections.sort(link.get(i));
            for (Integer t : link.get(i))
                sb.append(t).append(' ');
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int u, int v) {
        int rootU = find(u), rootV = find(v);

        if (rootU != rootV) {
            parent[rootU] = rootV;
            return true;
        }
        return false;
    }

    static class Edge implements Comparable<Edge> {
        int u, v, weight;

        public Edge(int u, int v, int weight) {
            this.u = u;
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return weight - o.weight;
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
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}