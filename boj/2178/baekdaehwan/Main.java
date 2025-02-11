
// 12296KB	76ms
/**
 * BFS 기본 문제, 0/1로만 입력이 구성되므로 boolean타입으로 맵을 구성
 */

import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][] board = new boolean[N][M];
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        int[] dr = { -1, 1, 0, 0 };
        int[] dc = { 0, 0, -1, 1 };

        for (int i = 0; i < N; ++i) {
            int j = 0;
            for (char c : br.readLine().toCharArray()) {
                board[i][j++] = c == '1';
            }
        }

        int res = 0;
        queue.add(new int[] { 0, 0, 1 });
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            if (cur[0] == N - 1 && cur[1] == M - 1) {
                res = cur[2];
                break;
            }
            for (int d = 0; d < 4; ++d) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];
                if (0 <= nr && nr < N && 0 <= nc && nc < M && !visited[nr][nc] && board[nr][nc]) {
                    visited[nr][nc] = true;
                    queue.add(new int[] { nr, nc, cur[2] + 1 });
                }
            }
        }
        System.out.println(res);
    }
}