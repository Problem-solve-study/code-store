//47524KB	244ms
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] cakes;
    static long[][] dp;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine().trim());
        cakes = new int[n];
        dp = new long[n][n];
        
        for (int i = 0; i < n; i++) {
            cakes[i] = Integer.parseInt(br.readLine());
        }
        for (int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans = Math.max(ans, cakes[i] + ioi(i, i));
        }
        
        System.out.println(ans);
    }

    static long ioi(int left, int right) {
        if ((right + 1) % n == left) return 0;
        if (cakes[(right + 1) % n] < cakes[(left + n - 1) % n]) {
            return joi((left + n - 1) % n, right);
        } else {
            return joi(left, (right + 1) % n);
        }
    }
    
    static long joi(int left, int right) {
        if ((right + 1) % n == left) return 0;
        if (dp[left][right] != -1) return dp[left][right];
        
        long leftSum = ioi((left + n - 1) % n, right) + cakes[(left + n - 1) % n];
        long rightSum = ioi(left, (right + 1) % n) + cakes[(right + 1) % n];
        
        return dp[left][right] = Math.max(leftSum, rightSum);
    }
}
