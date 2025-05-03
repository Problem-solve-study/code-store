// 80696 KB, 272 ms
/*
 * 트리 구조. bfs 돌면서 지지대 중 depth 가 작은 것 2개의 depth를 구하면 끝.
 * 답은 N - 1 - depth1 - depth2
 * 2개의 지지대와 연결되어야 하기 때문.
 * 
 * 입력이 많아보이길래 buffer 이용한 fast io로 구현해봄.
 * buffer 사용 안했을 경우 344 ms, 사용한 경우 272 ms 가 나오더라.
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static int N, S, P, res;
    static boolean[] visit;
    static Node[] tree;
    static PriorityQueue<Integer> depthPQ = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        N = readInt();
        S = readInt();
        P = readInt();

        res = N - 1;

        tree = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            tree[i] = new Node();

        int u, v;
        for (int i = 1; i < N; i++) {
            u = readInt();
            v = readInt();
            tree[u].link.add(v);
            tree[v].link.add(u);
        }

        visit = new boolean[N + 1];
        visit[P] = true;

        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { P, 0 });
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if (cur[0] <= S) {
                res -= cur[1];
                if (res != N - 1 - cur[1])
                    break;
            }

            for (int next : tree[cur[0]].link) {
                if (visit[next])
                    continue;
                visit[next] = true;
                q.offer(new int[] { next, cur[1] + 1 });
            }
        }
        System.out.println(res);
    }

    static class Node {
        ArrayList<Integer> link = new ArrayList<>();
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static int pos, len;
    static byte[] buf = new byte[4096];

    static int read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }
}