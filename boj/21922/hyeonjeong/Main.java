// 481388KB 1408ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 완전탐색
 * 180도 다른 방향끼리는 방문체크 공유했다가 1% 틀이길래 방향마다 방문 체크 했더니 통과되네요
 */
public class Main {
    static final int[][] deltas = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};   // 시계방향
    static final int UP = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static final int LEFT =3;

    static final int VER = 1;
    static final int HOR = 2;
    static final int RIGHT_DIAG = 3;     // /
    static final int LEFT_DIAG = 4;      // \

    static int n;
    static int m;
    static int[][] map;
    static boolean[][][] visited;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        List<int[]> airConditioners = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] == 9) {
                    airConditioners.add(new int[]{i, j});
                }
            }
        }

        visited = new boolean[n][m][4];
        for (int[] airConditioner : airConditioners) {
            for (int dir = 0; dir < deltas.length; dir++) {
                dfs(airConditioner[0], airConditioner[1], dir);
            }
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int k = 0; k < 4; k++) {
                    if (visited[i][j][k]) {
                        sum++;
                        break;
                    }
                }
            }
        }
        System.out.println(sum);
    }

    static void dfs(int i, int j, int dir) {
        visited[i][j][dir] = true;

        // display(String.format("visit: %d, %d", i, j));

        int nextDir = getNextDir(i, j, dir);

        int ni = i + deltas[nextDir][0];
        int nj = j + deltas[nextDir][1];

        if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
            return;
        }

        if (visited[ni][nj][nextDir]) {
            return;
        }

        dfs(ni, nj, nextDir);
    }

    static int getNextDir(int i, int j, int dir) {
        // - 모양
        if (map[i][j] == HOR && (dir == UP || dir == DOWN)) {
            return (dir + 2) % 4;
        }

        // | 모양
        if (map[i][j] == VER && (dir == RIGHT || dir == LEFT)) {
            return (dir + 2) % 4;
        }

        // / 모양 물건: 세로는 시계방향, 가로는 반시계방향 회전
        if (map[i][j] == RIGHT_DIAG) {
            if (dir == UP || dir == DOWN) {
                return dir + 1;
            }

            return dir - 1;
        }

        // \ 모양 물건: 세로는 반시계방향, 가로는 시계방향 회전
        if (map[i][j] == LEFT_DIAG) {
            if (dir == UP || dir == DOWN) {
                return (dir + 3) % 4;
            }
            
            return (dir + 1) % 4;
        }

        return dir;
    }

    static void display(String message) {
        System.out.println(message);
        display();
    }

    static void display() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(visited[i][j][0] || visited[i][j][1] ? "O " : "X ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
