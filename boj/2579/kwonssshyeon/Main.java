// 11616KB	64ms
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] step;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        step = new int[n+1];
        for (int i=1; i<=n; i++) {
            step[i] = Integer.parseInt(br.readLine());
        }
        if (n == 1) {
            System.out.print(step[1]);
            return;
        } else if (n == 2) {
            System.out.print(step[1] + step[2]);
            return;
        }
        int[] dp = new int[n+1];
        dp[1] = step[1];
        dp[2] = step[1] + step[2];
        for (int i=3; i<=n; i++) {
            dp[i] = Math.max(dp[i-2], dp[i-3] + step[i-1]) + step[i];
        }
        System.out.print(dp[n]);
    }
}