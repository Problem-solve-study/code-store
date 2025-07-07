import java.io.*;
import java.util.*;

/*
41696KB, 308ms

다익스트라스럽게 BFS를 돌리면 나오는거 아닌가? 했음
특수장비 설명을 봤을 때 방향이 있길래 방향까지 고려를 해야하나 싶었는데 방향은 고려를 할 필요가 없음
특수장비를 사용하는 경우와 사용하지 않은 경우를 모두 고려할 필요는 있겠다 싶어서 dist 배열을 3차원으로 구성
이러고 그대로 그래프 탐색하면 그냥 풀리는듯
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n, m;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][][] dist;

    public static void main(String[] args) throws Exception {
        n = nextInt();
        m = nextInt();
        int[][] map = new int[n][m];
        dist = new int[n][m][2];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = nextInt();
                dist[i][j][0] = dist[i][j][1] = Integer.MAX_VALUE;
            }
        }

        int ans = Integer.MAX_VALUE;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[] {0, 0, 1, map[0][0]});
        dist[0][0][1] = map[0][0];

        while (!q.isEmpty()) {
            int[] cur = q.remove();
            if (cur[0] == n - 1 && cur[1] == m - 1) {
                ans = Integer.min(ans, cur[3]);
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur[0] + dr[i];
                int nc = cur[1] + dc[i];
                if (!bc(nr, nc)) continue;

                int nv = Math.max(cur[3], map[nr][nc]);
                if (dist[nr][nc][cur[2]] > nv) {
                    q.add(new int[] {nr, nc, cur[2], nv});
                    dist[nr][nc][cur[2]] = nv;
                }

                //특수 장비를 사용할 수 있으면 특수 장비를 사용해봄
                if (cur[2] == 1) {
                    int nnr = cur[0] + (dr[i] * 2);
                    int nnc = cur[1] + (dc[i] * 2);
                    if (!bc(nnr, nnc)) continue;

                    int nnv = Math.max(cur[3], map[nnr][nnc]);
                    if (dist[nnr][nnc][0] > nnv) {
                        q.add(new int[] {nnr, nnc, 0, nnv});
                        dist[nnr][nnc][0] = nnv;
                    }
                }
            }
        }

        System.out.print(ans);
    }

    static boolean bc(int r, int c) {
        return (0 <= r && r < n) && (0 <= c && c < m);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
