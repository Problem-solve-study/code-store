// 	85972KB	624ms
import java.io.*;
import java.util.*;

/**
 * 서로 다른 2점을 잇는 경로는 1개뿐이고, 전체 간선의 개수가 (정점개수-1) => 사이클이 없는 트리 구조임을 파악
 * 그림을 보고, 가장 가까운 2개의 지지대와의 연결만 남겨두고 나머지를 다 깨면 되겠다고 생각함.
 * 그래서 다익스트라로 P에서 모든 점까지의 최단 거리를 구하고 두 지지대까지의 거리를 더하면 되겠다.
 * (트리라서 겹치는 간선이 없기때문에 가능)
 * 근데 가중치가 없어서 그냥 BFS해도 됬겠다
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n,s,p;
    static ArrayList<Integer>[] map;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        s = nextInt();
        p = nextInt();
        map = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i=0; i<n-1; i++) {
            int a = nextInt();
            int b = nextInt();
            map[a].add(b);
            map[b].add(a);
        }
        int[] dist = dijkstra();
        int min1 = 400_000, min2 = 400_000;
        for (int i=1; i<=s; i++) {
            if (min1 >= dist[i]) {
                min2 = min1;
                min1 = dist[i];
            }
            else if (min2 > dist[i]) {
                min2 = dist[i];
            }
        }
        int answer = n - min1 - min2 - 1;
        System.out.print(answer);
    }

    static int[] dijkstra() {
        int[] dist = new int[n+1];
        Arrays.fill(dist, 400_000);
        Comparator<int[]> comparator = (n1, n2) -> Integer.compare(n1[1], n2[1]);
        PriorityQueue<int[]> queue = new PriorityQueue<>(comparator);
        queue.add(new int[]{p, 0});
        dist[p] = 0;

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int now = node[0];
            int cost = node[1];

            if (dist[now] < cost) continue;

            for (int next : map[now]) {
                if (dist[next] > dist[now] + 1) {
                    dist[next] = dist[now] + 1;
                    queue.add(new int[] {next, dist[next]});
                }
            }
        }
        return dist;
    }


    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
