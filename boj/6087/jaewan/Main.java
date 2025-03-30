// 13004 KB, 88 ms
/*
 * 최소 갯수의 거울을 써서 레이저를 연결
 * 거울을 사용하면 진행 방향을 90도 회전시킬 수 있다.
 * 
 * W, H <= 100
 * 같은 칸에, 방향별로 visit 배열을 만든다.
 * 같은 칸에 도달하더라도, 방향이 다르면 다른 경우임.
 * 
 * 3차원 BFS. DFS 인가?
 * 같은 방향으로 계속 진행, 반시계방향 회전, 시계방향 회전 3가지 경우.
 * 반시계 회전은 -1,시계 회전은 +1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    static int startX = -1, startY, endX, endY;
    static boolean[][] wall;
    static int[][][] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int W = Integer.parseInt(input[0]), H = Integer.parseInt(input[1]);
        // 0은 방문안한 상태, 방문하면 사용한 거울 개수 +1 값 저장
        visit = new int[4][H][W];
        wall = new boolean[H][W];
        for (int i = 0; i < H; i++) {
            String inStr = br.readLine();
            for (int j = 0; j < W; j++) {
                switch (inStr.charAt(j)) {
                    case '*':
                        wall[i][j] = true;
                        break;
                    case 'C':
                        if (startX == -1) {
                            startY = i;
                            startX = j;
                        } else {
                            endY = i;
                            endX = j;
                        }
                        break;
                }
            }
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>();
        // 상: 0, 우: 1, 하: 2, 좌: 3
        int[] dy = { -1, 0, 1, 0 }, dx = { 0, 1, 0, -1 };
        for (int i = 0; i < 4; i++) {
            queue.offer(new int[] { i, startY, startX });
            visit[i][startY][startX] = 1;
        }
        while (!queue.isEmpty()) {
            // 방향, y, x 좌표
            int[] cur = queue.poll();

            // 그대로
            int ndir = cur[0], ny = cur[1] + dy[ndir], nx = cur[2] + dx[ndir];
            if ((ny >= 0 && nx >= 0 && ny < H && nx < W && !wall[ny][nx])
                    && (visit[ndir][ny][nx] == 0 || visit[ndir][ny][nx] > visit[cur[0]][cur[1]][cur[2]])) {
                visit[ndir][ny][nx] = visit[cur[0]][cur[1]][cur[2]];
                queue.offer(new int[] { ndir, ny, nx });
            }

            // 반시계방향 회전
            ndir = (cur[0] + 3) % 4;
            ny = cur[1] + dy[ndir];
            nx = cur[2] + dx[ndir];
            if ((ny >= 0 && nx >= 0 && ny < H && nx < W && !wall[ny][nx])
                    && (visit[ndir][ny][nx] == 0 || visit[ndir][ny][nx] > visit[cur[0]][cur[1]][cur[2]] + 1)) {
                visit[ndir][ny][nx] = visit[cur[0]][cur[1]][cur[2]] + 1;
                queue.offer(new int[] { ndir, ny, nx });
            }

            // 시계 회전
            ndir = (cur[0] + 1) % 4;
            ny = cur[1] + dy[ndir];
            nx = cur[2] + dx[ndir];
            if ((ny >= 0 && nx >= 0 && ny < H && nx < W && !wall[ny][nx])
                    && (visit[ndir][ny][nx] == 0 || visit[ndir][ny][nx] > visit[cur[0]][cur[1]][cur[2]] + 1)) {
                visit[ndir][ny][nx] = visit[cur[0]][cur[1]][cur[2]] + 1;
                queue.offer(new int[] { ndir, ny, nx });
            }
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++)
            if (visit[i][endY][endX] != 0)
                res = Math.min(res, visit[i][endY][endX]);
        System.out.println(res - 1);
    }
}