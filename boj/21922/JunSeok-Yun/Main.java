
//253608KB 716ms
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = { -1, 0, 1, 0 };
    static int[] dy = { 0, 1, 0, -1 };
    static int[][] convert = { { 0, 0, 2, 1, 3 }, { 0, 3, 1, 0, 2 }, { 0, 2, 0, 3, 1 }, { 0, 1, 3, 2, 0 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int cnt = 0;
        int totalCnt = 0;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    cnt++;
                }
            }
        }
        if (cnt == 0) {
            System.out.println(0);
            return;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 9) {
                    visited[i][j] = true;
                    solve(i, j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    totalCnt++;
                }
            }
        }
        System.out.println(totalCnt);
    }

    public static boolean isValidation(int nx, int ny) {
        return (nx >= 0 && ny >= 0 && nx < N && ny < M);
    }

    public static void solve(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            int dir = i;
            while (true) {
                nx += dx[dir];
                ny += dy[dir];
                if (!isValidation(nx, ny) || map[nx][ny] == 9) {
                    break;
                }

                visited[nx][ny] = true;
                if (map[nx][ny] != 0) {
                    int shape = map[nx][ny];
                    dir = convert[dir][shape];
                }
            }
        }
    }
}