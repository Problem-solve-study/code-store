// 13504KB, 80 ms
/*
 * N명이 뭉치면 N^2의 위력, B, W 위력의 합을 구해라.
 * BFS 사용
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    static int N, M, resW, resB;
    static int[][] map;
    static boolean[][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        M = Integer.parseInt(input[0]);
        N = Integer.parseInt(input[1]);

        map = new int[N][M];
        visit = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String inStr = br.readLine();
            for (int j = 0; j < M; j++) {
                switch (inStr.charAt(j)) {
                    case 'W':
                        map[i][j] = 1;
                        break;
                    default:
                        break;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!visit[i][j]) {
                    switch (map[i][j]) {
                        case 0:
                            resB += Math.pow(bfs(i, j), 2);
                            break;

                        case 1:
                            resW += Math.pow(bfs(i, j), 2);
                            break;
                    }
                }
            }
        }
        System.out.printf("%d %d", resW, resB);
    }

    static int bfs(int y, int x) {
        int cnt = 0;
        int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.offer(new int[] { y, x });
        visit[y][x] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            cnt++;
            for (int i = 0; i < 4; i++) {
                int ny = cur[0] + dy[i];
                int nx = cur[1] + dx[i];
                if (ny < 0 || nx < 0 || ny >= N || nx >= M     // 범위 벗어남
                        || visit[ny][nx]                       // 이미 방문한 경우
                        || map[ny][nx] != map[cur[0]][cur[1]]) // 다른 팀인 경우
                    continue;

                visit[ny][nx] = true;
                q.offer(new int[] { ny, nx });
            }
        }
        return cnt;
    }
}
