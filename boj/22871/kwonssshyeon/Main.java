// 12824KB	160ms

import java.io.*;
import java.util.*;

/**
 * dp 배열에 가능한 K의 최솟값을 갱신하며 dp 진행
 * 
 * 이전에 기록된 K의 최솟값, 새로 계산된 K 중 큰 것의 최솟값으로 갱신
 * 
 * 오버플로 때문에 4틀
 */
public class Main {
    static int n;
    static int[] power;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        power = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<n; i++) {
            power[i] = Integer.parseInt(st.nextToken());
        }

        long[] dp = new long[n];
        dp[0] = 0L;
        for (int i=1; i<n; i++) {
            dp[i] = 5_000_000_000L;
            for (int j=0; j<i; j++) {
                dp[i] = Math.min(dp[i], Math.max(dp[j], ((i - j) * (1L + Math.abs(power[i] - power[j])))));
            }
        }
        System.out.println(dp[n-1]);
    }
}