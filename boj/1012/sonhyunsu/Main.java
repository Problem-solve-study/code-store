import java.io.*;
import java.util.*;

public class Main {

    static int T, n, m;
    static boolean visited[][], d[][];
    static int dx[] = {1, -1, 0, 0}, dy[] = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            d = new boolean[m][n];
            visited = new boolean[m][n];
            for (int i = 0; i < k; i++) {
                st = new StringTokenizer(br.readLine());
                d[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = true;
            }

            int worm = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (d[i][j] && !visited[i][j]) {
                        dfs(i, j);
                        worm++;
                    }
                }
            }

            System.out.println(worm);
        }
    }

    static void dfs(int x, int y) {
        if (!isInRange(x, y) || d[x][y] == false || visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        for (int i = 0; i < 4; i++) {
            dfs(x + dx[i], y + dy[i]);
        }
    }

    static boolean isInRange(int x, int y) {
        return 0 <= x && x < m && 0<= y && y < n;
    }
}