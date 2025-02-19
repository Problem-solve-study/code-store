// 21840 KB, 388 ms
/*
 * N+1일에 퇴사, N일동안 최대한 많은 상담.
 * 걸리는 시간 T와 금액 P
 * 
 * 상담을 적절히 해서 최대 수익
 * N <= 1,500,000
 * T <= 50, P <= 1,000
 * 
 * dp[i+Ti-1] = max(dp[i+Ti-1], dp[i-1]+Pi)
 * dp[i] = max(dp[i], dp[i-1])
 * 
 * dp[N] 이 결과
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		int N = nextInt(st);
		int[] dp = new int[N + 1];
		int T, P;
		for (int i = 1; i <= N; i++) {
			T = nextInt(st);
			P = nextInt(st);

			dp[i] = Math.max(dp[i], dp[i - 1]);
			if (i + T - 1 > N) continue;
			dp[i + T - 1] = Math.max(dp[i + T - 1], dp[i - 1] + P);
		}
		System.out.println(dp[N]);
	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}