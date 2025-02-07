import java.io.*;

public class Main {
    static int count;
    static int[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp = new int[n+1];
        count = fibonacci(n);
        System.out.print(count);
    }

    
    static int fibonacci(int n) {
        if (n < 2) return 1;
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2; i<=n; i++) {
            dp[i] = (dp[i-1] + dp[i-2] + 1) % 1_000_000_007;
        }
        return dp[n];
    }
}