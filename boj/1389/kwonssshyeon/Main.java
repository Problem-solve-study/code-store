
//	12296KB	92ms
import java.io.*;
import java.util.*;

/**
 * 모든 두 정점 간 최단 거리가 필요하다.
 * 플로이드 워셜
 */
public class Main {
    static int n, m, map[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j)
                    map[i][j] = 0;
                else
                    map[i][j] = 5001;
            }
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[b][a] = 1;
        }
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }
        int max = Integer.MAX_VALUE;
        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int temp = 0;
            for (int j = 1; j <= n; j++) {
                temp += map[i][j];
            }
            if (max > temp) {
                max = temp;
                answer = i;
            }
        }
        System.out.print(answer);
    }
}
