import java.io.*;
import java.util.*;

/*
13648KB, 100ms

그래프 탐색 기초 문제 중 하나
그래프 탐색을 수행하며 서로 다른 군집의 개수 세기
 */

public class Main {
    static int m;
    static int n;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static boolean[][] map;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            map = new boolean[m][n];
            visited = new boolean[m][n];

            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());

                map[r][c] = true;
            }

            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    //군집 개수 카운팅
                    //bfs를 수행했는데 현재 위치가 방문이 되지 않았다? -> 다른 군집
                    if (!visited[i][j] && map[i][j]) {
                        bfs(i, j);
                        res++;
                    }
                }
            }

            sb.append(res).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {r, c});
        visited[r][c] = true;

        while (!q.isEmpty()) {
            int[] cur = q.remove();

            for (int i = 0; i < dr.length; i++) {
                int nextR = cur[0] + dr[i];
                int nextC = cur[1] + dc[i];

                if (boundaryCheck(nextR, nextC) && !visited[nextR][nextC] && map[nextR][nextC]) {
                    q.add(new int[] {nextR, nextC});
                    visited[nextR][nextC] = true;
                }
            }
        }
    }

    static boolean boundaryCheck(int r, int c) {
        return (0 <= r && r < m) && (0 <= c && c < n);
    }
}