// 13112KB	68ms

import java.util.*;
import java.io.*;

public class Main {
    static int n;
    static int[] scv = new int[3];
    static int[][][] dp = new int[61][61][61];
    static int[][] dmg = {{9,3,1}, {3,1,9}, {1,9,3}, {3,9,1}, {9,1,3}, {1,3,9}};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i< n; i++) scv[i] = Integer.parseInt(st.nextToken());
        System.out.println(solve(scv[0],scv[1],scv[2]));
    }

    static int solve(int i, int j, int k) {
        if (i==0 && j==0 && k==0) return 0;
        else if (dp[i][j][k] != 0) return dp[i][j][k];
        int min = Integer.MAX_VALUE;
        for (int[] d: dmg) {
            int ni = Math.max(0, i-d[0]);
            int nj = Math.max(0, j-d[1]);
            int nk = Math.max(0, k-d[2]);
            min = Math.min(min, solve(ni, nj, nk));
        }
        dp[i][j][k] = min+1;
        return dp[i][j][k];
    }
}