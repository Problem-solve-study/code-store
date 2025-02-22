import java.io.*;
import java.util.*;

/*
31296KB, 260ms

기초 그래프 탐색 문제
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int n, m;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int[][] map;
    static boolean[][] v;

    public static void main(String[] args) throws Exception {
        n = nextInt();
        m = nextInt();
        map = new int[n][m];
        v = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = nextInt();
            }
        }

        int cnt = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!v[i][j] && map[i][j] == 1) {
                    cnt++;
                    max = Math.max(max, bfs(i, j));
                }
            }
        }

        bw.write(String.format("%d%n%d", cnt, max));
        bw.flush();
    }

    static int bfs(int r, int c) {
        int size = 0;

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        v[r][c] = true;

        while (!q.isEmpty()) {
            int[] pos = q.remove();
            size++;

            for (int i = 0; i < dr.length; i++) {
                int nr = pos[0] + dr[i];
                int nc = pos[1] + dc[i];

                if (boundaryCheck(nr, nc) && map[nr][nc] == 1 && !v[nr][nc]) {
                    q.add(new int[] {nr, nc});
                    v[nr][nc] = true;
                }
            }
        }

        return size;
    }

    static boolean boundaryCheck(int r, int c) {
        return (0 <= r && r < n) && (0 <= c && c < m);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
