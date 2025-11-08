
/*
 * Dijkstra 문제
 * 
 * 그래프를 인접 리스트와 행렬로 구현하는 두 가지 방법이 가능한데
 * 도로를 추가, 삭제하는 쿼리의 편의를 위해선 인접 행렬이 O(1) 로 간단하다.
 * 
 * 다익스트라 알고리즘에서 연결된 노드 탐색은 인접 리스트로 구현하는 것이 희소 행렬일수록 더 유리하다.
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {
    static int N, M;
    static int[] dist;
    static Node[] graph;

    static final int MAX = 999;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        graph = new Node[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new Node();

        for (int i = 0; i < M; i++) {
            int u = readInt(), v = readInt();
            graph[u].link.add(v);
            graph[v].link.add(u);
        }

        int Q = readInt();
        StringBuilder sb = new StringBuilder();
        while (Q-- > 0) {
            int a = readInt(), i = readInt(), j = readInt();
            switch (a) {
                case 1:
                    graph[i].link.add(j);
                    graph[j].link.add(i);
                    break;
                case 2:
                    graph[i].link.remove(j);
                    graph[j].link.remove(i);
            }
            dist = new int[N + 1];
            dijkstra();

            for (int j2 = 1; j2 <= N; j2++)
                sb.append(dist[j2] != MAX ? dist[j2] : -1).append(' ');
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void dijkstra() {
        Arrays.fill(dist, MAX);
        dist[1] = 0;
        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.offer(new Data(1, 0));
        int cnt = 1;

        while (!pq.isEmpty() && cnt < N) {
            Data cur = pq.poll();

            for (Integer nextIdx : graph[cur.idx].link) {
                int nextDist = cur.dist + 1;
                if (dist[nextIdx] <= nextDist)
                    continue;
                dist[nextIdx] = nextDist;
                pq.offer(new Data(nextIdx, nextDist));
                cnt++;
            }
        }
    }

    static class Data implements Comparable<Data> {
        int idx, dist;

        public Data(int idx, int dist) {
            this.idx = idx;
            this.dist = dist;
        }

        @Override
        public int compareTo(Main.Data o) {
            return dist - o.dist;
        }
    }

    static class Node {
        HashSet<Integer> link = new HashSet<>();
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;

        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }
}