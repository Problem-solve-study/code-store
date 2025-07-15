/**
 * 26824KB	356ms
 * 조건 잘못적어서 3틀.. 자신에게 화나네요.
 * 간단한 분리집합 문제입니다. 각 집합 간에 연결여부 결정에만 신경쓰면 됩니다.
 * 저는 각 집합에서 가장 작은 탈출구 비용을 항상 루트에 적용하는 방식으로 구현하였습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] cost, parent;
    static Edge[] edge;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        cost = new int[N];
        parent = new int[N];
        edge = new Edge[M];
        for (int i=0; i<N; ++i) parent[i] = i;
        for (int i=0; i<M; ++i) edge[i] = new Edge(ni()-1, ni()-1, ni());
        for (int i=0; i<N; ++i) cost[i] = ni();
        Arrays.sort(edge);

        int res = 0;
        for (Edge e: edge) {
            int a = findSet(e.a);
            int b = findSet(e.b);
            if (a != b) {
                int minCost = Math.min(cost[a], cost[b]);
                if (cost[a]+cost[b] > e.w+minCost) {
                    res += e.w;
                    cost[b] = minCost;
                    parent[a] = b;
                }
            }
        }
        boolean[] check = new boolean[N];
        for (int i=0; i<N; ++i) {
            int c = findSet(i);
            if (!check[c]) {
                check[c] = true;
                res += cost[c];
            }
        }
        System.out.println(res);
    }

    static int findSet(int c) {
        if (parent[c] != c) parent[c] = findSet(parent[c]);
        return parent[c];
    }

    static class Edge implements Comparable<Edge> {
        int a, b, w;
        Edge(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
        @Override
        public int compareTo(Edge o) {
            return w-o.w;
        }
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
