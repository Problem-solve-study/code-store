/**
 * 75044KB	528ms
 * 흔한 BFS 문제입니다. 3으로 바뀐 이후, 더 확산이 되면 안됨을 주의하세요
 */

import java.util.*;
import java.io.*;

public class Main {
    static int N, M;
    static int[][][] map;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {-1,1,0,0};

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        map = new int[N][M][2];

        int r1 = 0;
        int c1 = 0;
        int r2 = 0;
        int c2 = 0;


        for (int r=0; r<N; ++r) {
            for (int c=0; c<M; ++c) {
                map[r][c][0] = ni();
                if (map[r][c][0] == 1) {
                    r1 = r;
                    c1 = c;
                } else if (map[r][c][0] == 2) {
                    r2 = r;
                    c2 = c;
                }
            }
        }

        int[] cnt = new int[4];

        Queue<Edge> q = new ArrayDeque<>();
        q.add(new Edge(r1, c1, 1, 0));
        q.add(new Edge(r2, c2, 2, 0));
        while (!q.isEmpty()) {
            Edge c = q.poll();
            if (map[c.r][c.c][0] == 3) continue;

            for (int d=0; d<4; ++d) {
                int nr = c.r + dr[d];
                int nc = c.c + dc[d];
                if (!isValid(nr, nc)) continue;
                if (map[nr][nc][0] == c.i) continue;
                if (map[nr][nc][0] == flip(c.i) && map[nr][nc][1] == c.t+1) {
                    map[nr][nc][0] = 3;
                    cnt[flip(c.i)]--;
                    cnt[3]++;
                } else if (map[nr][nc][0] == 0) {
                    map[nr][nc][0] = c.i;
                    map[nr][nc][1] = c.t+1;
                    cnt[c.i]++;
                    q.add(new Edge(nr, nc, c.i, c.t+1));
                }
            }
        }
        StringBuilder res = new StringBuilder();
        res.append(cnt[1]+1).append(' ').append(cnt[2]+1).append(' ').append(cnt[3]);
        System.out.println(res);
    }

    static int flip(int i) {
        return i%2+1;
    }

    static boolean isValid(int r, int c) {
        return 0<=r&&r<N && 0<=c&&c<M && map[r][c][0]!=-1;
    }

    static class Edge{
        int r, c, i, t;
        public Edge(int r, int c, int i, int t) {
            this.r = r;
            this.c = c;
            this.i = i;
            this.t = t;
        }
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
