// 45176KB 356ms

import java.io.*;
import java.util.*;

/**
 * BFS
 * 큐에 (i, j, 벽을 부순 횟수, 거리)를 저장하며 탐색
 */
public class Main2 {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        int n = next();
        int m = next();

        int si = next() - 1;
        int sj = next() - 1;
        int ei = next() - 1;
        int ej = next() - 1;
        boolean[][] map = new boolean[n][m];
        boolean[][][] visited = new boolean[2][n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = next() == 1;
            }
        }

        int[][] deltas = {{0,1}, {1,0}, {0,-1}, {-1,0}};
        Deque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{ si, sj, 0, 0 });
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            if (curr[0] == ei && curr[1] == ej) {
                System.out.print(curr[3]);
                return;
            }

            for (int[] delta : deltas) {
                int ni = curr[0] + delta[0];
                int nj = curr[1] + delta[1];

                if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                    continue;
                }

                if (map[ni][nj] == false && !visited[curr[2]][ni][nj]) {
                    visited[curr[2]][ni][nj] = true;
                    queue.add(new int[]{ ni, nj, curr[2], curr[3] + 1 });
                }

                if (map[ni][nj] == true && curr[2] == 0 && !visited[curr[2] + 1][ni][nj]) {
                    visited[curr[2] + 1][ni][nj] = true;
                    queue.add(new int[]{ ni, nj, curr[2] + 1, curr[3] + 1 });
                }
            }
        }
    
        System.out.print(-1);
    }
 

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
