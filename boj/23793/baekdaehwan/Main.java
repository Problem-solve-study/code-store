/**
 * 49632KB	776ms
 * 간단한 다익스트라 문제입니다. 슬슬 다익스트라 코드를 작성해보고 싶었는데 좋네요.
 */

import java.io.*;
import java.util.*;


public class Main {
    static int N, M, X, Y, Z;
    static List<List<Edge>> adj;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        adj = new ArrayList<>();
        for (int i=0; i<N; ++i) adj.add(new ArrayList<>());
        for (int i=0; i<M; ++i) adj.get(ni()-1).add(new Edge(ni()-1, ni()));
        X = ni()-1;
        Y = ni()-1;
        Z = ni()-1;

        long[] dijkX = dijk(X);
        long[] dijkY = dijk(Y);
        long[] dijkXwithoutY = dijkWithout(X, Y);
        StringBuilder res = new StringBuilder();

        res.append(dijkX[Y]!=Long.MAX_VALUE && dijkY[Z]!=Long.MAX_VALUE? dijkX[Y]+dijkY[Z]:-1);
        res.append(' ');
        res.append(dijkXwithoutY[Z]!=Long.MAX_VALUE? dijkXwithoutY[Z]:-1);
        System.out.println(res);
    }

    static long[] dijk(int s) {
        long[] dijk = new long[N];
        Arrays.fill(dijk, Long.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(s, 0));
        dijk[s] = 0;
        while (!pq.isEmpty()) {
            Edge c = pq.poll();
            if (c.w > dijk[c.i]) continue;
            for (Edge n : adj.get(c.i)) {
                if (dijk[n.i] <= dijk[c.i]+n.w) continue;
                dijk[n.i] = dijk[c.i]+n.w;
                pq.add(new Edge(n.i, dijk[n.i]));
            }
        }
        return dijk;
    }

    static long[] dijkWithout(int s, int t) {
        long[] dijk = new long[N];
        Arrays.fill(dijk, Long.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(s, 0));
        dijk[s] = 0;
        while (!pq.isEmpty()) {
            Edge c = pq.poll();
            if (c.w > dijk[c.i]) continue;
            for (Edge n : adj.get(c.i)) {
                if (n.i == t) continue;
                if (dijk[n.i] <= dijk[c.i]+n.w) continue;
                dijk[n.i] = dijk[c.i]+n.w;
                pq.add(new Edge(n.i, dijk[n.i]));
            }
        }
        return dijk;
    }

    static class Edge implements Comparable<Edge> {
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
        st.nextToken();
        return (int) st.nval;
    }
}
