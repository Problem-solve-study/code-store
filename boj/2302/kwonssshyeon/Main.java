//	11504KB	64ms
import java.io.*;
import java.util.*;

/**
 * VIP 고정석을 기준으로 분할하여 각 구간의 최대 경우의 수를 구함
 * 구간 별 최댓값끼리 곱하여 전체 경우의 수를 구함
 */
public class Main {
    static int n, m;
    static HashSet<Integer> fixed = new HashSet<>();
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            fixed.add(Integer.parseInt(br.readLine()));
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (fixed.contains(i)) {
                dp[i] = 1;
            } else if (fixed.contains(i - 1)) {
                dp[i] = 1;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }
        int answer = 1;
        for (int f : fixed) {
            answer *= dp[f - 1];
        }
        answer *= dp[n];
        System.out.print(answer);
    }
}
