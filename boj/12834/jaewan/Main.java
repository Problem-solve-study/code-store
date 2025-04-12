package jaewan;
// 13200 KB, 112

/*
 * KIST 기사단 사람들의 집-KIST 최단거리 + 집-씨알푸드의 최단거리 의 합을 출력.
 * 도달할 수 없는 경우는 -1
 * 
 * 첫째 줄 기사단 팀원의 수 N <= 100, 장소의 수 V <= 1,000, 도로의 수 E <= 10,000
 * 
 * 둘째 줄 KIST의 위치 A, 씨알푸드의 위치 B.
 * 
 * 셋째 줄 팀원 N명의 집의 위치 H
 * 
 * 넷째 줄부터 E+3 번째 줄까지 도로의 양 끝 장소 a, b와 길이 l
 * 
 * Floyd-Warshall은 시간복잡도가 O(V^3)으로, 1,000,000,000 이므로 시간초과가 날 것.
 * 그래서 각 팀원의 집을 시작 위치로 Dijkstra를 돌려야 할 듯.
 * 다익스트라의 시간복잡도는 O(E log E) * 100
 * 
 * 아, A에서 하고, B에서 다익스트라 두 번만 하면 되는구나.
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static final int MAX = 999_999_999;

    static int N, V, E, A, B;
    static int[] homes;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        N = readInt();
        V = readInt();
        E = readInt();
        A = readInt();
        B = readInt();

        homes = new int[N];
        graph = new Node[V + 1];
        for (int i = 1; i <= V; i++)
            graph[i] = new Node();
        for (int i = 0; i < N; i++)
            homes[i] = readInt();
        for (int i = 0; i < E; i++) {
            int u = readInt(), v = readInt(), w = readInt();
            graph[u].link.add(new Data(v, w));
            graph[v].link.add(new Data(u, w));
        }

        System.out.println(Dijkstra(A) + Dijkstra(B));
    }

    // 팀원의 집 위치를 start로 받아서, 해당 팀원의 KIST 까지의 거리와 씨알푸드까지 거리 합을 리턴
    static int Dijkstra(int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, MAX);
        dist[start] = 0;
        PriorityQueue<Data> pq = new PriorityQueue<>();
        pq.offer(new Data(start, dist[start]));
        while (!pq.isEmpty()) {
            Data cur = pq.poll();
            if (cur.weight > dist[cur.v])
                continue;

            for (Data next : graph[cur.v].link) {
                int nextDist = cur.weight + next.weight;
                if (dist[next.v] <= nextDist)
                    continue;
                dist[next.v] = nextDist;
                pq.offer(new Data(next.v, nextDist));
            }
        }

        int sum = 0;
        for (int i = 0; i < N; i++)
            sum += dist[homes[i]] != MAX ? dist[homes[i]] : -1;
        return sum;
    }

    static class Node {
        ArrayList<Data> link = new ArrayList<>();
    }

    static class Data implements Comparable<Data> {
        int v, weight;

        public Data(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }

        @Override
        public int compareTo(Data d) {
            return weight - d.weight;
        }

    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}