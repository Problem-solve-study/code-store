import java.io.*;
import java.util.*;

/*
12164KB, 76ms

2차원 배열이 주어지면서 최단 거리를 찾아라 -> BFS
BFS 기초 문제인고 같고 BFS를 구현해주면 된다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 1});
        visited[0][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];

            if (r == n - 1 && c == m - 1) {
                bw.write(String.valueOf(cnt));
                break;
            }

            for (int i = 0; i < dr.length; i++) {
                int nextR = r + dr[i];
                int nextC = c + dc[i];

                if (boundaryCheck(nextR, nextC, n, m) && !visited[nextR][nextC] && map[nextR][nextC] != 0) {
                    q.add(new int[] {nextR, nextC, cnt + 1});
                    visited[nextR][nextC] = true;
                }
            }
        }

        bw.flush();
    }

    static boolean boundaryCheck(int r, int c, int n, int m) {
        return (0 <= r && r < n) && (0 <= c && c < m);
    }
}