//문제: BOJ 9461 파도반 수열
//메모리: 11380 KB
//시간: 64 ms

/*
 *  원래 점화식은
 *  dp[i] = dp[i-1] + dp[i-5];
 *  dp[0]=0, dp[1]=dp[2]=dp[3]=1, dp[4]=2
 *  dp[5] = dp[4]+dp[0] = dp[4]
 *  dp[6] = dp[5]+dp[1] = dp[4]+dp[0]+dp[1] = dp[4]+dp[1] = dp[4]+dp[3]
 *  dp[7] = dp[6]+dp[2] = dp[5]+dp[1]+dp[2] = dp[5]+dp[4]
 *  dp[8] = dp[7]+dp[3] = dp[6]+dp[2]+dp[3] = dp[6]+dp[5]
 *  dp[9] = dp[8]+dp[4] = dp[7]+dp[3]+dp[4] = dp[7]+dp[6]
 *  =>
 *  dp[i] = dp[i-2]+dp[i-3]
 *  i에 3~5를 넣어보면 성립한다
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		long[] dp = new long[101];
		dp[1] = dp[2] = 1;
		for (int i = 3; i <= 100; i++)
			dp[i] = dp[i - 3] + dp[i - 2];
		int T = nextInt();
		while (T-- > 0)
			sb.append(dp[nextInt()]).append('\n');
		System.out.println(sb);
	}

	static int nextInt() throws IOException {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
