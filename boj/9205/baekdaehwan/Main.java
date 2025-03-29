/**
 * 15576KB	120ms
 * 
 * t=50, n=100밖에 되지 않기 때문에, 가능한 모든 간선을 다 탐색하더라도 50만입니다.
 * 그래서 분리집합으로 풀었습니다.
 * 깔끔하게 풀리는 문제네요.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int[][] cord;
    static int[] parent;

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        StringBuilder res = new StringBuilder();

        int TC = ni();
        for (int tc=0; tc<TC; ++tc) {
            int N = ni()+2;
            parent = new int[N];
            cord = new int[N][2];

            for (int i=0; i<N; ++i) parent[i] = i;
            for (int i=0; i<N; ++i) {
                cord[i][0] = ni();
                cord[i][1] = ni();
            }

            for (int i=0; i<N; ++i) {
                for (int j=i+1; j<N; ++j) {
                    if (Math.abs(cord[i][0]-cord[j][0])+Math.abs(cord[i][1]-cord[j][1]) <= 1000) union(i, j);
                }
            }
            res.append(findSet(0)==findSet(N-1)? "happy\n":"sad\n");
        }
        System.out.print(res);
    }

    static void union(int p, int c) {
        parent[findSet(c)] = findSet(p);
    }

    static int findSet(int c) {
        if (parent[c] != c) parent[c] = findSet(parent[c]);
        return parent[c];
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
