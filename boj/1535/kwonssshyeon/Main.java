// 11604KB	68ms
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] strength, happiness;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        strength = new int[n+1];
        happiness = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            strength[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=n; i++) {
            happiness[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[n+1][101];

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=100; j++) {
                if (j > strength[i]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-strength[i]] + happiness[i]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        System.out.print(dp[n][100]);
    }
}
