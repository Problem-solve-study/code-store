//문제: 11952번 좀비
//메모리: 47664 KB
//시간: 408 ms

/*
 * 안전도시, 위험도시, 좀비도시가 있다.
 * 경로에는 좀비도시가 포함되면 안된다.
 * 안전도시와 위험도시는 숙박비가 다르다.
 * 위 조건을 위해 도시에 type을 부여한다.
 * 위험도시는 좀비도시로 부터 bfs로 type을 부여한다.
 * 그 후 도착도시의 숙박비를 cost로 다익스트라를 돌린다.
 * 도착도시는 숙박비가 들지 않으므로 다익스트라로 구한 비용에 도착도시의 숙박비를 빼고 출력한다.
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main {
    static class Node implements Comparable<Node> {
        int to;
        long cost;

        public Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return Long.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        int N = nextInt(), M = nextInt(), K = nextInt(), S = nextInt();
        long[] cost = new long[2];
        int[] type = new int[N + 1];
        cost[0] = nextInt();
        cost[1] = nextInt();

        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            int k = nextInt();
            type[k] = 2;
            q.add(new int[]{k, 0});
        }

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());
        for (int i = 0; i < M; i++) {
            int from = nextInt(), to = nextInt();
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        while (!q.isEmpty()) {
            int[] now = q.poll();
            if (now[1] == S) continue;
            for (int next : graph.get(now[0])) {
                if (type[next] != 0) continue;
                type[next] = 1;
                q.add(new int[]{next, now[1] + 1});
            }
        }

        boolean[] visited = new boolean[N + 1];
        long[] dist = new long[N + 1];
        for(int i=0;i<=N;i++) dist[i] = Long.MAX_VALUE;
        dist[1] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (visited[now.to]) continue;
            visited[now.to] = true;

            for (int next : graph.get(now.to)) {
                if (type[next] == 2) continue;
                long dis = dist[now.to] + cost[type[next]];
                if (dist[next] > dis) {
                    dist[next] = dis;
                    pq.add(new Node(next, dis));
                }
            }
        }

        System.out.println(dist[N] - cost[type[N]]);
    }

    static int nextInt() throws Exception {
        int n, c;
        while ((c = System.in.read()) < 48) ;
        n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
