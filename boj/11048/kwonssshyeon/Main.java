// 72180KB	396ms
import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n+1][m+1];
        for (int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                map[i][j] += Math.max(Math.max(map[i-1][j], map[i][j-1]), map[i-1][j-1]);
            }
        }
        System.out.print(map[n][m]);
    }
}
