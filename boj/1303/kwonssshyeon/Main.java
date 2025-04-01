
// 12184KB	76ms
import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static char map[][];
    static boolean visited[][];
    static int[] dx = { 1, 0, 0, -1 };
    static int[] dy = { 0, 1, -1, 0 };
    static int white, blue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        visited = new boolean[n][m];
        int totalW = 0, totalB = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j]) {
                    white = 0;
                    blue = 0;
                    visited[i][j] = true;
                    dfs(i, j, map[i][j]);
                    if (map[i][j] == 'W')
                        totalW += (white * white);
                    else
                        totalB += (blue * blue);
                }
            }
        }

        System.out.print(totalW + " " + totalB);
    }

    static void dfs(int y, int x, char type) {
        if (type == 'W')
            white++;
        else
            blue++;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (ny >= 0 && ny < n && nx >= 0 && nx < m && !visited[ny][nx]) {
                if (map[ny][nx] == type) {
                    visited[ny][nx] = true;
                    dfs(ny, nx, type);
                }
            }
        }
    }
}