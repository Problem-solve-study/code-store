/**
 * 27140KB	212ms
 * 
 * [사고흐름]
 * 그냥 다익스트라인것 같았습니다. (0-1 BFS로 최적화된 다익스트라)
 * 두 죄수가 같은 경로를 이용할 수도 있다는 점이 어려운 포인트입니다.
 * 두 죄수가 같은 공간에서 만난 다음 같이 탈출하는 것이 빠를 수도 있고, 
 * 두 죄수가 서로 다른 경로를 이용하는 것이 더 빠를수도 있기 때문입니다.
 * 그래서 바깥에서 어떠한 문까지 이동하기 위해서 연 문의 수 또한 필요하다고 생각했습니다.
 * 
 * 이러면 답은 두 죄수가 특정 문까지 이동하는데 연 문의 수 + 바깥에서 해당 문까지 연 문의 개수가 최소가 되는 경우가 됩니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int R, C;
    static boolean[][] isWall;
    static boolean[][] isDoor;
    static Pos[] criminal;
    static Deque<Pos> q; 
    static int[] dr = {0,0,1,-1};
    static int[] dc = {1,-1,0,0};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int TC = Integer.parseInt(br.readLine());
        for (int tc=0; tc<TC; ++tc) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            R = Integer.parseInt(st.nextToken())+2;
            C = Integer.parseInt(st.nextToken())+2;
            q = new ArrayDeque<>();
            isWall = new boolean[R][C];
            isDoor = new boolean[R][C];
            criminal = new Pos[2];
            int ti = 0;
            for (int r=1; r<R-1; ++r) {
                String line = br.readLine();
                for (int c=1; c<C-1; ++c) {
                    if (line.charAt(c-1) == '*') isWall[r][c] = true;
                    else if (line.charAt(c-1) == '#') isDoor[r][c] = true;
                    else if (line.charAt(c-1) == '$') criminal[ti++] = new Pos(r, c, 0);
                }
            }

            int[][] dijkA = dijk(criminal[0].r, criminal[0].c);
            int[][] dijkB = dijk(criminal[1].r, criminal[1].c);
            int[][] dijkO = dijk(0, 0);

            int res = dijkA[0][0]+dijkB[0][0]+dijkO[0][0]+2;
            for (int r=1; r<R-1; ++r) {
                for (int c=1; c<C-1; ++c) {
                    if (isDoor[r][c]) res = Math.min(res, dijkA[r][c]+dijkB[r][c]+dijkO[r][c]);
                }
            }
            
            sb.append(res-2).append('\n');
        }
        System.out.print(sb);
    }
    
    static int[][] dijk(int sr, int sc) {
        int[][] dijk = new int[R][C];
        for (int r=0; r<R; ++r) Arrays.fill(dijk[r], Integer.MAX_VALUE);
        q.add(new Pos(sr, sc, 0));
        dijk[sr][sc] = 0;
        
        while (!q.isEmpty()) {
            Pos c = q.pollFirst();
            if (dijk[c.r][c.c] < c.cost) continue;
            for (int d=0; d<4; ++d) {
                int nr = c.r + dr[d];
                int nc = c.c + dc[d];
                if (movable(nr, nc)) {
                    int nCost = c.cost + (isDoor[nr][nc]? 1:0);
                    if (dijk[nr][nc] > nCost) {
                        dijk[nr][nc] = nCost;
                        if (isDoor[nr][nc]) q.addLast(new Pos(nr, nc, nCost));
                        else q.addFirst(new Pos(nr, nc, nCost));
                    }
                }
            }
        }
        
        return dijk;
    }
    
    static boolean isValid(int r, int c) {
        return 0<=r&&r<R && 0<=c&&c<C;
    }
    
    static boolean movable(int r, int c) {
        return isValid(r, c) && !isWall[r][c];
    }
    
    static class Pos{
        int r, c, cost;
        public Pos(int r, int c, int cost) {
            this.r = r;
            this.c = c;
            this.cost = cost;
        }
    }
}