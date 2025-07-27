/**
 * 	29888KB	520ms
 * 
 * 코드는 길지만 간만에 깔끔한 문제.
 * MST를 만들고, 어떤 한 정점에서 가장 먼 점 A를 찾고 A에서 가장 먼 정점을 찾으면 됨.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, C;
    static int[] parent;
    static List<Edge1> edges, confirmed;
    static List<List<Edge2>> adj;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        parent = new int[N];
        edges = new ArrayList<>();
        adj = new ArrayList<>();
        for (int i=0; i<N; ++i) parent[i] = i;
        for (int i=0; i<N; ++i) adj.add(new ArrayList<>());
        for (int i=0; i<M; ++i) edges.add(new Edge1(ni(),ni(),ni()));
        Collections.sort(edges);

        for (Edge1 e : edges) {
            if (union(e.i, e.j)) {
                C += e.c;
                adj.get(e.i).add(new Edge2(e.j, e.c));
                adj.get(e.j).add(new Edge2(e.i, e.c));
            }
        }

        System.out.println(C+"\n"+bfs(bfs(0)[0])[1]);
    }

    static int findSet(int c) {
        if (parent[c]!=c) parent[c] = findSet(parent[c]);
        return parent[c];
    }

    static boolean union(int p, int c) {
        p = findSet(p);
        c = findSet(c);
        parent[c] = p;
        return p!=c;
    }

    static int[] bfs(int s) {
        Queue<Edge2> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        int mi = s;
        int mc = 0;

        q.add(new Edge2(s, 0));
        visited[s] = true;
        while (!q.isEmpty()) {
            Edge2 c = q.poll();
            if (mc < c.c) {
                mi = c.i;
                mc = c.c;
            }
            for (Edge2 n: adj.get(c.i)) {
                if (visited[n.i]) continue;
                visited[n.i] = true;
                q.add(new Edge2(n.i, c.c+n.c));
            }
        }
        return new int[]{mi, mc};
    }

    static class Edge1 implements Comparable<Edge1> {
        int i, j, c;
        Edge1(int i, int j, int c) {
            this.i = i;
            this.j = j;
            this.c = c;
        }

        @Override
        public int compareTo(Edge1 o) {
            return this.c - o.c;
        }
    }

    static class Edge2 {
        int i, c;
        Edge2(int i, int c) {
            this.i = i;
            this.c = c;
        }
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
