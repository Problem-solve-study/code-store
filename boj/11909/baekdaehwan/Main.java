/**
 * 374520KB	2664ms
 * 
 * DP or 다익스트라
 * 간선 개수 상 풀리긴 하는 문제여서 다익스트라로 풀어봤습니다.
 * DP가 훨씬 빠를거 같네요
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[][] dijk;
    static PriorityQueue<int[]> pq;
    static int[] dr = {1, 0};
    static int[] dc = {0, 1};

    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        N = ni();
        map = new int[N][N];
        dijk = new int[N][N];
        pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        for (int r=0; r<N; ++r) {
            for (int c=0; c<N; ++c) map[r][c] = ni();
        }
        for (int r=0; r<N; ++r) Arrays.fill(dijk[r], Integer.MAX_VALUE);

        dijk[0][0] = 0;
        pq.offer(new int[]{0, 0, 0});
        while (!pq.isEmpty()) {
            int[] a = pq.poll();
            int r = a[0];
            int c = a[1];
            if (dijk[r][c] < a[2]) continue;

            for (int d=0; d<2; ++d) {
                int nr = r+dr[d];
                int nc = c+dc[d];
                if (valid(nr, nc)) {
                    int cost = Math.max(map[nr][nc]+1-map[r][c], 0);
                    if (dijk[nr][nc] > dijk[r][c]+cost) {
                        dijk[nr][nc] = dijk[r][c]+cost;
                        pq.offer(new int[]{nr, nc, dijk[nr][nc]});
                    }
                }
            }
        }
        System.out.println(dijk[N-1][N-1]);
    }

    static boolean valid(int r, int c) {
        return 0<=r&&r<N && 0<=c&&c<N;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
