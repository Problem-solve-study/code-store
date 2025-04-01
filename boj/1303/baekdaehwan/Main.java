/**
 * 12180KB	76ms
 * 
 * [사고흐름]
 * 음.. 섬 크기 구하는 문제랑 비슷한데, 그걸 바다에도 적용하는 문제네
 * 
 * [알고리즘 설명]
 * isW로 각 색상 구분하고, DFS에서 파라미터로 시작지점 색상을 추가하여 돌렸습니다
 */

import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static boolean[][] isW;
    static boolean[][] visited;

    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        isW = new boolean[R][C];
        visited = new boolean[R][C];

        for (int r=0; r<R; ++r) {
            String line = br.readLine();
            for (int c=0; c<C; ++c) isW[r][c] = line.charAt(c) == 'W';
        }
        int w = 0;
        int b = 0;
        for (int r=0; r<R; ++r) {
            for (int c=0; c<C; ++c) {
                if (!visited[r][c]) {
                    if (isW[r][c]) w += (int) Math.pow(getCnt(r, c, true), 2);
                    else b += (int) Math.pow(getCnt(r, c, false), 2);
                }
            }
        }
        System.out.println(w + " " + b);
    }

    static int getCnt(int r, int c, boolean color) {
        visited[r][c] = true;
        int cnt = 1;
        for (int d=0; d<4; ++d) {
            int nr = r+dr[d];
            int nc = c+dc[d];
            if (isValid(nr, nc) && !visited[nr][nc] && isW[nr][nc]==color) {
                cnt += getCnt(nr, nc, color);
            }
        }
        return cnt;
    }

    static boolean isValid(int r, int c) {
        return 0<=r&&r<R && 0<=c&&c<C;
    }
}