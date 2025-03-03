import java.io.*;
import java.util.*;

/*
49568KB, 492ms

절대 의도한건 아니고 분명 랜덤으로 뽑은 문제였는데 A형 2번 문제가 나왔다.
풀이도 동일한듯. 인접리스트 2개 만들어주고 그래프 탐색 2번 하면 끝.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, M, X;

    public static void main(String[] args) throws Exception {
        N = nextToken();
        M = nextToken();
        X = nextToken();
        ArrayList<Integer>[] upAdj = new ArrayList[N + 1];
        ArrayList<Integer>[] downAdj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            upAdj[i] = new ArrayList<>();
            downAdj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            int a = nextToken();
            int b = nextToken();
            upAdj[a].add(b);
            downAdj[b].add(a);
        }

        int parentCnt = bfs(X, upAdj);
        int childCnt = bfs(X, downAdj);
        System.out.printf("%d %d", Math.min(N - parentCnt, 1 + childCnt), Math.max(N - parentCnt, 1 + childCnt));
    }

    static int bfs(int x, ArrayList<Integer>[] adj) {
        int cnt = -1;
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] v = new boolean[N + 1];
        q.add(x);
        v[x] = true;

        while (!q.isEmpty()) {
            int cur = q.remove();
            cnt++;

            for (int n : adj[cur]) {
                if (!v[n]) {
                    q.add(n);
                    v[n] = true;
                }
            }
        }

        return cnt;
    }

    static int nextToken() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
