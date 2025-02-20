/**
 * 36496KB	128ms
 * 6트 성공.. 저는 바보입니다.
 * 
 * [사고 흐름]
 * 그리디로 조건 걸어도 되긴 할 텐데, N이 1000000이니까 BFS돌리면 될거같았음.
 * 
 * [알고리즘]
 * BFS에는 else if로 조건 거는거 아닙니다
 */


import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int res = Integer.MIN_VALUE;
        boolean[] v = new boolean[F+1];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{S, 0});
        v[S] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int f = cur[0];
            int c = cur[1];

            if (f == G) {
                res = c;
                break;
            }
            if (f+U <= F && !v[f+U]) {
                v[f+U] = true;
                q.add(new int[]{f+U, c+1});
            }
            if (f-D > 0 && !v[f-D]) {
                v[f-D] = true;
                q.add(new int[]{f-D, c+1});
            }
        }
        
        System.out.println(res==Integer.MIN_VALUE? "use the stairs": res);
    }
}