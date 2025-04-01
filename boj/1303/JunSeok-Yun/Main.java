
//12944KB 76ms
import java.io.*;
import java.util.*;

class Soldier {
    int x;
    int y;

    Soldier(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class Main {
    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static Queue<Soldier> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[M][N];
        visited = new boolean[M][N];
        int wCnt = 0;
        int bCnt = 0;

        for (int i = 0; i < M; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j);
            }
        }
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'W' && !visited[i][j]) {
                    wCnt += bfs(i, j, 'W');
                } else if (map[i][j] == 'B' && !visited[i][j]) {
                    bCnt += bfs(i, j, 'B');
                }
            }
        }
        System.out.println(wCnt + " " + bCnt);
    }

    public static boolean isValidation(int nx, int ny) {
        return (nx >= 0 && ny >= 0 && nx < M && ny < N);
    }

    public static int bfs(int x, int y, char find) {
        int cnt = 1;
        q.offer(new Soldier(x, y));
        visited[x][y] = true;
        while (!q.isEmpty()) {
            Soldier soldier = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = soldier.x + dx[i];
                int ny = soldier.y + dy[i];
                if (isValidation(nx, ny) && !visited[nx][ny] && map[nx][ny] == find) {
                    q.offer(new Soldier(nx, ny));
                    visited[nx][ny] = true;
                    cnt++;
                }
            }
        }
        return cnt * cnt;
    }
}