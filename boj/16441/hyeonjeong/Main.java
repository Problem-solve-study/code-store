// 14620KB 116ms

import java.util.*;
import java.io.*;

/**
 * (i, j, direction)을 기준으로 중복 체크하며 BFS
 * 큐에 isIce도 넣을까 했는데 그거보다 빙판일 때 계속 직진시키는 게 더 빠를 것 같아서 이렇게 했습니다.
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        final char FIELD = '.';
        final char ICE = '+';
        final char MOUNTAIN = '#';
        final char WOLF = 'W';

        int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        char[][] map = new char[n][m];
        Deque<int[]> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[n][m][4];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == WOLF) {
                    for (int di = 0; di < 4; di++) {
                        visited[i][j][di] = true;
                        queue.add(new int[]{ i, j, di });
                    }
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            for (int di = 0; di < 4; di++) {
                int ni = curr[0] + deltas[di][0];
                int nj = curr[1] + deltas[di][1];

                if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                    continue;
                }

                if (map[ni][nj] == MOUNTAIN) {
                    continue;
                }

                if (visited[ni][nj][di]) {
                    continue;
                }

                visited[ni][nj][di] = true;
                if (map[ni][nj] == ICE) {
                    while (ni >= 0 && ni < n && nj >= 0 && nj < m && map[ni][nj] == ICE) {
                        ni += deltas[di][0];
                        nj += deltas[di][1];
                        visited[ni][nj][di] = true;
                    }

                    if (map[ni][nj] == MOUNTAIN) {
                        ni -= deltas[di][0];
                        nj -= deltas[di][1];
                    }
                }
                queue.add(new int[]{ ni, nj });
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == FIELD && !visited[i][j][0] && !visited[i][j][1] && !visited[i][j][2] && !visited[i][j][3]) {
                    sb.append('P');
                } else {
                    sb.append(map[i][j]);
                }
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }
}
