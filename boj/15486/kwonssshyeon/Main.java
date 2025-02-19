// 33488KB	400ms
import java.io.*;
import java.util.*;

/**
 * 상향식 DP 문제
 */
public class Main {
	static int n;
	static int[] time, pay, dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		n = Integer.parseInt(br.readLine());
		time = new int[n+1];
		pay = new int[n+1];
		dp = new int[n+1];
		for (int i=1; i<=n; i++) {
			st.nextToken();
			int t = (int) st.nval;
			st.nextToken();
			int p = (int) st.nval;
			time[i] = t; pay[i] = p;
		}
        /**
         * 현재위치의 상담을 하고 난 후 x일 뒤 최댓값을 갱신
         */
		for (int i=1; i<=n; i++) {
			dp[i] = Math.max(dp[i-1], dp[i]);
			int next = i + time[i] - 1;
			if (next <= n) {
				dp[next] = Math.max(dp[next], dp[i-1] + pay[i]);
			}
		}
		System.out.print(dp[n]);
	}
}