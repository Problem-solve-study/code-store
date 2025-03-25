// 13124KB	132ms
import java.io.*;

public class Main {
    static int n;
    static int answer, count;
    static int[] square = new int[100_001];
    static int[] dp = new int[100_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        calculateSquare();

        for (int i=0; i < count; i++) {
            dp[square[i]] = 1;
        }
        dp[1] = 1;
        for (int i=2; i<=n; i++) {
            if (dp[i] == 0) dp[i] = dp[i-1] + 1;
            for (int j=count; j>=1; j--) {
                if (i-square[j] > 0)
                    dp[i] = Math.min(dp[i], dp[i-square[j]] + 1);
            }
        }
        System.out.print(dp[n]);
    }

    static void calculateSquare() {
        for (int i=1; i*i <= 100_000; i++) {
            square[i] = i*i;
            count++;
        }
    }
}