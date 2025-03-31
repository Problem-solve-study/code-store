import java.io.*;
import java.util.*;

/*
12896KB, 96ms

방향까지 고려해야하는 BFS.
 */

public class Main {
    static int W, H;
    static char[][] map;
    static int[][] dp0, dp1;
    static int[] c1, c2;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        dp0 = new int[H][W]; //좌우 이동 시의 최솟값
        dp1 = new int[H][W]; //상하 이동 시의 최솟값
        for (int i = 0; i < H; i++) {
            Arrays.fill(dp0[i], 100_000_000);
            Arrays.fill(dp1[i], 100_000_000);
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'C') {
                    if (c1 == null) c1 = new int[] {i, j};
                    else c2 = new int[] {i, j};
                }
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(c1[0]); q.add(c1[1]); dp0[c1[0]][c1[1]] = dp1[c1[0]][c1[1]] = 0;
        while (!q.isEmpty()) {
            int r = q.remove(); int c = q.remove();

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (bc(nr, nc) && map[nr][nc] != '*') {
                    if (i < 2) {
                        //이 경우는 현재 r, c에서 상하로 움직이는 경우
                        //상하로 움직이는 경우이므로 상하 최솟값을 담는 DP를 갱신
                        if (dp1[nr][nc] > Math.min(dp0[r][c] + 1, dp1[r][c])) {
                            q.add(nr); q.add(nc); dp1[nr][nc] = Math.min(dp0[r][c] + 1, dp1[r][c]);
                        }
                    } else {
                        //이 경우는 현재 r, c에서 좌우로 움직이는 경우
                        //좌우로 움직이는 경우이므로 좌우 최솟값을 담는 DP를 갱신
                        if (dp0[nr][nc] > Math.min(dp0[r][c], dp1[r][c] + 1)) {
                            q.add(nr); q.add(nc); dp0[nr][nc] = Math.min(dp0[r][c], dp1[r][c] + 1);
                        }
                    }
                }
            }
        }
        System.out.print(Math.min(dp0[c2[0]][c2[1]], dp1[c2[0]][c2[1]]));
    }

    static boolean bc(int r, int c) {
        return (0 <= r && r < H) && (0 <= c && c < W);
    }
}
