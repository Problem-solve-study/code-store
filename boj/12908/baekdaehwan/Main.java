/**
 * 	11592KB	64ms
 * 최단경로 문제입니다. 출발지, 도착지, 텔레포트 양 끝 노드로 두고 최단경로 알고리즘을 수행하면 됩니다.
 * 총 노드 개수가 8개로 고정되어 있으므로 플로이드 워셜을 사용해도 상관없겠네요.
 */

import java.util.*;

public class Main {
    static long[] dijk;
    static List<List<Edge>> adj;
    static int[][] A;

    public static void main(String[] args) throws Exception {
        adj = new ArrayList<>();
        for (int i=0; i<8; ++i) adj.add(new ArrayList<>());
        A = new int[10][2];
        A[0][0] = ni();
        A[0][1] = ni();
        A[1][0] = ni();
        A[1][1] = ni();
        int ai = 2;
        for (int i=0; i<3; ++i) {
            A[ai][0] = ni();
            A[ai][1] = ni();
            ++ai;
            A[ai][0] = ni();
            A[ai][1] = ni();
            adj.get(ai-1).add(new Edge(ai, 10));
            adj.get(ai).add(new Edge(ai-1, 10));
            ++ai;
        }
        for (int i=0; i<8; ++i) {
            for (int j=0; j<8; ++j) {
                if (i==j) continue;
                adj.get(i).add(new Edge(j, dist(i,j)));
            }
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dijk = new long[8];
        Arrays.fill(dijk, Long.MAX_VALUE);
        dijk[0] = 0;
        pq.add(new Edge(0, 0));
        while (!pq.isEmpty()) {
            Edge c = pq.poll();
            if (dijk[c.i] > c.w) continue;
            for (Edge n : adj.get(c.i)) {
                if (dijk[n.i] <= c.w+n.w) continue;
                dijk[n.i] = c.w+n.w;
                pq.add(new Edge(n.i, dijk[n.i]));
            }
        }
        System.out.println(dijk[1]);
    }

    static int dist(int i, int j) {
        return Math.abs(A[i][0]-A[j][0]) + Math.abs(A[i][1]-A[j][1]);
    }

    static class Edge implements Comparable<Edge>{
        int i;
        long w;
        Edge(int i, long w) {
            this.i = i;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o) {
            return Long.compare(w, o.w);
        }
    }

    static int ni() throws Exception {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
