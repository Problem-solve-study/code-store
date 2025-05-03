/**
 * 	77316KB	396ms
 * 
 * [사고흐름]
 * 일반적으로 알고있는 얼음깨기 팽귄때문에 인지부조화가 좀 왔습니다. 
 * 문제의 정의에 비해서 핵심은 간결합니다. 팽귄이 서 있는 얼음으로부터 트리의 형태로 얼음이 연결되어 있습니다.
 * 연결된 얼음 중 지지대의 역할을 하는 얼음까지 연결된 얼음까지의 경로 중, 가장 짧은 2개의 경로를 찾으면 됩니다.
 * 
 * 즉, 단순 BFS문제입니다. 문제에서 핵심을 파악하는 속도를 기르기 좋은 문제같습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, S, P;
    static List<List<Integer>> adj;
    static Queue<Pos> q;
    static boolean[] visited;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        S = ni();
        P = ni()-1;
        adj = new ArrayList<>();
        q = new ArrayDeque<>();
        visited = new boolean[N];
        for (int i=0; i<N; ++i) adj.add(new ArrayList<>());
        for (int i=1; i<N; ++i) {
            int a = ni()-1;
            int b = ni()-1;
            adj.get(a).add(b);
            adj.get(b).add(a);
        }

        int res = N-1;
        int cnt = 0;
        visited[P] = true;
        q.add(new Pos(P, 0));

        while (!q.isEmpty()) {
            Pos p = q.poll();
            if (isSupport(p.i)) {
                res -= p.len;
                if (++cnt == 2) break;
            }

            for (int n: adj.get(p.i)) {
                if (!visited[n]) {
                    visited[n] = true;
                    q.add(new Pos(n, p.len+1));
                }
            }
        }

        System.out.println(res);
    }

    static class Pos{
        int i, len;
        public Pos(int i, int len) {
            this.i = i;
            this.len = len;
        }
    }

    static boolean isSupport(int i) {
        return i<S;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}