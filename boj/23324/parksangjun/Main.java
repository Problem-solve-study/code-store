package p_23324;

import java.io.*;
import java.util.*;

public class Main {

    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int V, E, K, t1, t2;
    static List<Edge>[] adj;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        adj = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = (i == K) ? 1 : 0;

            if (i == K) {
                t1 = v1;
                t2 = v2;
            }

            adj[v1].add(new Edge(v2, w));
            adj[v2].add(new Edge(v1, w));
        }
    }

    static void solve() throws IOException {

        long[] dist = dijkstra(t1);

        long zeroCount = 0;
        long oneCount = 0;
        for (int i = 1; i <= V; i++) {
            if (dist[i] == 0)
                ++zeroCount;
            else
                ++oneCount;
        }

        long res = zeroCount * oneCount;

        bw.write(res + "\n");
        bw.flush();
        bw.close();
    }

    static long[] dijkstra(int start) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) ->
                Long.compare(a[0], b[0])
        );

        long[] dist = new long[V + 1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[start] = 0;

        pq.offer(new long[]{0, start});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long curDist = cur[0];
            int curNode = (int) cur[1];


            if (dist[curNode] < curDist) continue;

            for (Edge edge : adj[curNode]) {
                int nextNode = edge.to;
                long nextDist = curDist + edge.weight;

                if (dist[nextNode] > nextDist) {
                    dist[nextNode] = nextDist;
                    pq.offer(new long[]{nextDist, nextNode});
                }
            }
        }

        return dist;
    }
}