// 11592KB	68ms

import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int c = 1;
        int p = 2;
        dp = new int[n+1][2];
        for (int i=0; i<=n; i++) dp[i][0] = Integer.MAX_VALUE;
        while (p<=n) {
            dp[p][0] = Math.min(dp[p][0], c);
            for (int i=p; i<=n; i++) {
                if (dp[i-p][0] != Integer.MAX_VALUE) dp[i][0] = Math.min(dp[i][0], dp[i-p][0]+c);
                dp[i][1] = Math.max(dp[i][1], dp[i-p][1]+c);
            }
            int t = p;
            p = c+p;
            c = t;
        }
        System.out.println(dp[n][0] + " " + dp[n][1]);
    }
}