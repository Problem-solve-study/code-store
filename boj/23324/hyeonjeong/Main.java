// 19588KB 232ms

import java.util.*;
import java.io.*;

/**
 * m개의 간선 중 하나만 가중치가 1, 나머지는 0
 * -> 가중치 1 간선으로 연결되어야 하는 서브그래프끼리만 가중치 증가
 * -> UnionFind를 통해 모든 가중치 0 간선을 연결한 후, 가중치 1 간선으로 연결되는 서브그래프의 곱 계산
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[] ids;
    static long[] sizes;

    public static void main(String[] args) throws IOException {
        int n = next();
        int m = next();
        int k = next() - 1;
        
        ids = new int[n];
        sizes = new long[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            sizes[i] = 1L;
        }

        int ku = 0;
        int kv = 0;
        for (int i = 0; i < m; i++) {
            int u = next() - 1;
            int v = next() - 1;
            if (i != k) {
                union(u, v);
                continue;
            }
            ku = u;
            kv = v;
        }

        int uRoot = find(ku);
        int vRoot = find(kv);

        // System.out.println(ku + ", " + kv);
        // System.out.println(uRoot + ", " + vRoot);
        // System.out.println(Arrays.toString(ids));
        // System.out.println(Arrays.toString(sizes));

        if (uRoot == vRoot) {
            System.out.print(0);
            return;
        }
        System.out.println(sizes[uRoot] * sizes[vRoot]);
    }

    static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        ids[xRoot] = yRoot;
        sizes[yRoot] += sizes[xRoot];
    }

    static int find(int x) {
        if (ids[x] == x) {
            return x;
        }
        return ids[x] = find(ids[x]);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
