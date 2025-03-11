// 18080KB 92ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static final int[][] flowerDeltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static final int[][] checkNearDeltas = {{2, 0}, {-2, 0}, {0, 2}, {0, -2}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    static int n;
    static int[][] map;
    static boolean[][] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[n][n];

        dfs(0, -1, 0, 0);

        System.out.println(min);
    }

    static void dfs(int pi, int pj, int depth, int cost) {
        if (depth == 3) {
            if (cost < min) {
                min = cost;
            }
            return;
        }

        for (int i = pi; i < n - 1; i++) {
            for (int j = (pi == i ? pj + 1 : 1); j < n - 1; j++) {
                if (!isSowable(i, j)) {
                    continue;
                }

                // visit
                visited[i][j] = true;
                dfs(i, j, depth + 1, cost + getCost(i, j));
                visited[i][j] = false;
            }
        }
    }

    static boolean isSowable(int i, int j) {
        for (int[] delta : flowerDeltas) {
            int ni = i + delta[0];
            int nj = j + delta[1];
            if (ni < 0 || ni >= n || nj < 0 || nj >= n) {
                return false;
            }

            if (visited[ni][nj]) {
                return false;
            }
        }

        for (int[] delta : checkNearDeltas) {
            int ni = i + delta[0];
            int nj = j + delta[1];
            if (ni < 0 || ni >= n || nj < 0 || nj >= n) {
                continue;
            }

            if (visited[ni][nj]) {
                return false;
            }
        }
        
        return true;
    }

    static int getCost(int i, int j) {
        int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        int cost = map[i][j];
        for (int[] delta : deltas) {
            int ni = i + delta[0];
            int nj = j + delta[1];
            cost += map[ni][nj];
        }

        return cost;
    }
}
