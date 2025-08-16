
// 19220 KB, 216 ms
/*
 * 문제 쪼개기
 * 
 * 입력은 그래프다.
 * 구해야 하는 것은, 트리를 만들고, 그 트리에서 모든 정점을 방문하고 출발 노드로 돌아올 때 최소비용이다.
 * 
 * 1. 트리 만들기
 * 2. 트리에서 최소 비용 경로 찾기
 * 3. 출발점 정하기
 * 
 * 우선 비용을 계산해 보기로 했다.
 * > 모든 노드를 한번씩 방문해야 한다.
 * > 그리고 각 노드의 자식 수만큼 추가로 방문하게 된다.
 * > 시작 노드는 +1 번 더 방문한다.
 * > 모든 간선은 왔다 갔다, 2번씩 방문한다.
 * 
 * 계산해 보니,연결된 양쪽 노드의 방문 비용 + 도로 비용 x 2 가 간선 하나를 추가할 때 비용이다.
 * 이걸로 Kruskal 알고리즘 돌리고, 시작 노드는 1번 더 방문하므로, 노드 방문 비용 중 최솟값을 더하면 끝!
 */
import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = readInt(), P = readInt(), res = Integer.MAX_VALUE;
        int[] country = new int[N + 1];
        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            country[i] = readInt();
            res = Math.min(res, country[i]);
            parent[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < P; i++) {
            int u = readInt(), v = readInt(), w = readInt();
            pq.offer(new Edge(u, v, country[u] + country[v] + (w << 1)));
        }

        int cnt = N - 1;
        while (!pq.isEmpty() && cnt > 0) {
            Edge cur = pq.poll();

            // union 됐으면 true 반환
            if (union(cur.u, cur.v)) {
                res += cur.w;
                N--;
            }
        }

        System.out.println(res);
    }

    static class Edge implements Comparable<Edge> {
        int u, v, w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Main.Edge o) {
            return w - o.w;
        }

    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static boolean union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU == rootV)
            return false;
        parent[rootU] = rootV;
        return true;
    }

    static int find(int x) {
        if (x != parent[x])
            parent[x] = find(parent[x]);
        return parent[x];
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
}