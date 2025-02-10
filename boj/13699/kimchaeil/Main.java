//메모리: 11540 KB
//시간: 68 ms
import java.io.*;

public class Main {
	static long[] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		dp = new long[n + 1];
		dp[0] = 1;

		for (int i = 1; i <= n; i++) {
			for (int j = 0; j < i; j++) {
				dp[i] += dp[j] * dp[i - j - 1];
			}
		}

		System.out.println(dp[n]);
	}

}