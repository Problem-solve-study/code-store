//문제: BOJ 1389번 케빈 베이컨의 6단계 법칙
//메모리: 12016 KB
//시간: 84 ms

/*
 * 모든 사람의 타인까지의 최단 거리를 구해야하므로
 * 플로이드 워셜 알고리즘을 사용했다.
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();
		int[][] dp = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				dp[i][j] = 100;

		while (m-- > 0) {
			int a = nextInt();
			int b = nextInt();
			dp[a][b] = dp[b][a] = 1;
		}

		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if (i == k)
					continue;
				for (int j = 1; j <= n; j++) {
					if (j == k || j == i)
						continue;
					dp[i][j] = Math.min(dp[i][k] + dp[k][j], dp[i][j]);
				}
			}
		}

		int min = Integer.MAX_VALUE, idx = -1;
		for (int i = 1; i <= n; i++) {
			int sum = 0;
			for (int j = 1; j <= n; j++) {
				if (j == i)
					continue;
				sum += dp[i][j];
			}
			if (sum < min) {
				min = sum;
				idx = i;
			}
		}
		System.out.print(idx);
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
