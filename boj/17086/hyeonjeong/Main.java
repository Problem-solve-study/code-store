// 12020KB 76ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// 완전탐색 + BFS
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[][] delats = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, -1}, {-1, 1}};
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        boolean[][] visited = new boolean[n][m];

        Queue<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                visited[i][j] = Integer.parseInt(st.nextToken()) == 1;
                if (visited[i][j]) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int dist = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] curr = queue.poll();

                for (int[] delta : delats) {
                    int ni = curr[0] + delta[0];
                    int nj = curr[1] + delta[1];
                    
                    if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                        continue;
                    }

                    if (visited[ni][nj]) {
                        continue;
                    }

                    visited[ni][nj] = true;
                    queue.add(new int[]{ni, nj});
                }
            }

            dist++;
        }

        System.out.println(dist - 1);
    }
}
