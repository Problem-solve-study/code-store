import java.io.*;
import java.util.*;

/*
76540KB, 432ms

어떻게 풀지하고 여러 고민을 많이 했는데, 결국 현재 위치와 지지하는 지지대와 연결된 경로를 2개만 유지하면 되는 문제다.
이때 최대한 많은 얼음을 깨뜨려야하는 것이므로 지지대와 연결된 가장 짧은 경로 2개만 유지하고 나머지 얼음을 모두 깨면 된다.
현재 밟은 얼음의 위치로부터 BFS를 돌리고 가장 가까운 2개의 지지대를 탐색
visited 체크를 깜빡하고 안했다가 시초로 1틀
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, S, P;
    static ArrayList<Integer>[] adj;

    public static void main(String[] args) throws Exception {
        N = nextInt(); S = nextInt(); P = nextInt();
        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int a = nextInt();
            int b = nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        //re는 깰 얼음의 수를 나타낸다. 현재 밟고 있는 얼음은 깰 수 없으므로 -1
        int res = N - 1;
        boolean[] v = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        q.add(P); q.add(0); v[P] = true;
        int cnt = 0;
        while (!q.isEmpty()) {
            int cur = q.remove();
            int len = q.remove();
            //만일 현재 보는 위치가 지지대라면 지금까지의 경로는 깰 수 없음
            if (cur <= S) {
                res -= len;
                cnt++;
                //지지대 얼음을 2개 발견했으면 나머지 얼음은 모두 깨면 된다.
                if (cnt == 2) {
                    break;
                }
            }

            for (int n : adj[cur]) {
                if (!v[n]) {
                    q.add(n); q.add(len + 1); v[n] = true;
                }
            }
        }

        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
