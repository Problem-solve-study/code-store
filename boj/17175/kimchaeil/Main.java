//메모리: 11480KB
//시간: 68ms

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] dp = new int[n + 1];
		dp[0] = 1;
		if (n > 0) {
			dp[1] = 1;
			for (int i = 2; i <= n; i++) {
				dp[i] = (dp[i - 2] + dp[i - 1] + 1) % 1_000_000_007;
			}
		}
		System.out.println(dp[n]);
	}

}