import java.io.*;
import java.util.*;

public class Main {
    static int n, m, k;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

        int uk = 0, vk = 0;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if (i == k) {
                uk = u;
                vk = v; // 가중치 1 간선
            } else {
                graph[u].add(v);
                graph[v].add(u);
            }
        }

        visited = new boolean[n + 1];
        bfs(1); // 0-가중치 간선만 탐색

        boolean connected = true;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                connected = false;
                break;
            }
        }

        // 0-가중치만으로 연결되면 모든 최단 거리 0
        if (connected) {
            System.out.println(0);
        } else {
            visited = new boolean[n + 1];
            long sizeU = dfs(uk, vk);
            long sizeV = n - sizeU;
            System.out.println(sizeU * sizeV);
        }
    }

    static void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : graph[u]) {
                if (!visited[v]) {
                    visited[v] = true;
                    q.add(v);
                }
            }
        }
    }

    static long dfs(int node, int blocked) {
        visited[node] = true;
        long cnt = 1;
        for (int next : graph[node]) {
            if (!visited[next] && next != blocked) {
                cnt += dfs(next, blocked);
            }
        }
        return cnt;
    }
}
