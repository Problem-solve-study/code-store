// 29620KB	292ms
import java.io.*;
import java.util.*;

/**
 * n to n 의 경우로 최단경로를 찾아야했기 때문에 플로이드 워셜 알고리즘 이용
 * 양방향인 경우 길을 바꿀 필요가 없으므로 비용 0
 * 일방향인 경우 길을 한번 바꿔야 하므로 비용 1
 */
public class Main {
    static int n,m;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int[][] dist = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                dist[i][j] = n;
            }
            dist[i][i] = 0;
        }

        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[u][v] = 0;
            dist[v][u] = (b == 0) ? 1 : 0;
        }

        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        int k = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            sb.append(dist[s][e]).append("\n");
        }
        System.out.print(sb);
    }
}
