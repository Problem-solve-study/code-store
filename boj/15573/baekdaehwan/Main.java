/**
 * 300008KB	1196ms
 * 
 * [사고 흐름]
 * 일단 매개변수탐색인 것은 바로 알 수 있었습니다.
 * 근데 이거 DP로도 풀릴 것 같네요. DP[r][c]를 각 광물을 캐기 위한 최소성능이라고 가정하겠습니다.
 * DP의 모든 값을 구한 후, 이것을 하나의 배열에 넣고 정렬합니다.
 * 그러면 그 배열의 K번째 원소의 값이 답이 됩니다.
 * 
 * 사실 이 방법이 더 쉬울 것 같은데, 간만에 정석대로 풀어봤습니다.
 * 나름 재밌는 문제네요!
 * 
 * [알고리즘 설명]
 * 상좌우에 1짜리 패딩을 걸고 BFS를 돌리면 됩니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A;
    static boolean[][] V;
    static int[] dr = {1,-1,0,0};
    static int[] dc = {0,0,1,-1};

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni()+1;
        M = ni()+2;
        K = ni();
        A = new int[N][M];

        int l=Integer.MAX_VALUE;
        int h=0;
        for (int r=1; r<N; ++r) {
            for (int c=1; c<M-1; ++c) {
                A[r][c] = ni();
                l = Math.min(l, A[r][c]);
                h = Math.max(h, A[r][c]);
            }
        }

        System.out.println(lb(l, h+1));
    }

    static int lb(int l, int h) {
        while (l < h) {
            V = new boolean[N][M];
            int m = (l+h) >> 1;
            if (check(m)) l = m + 1;
            else h = m;
        }
        return h;
    }

    static boolean check(int p) {
        Queue<int[]> q = new ArrayDeque<>();
        int cnt = 0;

        V[0][0] = true;
        q.add(new int[]{0,0});
        while (!q.isEmpty()) {
            int[] c = q.poll();
            if (0 < A[c[0]][c[1]]) ++cnt;

            for (int d=0; d<4; d++) {
                int nr = c[0] + dr[d];
                int nc = c[1] + dc[d];

                if (!isValid(nr, nc) || V[nr][nc] || p < A[nr][nc]) continue;
                V[nr][nc] = true;
                q.add(new int[]{nr,nc});
            }
        }
        return cnt < K;
    }

    static boolean isValid(int r, int c) {
        return 0<=r&&r<N && 0<=c&&c<M;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
