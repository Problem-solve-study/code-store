//15312KB	352ms
import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] map;
    static int answer;
    static final int[] moveHoriz = {0,1};
    static final int[] moveVerti = {1,0};
    static final int[] moveCross = {1,1};
    
    static enum DIRECT {
        HORIZ(0, new int[][] {moveHoriz, moveCross}),
        VERTI(1, new int[][] {moveVerti, moveCross}),
        CROSS(2, new int[][] {moveHoriz, moveCross, moveVerti});
        
        final int num;
        final int[][] move;

        private DIRECT(int num, int[][] move) {
            this.num = num;
            this.move = move;
        }
    }
    
    static final DIRECT[][] next = {{DIRECT.HORIZ, DIRECT.CROSS},
                                    {DIRECT.VERTI, DIRECT.CROSS},
                                    {DIRECT.HORIZ, DIRECT.CROSS, DIRECT.VERTI}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        dfs(0,1,DIRECT.HORIZ);
        System.out.print(answer);
    }

    
    static void dfs(int y, int x, DIRECT direct) {
        if (y == n-1 && x == n-1) {
            answer++;
            return;
        }

        for (int i=0; i<direct.move.length; i++) {
            int ny = y + direct.move[i][0];
            int nx = x + direct.move[i][1];
            if (ny>=0 && ny<n && nx>=0 && nx<n && hasWall(y, x, ny, nx)) {
                dfs(ny, nx, next[direct.num][i]);
            }
        }
    }


    static boolean hasWall(int y1, int x1, int y2, int x2) {
        for (int i=y1; i<=y2; i++) {
            for (int j=x1; j<=x2; j++) {
                if (map[i][j] != 0) {
                    return false;
                }
            }
        }
        return true;
    }
}