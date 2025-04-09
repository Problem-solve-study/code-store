
//11760KB 80ms
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static boolean[][] visited;
    static int[][] distance;
    static List<List<Integer>> adjList;
    static Queue<Integer> q;
    static int minVal = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList<>();
        visited = new boolean[N + 1][N + 1];
        distance = new int[N + 1][N + 1];

        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            adjList.get(start).add(end);
            adjList.get(end).add(start);
        }

        for (int i = 1; i <= N; i++) {
            bfs(i);
        }

        for (int i = 1; i <= N; i++) {
            if (minVal == distance[i][0]) {
                System.out.println(i);
                return;
            }
        }
    }

    public static void bfs(int root) {
        q = new ArrayDeque<>();
        q.add(root);
        visited[root][root] = true;
        distance[root][root] = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            int size = adjList.get(cur).size();

            for (int i = 0; i < size; i++) {
                if (!visited[root][adjList.get(cur).get(i)]) {
                    q.offer(adjList.get(cur).get(i));
                    visited[root][adjList.get(cur).get(i)] = true;
                    distance[root][adjList.get(cur).get(i)] = distance[root][cur] + 1;
                }
            }
        }
        int cnt = 0;
        for (int i = 1; i <= N; i++) {
            if (i == root)
                continue;
            cnt += distance[root][i];
        }
        minVal = Math.min(minVal, cnt);
        distance[root][0] = cnt;
    }
}