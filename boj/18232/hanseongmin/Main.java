import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;

/*
62540KB, 548ms

웰노운 BFS
텔레포트 연결을 양방향으로 안했다가 1틀함
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, M, S, E;

    public static void main(String[] args) throws Exception {
        N = nextInt();
        M = nextInt();
        S = nextInt();
        E = nextInt();

        ArrayList<Integer>[] tp = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            tp[i] = new ArrayList<>();
        }
        boolean[] v = new boolean[N + 1];
        for (int i = 0; i < M; i++) {
            int a = nextInt();
            int b = nextInt();
            tp[a].add(b);
            tp[b].add(a);
        }

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.addLast(new int[] {S, 0});
        v[S] = true;
        int ans = 0;
        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int pos = cur[0];
            int time = cur[1];
            if (pos == E) {
                ans = time;
                break;
            }

            if (bc(pos + 1) && !v[pos + 1]) {
                q.addLast(new int[] {pos + 1, time + 1});
                v[pos + 1] = true;
            }

            if (bc(pos - 1) && !v[pos - 1]) {
                q.addLast(new int[] {pos - 1, time + 1});
                v[pos - 1] = true;
            }

            for (int nextPos : tp[pos]) {
                if (bc(nextPos) && !v[nextPos]) {
                    q.addLast(new int[] {nextPos, time + 1});
                    v[nextPos] = true;
                }
            }
        }
        System.out.print(ans);
    }

    static boolean bc(int pos) {
        return (1 <= pos && pos <= N);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}