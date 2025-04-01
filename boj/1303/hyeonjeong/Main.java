// 12520KB 76ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 완탐 + 개수 세기
public class Main {
    static final int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1 , 0}};
    static int n;
    static int m;
    static boolean[][] map;

    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        m = Integer.parseInt(input[0]);
        n = Integer.parseInt(input[1]);
        map = new boolean[n][m];
        
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                map[i][j] = line[j] == 'W';
            }
        }
        
        int wSum = 0;
        int bSum = 0;
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j]) {
                    continue;
                }

                int count = dfs(i, j);

                if (map[i][j]) {
                    wSum += count * count;
                    continue;
                }
                
                bSum += count * count;
            }
        }

        System.out.printf("%d %d", wSum, bSum);
    }

    static int dfs(int i, int j) {
        int sum = 1;
        visited[i][j] = true;

        for (int[] delta : deltas) {
            int ni = i + delta[0];
            int nj = j + delta[1];

            if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                continue;
            }

            if (visited[ni][nj] || map[i][j] != map[ni][nj]) {
                continue;
            }

            sum += dfs(ni, nj);
        }

        return sum;
    }
}
