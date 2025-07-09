import java.io.*;
import java.util.*;

/*
17976KB, 184ms

유닛의 크기가 1x1이 아닐 수 있어서 유닛의 기준 좌표를
사각형의 좌상단에 위치한다고 생각하고
장애물 체크하며 BFS 돌리기
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};
    static int N, M, A, B, K;
    //true면 장애물
    static boolean[][] map, v;

    public static void main(String[] args) throws Exception {
        N = nextInt();
        M = nextInt();
        A = nextInt();
        B = nextInt();
        K = nextInt();

        map = new boolean[N + 1][M + 1];
        v = new boolean[N + 1][M + 1];
        for (int i = 0; i < K; i++) {
            map[nextInt()][nextInt()] = true;
        }

        int sr = nextInt();
        int sc = nextInt();
        int er = nextInt();
        int ec = nextInt();

        int ans = -1;
        v[sr][sc] = true;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[] {sr, sc, 0});
        while (!q.isEmpty()) {
            int[] cur = q.removeFirst();
            int r = cur[0];
            int c = cur[1];
            int cnt = cur[2];
            if (r == er && c == ec) {
                ans = cnt;
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (!bc(nr, nc)) continue;
                if (v[nr][nc] || obstacleCheck(nr, nc)) continue;
                v[nr][nc] = true;
                q.add(new int[] {nr, nc, cnt + 1});
            }
        }
        System.out.print(ans);
    }

    static boolean obstacleCheck(int r, int c) {
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                if (map[r + i][c + j]) return true;
            }
        }

        return false;
    }

    static boolean bc(int r, int c) {
        return (0 < r && r <= N - A + 1) && (0 < c && c <= M - B + 1);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}