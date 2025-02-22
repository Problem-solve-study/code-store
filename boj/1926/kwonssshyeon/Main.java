// 50808KB	264ms
import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map;
    static int[] dx = {1,0,0,-1};
    static int[] dy = {0,-1,1,0};
    static boolean[][] visited;
    static int size;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int max = 0, count = 0;
        visited = new boolean[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (!visited[i][j] && map[i][j]==1) {
                    size = 0;
                    count++;
                    dfs(i, j);
                    max = Math.max(max, size);
                }
            }
        }
        System.out.println(count);
        System.out.print(max);
    }

    static void dfs(int y, int x) {
        size++;
        visited[y][x] = true;

        for (int i=0; i<4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny >=0 && ny<n && nx>=0 && nx<m) {
                if (!visited[ny][nx] && map[ny][nx]==1) {
                    dfs(ny, nx);

                }
            }
        }
    }
}