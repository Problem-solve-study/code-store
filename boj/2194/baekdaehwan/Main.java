/**
 * 17480KB	312ms
 * [사고흐름]
 * BFS네? -1 출력 안해서 1틀
 * 
 * [알고리즘 설명]
 * BFS인데 갈 수 있는 지점 검사만 A*B 크기로 수행하는 코드입니다.
 */

import java.util.*;
import java.io.*;

public class Main {
    static int R, C, A, B, K;
    static boolean[][] X;
    static boolean[][] visited;
    static Queue<Node> q;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        R = ni();
        C = ni();
        A = ni();
        B = ni();
        K = ni();
        X = new boolean[R][C];
        visited = new boolean[R][C];
        q = new ArrayDeque<>();

        for (int k=0; k<K; k++) {
            X[ni()-1][ni()-1] = true;
        }

        int sr = ni()-1;
        int sc = ni()-1;
        int er = ni()-1;
        int ec = ni()-1;

        q.add(new Node(sr, sc, 0));
        visited[sr][sc] = true;
        int res = -1;
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.r==er && cur.c==ec) {
                res = cur.t;
                break;
            }
            for (int d=0; d<4; ++d) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];
                if (movable(nr, nc) && !visited[nr][nc]) {
                    visited[nr][nc] = true;
                    q.add(new Node(nr, nc, cur.t+1));
                }
            }
        }
        System.out.println(res);
    }

    static class Node {
        int r, c, t;
        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }
    }

    static boolean isValid(int r, int c) {
        return 0<=r&&r<R && 0<=c&&c<C && !X[r][c];
    }

    static boolean movable(int r, int c) {
        for (int a=0; a<A; ++a) {
            for (int b=0; b<B; ++b) {
                if (!isValid(r+a,c+b)) return false;
            }
        }
        return true;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
