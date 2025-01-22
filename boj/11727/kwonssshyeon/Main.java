import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        

        if (n == 1) {
            System.out.print(1);
        } else if (n == 2) {
            System.out.print(3);
        } else if (n == 3) {
            System.out.print(5);
        } else {
            int[] dp = new int[n+1];
            dp[1] = 1;
            dp[2] = 3;
            dp[3] = 5;
            for (int i=4; i<=n; i++) {
                dp[i] = (dp[i-2]*2 + dp[i-1]) % 10007;
            }
            System.out.print(dp[n]);
        }
    }
}