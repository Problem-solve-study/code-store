// 22544KB	288ms
import java.io.*;
import java.util.*;

/**
 * 구해야 하는게 A에서 사람들 집까지의 최단 경로 합 + B에서 사람들 집까지의 최단 경로 합
 * 그래서 A,B 를 각각 시작점으로 다익스트라 2번 수행
 */

public class Main {
    static int n,v,e;
    static int a, b;
    static int[] house;
    static int[] dist;
    static ArrayList<int[]>[] map;
    static final int MAX = 10_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        house = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            house[i] = Integer.parseInt(st.nextToken());
        }
        map = new ArrayList[v+1];
        for (int i=1; i<=v; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());
            int length = Integer.parseInt(st.nextToken());
            map[p1].add(new int[] {p2, length});
            map[p2].add(new int[] {p1, length});
        }
        int answer = 0;
        dist = new int[v+1];
        dijkstra(a);
        for (int h : house) {
            answer += (dist[h] == MAX) ? -1 : dist[h];
        }
        dijkstra(b);
        for (int h : house) {
            answer += (dist[h] == MAX) ? -1 : dist[h];
        }
        System.out.print(answer);
    }

    static void dijkstra(int start) {
        Comparator<int[]> comparator = (m1, m2) -> Integer.compare(m1[1], m2[1]);
        PriorityQueue<int[]> queue = new PriorityQueue<>(comparator);
        Arrays.fill(dist, MAX);
        dist[start] = 0;
        queue.add(new int[] {start, 0});
        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            if (dist[now[0]] < now[1]) continue;

            for (int[] next : map[now[0]]) {
                if (dist[next[0]] > dist[now[0]] + next[1]) {
                    dist[next[0]] = dist[now[0]] + next[1];
                    queue.add(new int[] {next[0], dist[next[0]]});
                }
            }
        }
    }
}