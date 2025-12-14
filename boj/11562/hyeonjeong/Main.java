// 15976KB 200ms

import java.util.*;
import java.io.*;

/**
 * 처음엔 역방향으로 건너 간 간선의 개수를 기준으로 다익스트라로 접근했는데, 시간초과와 메모리초과를 맞고...
 * n이 작아서 플루이드워셜로 모두 구한 후에 응답만 하는 것으로 수정했습니다.
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = next();
        int m = next();

        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }

                map[i][j] = 250;
            }
        }

        for (int i = 0; i < m; i++) {
            int u = next() - 1;
            int v = next() - 1;
            int b = next();

            map[u][v] = 0;
            map[v][u] = (b + 1) % 2;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][k] + map[k][j] >= map[i][j]) {
                        continue;
                    }
                    map[i][j] = map[i][k] + map[k][j];
                }
            }
        }

        int k = next();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < k; i++) {
            int s = next() - 1;
            int e = next() - 1;

            sb.append(map[s][e]).append('\n');
        }

        System.out.println(sb);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
