// 111124KB	424ms
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[] word;
    static int[][] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        word = br.readLine().toCharArray();
        dp = new int[n][n];    
        for (int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
        dfs(0, n-1);
        System.out.println(dp[0][n-1]);
    }


    static int dfs(int left, int right) {
        if (left >= right) {
            return 0;
        }
        if (dp[left][right] != -1) return dp[left][right];
        if (word[left] == word[right]) {
            dp[left][right] = dfs(left + 1, right - 1);
        }
        else {
            dp[left][right]  = Math.min(dfs(left, right - 1), dfs(left + 1, right)) + 1;
        }
        return dp[left][right];
    }
}