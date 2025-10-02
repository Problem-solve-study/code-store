// 34056KB 520ms

import java.io.*;
import java.util.*;

// 다익스트라
// 최단 경로 대신 경로의 최대값의 최소값을 저장
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int N = nextInt();
        int M = nextInt();
        int T = nextInt();

        int[][] map = new int[N][N];
        int[][] dist = new int[N][N];   // dist[i][j]: i -> j로 가는 경로들의 최대 높이의 최소값

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        PriorityQueue<int[]> heap = new PriorityQueue<>((int[] n1, int[] n2) -> n1[2] - n2[2]);
        for (int edge = 0; edge < M; edge++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            int height = nextInt();

            map[u][v] = height;
            dist[u][v] = height;
            heap.add(new int[]{u, v, height});
        }

        while (!heap.isEmpty()) {
            int[] edge = heap.poll();

            int u = edge[0];
            int v = edge[1];
            int maxHeight = edge[2];
            if (dist[u][v] < maxHeight) {
                continue;
            }

            // v의 이웃 업데이트
            for (int neighbor = 0; neighbor < N; neighbor++) {
                if (map[v][neighbor] == 0) {
                    continue;
                }

                // u -> v -> neighbor의 최대 높이의 최소값
                int newMaxHeight = Math.max(maxHeight, map[v][neighbor]);

                if (dist[u][neighbor] <= newMaxHeight) {
                    continue;
                }

                dist[u][neighbor] = newMaxHeight;
                heap.add(new int[]{u, neighbor, dist[u][neighbor]});
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            int u = nextInt() - 1;
            int v = nextInt() - 1;
            
            sb.append(dist[u][v] == Integer.MAX_VALUE ? -1 : dist[u][v]).append('\n');
        }

        System.out.println(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
