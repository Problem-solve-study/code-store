//문제: BOJ 9465 스티커
//메모리: 17880 KB
//시간: 180 ms

/*
 * n번 열의 위쪽 스티커를 사용하는 경우는 n-1번 열에서 아래쪽 스티커를 사용했거나 n-1번 열을 사용하지 않았을 때
 * n번 열의 아래쪽 스티커를 사용하는 경우는 n-1번 열에서 위쪽 스티커를 사용했거나 n-1번 열을 사용하지 않았을 때
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		int test_case = nextInt();
		while (test_case-- > 0) {
			int n = nextInt();

			int[][] dp = new int[2][n + 1];
			for (int i = 0; i < 2; i++)
				for (int j = 1; j <= n; j++)
					dp[i][j] = nextInt();

			for (int i = 2; i <= n; i++) {
				dp[0][i] += dp[1][i - 1] > dp[1][i - 2] ? dp[1][i - 1] : dp[1][i - 2];
				dp[1][i] += dp[0][i - 1] > dp[0][i - 2] ? dp[0][i - 1] : dp[0][i - 2];
			}
			sb.append(Math.max(dp[0][n], dp[1][n])).append('\n');
		}

		System.out.println(sb);
	}

	static int nextInt() throws IOException {
		int c, n;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
