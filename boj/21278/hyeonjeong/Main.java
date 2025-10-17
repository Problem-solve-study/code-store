// 13316KB 120ms

import java.io.*;
import java.util.*;

/**
 * 건물의 최단 경로를 구한 후,
 * => 플로이드 워셜: 100^3
 * 
 * 100_C_2개 경우의 수에 대해, 최단 경로의 왕복 합을 구해 최소값을 구한다.
 * => 100_C_2 * 100
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        final int MAX = 101;
        int n = next();
        int m = next();

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    map[i][j] = 0;
                    continue;
                }

                map[i][j] = MAX;
            }
        }

        for (int i = 0; i < m; i++) {
            int u = next() - 1;
            int v = next() - 1;
            map[u][v] = 1;
            map[v][u] = 1;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] > map[i][k] + map[k][j]) {
                        map[i][j] = map[i][k] + map[k][j];
                        map[j][i] = map[i][k] + map[k][j];
                    }
                }
            }
        }

        int minSum = Integer.MAX_VALUE;
        int[] answer = new int[2];
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int sum = 0;
                for (int k = 0; k < n; k++) {
                    sum += Math.min(map[k][i], map[k][j]);
                }

                if (minSum > sum) {
                    minSum = sum;
                    answer[0] = i;
                    answer[1] = j;
                }
            }
        }

        System.out.printf("%d %d %d", answer[0] + 1, answer[1] + 1, minSum * 2);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
