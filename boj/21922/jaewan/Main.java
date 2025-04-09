/*
 * 상, 하, 좌, 우
 * 
 * 에어컨에서 바람이 나가고,
 * 물건이 바람의 방향을 바꾼다.
 * 1번 물건은 좌우 에서 바람이 오면 막음
 * 2번 물건은 상하 방향 막음
 * 3번 물건은 상<->우, 하<->좌
 * 4번 물건은 상<->좌, 하<->우
 * 
 * dfs 돌면서 count.
 */

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static int N, M, cnt;
    static int[] dy = { -1, 1, 0, 0 }, dx = { 0, 0, -1, 1 };
    static int[][] map;
    static boolean[][][] visit;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        map = new int[N][M];
        visit = new boolean[N][M][4];

        ArrayList<AirConditioner> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = readInt();
                if (map[i][j] == 9) {
                    AirConditioner a = new AirConditioner(i, j);
                    list.add(a);
                }
            }
        }

        for (AirConditioner a : list) {
            for (int i = 0; i < 4; i++) {
                visit[a.y][a.x][i] = true;
                DFS(a.y, a.x, i);
            }
        }

        boolean flag = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < 4; k++) {
                    if (visit[i][j][k]) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    cnt++;
                    flag = false;
                }

            }
        }
        System.out.println(cnt);
    }

    static void DFS(int y, int x, int dir) {

        int ny = y + dy[dir], nx = x + dx[dir], nDir = dir;
        if (ny < 0 || nx < 0 || ny >= N || nx >= M || visit[ny][nx][nDir])
            return;
        visit[ny][nx][nDir] = true;
        // 좌우 방향 막음
        if (map[ny][nx] == 1 && (dir == 2 || dir == 3))
            return;
        // 상하 방향 막음
        if (map[ny][nx] == 2 && (dir == 0 || dir == 1))
            return;
        if (map[ny][nx] == 3)
            nDir = 3 - dir;
        if (map[ny][nx] == 4) {
            if (dir == 0 || dir == 2)
                nDir = 2 - dir;
            else if (dir == 1 || dir == 3)
                nDir = 4 - dir;
        }
        DFS(ny, nx, nDir);
    }

    static class AirConditioner {
        int y, x;

        public AirConditioner(int y, int x) {
            this.y = y;
            this.x = x;
        }

    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}